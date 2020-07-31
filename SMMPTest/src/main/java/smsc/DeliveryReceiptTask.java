package smsc;

import org.apache.log4j.Logger;
import org.jsmpp.bean.*;
import org.jsmpp.extra.SessionState;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.util.DeliveryReceiptState;
import org.jsmpp.util.MessageId;

import java.util.Date;

class DeliveryReceiptTask implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(DeliveryReceiptTask.class);
    private final SMPPServerSession session;
    private final MessageId messageId;

    private final TypeOfNumber sourceAddrTon;
    private final NumberingPlanIndicator sourceAddrNpi;
    private final String sourceAddress;

    private final TypeOfNumber destAddrTon;
    private final NumberingPlanIndicator destAddrNpi;
    private final String destAddress;

    private final int totalSubmitted;
    private final int totalDelivered;

    private final byte[] shortMessage;

    public DeliveryReceiptTask(SMPPServerSession session, SubmitSm submitSm, MessageId messageId) {
        this.session = session;
        this.messageId = messageId;

        // reversing destination to source
        sourceAddrTon = TypeOfNumber.valueOf(submitSm.getDestAddrTon());
        sourceAddrNpi = NumberingPlanIndicator.valueOf(submitSm.getDestAddrNpi());
        sourceAddress = submitSm.getDestAddress();

        // reversing source to destination
        destAddrTon = TypeOfNumber.valueOf(submitSm.getSourceAddrTon());
        destAddrNpi = NumberingPlanIndicator.valueOf(submitSm.getSourceAddrNpi());
        destAddress = submitSm.getSourceAddr();

        totalSubmitted = totalDelivered = 1;

        shortMessage = submitSm.getShortMessage();
    }

    public DeliveryReceiptTask(SMPPServerSession session,
                               SubmitMulti submitMulti, MessageId messageId) {
        this.session = session;
        this.messageId = messageId;

        // set to unknown and null, since it was submit_multi
        sourceAddrTon = TypeOfNumber.UNKNOWN;
        sourceAddrNpi = NumberingPlanIndicator.UNKNOWN;
        sourceAddress = null;

        // reversing source to destination
        destAddrTon = TypeOfNumber.valueOf(submitMulti.getSourceAddrTon());
        destAddrNpi = NumberingPlanIndicator.valueOf(submitMulti.getSourceAddrNpi());
        destAddress = submitMulti.getSourceAddr();

        // distribution list assumed only contains single address
        totalSubmitted = totalDelivered = submitMulti.getDestAddresses().length;

        shortMessage = submitMulti.getShortMessage();
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            LOGGER.error("Interrupted " + e1);
        }
        SessionState state = session.getSessionState();
        if (!state.isReceivable()) {
            LOGGER.debug("Not sending delivery receipt for message id: " + messageId + " since session state is: " + state);
            return;
        }
        String stringValue = Integer.valueOf(messageId.getValue(), 16).toString();
        try {

            DeliveryReceipt delRec = new DeliveryReceipt(stringValue, totalSubmitted, totalDelivered, new Date(), new Date(),
                    DeliveryReceiptState.DELIVRD, "000", new String(shortMessage));
            session.deliverShortMessage(
                    "mc",
                    sourceAddrTon, sourceAddrNpi, sourceAddress,
                    destAddrTon, destAddrNpi, destAddress,
                    new ESMClass(MessageMode.DEFAULT, MessageType.SMSC_DEL_RECEIPT, GSMSpecificFeature.DEFAULT),
                    (byte) 0,
                    (byte) 0,
                    new RegisteredDelivery(0),
                    DataCodings.ZERO,
                    delRec.toString().getBytes());
            LOGGER.debug("Sending delivery receipt for message id " + messageId + ": " + stringValue);
        } catch (Exception e) {
            LOGGER.error("Failed sending delivery_receipt for message id " + messageId + ":" + stringValue, e);
        }
    }
}
