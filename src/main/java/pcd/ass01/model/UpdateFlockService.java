package pcd.ass01.model;

import java.util.Collection;
import java.util.concurrent.Callable;

public class UpdateFlockService implements Callable<Void> {

    private Boid boid;
    private BoidsFlockFunctions functions;
    private Flock flock;

    public UpdateFlockService(Boid boid, Flock flock) {
        this.boid = boid;
        this.functions = new BoidsFlockFunctionsImpl();
        this.flock = flock;
    }

    @Override
    public Void call() {
        Collection<Boid> nearbyBoids = this.functions.getNearbyBoids(boid,
                flock.getBoids(), flock.getPerceptionRadius());
        V2d velocity = functions.getLimitedSpeed(
                boid.getVel().sum(getAlignmentCohesionSeparationToSum(boid, nearbyBoids)),
                flock.getMaxSpeed());
        P2d position = environmentWrapAround(boid.getPos().sum(velocity));
        flock.updateBoid(new Boid(position, velocity));
        return null;
    }

    private P2d environmentWrapAround(P2d boidPosition) {
        return this.functions.environmentWrapAround(boidPosition,
                -flock.getWidth()/2,
                flock.getWidth()/2,
                -flock.getHeight()/2,
                flock.getHeight()/2);
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
}
