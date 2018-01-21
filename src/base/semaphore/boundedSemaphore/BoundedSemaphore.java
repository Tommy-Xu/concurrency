package base.semaphore.boundedSemaphore;

public class BoundedSemaphore {
    private int upperBounds = 0;
    private int signals = 0;

    public BoundedSemaphore(int upperBounds) {
        this.upperBounds = upperBounds;
    }

    public synchronized void acquire() throws InterruptedException {
        while (signals==0){
            wait();
        }
        signals--;
        notifyAll();
    }

    public synchronized void release() throws InterruptedException {
        while (signals == upperBounds){
            wait();
        }
        signals++;
        notifyAll();
    }
}
