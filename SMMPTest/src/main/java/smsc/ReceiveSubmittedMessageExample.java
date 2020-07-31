package smsc;

import org.apache.log4j.Logger;
import org.jsmpp.PDUStringException;
import org.jsmpp.SMPPConstant;
import org.jsmpp.session.*;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveSubmittedMessageExample {
    private static final Logger LOGGER = Logger.getLogger(ReceiveSubmittedMessageExample.class);

    public static void main(String[] args) throws InterruptedException {
        try {
            // prepare the message receiver
            ServerMessageReceiverListener messageReceiverListener = new ServerMessageReceiverListener();

            LOGGER.info("Listening ...");
            SMPPServerSessionListener sessionListener = new SMPPServerSessionListener(8056);
            // set all default ServerMessageReceiverListener for all accepted SMPPServerSessionListener
            sessionListener.setMessageReceiverListener(messageReceiverListener);

            // accepting connection, session still in OPEN state
            SMPPServerSession session = sessionListener.accept();
            // or we can set for each accepted session session.setMessageReceiverListener(messageReceiverListener)
            LOGGER.info("Accept connection");

            try {
                BindRequest request = session.waitForBind(5000);
                LOGGER.info("Receive bind request for system id: " + request.getSystemId() +
                        " and password: " + request.getPassword());

                if ("guessId".equals(request.getSystemId()) &&
                        "jpwda23".equals(request.getPassword())) {

                    // accepting request and send bind response immediately
                    LOGGER.info("Accepting bind request");
                    request.accept(request.getSystemId());

                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    LOGGER.info("Rejecting bind request");
                    request.reject(SMPPConstant.STAT_ESME_RINVPASWD);
                }
            } catch (TimeoutException e) {
                LOGGER.error("No binding request made after 5000 millisecond", e);
            }

//            Original part
//            LOGGER.info("Closing session");
//            session.unbindAndClose();
//            LOGGER.info("Closing session listener");
//            sessionListener.close();

        } catch (PDUStringException e) {
            LOGGER.error("PDUString exception", e);
        } catch (IOException e) {
            LOGGER.error("I/O exception", e);
        }
    }
}
