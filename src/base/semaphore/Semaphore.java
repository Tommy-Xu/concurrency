package base.semaphore;

public class Semaphore {
    private boolean signal = false;

    public synchronized void acquire() throws InterruptedException {
        while (! signal){
            wait();
            signal = false;
        }
    }

    public synchronized void release(){
        signal = true;
        notify();
    }
}
