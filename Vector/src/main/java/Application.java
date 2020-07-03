import org.apache.log4j.Logger;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(0, 10);
        Point c = new Point(5, 15);

        Vector ab = new Vector(a, b);
        Vector ac = new Vector(a, c);

        double length1 = ab.length(ab);
        Vector sumOfTwoVectors = Vector.sum(ab, ac);

        LOGGER.info("Длинна вектора= " + ab + length1);
        LOGGER.info("Сумма векторов ab + ac= " + sumOfTwoVectors);


    }
}

