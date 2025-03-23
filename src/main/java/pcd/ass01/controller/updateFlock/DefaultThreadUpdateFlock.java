package pcd.ass01.controller.updateFlock;

import pcd.ass01.model.Flock;

import java.util.*;

public class DefaultThreadUpdateFlock implements UpdateFlock {

    private final Flock flock;
    private final int numberOfProcessor;

    public DefaultThreadUpdateFlock(Flock flock) {
        this.flock = flock;
        this.numberOfProcessor = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public void update() {
        updateBoids();
    }

    private void updateBoids() {
        List<UpdatePositionWorker> workers = new ArrayList<>();
        createAndStartWorkers(workers, numberOfProcessor);
        joinThreads(workers);
    }

    private void joinThreads(List<UpdatePositionWorker> workers) {
        for (Thread thread: workers) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createAndStartWorkers(List<UpdatePositionWorker> workers,
                                       int numberOfProcessor) {
        int jobSize = this.flock.getBoids().size()/numberOfProcessor;
        int from = 0;
        int to = jobSize - 1;
        for (int i = 0; i < numberOfProcessor - 1; i++) {
            var w = new UpdatePositionWorker(this.flock, from, to);
            w.start();
            workers.add(w);
            from = to + 1;
            to += jobSize;
        }
        var w = new UpdatePositionWorker(this.flock, from, this.flock.getBoids().size() - 1);
        w.start();
        workers.add(w);
    }
}
