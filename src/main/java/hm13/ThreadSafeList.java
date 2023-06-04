package hm13;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<T> {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final List<T> list;

    public ThreadSafeList(List<T> list) {
        this.list = list;
    }

    public void add(T value) {

        if (readWriteLock.writeLock().tryLock()) {
            try {
                list.add(value);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

    public T remove(int index) {
        if (readWriteLock.writeLock().tryLock()) {
            try {
                return list.remove(index);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
        return null;
    }

    public T get(int index) {
        if (readWriteLock.writeLock().tryLock()) {
            try {
                return list.get(index);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
        return null;
    }
}
