package smsc;

import org.apache.log4j.Logger;
import org.jsmpp.bean.*;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.session.DataSmResult;
import org.jsmpp.session.QuerySmResult;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.session.Session;
import org.jsmpp.util.MessageIDGenerator;
import org.jsmpp.util.MessageId;
import org.jsmpp.util.RandomMessageIDGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMessageReceiverListener implements org.jsmpp.session.ServerMessageReceiverListener {

    private final ExecutorService execServiceDelReceipt = Executors.newFixedThreadPool(100);
    // prepare generator of Message ID
    final MessageIDGenerator messageIdGenerator = new RandomMessageIDGenerator();

    private static final Logger LOGGER = Logger.getLogger(ServerMessageReceiverListener.class);

    public MessageId onAcceptSubmitSm(SubmitSm submitSm, SMPPServerSession source) throws ProcessRequestException {
        MessageId messageId = messageIdGenerator.newMessageId();
        LOGGER.info("Receiving submit_sm: " + new String(submitSm.getShortMessage()) + ", and return message id: " + messageId);
        if (SMSCDeliveryReceipt.FAILURE.containedIn(submitSm.getRegisteredDelivery()) || SMSCDeliveryReceipt.SUCCESS_FAILURE.containedIn(submitSm.getRegisteredDelivery())) {
            execServiceDelReceipt.execute(new DeliveryReceiptTask(source, submitSm, messageId));
        }
        return messageId;
    }

    public QuerySmResult onAcceptQuerySm(QuerySm querySm, SMPPServerSession source) throws ProcessRequestException {
        return null;
    }

    public SubmitMultiResult onAcceptSubmitMulti(SubmitMulti submitMulti, SMPPServerSession source)
            throws ProcessRequestException {
        return null;
    }

    public DataSmResult onAcceptDataSm(DataSm dataSm, Session source) throws ProcessRequestException {
        return null;
    }

    public void onAcceptCancelSm(CancelSm cancelSm, SMPPServerSession source) throws ProcessRequestException {
    }

    public void onAcceptReplaceSm(ReplaceSm replaceSm, SMPPServerSession source) throws ProcessRequestException {
    }
}
