package pcd.ass01;

import java.util.ArrayList;
import java.util.Collection;

public class FlockImpl implements Flock{

    private final Collection<Boid> boids;
    private final double width;
    private final double height;
    private final double maxSpeed;
    private final double perceptionRadius;
    private final double avoidRadius;
    private double separationWeight;

    public FlockImpl(double width,
                     double height,
                     double maxSpeed,
                     double perceptionRadius,
                     double avoidRadius,
                     double separationWeight) {
        this.boids = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.perceptionRadius = perceptionRadius;
        this.avoidRadius = avoidRadius;
        this.separationWeight = separationWeight;
    }

    @Override
    public synchronized Collection<Boid> getBoids() {
        return boids;
    }

    @Override
    public synchronized void addBoid(Boid boid) {
        this.boids.add(boid);
    }

    @Override
    public synchronized double getWidth() {
        return width;
    }

    @Override
    public synchronized double getHeight() {
        return height;
    }

    @Override
    public synchronized double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public synchronized double getPerceptionRadius() {
        return perceptionRadius;
    }

    @Override
    public synchronized double getAvoidRadius() {
        return avoidRadius;
    }

    @Override
    public synchronized double getSeparationWeight() {
        return separationWeight;
    }

    @Override
    public synchronized void setSeparationWeight(double separationWeight) {
        this.separationWeight = separationWeight;
    }


}
