package pcd.ass01.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingleValueMonitor<T> {
    private T value;
    private final Lock mutex;

    public SingleValueMonitor() {
        this.mutex = new ReentrantLock();
    }

    public void setValue(T value) {
        try {
            this.mutex.lock();
            this.value = value;
        } finally {
            this.mutex.unlock();
        }
    }

    public T getValue() {
        try {
            this.mutex.lock();
            return this.value;
        } finally {
            this.mutex.unlock();
        }
    }
}
