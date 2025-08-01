package coretopics;

public class MultiThreads {
    public static void main(String[] args) {

        PrintNumber pn = new PrintNumber();
        PrintOddEven t1 = new PrintOddEven(pn,true);
        t1.setName("odd");


        PrintOddEven t2 = new PrintOddEven(pn,false);
        t2.setName("even");

        t1.start();
        t2.start();
    }
}

class  PrintOddEven extends Thread
{
    PrintNumber printNumber;
    boolean isOdd;
    PrintOddEven(PrintNumber printNumber, boolean isOdd)
    {
        this.isOdd = isOdd;
        this.printNumber = printNumber;
    }
    @Override
    public void run() {

        for (int i = 1; i <= 20; i++) {
            if(i % 2 == 0 && !isOdd)
            {
                printNumber.printEven(i);

            }else if(isOdd && i%2 !=0)
            {
                printNumber.printOdd(i);
            }

//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                Thread.interrupted();
//            }
        }
    }
}

class  PrintNumber
{
    private volatile boolean isOdd= true;
    public synchronized void  printEven(int number)
    {
        while (isOdd)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("printEven " + Thread.currentThread().getName() + " " +number);
        isOdd = true;
        notify();

    }

    public synchronized void  printOdd(int number)
    {
        while (!isOdd)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("printOdd " + Thread.currentThread().getName() + " " +number);
        isOdd = false;
        notify();
    }
}
