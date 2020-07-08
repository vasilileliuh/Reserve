package main;

import org.apache.log4j.Logger;
import source.Point;
import source.SwingGUI;
import source.Vector;


public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {
//        Point a = new Point(0, 0);
//        Point b = new Point(0, 10);
//        Point c = new Point(5, 15);
//
//        Vector ab = new Vector(a, b);
//        Vector ac = new Vector(a, c);

//        double length = Vector.module(ab);
//        Vector sumOfTwoVectors = Vector.sum(ab, ac);
//        LOGGER.info("Длинна вектора \'" + ab + "\' равна: " + length);
//        LOGGER.info("Сумма векторов ab + ac= " + sumOfTwoVectors);
//
//        double angle = Vector.angleBetweenVectors(ab, ac);
//
//        LOGGER.info("Угол между векторами " + ab + " и " + ac + " равен: " + angle);
//
//        Vector de = new Vector(new Point(1, 0), new Point(2, 2));
//        Vector df = new Vector(new Point(1, 0), new Point(3, 3));
//
//        Vector sumOfTwoVectors2 = Vector.sum(de, df);
//        LOGGER.info("Сумма векторов de + df= " + sumOfTwoVectors2);
//        double angle2 = Vector.angleBetweenVectors(de, df);
//        LOGGER.info("Угол между векторами " + de + " и " + df + " равен: " + angle2);
        SwingGUI gui = new SwingGUI();
    }

}

