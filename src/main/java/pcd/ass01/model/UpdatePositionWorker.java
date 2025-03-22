package pcd.ass01.model;

import java.util.Collection;

public class UpdatePositionWorker extends Thread {

    private Flock flock;
    private int start;
    private int numberOfElements;
    private final BoidsFlockFunctions functions;

    public UpdatePositionWorker(Flock flock, int start, int end) {
        this.flock = flock;
        this.start = start;
        this.numberOfElements = end - start + 1;
        this.functions = new BoidsFlockFunctionsImpl();
    }

    @Override
    public void run() {
        this.flock.getBoids().stream().skip(start).limit(numberOfElements).forEach(this::updateSingleBoid);
    }

    private void updateSingleBoid(Boid boid) {
        Collection<Boid> nearbyBoids = this.functions.getNearbyBoids(boid,
                flock.getBoids(), flock.getPerceptionRadius());
        V2d velocity = functions.getLimitedSpeed(
                boid.getVel().sum(getAlignmentCohesionSeparationToSum(boid, nearbyBoids)),
                flock.getMaxSpeed());
        P2d position = environmentWrapAround(boid.getPos().sum(velocity));
        flock.updateBoid(new Boid(position, velocity));
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
