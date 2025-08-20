package dsa;

import java.util.Arrays;

public class ArrayProblem {
    public static void main(String[] args) {

        int[] arr = {0,0};
        System.out.println( pivot(arr));


//        int[] nums1 = {1,2,3,0,0,0};
//        int[] nums2 = {2,5,6};
//        merge(nums1,3,nums2,nums2.length);
    }
    
    public  static  int  pivot(int[] arr)
    {
        int[] preSum = new int[arr.length];
        int sum  = 0;
        for (int i = arr.length-1; i >=0; i--) {
            preSum[i] = sum;
            sum += arr[i];
        }

        System.out.println(Arrays.toString(preSum));
        sum = 0;

        for (int i = 0; i < arr.length ; i++) {
            if(preSum[i] == sum)
            {
                return  i;
            }
            sum += arr[i];
        }

        return -1;
        
    }
     


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = nums1.length - 1;
        int i = m - 1;
        int j = n - 1;

        while (i >= 0 || j >= 0) {
            if (j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        System.out.println("result " + Arrays.toString(nums1));
    }

//    public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        int a = 0;
//        int b = 0;
//        int count = 0;
//
//        int[] result = new int[m+n];
//        while (a < (m) && b < n)
//        {
//            if(nums1[a] < nums2[b])
//            {
//                result[count++] = nums1[a];
//                System.out.println("add  from a " + nums1[a]);
//                a++;
//            }else
//            {
//                result[count++] = nums2[b];
//                System.out.println("add from b " + nums1[a]);
//                b++;
//            }
//        }
//
//        while (a < (m))
//        {
//            result[count++] = nums1[a];
//            a++;
//        }
//
//        while (b < n)
//        {
//            System.out.println("b " + b);
//            System.out.println("n " + n);
//            result[count++] = nums2[b];
//            b++;
//        }
//
//
//        for (int i = 0; i < result.length; i++) {
//            nums1[i] = result[i];
//        }
//        System.out.println("result " + Arrays.toString(nums1));
//
//    }
}
