package pcd.ass01.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractBoidsMonitor {
    protected final Lock boidsMutex;
    protected Collection<Boid> boids;
    protected final Collection<Boid> tempNewBoids;

    public AbstractBoidsMonitor() {
        this.boidsMutex = new ReentrantLock();
        this.boids = new ArrayList<>();
        this.tempNewBoids = new ArrayList<>();
    }

    public Collection<Boid> getBoids() {
        try {
            boidsMutex.lock();
            return boids;
        } finally {
            boidsMutex.unlock();
        }
    }

    public void addBoid(Boid boid) {
        try {
            boidsMutex.lock();
            this.boids.add(boid);
        } finally {
            boidsMutex.unlock();
        }
    }

    public abstract void updateBoid(Boid newBoid);
    public abstract void updateFlock();
}
