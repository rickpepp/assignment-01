package pcd.ass01.model;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class BoidsMonitorWithSync extends AbstractBoidsMonitor {

    private final Condition finishedUpdate;
    private final Condition canUpdate;
    private boolean updatingFase = false;

    public BoidsMonitorWithSync() {
        this.finishedUpdate = boidsMutex.newCondition();
        this.canUpdate = boidsMutex.newCondition();
    }

    public void updateBoid(Boid newBoid) {
        try {
            boidsMutex.lock();
            while (!updatingFase) {
                try {
                    this.canUpdate.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            tempNewBoids.add(newBoid);
            if (tempNewBoids.size() == boids.size()) {
                boids = new ArrayList<>(tempNewBoids);
                tempNewBoids.clear();
                this.updatingFase = false;
                this.finishedUpdate.signal();
            }
        } finally {
            boidsMutex.unlock();
        }
    }

    public void updateFlock() {
        try {
            boidsMutex.lock();
            while (updatingFase) {
                try {
                    this.finishedUpdate.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.updatingFase = true;
            this.canUpdate.signalAll();
        } finally {
            boidsMutex.unlock();
        }
    }

}
