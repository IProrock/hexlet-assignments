package exercise;

// BEGIN
public class Circle {
    private Point centerPoint;
    private int radius;

    public Circle(Point centerPoint, int radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public float getSquare() throws NegativeRadiusException {

        if (this.radius < 0) {
            throw new NegativeRadiusException();
        }

        return (float) Math.PI * radius * radius;
    }
}
// END
