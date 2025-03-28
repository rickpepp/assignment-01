package pcd.ass01.model;

import java.util.ArrayList;

public class BoidsMonitorWithoutSync extends AbstractBoidsMonitor{

    @Override
    public void updateBoid(Boid newBoid) {
        try {
            boidsMutex.lock();
            tempNewBoids.add(newBoid);
            if (tempNewBoids.size() == boids.size()) {
                boids = new ArrayList<>(tempNewBoids);
                tempNewBoids.clear();
            }
        } finally {
            boidsMutex.unlock();
        }
    }

    @Override
    public void updateFlock() {

    }
}
