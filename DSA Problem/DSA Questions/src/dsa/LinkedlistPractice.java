package dsa;

public class LinkedlistPractice {

    public static void main(String[] args) {
        LinkedListModel l1 = new LinkedListModel();
        LinkedListModel l2 = new LinkedListModel();

        l1.insert(10);
        l1.insert(20);
        l1.insert(30);

        l2.insert(15);
        l2.insert(25);
        l2.insert(35);

//        l1.print();

       Node finalNode =  l1.MergedTwoList(l1.head,l2.head);

       l1.print(finalNode);


//        CircularLinklistModel cList = new CircularLinklistModel();

//        int[] arr = {10,10,20,30,30,30,40,40};
//        int[] arr = {10,20,30,40,50,60};
//        for (int i = 0; i < arr.length; i++) {
//            listModel.insert(arr[i]);
//            cList.insert(arr[i]);
//        }
//        cList.print();
//        cList.isCircular(listModel.head);

//        listModel.removeDuplicate();

//        listModel.print();
    }
}

class  CircularLinklistModel
{
    Node head = null;

    public  void  print()
    {
        Node current = head;
        while (current.next != head)
        {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }


    public  boolean isCircular(Node head)
    {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                System.out.println("circular");
                return true;
            }
        }
        System.out.println("no circular");
        return false;
    }

    public  void  insert(int value)
    {
        Node node = new Node(value);
        if(head == null)
        {
            head = node;
            head.next = node;
        }else
        {
            Node current = head;
            while (current.next != head)
            {
                current = current.next;
            }

            current.next = node;
            node.next = head;

        }
    }
}

class  LinkedListModel
{
    Node head,tail;

    public  void  checkPalindrome(Node head)
    {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverseList(slow);
        Node firstHalf = head;

        while (secondHalf != null)
        {
            if(firstHalf.data != secondHalf.data)
            {
                System.out.println("not palindrome");
                return;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        System.out.println("palindrome");



    }

    public Node removeElements(Node head, int val) {
        if(head == null) return  null;

        head.next = removeElements(head.next,val);

        if(head.data == val) return head.next;
        else return head;
    }


    public  Node reverseList(Node head)
    {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null)
        {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return  head;
    }


    public  Node  MergedTwoList(Node l1, Node l2)
    {
            Node dummy = new Node(-1);
            Node head1 = dummy;
            while (l1 != null && l2 != null)
            {
                if(l1.data < l2.data)
                {
                    head1.next = l1;
                    l1 = l1.next;
                }else
                {
                    head1.next = l2;
                    l2 = l2.next;
                }
                head1 = head1.next;
            }

            if(l1 != null) head1.next = l1;
            if(l2 != null) head1.next = l2;
            return  dummy.next;
    }

    public  void  removeDuplicate()
    {
        Node current = head;
        while (current != null && current.next != null)
        {
            if(current.data == current.next.data)
            {
                Node dlt = current.next;
                current.next =dlt.next;
                dlt.next = null;
            }else current  = current.next;
        }
    }

    public  void  print()
    {
        Node temp = head;
        while (temp != null)
        {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }


    public  void  print(Node head)
    {
        Node temp = head;
        while (temp != null)
        {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public void insert(int value)
    {
        Node node = new Node(value);
        if(head == null)
        {
            head = tail = node;
        }else
        {
            tail.next = node;
            tail = node;
        }
    }

    public  void  delete(int value)
    {
        Node temp = head;

        if(head.data == value)
        {
            if(head ==  tail) head = tail = null;
            else  head = head.next;
        }else System.out.println("not present");


        while (temp.next != null)
        {
            if(temp.next.data == value)
            {
                Node dlt = temp.next;
                temp.next = dlt.next;
                dlt.next = null;
                break;
            }
            temp = temp.next;
        }

    }





}

class  Node
{
     public Node(int data)
     {
         this.data = data;
     }
    public  int data;
    public  Node next;
}
