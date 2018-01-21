package base.semaphore;

public class SendingThread extends Thread{
    Semaphore semaphore = null;

    public SendingThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while(true){
            //do something, then signal
            semaphore.release();
        }
    }
}
