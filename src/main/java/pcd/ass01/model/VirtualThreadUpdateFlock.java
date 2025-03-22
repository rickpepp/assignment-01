package pcd.ass01.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadUpdateFlock implements UpdateFlock {

    private final ExecutorService executor;
    private final Flock flock;

    public VirtualThreadUpdateFlock(Flock flock) {
        executor = Executors.newVirtualThreadPerTaskExecutor();
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
