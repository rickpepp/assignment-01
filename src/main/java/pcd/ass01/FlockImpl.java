package pcd.ass01;

import java.util.ArrayList;
import java.util.Collection;

public class FlockImpl implements Flock{

    private final Collection<Boid> boids;
    private final double width;
    private final double height;
    private final double maxSpeed;

    public FlockImpl(double width, double height, double maxSpeed) {
        this.boids = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
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
}
