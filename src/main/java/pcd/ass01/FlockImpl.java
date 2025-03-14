package pcd.ass01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlockImpl implements Flock{

    private final Collection<Boid> boids;
    private final double width;
    private final double height;

    public FlockImpl(double width, double height) {
        this.boids = new ArrayList<>();
        this.width = width;
        this.height = height;
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
}
