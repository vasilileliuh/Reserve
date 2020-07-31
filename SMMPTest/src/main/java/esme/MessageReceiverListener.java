package esme;

import org.apache.log4j.Logger;
import org.jsmpp.bean.*;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.session.DataSmResult;
import org.jsmpp.session.Session;
import org.jsmpp.util.InvalidDeliveryReceiptException;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageReceiverListener implements org.jsmpp.session.MessageReceiverListener {
    private static final Logger LOGGER = Logger.getLogger(MessageReceiverListener.class);
    final AtomicInteger counter = new AtomicInteger();

    public void onAcceptDeliverSm(DeliverSm deliverSm)
            throws ProcessRequestException {
        if (MessageType.SMSC_DEL_RECEIPT.containedIn(deliverSm.getEsmClass())) {
            counter.incrementAndGet();
            // delivery receipt
            try {
                DeliveryReceipt delReceipt = deliverSm.getShortMessageAsDeliveryReceipt();
                long id = Long.parseLong(delReceipt.getId());
                String messageId = Long.toString(id, 16).toUpperCase();
                LOGGER.info("Receiving delivery receipt for message " + messageId + " : " + delReceipt);
            } catch (InvalidDeliveryReceiptException e) {
                LOGGER.error("Failed getting delivery receipt", e);
            }
        } else {
            // regular short message
            LOGGER.info("Receiving message: " + new String(deliverSm.getShortMessage()));
        }
    }

    public void onAcceptAlertNotification(
            AlertNotification alertNotification) {
    }

    public DataSmResult onAcceptDataSm(DataSm dataSm, Session source)
            throws ProcessRequestException {
        return null;
    }
}
