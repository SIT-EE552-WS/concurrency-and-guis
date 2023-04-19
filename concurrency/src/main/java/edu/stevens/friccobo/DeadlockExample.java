package edu.stevens.friccobo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// Deadlock - neither thread can proceed because they both depend on each other
// Starvation - one busy thread is taking all the resources
// Livelock - each thread is too busy to release its resources
public class DeadlockExample {

    static class Friend {
        private final String name;
        private final Lock lock = new ReentrantLock();

        Friend(String name) {
            this.name = name;
        }

        public synchronized void bow(Friend bower) {
            if (isImpending(bower)) {
                try {
                    System.out.format("%s: %s has bowed to me!%n", this.name, bower.name);
                    bower.bowBack(this);
                } finally {
                    lock.unlock();
                    bower.lock.unlock();
                }
            }
        }

        private boolean isImpending(Friend bower) {
            boolean myLock = false;
            boolean yourLock = false;

            try {
                myLock = lock.tryLock();
                yourLock = bower.lock.tryLock();
            } finally {
                if (!myLock || !yourLock) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        bower.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        private synchronized void bowBack(Friend friend) {
            System.out.format("%s: %s has bowed back to me!%n", name, friend.name);
        }
    }

    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}
