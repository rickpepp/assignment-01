package pcd.ass01.model;

import java.util.Collection;

public class FlockImpl implements Flock {

    private final double width;
    private final double height;
    private final double maxSpeed;
    private final double perceptionRadius;
    private final double avoidRadius;
    private final SingleValueMonitor<Double> separationWeightMonitor;
    private final SingleValueMonitor<Double> cohesionWeightMonitor;
    private final SingleValueMonitor<Double> alignmentWeightMonitor;
    private final AbstractBoidsMonitor boidsMonitorWithSync;

    public FlockImpl(double width,
                     double height,
                     double maxSpeed,
                     double perceptionRadius,
                     double avoidRadius,
                     double separationWeight,
                     double alignmentWeight,
                     double cohesionWeight,
                     AbstractBoidsMonitor boidsMonitor) {
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.perceptionRadius = perceptionRadius;
        this.avoidRadius = avoidRadius;
        this.separationWeightMonitor = new SingleValueMonitor();
        this.alignmentWeightMonitor = new SingleValueMonitor();
        this.cohesionWeightMonitor = new SingleValueMonitor();
        this.separationWeightMonitor.setValue(separationWeight);
        this.alignmentWeightMonitor.setValue(alignmentWeight);
        this.cohesionWeightMonitor.setValue(cohesionWeight);
        this.boidsMonitorWithSync = boidsMonitor;
    }

    @Override
    public Collection<Boid> getBoids() {
        return this.boidsMonitorWithSync.getBoids();
    }

    @Override
    public void addBoid(Boid boid) {
        this.boidsMonitorWithSync.addBoid(boid);
    }

    @Override
    public void updateBoid(Boid newBoid) {
        this.boidsMonitorWithSync.updateBoid(newBoid);
    }

    @Override
    public void updateFlock() {
        this.boidsMonitorWithSync.updateFlock();
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public double getPerceptionRadius() {
        return perceptionRadius;
    }

    @Override
    public double getAvoidRadius() {
        return avoidRadius;
    }

    @Override
    public double getSeparationWeight() {
        return this.separationWeightMonitor.getValue();
    }

    @Override
    public double getAlignmentWeight() {
        return this.alignmentWeightMonitor.getValue();
    }

    @Override
    public double getCohesionWeight() {
        return this.cohesionWeightMonitor.getValue();
    }

    @Override
    public void setSeparationWeight(double separationWeight) {
        this.separationWeightMonitor.setValue(separationWeight);
    }

    @Override
    public void setAlignmentWeight(double alignmentWeight) {
        this.alignmentWeightMonitor.setValue(alignmentWeight);
    }

    @Override
    public void setCohesionWeight(double cohesionWeight) {
        this.cohesionWeightMonitor.setValue(cohesionWeight);
    }
}
