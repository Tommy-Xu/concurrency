package base.semaphore;

public class RecevingThread extends Thread{
    Semaphore semaphore = null;

    public RecevingThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true){
            try {
                semaphore.acquire();
                //receive signal, then do something
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
