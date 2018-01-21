package base.lock.FairLock;

import java.util.ArrayList;
import java.util.List;

public class FairLock {
    private boolean isLockeed = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }
        while (isLockedForThisThread){
            synchronized (this) {
                isLockedForThisThread = isLockeed || waitingThreads.get(0)!=queueObject;
                if(! isLockedForThisThread) {
                    isLockeed = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    return;
                }
            }

            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                    throw e;
                }
            }
        }
    }

    public synchronized void unlock() {
        if(lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLockeed = false;
        lockingThread = null;
        if(waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
    }
}
