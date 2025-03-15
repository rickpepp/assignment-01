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
        /* change velocity vector according to separation, alignment, cohesion */

        Collection<Boid> nearbyBoids = flock.getNearbyBoids(boid);

        V2d separation = this.functions.calculateSeparation(boid, nearbyBoids, flock.getAvoidRadius());
        V2d alignment = this.functions.calculateAlignment(boid, nearbyBoids);
        V2d cohesion = this.functions.calculateCohesion(boid, nearbyBoids);

        boid.setVel(boid.getVel().sum(alignment.mul(flock.getAlignmentWeight()))
                .sum(separation.mul(flock.getSeparationWeight()))
                .sum(cohesion.mul(flock.getCohesionWeight())));

        /* Limit speed to MAX_SPEED */

        double speed = boid.getVel().abs();

        if (speed > flock.getMaxSpeed()) {
            boid.setVel(boid.getVel()
                    .getNormalized()
                    .mul(flock.getMaxSpeed()));
        }

        /* Update position */
        boid.setPos(boid.getPos().sum(boid.getVel()));

        /* environment wrap-around */

        if (boid.getPos().x() < -flock.getWidth()/2) boid.setPos(boid.getPos().sum(new V2d(flock.getWidth(), 0)));
        if (boid.getPos().x() >= flock.getWidth()/2) boid.setPos(boid.getPos().sum(new V2d(-flock.getWidth(), 0)));
        if (boid.getPos().y() < -flock.getHeight()/2) boid.setPos(boid.getPos().sum(new V2d(0, flock.getHeight())));
        if (boid.getPos().y() >= flock.getHeight()/2) boid.setPos(boid.getPos().sum(new V2d(0, -flock.getHeight())));
    }
}
