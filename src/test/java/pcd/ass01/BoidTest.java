package pcd.ass01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pcd.ass01.model.Boid;
import pcd.ass01.model.P2d;
import pcd.ass01.model.V2d;

import static org.junit.jupiter.api.Assertions.*;

class BoidTest {

    private Boid boid;

    @BeforeEach
    void init() {
        this.boid = new Boid(new P2d(10, -10), new V2d(5, -5));
    }

    @Test
    void testPos() {
        boid.setPos(new P2d(-15, 5.5));
        assertEquals(new P2d(-15, 5.5), boid.getPos());
    }

    @Test
    void testVel() {
        boid.setVel(new V2d(-99, -0));
        assertEquals(new V2d(-99, 0), boid.getVel());
    }
}