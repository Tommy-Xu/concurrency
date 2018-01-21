package base.lock.FairLock;

public class QueueObject {
    private boolean isNotified = false;

    public synchronized void doWait() throws InterruptedException {
        while (! isNotified){
            this.wait();
        }
        isNotified = false;
    }

    public synchronized void doNotify() {
        isNotified = true;
        this.notify();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
