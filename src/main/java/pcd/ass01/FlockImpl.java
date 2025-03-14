package pcd.ass01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FlockImpl implements Flock{

    private Collection<Boid> boids;

    public FlockImpl() {
        this.boids = new ArrayList<>();
    }

    @Override
    public synchronized Collection<Boid> getBoids() {
        return boids;
    }

    @Override
    public synchronized void addBoid(Boid boid) {
        this.boids.add(boid);
    }
}
