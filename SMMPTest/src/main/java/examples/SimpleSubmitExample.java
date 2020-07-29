package examples;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.*;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;
import org.apache.log4j.*;

import java.io.IOException;
import java.util.Date;

public class SimpleSubmitExample {
    private static final Logger LOGGER = Logger.getLogger(SimpleSubmitExample.class);
    private static final TimeFormatter TIME_FORMATTER = new AbsoluteTimeFormatter();
//    https://github.com/opentelecoms-org/jsmpp/tree/master/jsmpp-examples/src/main/java/org/jsmpp/examples

    public static void main(String[] args) {

        SMPPSession session = new SMPPSession();

        try {
            LOGGER.info("Connecting");
            String systemId = session.connectAndBind("localhost", 8056,
                    new BindParameter(BindType.BIND_TRX, "guessId", "jpwda23", "cp",
                            TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
            LOGGER.info("Connected with SMSC with system id: " + systemId);
//          modifications with infinite while loop
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error("An Interrupted Exception occurred: ", e);
                }

                try {
                    String messageId = session.submitShortMessage("CMT",
                            TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.UNKNOWN, "1616",
                            TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.UNKNOWN, "628176504657",
                            new ESMClass(), (byte) 0, (byte) 1, TIME_FORMATTER.format(new Date()), null,
                            new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
                            (byte) 0, new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false),
                            (byte) 0, "jSMPP simplify SMPP on Java platform".getBytes());
                    LOGGER.info("Message submitted, message_id is: " + messageId);
                } catch (PDUException e) {
                    // Invalid PDU parameter
                    LOGGER.error("Invalid PDU parameter", e);
                } catch (ResponseTimeoutException e) {
                    // Response timeout
                    LOGGER.error("Response timeout", e);
                } catch (InvalidResponseException e) {
                    // Invalid response
                    LOGGER.error("Receive invalid response", e);
                } catch (NegativeResponseException e) {
                    // Receiving negative response (non-zero command_status)
                    LOGGER.error("Receive negative response, e");
                } catch (IOException e) {
                    LOGGER.error("IO error occurred", e);
                }

//            session.unbindAndClose();
            }
        } catch (IOException e) {
            LOGGER.error("Failed connect and bind to host", e);

        }
    }

}
