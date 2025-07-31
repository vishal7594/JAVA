package dsa;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Binary {

    public static int[] arr = {2,4,5,8,10,12};

    public  static int target = 12;
    public  static int ins = 1;
    public  static int delete = 2;


    public static void main(String[] args) {
//        delete();
        update();
//        search();
    }

    public  static void  update()
    {
        delete();
        insert();
    }

    public  static void  delete()
    {
        int left = 0, right = arr.length-1;

        while (left <= right)
        {
            int mid = (left+right)/2;

            if(arr[mid] == delete)
            {
                System.out.println("find " + delete);

                int index = mid;
                while (index+1 < arr.length)
                {
                    arr[index] = arr[index+1];
                    index++;
                }
                arr = Arrays.copyOf(arr,arr.length-1);
                System.out.println("NEW ARRAY " + Arrays.toString(arr));

                return;
            }else if(arr[mid] < delete)
            {
                left = mid+1;
            }else right = mid-1;
        }

        System.out.println("not found");
    }


    public  static void  insert()
    {
        int left = 0, right = arr.length-1;

        while (left <= right)
        {
            System.out.println("left " + left + " right " + right);
            int mid = (left+right)/2;
            if(arr[mid] <= ins)
            {
                left = mid+1;
            }else right = mid-1;
        }

        arr = Arrays.copyOf(arr,arr.length+1);
//        System.out.println("new size " + arr.length + " left " + left);

        int start = arr.length-1;
        while (left < start)
        {
            arr[start] = arr[start-1];
            start--;
        }

        arr[left] = ins;

        System.out.println("final arry " + Arrays.toString(arr));
    }

    public  static  void  search()
    {
        int left = 0, right = arr.length-1;

        while (left <= right)
        {
            int mid = (left+right)/2;

            if(arr[mid] == target)
            {
                System.out.println("find " + target);
                return;
            }else if(arr[mid] < target)
            {
                left = mid+1;
            }else right = mid-1;
        }

        System.out.println("not found");

    }
}
