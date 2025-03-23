package pcd.ass01.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingleValueMonitor {
    private double value;
    private final Lock mutex;

    public SingleValueMonitor() {
        this.mutex = new ReentrantLock();
    }

    public void setValue(double value) {
        try {
            this.mutex.lock();
            this.value = value;
        } finally {
            this.mutex.unlock();
        }
    }

    public double getValue() {
        try {
            this.mutex.lock();
            return this.value;
        } finally {
            this.mutex.unlock();
        }
    }
}
