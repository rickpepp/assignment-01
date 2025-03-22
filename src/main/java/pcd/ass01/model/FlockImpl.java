package pcd.ass01.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FlockImpl implements Flock {

    private Collection<Boid> boids;
    private final Collection<Boid> tempNewBoids;
    private final double width;
    private final double height;
    private final double maxSpeed;
    private final double perceptionRadius;
    private final double avoidRadius;
    private double separationWeight;
    private double alignmentWeight;
    private double cohesionWeight;
    private final Lock updateBoidMutex;

    public FlockImpl(double width,
                     double height,
                     double maxSpeed,
                     double perceptionRadius,
                     double avoidRadius,
                     double separationWeight,
                     double alignmentWeight,
                     double cohesionWeight) {
        this.boids = new ArrayList<>();
        this.tempNewBoids = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.perceptionRadius = perceptionRadius;
        this.avoidRadius = avoidRadius;
        this.separationWeight = separationWeight;
        this.alignmentWeight = alignmentWeight;
        this.cohesionWeight = cohesionWeight;
        this.updateBoidMutex = new ReentrantLock();
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
    public void updateBoid(Boid newBoid) {
        try {
            updateBoidMutex.lock();
            tempNewBoids.add(newBoid);
            if (tempNewBoids.size() == boids.size()) {
                boids = new ArrayList<>(tempNewBoids);
                tempNewBoids.clear();
            }
        } finally {
            updateBoidMutex.unlock();
        }
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
    public synchronized double getAlignmentWeight() {
        return alignmentWeight;
    }

    @Override
    public synchronized double getCohesionWeight() {
        return cohesionWeight;
    }

    @Override
    public synchronized void setSeparationWeight(double separationWeight) {
        this.separationWeight = separationWeight;
    }

    @Override
    public synchronized void setAlignmentWeight(double alignmentWeight) {
        this.alignmentWeight = alignmentWeight;
    }

    @Override
    public synchronized void setCohesionWeight(double cohesionWeight) {
        this.cohesionWeight = cohesionWeight;
    }

}
