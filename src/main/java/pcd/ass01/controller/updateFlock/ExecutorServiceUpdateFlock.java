package pcd.ass01.controller.updateFlock;

import pcd.ass01.model.Flock;

import java.util.concurrent.*;

public class ExecutorServiceUpdateFlock implements UpdateFlock {

    private final ExecutorService executor;
    private final Flock flock;

    public ExecutorServiceUpdateFlock(Flock flock) {
        executor = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        this.flock = flock;
    }

    @Override
    public void update() {
        try {
            executor.invokeAll(this.flock.getBoids().stream()
                    .map(boid -> new UpdateFlockService(boid, flock)).toList());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
