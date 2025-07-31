package multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {
    public static void main(String[] args) {

       Long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("time started: " + System.currentTimeMillis());
//        for (int i = 0; i < numThreads; i++) {
//            final int start = i * chunkSize;
//            final int end = (i == numThreads - 1) ? totalPrints : start + chunkSize;
//
//            executor.submit(() -> {
//                for (int j = start; j < end; j++) {
//                    System.out.println("Hello World " + j);
//                }
//            });
//        }
        Long endTime = System.currentTimeMillis();
        System.out.println("total time taken: " + (endTime - startTime) + " ms");
    }
}
