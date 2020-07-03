public class Vector {
    private Point startPoint;
    private Point endPoint;
    private double length;

    public Vector(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public double length(Vector vector) {
        return Math.sqrt(Math.pow(vector.getEndPoint().getX() - vector.getStartPoint().getX(), 2) +
                Math.pow(vector.getEndPoint().getY() - vector.getStartPoint().getY(), 2));
    }

    public static Vector sum(Vector vectorOne, Vector vectorTwo) {
        return new Vector(new Point(vectorOne.startPoint.getX() + vectorTwo.startPoint.getX(), vectorOne.startPoint.getY() +
                vectorTwo.startPoint.getY()), new Point(vectorOne.endPoint.getX() + vectorTwo.endPoint.getX(), vectorOne.endPoint.getY() + vectorTwo.endPoint.getY()));
    }


    @Override
    public String toString() {
        return "Vector{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
