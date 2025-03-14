package pcd.ass01;

import org.junit.jupiter.api.Test;

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
}