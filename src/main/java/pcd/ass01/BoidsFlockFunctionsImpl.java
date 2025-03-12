package pcd.ass01;

import java.util.Collection;

public class BoidsFlockFunctionsImpl implements BoidsFlockFunctions{

    @Override
    public V2d calculateAlignment(Boid actualBoid, Collection<Boid> nearbyBoids) {
        double avgVx = 0;
        double avgVy = 0;
        if (!nearbyBoids.isEmpty()) {
            for (Boid other : nearbyBoids) {
                V2d otherVel = other.getVel();
                avgVx += otherVel.x();
                avgVy += otherVel.y();
            }
            avgVx /= nearbyBoids.size();
            avgVy /= nearbyBoids.size();
            return new V2d(avgVx - actualBoid.getVel().x(), avgVy - actualBoid.getVel().y()).getNormalized();
        } else {
            return new V2d(0, 0);
        }
    }

    @Override
    public V2d calculateCohesion(Boid actualBoid, Collection<Boid> nearbyBoids) {
        return null;
    }

    @Override
    public V2d calculateSeparation(Boid actualBoid, Collection<Boid> nearbyBoids, double avoidRadius) {
        return null;
    }
}
