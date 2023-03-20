package exercise;

// BEGIN
public class Segment {
    private Point first;
    private Point second;

    public Segment(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    public Point getBeginPoint() {
        return this.first;
    }

    public Point getEndPoint() {
        return this.second;
    }

    public Point getMidPoint() {
        int midX = (this.first.getX() + this.second.getX()) / 2;
        int midY = (this.first.getY() + this.second.getY()) / 2;

        return new Point(midX, midY);
    }
}
// END
