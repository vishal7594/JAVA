package dsa;

public class Recursion {
    public static void main(String[] args) {
        System.out.println("fact 5 " + numberCount(1));
//        System.out.println("sum 5 " + product(5,3));
    }

    public static int factorial(int v)
    {
        if(v == 0 || v == 1) return 1;
        return  v * factorial(v-1);
    }

    public  static  int sum(int n)
    {
        if (n == 0) return 0;  // base case
        return n + sum(n - 1); // recursive call
    }

    public  static  int product(int n1, int n2)
    {
       if(n2 == 0) return 0;
       return  n1 + product(n1,n2-1);
    }

    public  static  int getMax(int[] arr , int n)
    {
        if(n == 1) return  arr[0];
        return Math.max(arr[n-1],getMax(arr,n-1));
    }

    public  static  int numberCount(int value)
    {
        if(value == 0) return 0;
        return value % 10 + numberCount(value/10);
    }

    public  int getFibonnaci(int n) {
        if(n == 1) return  0;
        if(n == 2) return  1;
        return getFibonnaci(n-1) + getFibonnaci(n-2);
    }

}
