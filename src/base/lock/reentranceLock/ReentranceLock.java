package base.lock.reentranceLock;

public class ReentranceLock {
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (isLocked && lockedBy!=currentThread){
            wait();
        }
        isLocked = true;
        lockCount++;
        lockedBy = currentThread;
    }

    public synchronized void unLock(){
        if(Thread.currentThread() == lockedBy){
            lockCount--;
            if(lockCount == 0){
                isLocked = false;
                notify();
            }
        }
    }
}
