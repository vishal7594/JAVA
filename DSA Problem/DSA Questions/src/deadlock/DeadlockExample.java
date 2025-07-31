package deadlock;

public class DeadlockExample {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1(pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task2(pen, paper), "Thread-2");

        thread1.start();

        thread2.start();

//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
 class  Pen
{
    public synchronized void  writeWithPenAndPaper(Paper paper)
    {
        System.out.println(Thread.currentThread().getName() + " is writing with pen");
        paper.finishWriting();
    }

    public  synchronized void finishWriting()
    {
        System.out.println(Thread.currentThread().getName() + " is finishing writing with pen");
    }
}

class Paper
{
    public synchronized void  writeWithPaperAndPen(Pen pen)
    {
        System.out.println(Thread.currentThread().getName() + " is writing with paper");
        pen.finishWriting();
    }

    public  synchronized void finishWriting()
    {
        System.out.println(Thread.currentThread().getName() + " is finishing writing with paper");
    }
}

class  Task1 implements Runnable
{
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}

class  Task2 implements Runnable
{
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {

        synchronized (pen)
        {
            paper.writeWithPaperAndPen(pen);
        }
    }
}
