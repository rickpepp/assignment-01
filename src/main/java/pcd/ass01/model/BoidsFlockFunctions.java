package pcd.ass01.model;

import java.util.Collection;

public interface BoidsFlockFunctions {
    public V2d calculateAlignment(Boid actualBoid, Collection<Boid> nearbyBoids);
    public V2d calculateCohesion(Boid actualBoid, Collection<Boid> nearbyBoids);
    public V2d calculateSeparation(Boid actualBoid, Collection<Boid> nearbyBoids, double avoidRadius);
    public V2d weightAlignmentCohesionSeparationToSum(V2d alignment,
                                                      V2d cohesion,
                                                      V2d separation,
                                                      double alignmentWeight,
                                                      double cohesionWeight,
                                                      double separationWeight);
    public V2d getLimitedSpeed(V2d actualSpeed, double maxSpeed);
}
