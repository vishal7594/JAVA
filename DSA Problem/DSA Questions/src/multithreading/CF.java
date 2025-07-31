package multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CF {
    public static void main(String[] args) {


        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println("Task 1 is running in " + Thread.currentThread().getName());
            return "Result of Task 1";
        });
//        try {
//            cf1.get();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("Task 1 is submitted in " + Thread.currentThread().getName());

    }
}
