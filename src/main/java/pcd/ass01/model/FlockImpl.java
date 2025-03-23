package pcd.ass01.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FlockImpl implements Flock {

    private final double width;
    private final double height;
    private final double maxSpeed;
    private final double perceptionRadius;
    private final double avoidRadius;
    private double separationWeight;
    private double alignmentWeight;
    private double cohesionWeight;

    private final Lock separationMutex;
    private final Lock cohesionMutex;

    private final BoidsMonitor boidsMonitor;

    public FlockImpl(double width,
                     double height,
                     double maxSpeed,
                     double perceptionRadius,
                     double avoidRadius,
                     double separationWeight,
                     double alignmentWeight,
                     double cohesionWeight) {
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.perceptionRadius = perceptionRadius;
        this.avoidRadius = avoidRadius;
        this.separationWeight = separationWeight;
        this.alignmentWeight = alignmentWeight;
        this.cohesionWeight = cohesionWeight;
        this.separationMutex = new ReentrantLock();
        this.cohesionMutex = new ReentrantLock();
        this.boidsMonitor = new BoidsMonitor();
    }

    @Override
    public Collection<Boid> getBoids() {
        return this.boidsMonitor.getBoids();
    }

    @Override
    public void addBoid(Boid boid) {
        this.boidsMonitor.addBoid(boid);
    }

    @Override
    public void updateBoid(Boid newBoid) {
        this.boidsMonitor.updateBoid(newBoid);
    }

    @Override
    public  double getWidth() {
        return width;
    }

    @Override
    public  double getHeight() {
        return height;
    }

    @Override
    public  double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public  double getPerceptionRadius() {
        return perceptionRadius;
    }

    @Override
    public  double getAvoidRadius() {
        return avoidRadius;
    }

    @Override
    public double getSeparationWeight() {
        try {
            separationMutex.lock();
            return separationWeight;
        } finally {
            separationMutex.unlock();
        }
    }

    @Override
    public synchronized double getAlignmentWeight() {
        return alignmentWeight;
    }

    @Override
    public double getCohesionWeight() {
        try {
            cohesionMutex.lock();
            return cohesionWeight;
        } finally {
            cohesionMutex.unlock();
        }
    }

    @Override
    public void setSeparationWeight(double separationWeight) {
        try {
            separationMutex.lock();
            this.separationWeight = separationWeight;
        } finally {
            separationMutex.unlock();
        }
    }

    @Override
    public synchronized void setAlignmentWeight(double alignmentWeight) {
        this.alignmentWeight = alignmentWeight;
    }

    @Override
    public void setCohesionWeight(double cohesionWeight) {
        try {
            cohesionMutex.lock();
            this.cohesionWeight = cohesionWeight;
        } finally {
            cohesionMutex.unlock();
        }
    }
}
