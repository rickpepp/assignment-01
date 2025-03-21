package pcd.ass01;

import org.junit.jupiter.api.Test;
import pcd.ass01.model.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class FlockTest {
    @Test
    void addBoidTest() {
        Flock flock = new FlockBuilder().buildFlock();
        Boid boid1 = new Boid(new P2d(0, 0), new V2d(0, 0));
        Boid boid2 = new Boid(new P2d(0, 0), new V2d(0, 0));
        flock.addBoid(boid1);
        flock.addBoid(boid2);
        Collection<Boid> resultExpected = new ArrayList<>();
        resultExpected.add(boid1);
        resultExpected.add(boid2);
        assertTrue(flock.getBoids().size() == resultExpected.size() &&
                flock.getBoids().containsAll(resultExpected) &&
                resultExpected.containsAll(flock.getBoids()));
    }

    @Test
    void getWidthTest() {
        Flock flock = new FlockBuilder()
                .width(800)
                .buildFlock();
        assertEquals(800, flock.getWidth());
    }

    @Test
    void getHeightTest() {
        Flock flock = new FlockBuilder()
                .height(800)
                .buildFlock();
        assertEquals(800, flock.getHeight());
    }

    @Test
    void getMaxSpeed() {
        Flock flock = new FlockBuilder()
                .maxSpeed(10)
                .buildFlock();
        assertEquals(10, flock.getMaxSpeed());
    }

    @Test
    void getPerceptionRadius() {
        Flock flock = new FlockBuilder()
                .perceptionRadius(5)
                .buildFlock();
        assertEquals(5, flock.getPerceptionRadius());
    }

    @Test
    void getAvoidRadius() {
        Flock flock = new FlockBuilder()
                .avoidRadius(1)
                .buildFlock();
        assertEquals(1, flock.getAvoidRadius());
    }

    @Test
    void getSeparationWeight() {
        Flock flock = new FlockBuilder()
                .separationWeight(2)
                .buildFlock();
        assertEquals(2, flock.getSeparationWeight());
    }

    @Test
    void setSeparationWeight() {
        Flock flock = new FlockBuilder()
                .separationWeight(2)
                .buildFlock();
        flock.setSeparationWeight(5);
        assertEquals(5, flock.getSeparationWeight());
    }

    @Test
    void getAlignmentWeight() {
        Flock flock = new FlockBuilder()
                .alignmentWeight(3)
                .buildFlock();
        assertEquals(3, flock.getAlignmentWeight());
    }

    @Test
    void setAlignmentWeight() {
        Flock flock = new FlockBuilder()
                .alignmentWeight(3)
                .buildFlock();
        flock.setAlignmentWeight(0);
        assertEquals(0, flock.getAlignmentWeight());
    }

    @Test
    void getCohesionWeight() {
        Flock flock = new FlockBuilder()
                .cohesionWeight(10)
                .buildFlock();
        assertEquals(10, flock.getCohesionWeight());
    }

    @Test
    void setCohesionWeight() {
        Flock flock = new FlockBuilder()
                .cohesionWeight(10)
                .buildFlock();
        flock.setCohesionWeight(19);
        assertEquals(19, flock.getCohesionWeight());
    }

    @Test
    void updateBoid() {
        Flock flock = new FlockBuilder()
                .cohesionWeight(10)
                .buildFlock();
        Boid boid1 = new Boid(new P2d(0, 2), new V2d(0, 0));
        Boid boid2 = new Boid(new P2d(2, 0), new V2d(0, 0));
        flock.addBoid(boid1);
        flock.addBoid(boid2);
    }
}