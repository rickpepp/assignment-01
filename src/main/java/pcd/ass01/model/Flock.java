package pcd.ass01.model;

import java.util.Collection;

public interface Flock {
    public Collection<Boid> getBoids();
    public void addBoid(Boid boid);
    public void updateBoid(Boid oldBoid, Boid newBoid);
    public void updateBoids(Collection<Boid> newBoids);
    public double getWidth();
    public double getHeight();
    public double getMaxSpeed();
    public double getPerceptionRadius();
    public double getAvoidRadius();
    public double getSeparationWeight();
    public double getAlignmentWeight();
    public double getCohesionWeight();
    public void setSeparationWeight(double separationWeight);
    public void setAlignmentWeight(double alignmentWeight);
    public void setCohesionWeight(double cohesionWeight);
}
