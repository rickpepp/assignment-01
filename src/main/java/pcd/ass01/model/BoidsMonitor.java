package pcd.ass01.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoidsMonitor {

    private final Lock boidsMutex;
    private Collection<Boid> boids;
    private final Collection<Boid> tempNewBoids;

    public BoidsMonitor() {
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

    public void updateBoid(Boid newBoid) {
        try {
            boidsMutex.lock();
            tempNewBoids.add(newBoid);
            if (tempNewBoids.size() == boids.size()) {
                boids = new ArrayList<>(tempNewBoids);
                tempNewBoids.clear();
            }
        } finally {
            boidsMutex.unlock();
        }
    }

}
