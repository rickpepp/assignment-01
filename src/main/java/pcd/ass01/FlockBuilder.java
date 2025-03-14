package pcd.ass01;

public class FlockBuilder {

    private double width = 800;
    private double height = 800;
    private double maxSpeed = 10;
    private double perceptionRadius = 2;
    private double avoidRadius = 1;
    private double separationWeight = 1;

    public Flock buildFlock() {
        return new FlockImpl(width,
                height,
                maxSpeed,
                perceptionRadius,
                avoidRadius,
                separationWeight);
    }

    public FlockBuilder width(double width) {
        this.width = width;
        return this;
    }

    public FlockBuilder height(double height) {
        this.height = height;
        return this;
    }

    public FlockBuilder maxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public FlockBuilder perceptionRadius(double perceptionRadius) {
        this.perceptionRadius = perceptionRadius;
        return this;
    }

    public FlockBuilder avoidRadius(double avoidRadius) {
        this.avoidRadius = avoidRadius;
        return this;
    }

    public FlockBuilder separationWeight(double separationWeight) {
        this.separationWeight = separationWeight;
        return this;
    }

}
