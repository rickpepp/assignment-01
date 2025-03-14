package pcd.ass01;

import java.util.Collection;
import java.util.List;

public interface Flock {
    public Collection<Boid> getBoids();
    public void addBoid(Boid boid);
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
    public Collection<Boid> getNearbyBoids(Boid boid);
}
