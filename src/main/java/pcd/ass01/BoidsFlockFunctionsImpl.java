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
        double dx = 0;
        double dy = 0;
        int count = 0;
        for (Boid other: nearbyBoids) {
            P2d otherPos = other.getPos();
            double distance = actualBoid.getPos().distance(otherPos);
            if (distance < avoidRadius) {
                dx += actualBoid.getPos().x() - otherPos.x();
                dy += actualBoid.getPos().y() - otherPos.y();
                count++;
            }
        }
        if (count > 0) {
            dx /= count;
            dy /= count;
            return new V2d(dx, dy).getNormalized();
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

    private P2d calculateAveragePosition(Collection<Boid> nearbyBoids) {
        return nearbyBoids.stream()
                .map(Boid::getPos)
                .reduce(new P2d(0, 0), P2d::sum)
                .div(nearbyBoids.size());
    }
}
