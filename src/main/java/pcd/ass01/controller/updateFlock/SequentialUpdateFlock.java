package pcd.ass01.controller.updateFlock;

import pcd.ass01.model.*;

import java.util.Collection;

public class SequentialUpdateFlock implements UpdateFlock {

    private final Flock flock;
    private final BoidsFlockFunctions functions;
    private boolean started = false;
    private Thread thread;

    public SequentialUpdateFlock(Flock flock) {
        this.flock = flock;
        this.functions = new BoidsFlockFunctionsImpl();
        this.thread = new Thread(() -> {
            while (true)
                this.flock.getBoids().forEach(this::updateSingleBoid);
        });
    }

    @Override
    public void update() {
        if(!started) {
            this.started = true;
            this.thread.start();
        }
        this.flock.updateFlock();
    }


    private void updateSingleBoid(Boid boid) {
        Collection<Boid> nearbyBoids = this.functions.getNearbyBoids(boid,
                flock.getBoids(), flock.getPerceptionRadius());
        V2d newVelocity = functions.getLimitedSpeed(
                boid.getVel().sum(getAlignmentCohesionSeparationToSum(boid, nearbyBoids)),
                flock.getMaxSpeed());
        P2d newPosition = environmentWrapAround(boid.getPos().sum(newVelocity));
        flock.updateBoid(new Boid(newPosition, newVelocity));
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
