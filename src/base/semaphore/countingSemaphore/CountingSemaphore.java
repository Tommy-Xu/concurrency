package base.semaphore.countingSemaphore;

public class CountingSemaphore {
    private int signals = 0;

    public synchronized void acquire() throws InterruptedException {
        while (signals==0){
            wait();
        }
        signals--;
    }

    public synchronized void release(){
        signals++;
        notify();
    }
}
