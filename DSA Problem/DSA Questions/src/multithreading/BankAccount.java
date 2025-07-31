package multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private final Lock lock = new ReentrantLock();

    private int balance = 100;

    public void withdraw(int amount) {
        System.out.println("thread name : " + Thread.currentThread().getName() + " is trying to withdraw " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {

                    try {
                        System.out.println("thread name : " + Thread.currentThread().getName() + " is withdrawing " + amount);
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println("thread name : " + Thread.currentThread().getName() + " has withdrawn " + amount);
                    } catch (Exception e) {
                       Thread.currentThread().interrupt();
                        System.out.println("thread name : " + Thread.currentThread().getName() + " was interrupted while withdrawing " + amount);
                    }finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println("thread name : " + Thread.currentThread().getName() + " insufficient balance to withdraw " + amount);
                }
            }else
            {
                System.out.println("thread name : " + Thread.currentThread().getName() + " could not acquire lock to withdraw " + amount);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("thread name : " + Thread.currentThread().getName() + " was interrupted while trying to withdraw " + amount);
        }
    }
}
