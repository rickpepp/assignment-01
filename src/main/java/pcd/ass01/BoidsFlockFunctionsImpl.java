package pcd.ass01;

import java.util.Collection;
import java.util.List;

public class BoidsFlockFunctionsImpl implements BoidsFlockFunctions{

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
        List<P2d> tooNearBoids = nearbyBoids.stream().map(Boid::getPos)
                .filter(e -> actualBoid.getPos().distance(e) < avoidRadius).toList();
        if (tooNearBoids.isEmpty())
            return new V2d(0, 0);
        else
            return new V2d(tooNearBoids.stream()
                .reduce(new P2d(0, 0), (previous, next) -> previous.sum(actualBoid.getPos().sub(next)))
                .div(tooNearBoids.size())).getNormalized();
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
