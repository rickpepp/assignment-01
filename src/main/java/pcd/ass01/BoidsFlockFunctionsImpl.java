package pcd.ass01;

import java.util.Collection;

public class BoidsFlockFunctionsImpl implements BoidsFlockFunctions{

    @Override
    public V2d calculateAlignment(Boid actualBoid, Collection<Boid> nearbyBoids) {
        if (!nearbyBoids.isEmpty()) {
            return calculateAverageVelocity(nearbyBoids).sub(actualBoid.getVel()).getNormalized();
        } else {
            return new V2d(0, 0);
        }
    }

    private V2d calculateAverageVelocity(Collection<Boid> nearbyBoids) {
        return nearbyBoids.stream()
                .map(Boid::getVel)
                .reduce(new V2d(0, 0), V2d::sum)
                .div(nearbyBoids.size());
    }

    @Override
    public V2d calculateCohesion(Boid actualBoid, Collection<Boid> nearbyBoids) {
        double centerX = 0;
        double centerY = 0;
        if (!nearbyBoids.isEmpty()) {
            for (Boid other: nearbyBoids) {
                P2d otherPos = other.getPos();
                centerX += otherPos.x();
                centerY += otherPos.y();
            }
            centerX /= nearbyBoids.size();
            centerY /= nearbyBoids.size();
            return new V2d(centerX - actualBoid.getPos().x(), centerY - actualBoid.getPos().y()).getNormalized();
        } else {
            return new V2d(0, 0);
        }
    }

    @Override
    public V2d calculateSeparation(Boid actualBoid, Collection<Boid> nearbyBoids, double avoidRadius) {
        return null;
    }
}
