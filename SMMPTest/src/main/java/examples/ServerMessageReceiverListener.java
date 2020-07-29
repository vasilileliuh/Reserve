package examples;

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

public class ServerMessageReceiverListener implements org.jsmpp.session.ServerMessageReceiverListener {

    // prepare generator of Message ID
    final MessageIDGenerator messageIdGenerator = new RandomMessageIDGenerator();

    private static final Logger LOGGER = Logger.getLogger(ServerMessageReceiverListener.class);

    public MessageId onAcceptSubmitSm(SubmitSm submitSm, SMPPServerSession source) throws ProcessRequestException {
        LOGGER.info("Receiving message: " + new String(submitSm.getShortMessage()));
        // need message_id to response submit_sm
        return messageIdGenerator.newMessageId();
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
