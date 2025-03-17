package pcd.ass01.model;

import java.util.Collection;

public class SequentialUpdateFlock implements UpdateFlock {

    private final Flock flock;
    private final BoidsFlockFunctions functions;


    public SequentialUpdateFlock(Flock flock) {
        this.flock = flock;
        this.functions = new BoidsFlockFunctionsImpl();
    }

    @Override
    public void update() {
        for (Boid boid : this.flock.getBoids()) {
            updateSingleBoid(boid);
        }
    }

    private void updateSingleBoid(Boid boid) {
        Collection<Boid> nearbyBoids = this.functions.getNearbyBoids(boid,
                flock.getBoids(), flock.getPerceptionRadius());
        boid.setVel(functions.getLimitedSpeed(
                boid.getVel().sum(getAlignmentCohesionSeparationToSum(boid, nearbyBoids)),
                flock.getMaxSpeed()));
        boid.setPos(boid.getPos().sum(boid.getVel()));
        environmentWrapAround(boid);
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

    private void environmentWrapAround(Boid boid) {
        boid.setPos(this.functions.environmentWrapAround(boid.getPos(),
                -flock.getWidth()/2,
                flock.getWidth()/2,
                -flock.getHeight()/2,
                flock.getHeight()/2));
    }
}
