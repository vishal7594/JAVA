package multithreading;

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread producerThread = new Thread(new Producer(sharedResource), "Producer");
        Thread consumerThread = new Thread(new Consumer(sharedResource), "Consumer");

        producerThread.start();
        consumerThread.start();
        System.out.println(producerThread.getState());


//        try {
//            producerThread.join();
//            consumerThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}

class  SharedResource {
    private int data;

    private  boolean hasData = false;

    public synchronized void  produce(int value)
    {
        while (hasData)
        {
            try {
                wait();
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
        }
        System.out.println("Produced: " + value + " by " + Thread.currentThread().getName());
        data = value;
        hasData = true;
        notify();
    }

    public synchronized int consume()
    {
        while (!hasData)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        System.out.println("Consumed: " + data + " by " + Thread.currentThread().getName());
        return  data;
    }
}

class  Producer implements Runnable
{
    private  SharedResource sharedResource;

    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {

        for (int i= 0; i< 10; i++)
        {
            sharedResource.produce(i);
        }

    }
}

class  Consumer implements Runnable
{
    private  SharedResource sharedResource;

    public Consumer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {

        for (int i= 0; i< 10; i++)
        {
            sharedResource.consume();
        }

    }
}
