package pcd.ass01.model;

import java.util.Collection;
import java.util.stream.Collectors;

public class BoidsFlockFunctionsImpl implements BoidsFlockFunctions {

    @Override
    public V2d calculateAlignment(Boid actualBoid, Collection<Boid> nearbyBoids) {
        if (!nearbyBoids.isEmpty()) {
            return calculateAverageVelocity(nearbyBoids).sub(actualBoid.getVel()).getNormalized();
        } else {
            return new V2d(0, 0);
        }
    }

    @Override
    public V2d calculateCohesion(Boid actualBoid, Collection<Boid> nearbyBoids) {
        if (!nearbyBoids.isEmpty()) {
            return new V2d(calculateAveragePosition(nearbyBoids).sub(actualBoid.getPos())).getNormalized();
        } else {
            return new V2d(0, 0);
        }
    }

    @Override
    public V2d calculateSeparation(Boid actualBoid, Collection<Boid> nearbyBoids, double avoidRadius) {
        return nearbyBoids.stream().map(Boid::getPos)
                .filter(e -> actualBoid.getPos().distance(e) < avoidRadius)
                .map(value -> actualBoid.getPos().sub(value))
                .collect(Collectors.teeing(
                        Collectors.averagingDouble(P2d::x),
                        Collectors.averagingDouble(P2d::y),
                        V2d::new
                )).getNormalized();
    }

    @Override
    public V2d weightAlignmentCohesionSeparationToSum(V2d alignment,
                                                      V2d cohesion,
                                                      V2d separation,
                                                      double alignmentWeight,
                                                      double cohesionWeight,
                                                      double separationWeight) {
        return alignment.mul(alignmentWeight)
                .sum(cohesion.mul(cohesionWeight))
                .sum(separation.mul(separationWeight));
    }

    private V2d calculateAverageVelocity(Collection<Boid> nearbyBoids) {
        return nearbyBoids.stream()
                .map(Boid::getVel)
                .reduce(new V2d(0, 0), V2d::sum)
                .div(nearbyBoids.size());
    }

    private P2d calculateAveragePosition(Collection<Boid> nearbyBoids) {
        return nearbyBoids.stream()
                .map(Boid::getPos)
                .reduce(new P2d(0, 0), P2d::sum)
                .div(nearbyBoids.size());
    }
}
