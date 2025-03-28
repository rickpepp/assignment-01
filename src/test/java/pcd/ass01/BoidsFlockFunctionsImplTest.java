package pcd.ass01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pcd.ass01.controller.updateFlock.BoidsFlockFunctions;
import pcd.ass01.controller.updateFlock.BoidsFlockFunctionsImpl;
import pcd.ass01.model.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BoidsFlockFunctionsImplTest {

    private static final double BOID_X_POSITION = 10;
    private static final double BOID_Y_POSITION = -10;
    private static final double BOID_X_VELOCITY = 5;
    private static final double BOID_Y_VELOCITY = -5;
    private static final double BOID1_X_POSITION = 9.5;
    private static final double BOID1_Y_POSITION = -1;
    private static final double BOID1_X_VELOCITY = -5;
    private static final double BOID1_Y_VELOCITY = -5.2;
    private static final double BOID2_POSITION_AND_VELOCITY = 0;
    private static final double BOID3_POSITION_AND_VELOCITY = 90;
    private static final double NUMBER_OF_NEARBY = 3.0;
    private static final double AVOID_RADIUS = 10;

    private BoidsFlockFunctions functions;
    private Boid boid;

    @BeforeEach
    void init() {
        functions = new BoidsFlockFunctionsImpl();
        boid = new Boid(new P2d(BOID_X_POSITION, BOID_Y_POSITION), new V2d(BOID_X_VELOCITY, BOID_Y_VELOCITY));
    }

    @Test
    void calculateAlignment() {
        Collection<Boid> nearbyBoids = getNearbyBoids();
        V2d expectedAverageVelocity =
                new V2d((BOID1_X_VELOCITY + BOID2_POSITION_AND_VELOCITY + BOID3_POSITION_AND_VELOCITY) / NUMBER_OF_NEARBY,
                    (BOID1_Y_VELOCITY + BOID2_POSITION_AND_VELOCITY + BOID3_POSITION_AND_VELOCITY) / NUMBER_OF_NEARBY);
        assertEquals(expectedAverageVelocity.sum(boid.getVel().mul(-1)).getNormalized(),
                functions.calculateAlignment(boid, nearbyBoids));
    }

    private static Collection<Boid> getNearbyBoids() {
        Collection<Boid> nearbyBoids = new ArrayList<>(3);
        nearbyBoids.add(new Boid(new P2d(BOID1_X_POSITION, BOID1_Y_POSITION), new V2d(BOID1_X_VELOCITY, BOID1_Y_VELOCITY)));
        nearbyBoids.add(new Boid(new P2d(BOID2_POSITION_AND_VELOCITY, BOID2_POSITION_AND_VELOCITY),
                new V2d(BOID2_POSITION_AND_VELOCITY, BOID2_POSITION_AND_VELOCITY)));
        nearbyBoids.add(new Boid(new P2d(BOID3_POSITION_AND_VELOCITY, BOID3_POSITION_AND_VELOCITY),
                new V2d(BOID3_POSITION_AND_VELOCITY, BOID3_POSITION_AND_VELOCITY)));
        return nearbyBoids;
    }

    @Test
    void calculateAlignmentNoNearby() {
        Collection<Boid> nearbyBoids = new ArrayList<>(0);
        assertEquals(new V2d(0, 0), functions.calculateAlignment(boid, nearbyBoids));
    }

    @Test
    void calculateCohesion() {
        Collection<Boid> nearbyBoids = getNearbyBoids();
        P2d expectedPosition =
                new P2d((BOID1_X_POSITION + BOID2_POSITION_AND_VELOCITY + BOID3_POSITION_AND_VELOCITY) / NUMBER_OF_NEARBY,
                    (BOID1_Y_POSITION + BOID2_POSITION_AND_VELOCITY + BOID3_POSITION_AND_VELOCITY) / NUMBER_OF_NEARBY);
        V2d actualPosition = new V2d(boid.getPos().x(), boid.getPos().y()).mul(-1);
        expectedPosition = expectedPosition.sum(actualPosition);
        V2d expectedVelocity = new V2d(expectedPosition.x(), expectedPosition.y());
        assertEquals(expectedVelocity.getNormalized(),
                functions.calculateCohesion(boid, nearbyBoids));
    }

    @Test
    void calculateCohesionNoNearby() {
        Collection<Boid> nearbyBoids = new ArrayList<>(0);
        assertEquals(new V2d(0, 0), functions.calculateCohesion(boid, nearbyBoids));
    }

    @Test
    void calculateSeparation() {
        Collection<Boid> nearbyBoids = getNearbyBoids();
        V2d expectedVelocity = new V2d((BOID_X_POSITION - BOID1_X_POSITION), (BOID_Y_POSITION - BOID1_Y_POSITION)).getNormalized();
        assertEquals(expectedVelocity, functions.calculateSeparation(boid, nearbyBoids, AVOID_RADIUS));
    }

    @Test
    void calculateSeparationNoNearby() {
        Collection<Boid> nearbyBoids = new ArrayList<>(0);
        assertEquals(new V2d(0, 0), functions.calculateSeparation(boid, nearbyBoids, AVOID_RADIUS));
    }

    @Test
    void weightAlignmentCohesionSeparationToSum() {
        V2d alignment = new V2d(27.1, -0.1);
        V2d cohesion = new V2d(1, 7.6);
        V2d separation = new V2d(0, -9.1);
        double alignmentWeight = 1.0;
        double cohesionWeight = 0.1;
        double separationWeight = 2;
        V2d resultExpected = new V2d(alignmentWeight * alignment.x() + cohesionWeight * cohesion.x() + separationWeight * separation.x(),
                alignmentWeight * alignment.y() + cohesionWeight * cohesion.y() + separationWeight * separation.y());
        assertEquals(resultExpected, functions.weightAlignmentCohesionSeparationToSum(alignment,
                cohesion,
                separation,
                alignmentWeight,
                cohesionWeight,
                separationWeight));
    }

    @Test
    void limitMaxSpeed() {
        V2d speed = new V2d(150, -70);
        double maxSpeed = 50;
        assertEquals(speed.getNormalized().mul(maxSpeed), functions.getLimitedSpeed(speed, maxSpeed));
    }

    @Test
    void limitMaxSpeedNothingToDo() {
        V2d speed = new V2d(150, -70);
        double maxSpeed = 550;
        assertEquals(speed, functions.getLimitedSpeed(speed, maxSpeed));
    }

    @Test
    void getNearbyBoidsTest() {
        Flock flock = new FlockBuilder()
                .perceptionRadius(5)
                .buildFlock();
        Boid boid1 = new Boid(new P2d(1, 1), new V2d(0, 0));
        Boid boid2 = new Boid(new P2d(0, 0), new V2d(0, 0));
        Boid boid3 = new Boid(new P2d(9, -10), new V2d(0, 0));
        Boid boid4 = new Boid(new P2d(-1, 2), new V2d(0, 0));
        flock.addBoid(boid1);
        flock.addBoid(boid2);
        flock.addBoid(boid3);
        flock.addBoid(boid4);
        Collection<Boid> resultExpected = new ArrayList<>();
        resultExpected.add(boid1);
        resultExpected.add(boid4);
        Collection<Boid> result =
                new BoidsFlockFunctionsImpl().getNearbyBoids(boid2,
                        flock.getBoids(), flock.getPerceptionRadius());
        assertTrue(result.size() == resultExpected.size() &&
                result.containsAll(resultExpected) &&
                resultExpected.containsAll(result));
    }
}