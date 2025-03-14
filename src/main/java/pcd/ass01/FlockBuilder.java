package pcd.ass01;

public class FlockBuilder {

    private double width = 800;
    private double height = 800;

    public Flock buildFlock() {
        return new FlockImpl(width, height);
    }

    public FlockBuilder width(double width) {
        this.width = width;
        return this;
    }

    public FlockBuilder height(double height) {
        this.height = height;
        return this;
    }

}
