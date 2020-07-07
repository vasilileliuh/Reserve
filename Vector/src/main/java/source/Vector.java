package source;

public class Vector {
    private final Point START_POINT;
    private final Point END_POINT;
    private final double PROJECTION_X;
    private final double PROJECTION_Y;

    public Vector(Point START_POINT, Point END_POINT) {
        this.START_POINT = START_POINT;
        this.END_POINT = END_POINT;
        PROJECTION_X = projection(this, Axis.X);
        PROJECTION_Y = projection(this, Axis.Y);
    }

    public Point getSTART_POINT() {
        return START_POINT;
    }

    public Point getEND_POINT() {
        return END_POINT;
    }

    public static double module(Vector vector) {
        return Math.sqrt(Math.pow(vector.getEND_POINT().getX() - vector.getSTART_POINT().getX(), 2) +
                Math.pow(vector.getEND_POINT().getY() - vector.getSTART_POINT().getY(), 2));
    }

    public static Vector sum(Vector vectorOne, Vector vectorTwo) {
        return new Vector(new Point(vectorOne.START_POINT.getX() +
                vectorTwo.START_POINT.getX(), vectorOne.START_POINT.getY() +
                vectorTwo.START_POINT.getY()), new Point(vectorOne.END_POINT.getX() +
                vectorTwo.END_POINT.getX(), vectorOne.END_POINT.getY() + vectorTwo.END_POINT.getY()));
    }

    public static Vector subtraction(Vector vectorOne, Vector vectorTwo) {
        return new Vector(new Point(vectorOne.START_POINT.getX() +
                vectorTwo.START_POINT.getX(), vectorOne.START_POINT.getY() +
                vectorTwo.START_POINT.getY()), new Point(vectorOne.END_POINT.getX() +
                vectorTwo.END_POINT.getX(), vectorOne.END_POINT.getY() + vectorTwo.END_POINT.getY()));
    }

    private double projection(Vector vector, Axis axis) {
        double result = 0;
        switch (axis) {
            case X:
                result = vector.END_POINT.getX() - vector.START_POINT.getX();
                break;
            case Y:
                result = vector.END_POINT.getY() - vector.START_POINT.getY();
                break;
        }
        return result;
    }

    public static double angleBetweenVectors(Vector vectorOne, Vector vectorTwo) {
        return Math.toDegrees(Math.acos((vectorOne.PROJECTION_X * vectorTwo.PROJECTION_X +
                vectorOne.PROJECTION_Y * vectorTwo.PROJECTION_Y) /
                (module(vectorOne) * module(vectorTwo))));
    }

    public double getPROJECTION_X() {
        return PROJECTION_X;
    }

    public double getPROJECTION_Y() {
        return PROJECTION_Y;
    }

    @Override
    public String toString() {
        return "source.Vector{" +
                "startPoint= " + START_POINT +
                ", endPoint= " + END_POINT +
                '}';
    }

    enum Axis {
        X,
        Y
    }
}
