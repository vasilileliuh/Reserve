package Main;

import Animal.Animal;
import Animal.Cat;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        Animal barsik = new Cat("Barsik", 5, 7);
        Animal murzik = new Cat("Murzik", 2, 5);

        LOGGER.info("Был куплено новое животное: " + barsik);
        LOGGER.info("Был куплено новое животное: " + murzik);
        LOGGER.error("Это сообщение ошибки");

    }
}
