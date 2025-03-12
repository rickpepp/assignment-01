package pcd.ass01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BoidsFlockFunctionsImplTest {

    private BoidsFlockFunctions functions;
    private Boid boid;

    @BeforeEach
    void init() {
        functions = new BoidsFlockFunctionsImpl();
        boid = new Boid(new P2d(10, -10), new V2d(5, -5));
    }

    @Test
    void calculateAlignment() {
        Collection<Boid> nearbyBoids = getNearbyBoids();
        V2d expectedAverageVelocity = new V2d((-5.0 + 0.0 + 90.0) / 3.0, (-5.2 + 0.0 + 90.0) / 3.0);
        assertEquals(expectedAverageVelocity.sum(boid.getVel().mul(-1)).getNormalized(),
                functions.calculateAlignment(boid, nearbyBoids));
    }

    private static Collection<Boid> getNearbyBoids() {
        Collection<Boid> nearbyBoids = new ArrayList<>(3);
        nearbyBoids.add(new Boid(new P2d(9.5, -1), new V2d(-5, -5.2)));
        nearbyBoids.add(new Boid(new P2d(0, 0), new V2d(0, 0)));
        nearbyBoids.add(new Boid(new P2d(90, 90), new V2d(90, 90)));
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
        P2d expectedPosition = new P2d((9.5 + 0.0 + 90.0) / 3.0, (-1.0 + 0.0 + 90.0) / 3.0);
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
    }
}