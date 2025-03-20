package pcd.ass01.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class SequentialUpdateFlock implements UpdateFlock {

    private final Flock flock;
    private final BoidsFlockFunctions functions;
    private Optional<Collection<Boid>> newBoids;

    public SequentialUpdateFlock(Flock flock) {
        this.flock = flock;
        this.functions = new BoidsFlockFunctionsImpl();
        newBoids = Optional.empty();
    }

    @Override
    public void update() {
        if (newBoids.isEmpty())
            newBoids = Optional.of(new ArrayList<>(flock.getBoids().size()));
        else
            newBoids.get().clear();
        this.flock.getBoids().forEach(this::updateSingleBoid);
        this.flock.updateBoids(new ArrayList<>(newBoids.get()));
    }


    private void updateSingleBoid(Boid boid) {
        Collection<Boid> nearbyBoids = this.functions.getNearbyBoids(boid,
                flock.getBoids(), flock.getPerceptionRadius());
        V2d newVelocity = functions.getLimitedSpeed(
                boid.getVel().sum(getAlignmentCohesionSeparationToSum(boid, nearbyBoids)),
                flock.getMaxSpeed());
        P2d newPosition = environmentWrapAround(boid.getPos().sum(newVelocity));
        newBoids.get().add(new Boid(newPosition, newVelocity));
    }

    private V2d getAlignmentCohesionSeparationToSum(Boid boid, Collection<Boid> nearbyBoids) {
        return functions.weightAlignmentCohesionSeparationToSum(
                this.functions.calculateAlignment(boid, nearbyBoids),
                this.functions.calculateCohesion(boid, nearbyBoids),
                this.functions.calculateSeparation(boid, nearbyBoids, flock.getAvoidRadius()),
                flock.getAlignmentWeight(),
                flock.getCohesionWeight(),
                flock.getSeparationWeight());
    }

    private P2d environmentWrapAround(P2d position) {
        return this.functions.environmentWrapAround(position,
                -flock.getWidth()/2,
                flock.getWidth()/2,
                -flock.getHeight()/2,
                flock.getHeight()/2);
    }
}
