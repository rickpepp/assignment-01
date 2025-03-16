package pcd.ass01.model;

import java.util.Collection;

public class SequentialUpdateFlock implements UpdateFlock {

    private Flock flock;
    private BoidsFlockFunctions functions;


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
        updateVelocityWithCohesionAlignmentSeparation(boid);
        limitSpeedToMaxSpeed(boid);
        boid.setPos(boid.getPos().sum(boid.getVel()));
        environmentWrapAround(boid,
                -flock.getWidth()/2,
                flock.getWidth()/2,
                -flock.getHeight()/2,
                flock.getHeight()/2);
    }

    private void limitSpeedToMaxSpeed(Boid boid) {
        if (boid.getVel().abs() > flock.getMaxSpeed()) {
            boid.setVel(boid.getVel()
                    .getNormalized()
                    .mul(flock.getMaxSpeed()));
        }
    }

    private void updateVelocityWithCohesionAlignmentSeparation(Boid boid) {
        Collection<Boid> nearbyBoids = flock.getNearbyBoids(boid);
        boid.setVel(
                getNewVelocityWithWeightedCohesionAlignmentSeparation(boid,
                    this.functions.calculateSeparation(boid, nearbyBoids, flock.getAvoidRadius()),
                    this.functions.calculateAlignment(boid, nearbyBoids),
                    this.functions.calculateCohesion(boid, nearbyBoids)));
    }

    private V2d getNewVelocityWithWeightedCohesionAlignmentSeparation(Boid boid,
                                                                      V2d separation,
                                                                      V2d alignment,
                                                                      V2d cohesion) {
        return boid.getVel().sum(alignment.mul(flock.getAlignmentWeight()))
                .sum(separation.mul(flock.getSeparationWeight()))
                .sum(cohesion.mul(flock.getCohesionWeight()));
    }

    private void environmentWrapAround(Boid boid,
                                       double minX,
                                       double maxX,
                                       double minY,
                                       double maxY) {
        if (boid.getPos().x() < minX) boid.setPos(boid.getPos().sum(new V2d(maxX * 2, 0)));
        if (boid.getPos().x() >= maxX) boid.setPos(boid.getPos().sum(new V2d(minX * 2, 0)));
        if (boid.getPos().y() < minY) boid.setPos(boid.getPos().sum(new V2d(0, maxY * 2)));
        if (boid.getPos().y() >= maxY) boid.setPos(boid.getPos().sum(new V2d(0, minY * 2)));
    }
}
