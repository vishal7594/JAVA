package dsa;

import java.util.Queue;
import java.util.Stack;

public class Array2D {

    public static int[][] arr = {
            {1, 4, 0, 3},
            {6, 7, 2 , 9},
            {5, 2, 0, 6}
    };

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        String s1 = "vivek";
        s1 = "gaurav";
        String s3 = "vivek";
        String s2 = new String("vivek");

        Integer t1 = 12;
        Integer t2 = Integer.valueOf(12);

//        System.out.println(t1 == t2);
        System.out.println(s2 == "vivek");
//        System.out.println(s1 == s3);


//        print();
//        removeMaxFromAllRow();
//        findMinSum();
    }

    public  static  void  findMinSum()
    {
        int row = arr.length;
        int col = arr[0].length;
        for (int i = 1; i < col ; i++) {
            arr[0][i] = +arr[0][i] +  arr[0][i-1];
        }
        for (int i = 1; i < row ; i++) {
            arr[i][0] =  arr[i][0] +arr[i-1][0];
        }

        print();
        System.out.println();

        for (int i = 1; i <row ; i++) {
            for (int j = 1; j < col; j++) {
                arr[i][j] = Math.min(arr[i-1][j],arr[i][j-1]) + arr[i][j];
                print();

                System.out.println();
            }
        }

//        System.out.println("answer " + arr[row-1][col-1]);

    }

    public  static  void  print()
    {
        int row = arr.length;
        int col = arr[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

//        for (int i = row-1; i >=0; i--) {
//            for (int j = col-1; j >=0; j--) {
//                System.out.print(arr[i][j] + " ");
//            }
//        }

//        for (int i = 0; i < col; i++) {
//            for (int j = 0; j <row; j++) {
//                System.out.print(arr[j][i] + " ");
//            }
//        }

    }

    public  static  void  removeMaxFromAllRow()
    {
        int row = arr.length;
        int col = arr[0].length;

        for (int i = 0; i < row; i++) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            for (int j = 0; j <col; j++) {

                if(arr[i][j] > max)
                {
                    max = arr[i][j];
                    index = j;
                }
            }

            int maxIndex = index;
            while (maxIndex+1 < col)
            {
                arr[i][maxIndex] = arr[i][maxIndex+1];
                maxIndex++;
            }

        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j <col-1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
