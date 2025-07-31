package multithreading;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int nuberOfServices = 3;
        ExecutorService executor = Executors.newFixedThreadPool(nuberOfServices);
//        CountDownLatch latch = new CountDownLatch(nuberOfServices);

        CyclicBarrier barrier = new CyclicBarrier(nuberOfServices);

        executor.submit(new DependentService(barrier));
        executor.submit(new DependentService(barrier));
        executor.submit(new DependentService(barrier));

//        latch.await();
        System.out.println("All dependent services have completed execution");
        executor.shutdown();


//        Future<String> submit0 = executor.submit(new DependentService());
//        Future<String> submit1 = executor.submit(new DependentService());
//        Future<String> submit2 = executor.submit(new DependentService());
//
//        submit0.get();
//        submit1.get();
//        submit2.get();
//
//        System.out.println("All tasks completed successfully");
//        executor.shutdown();

//        scheduler.schedule(()-> System.out.println("Task executed after 2 seconds"), 2, TimeUnit.SECONDS);

//        scheduler.scheduleAtFixedRate(()->
//        {
//            System.out.println("Task executed at fixed rate every 2 seconds");
//        },0, 2, TimeUnit.SECONDS);
//
//
//        scheduler.schedule(()->
//        {
//            System.out.println("shutdown scheduler");
//            scheduler.shutdown();
//        },20, TimeUnit.SECONDS);


//       ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//
//        Future<Integer> future = executorService.submit(() ->
//        {
//            Thread.sleep(2000);
//            return 42;
//        });
//
//        Integer i = null;
//        try {
//            i = future.get(1, TimeUnit.SECONDS); // This will block until the result is available or timeout occurs
//            System.out.println("Future is done: " + future.isDone());
//            System.out.println("Result from Future: " + i);
//        } catch (TimeoutException |InterruptedException  | ExecutionException e){
//            throw new RuntimeException(e);
//        }

    }
}

class DependentService implements Callable<String> {
    private final CyclicBarrier barrier;

    public DependentService(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception {

        System.out.println(Thread.currentThread().getName() + " is executing DependentService");
        Thread.sleep(2000);
        barrier.wait();
        return "Ok";
    }
}
