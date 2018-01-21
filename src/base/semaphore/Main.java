package base.semaphore;

import base.semaphore.boundedSemaphore.BoundedSemaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();
        SendingThread sender = new SendingThread(semaphore);
        RecevingThread receiver = new RecevingThread(semaphore);

        receiver.start();;
        sender.start();
    }

    public void useAsLock() throws InterruptedException {
        BoundedSemaphore semaphore = new BoundedSemaphore(1);

        semaphore.acquire();
        try{
            //critical section
        } finally {
            semaphore.release();
        }
    }
}
