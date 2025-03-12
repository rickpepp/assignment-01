package pcd.ass01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoidTest {

    @Test
    void createBoid() {
        new Boid(new P2d(10, -10), new V2d(5, -5));
    }

    @Test
    void testPos() {
        Boid boid = new Boid(new P2d(10, -10), new V2d(5, -5));
        boid.setPos(new P2d(-15, 5.5));
        assertEquals(new P2d(-15, 5.5), boid.getPos());
    }

    @Test
    void testVel() {
        Boid boid = new Boid(new P2d(10, -10), new V2d(5, -5));
        boid.setVel(new V2d(-99, -0));
        assertEquals(new V2d(-99, 0), boid.getVel());
    }
}