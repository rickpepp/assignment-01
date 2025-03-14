package pcd.ass01;

import java.util.Collection;

public interface Flock {
    public Collection<Boid> getBoids();
    public void addBoid(Boid boid);
    public double getWidth();
    public double getHeight();
    public double getMaxSpeed();
    public double getPerceptionRadius();
    public double getAvoidRadius();
}
