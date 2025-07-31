package dsa;

import org.w3c.dom.Node;

public class DoubleEndedQueue {
    public static void main(String[] args) {

        DoubleEndedQueue queue = new DoubleEndedQueue();
        queue.insertFirst(1);
        queue.insertLast(2);
        queue.insertFirst(3);
        queue.insertLast(4);
        queue.insertFirst(5);
        queue.deleteLast();
        queue.print();
    }

    int[] arr = new int[5];
    int front = -1;
    int rear = -1;
    int size = 0;


    public  void  print()
    {
        if (size == 0) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = front; i <= rear; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void  insertFirst(int value)
    {
        if(size == arr.length) {
            throw  new RuntimeException("full");
        }

        if(front == -1) {
            front=rear=0;
            arr[front] = value;
        } else if(front > 0)
        {
            front-=1;
            arr[front] = value;
        }else
        {
            int i = rear;
            while (i >= front)
            {
                arr[i+1] = arr[i];
                i--;
            }
            rear++;
            arr[front] = value;
        }
        size++;
    }

    public void  insertLast(int value)
    {
        if(size == arr.length)  throw  new RuntimeException("full");
        if(front == -1) {
            front=rear=0;
            arr[rear] = value;
        }else if(rear < arr.length-1)
        {
            rear +=1;
            arr[rear] = value;
        }else {
            int i = front;
            while (i<=rear)
            {
                arr[i-1] = arr[i];
                i--;
            }
            front--;
            arr[rear] = value;
        }
        size++;
    }

    public  void  deleteFirst()
    {
        if(size == 0)  throw  new RuntimeException("empty");;

        if(front == rear) front= rear =-1;
        else
        {
            front += 1;
        }
    }

    public  void  deleteLast()
    {
        if(size == 0)  throw  new RuntimeException("empty");;

        if(front == rear) front= rear =-1;
        else
        {
            rear -= 1;
        }
    }

}
