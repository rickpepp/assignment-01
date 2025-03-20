package pcd.ass01.model;

import pcd.ass01.model.agents.UpdatePositionWorker;

import java.util.*;

public class DefaultThreadUpdateFlock implements UpdateFlock {

    private final Flock flock;
    private final int numberOfProcessor;
    private Optional<Collection<Boid>> newBoidsCollection;

    public DefaultThreadUpdateFlock(Flock flock) {
        this.flock = flock;
        this.numberOfProcessor = Runtime.getRuntime().availableProcessors();
        newBoidsCollection = Optional.empty();
    }

    @Override
    public void update() {
        if (newBoidsCollection.isEmpty())
            newBoidsCollection = Optional.of(Collections.synchronizedCollection(new ArrayList<>(flock.getBoids().size())));
        else
            newBoidsCollection.get().clear();
        updateBoids(newBoidsCollection.get());
        this.flock.updateBoids(new ArrayList<>(newBoidsCollection.get()));
    }

    private void updateBoids(Collection<Boid> newBoidsCollection) {
        List<UpdatePositionWorker> workers = new ArrayList<>();
        createAndStartWorkers(workers, numberOfProcessor, newBoidsCollection);
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
                                       int numberOfProcessor,
                                       Collection<Boid> newBoidsCollection) {
        int jobSize = this.flock.getBoids().size()/numberOfProcessor;
        int from = 0;
        int to = jobSize - 1;
        for (int i = 0; i < numberOfProcessor - 1; i++) {
            var w = new UpdatePositionWorker(this.flock, from, to, newBoidsCollection);
            w.start();
            workers.add(w);
            from = to + 1;
            to += jobSize;
        }
        var w = new UpdatePositionWorker(this.flock, from, this.flock.getBoids().size() - 1, newBoidsCollection);
        w.start();
        workers.add(w);
    }
}
