package dsa;

import java.util.Arrays;

public class ArrayProblem {
    public static void main(String[] args) {

        int[] arr = {0,0};
//        System.out.println( pivot(arr));

        System.out.println(findPages(new int[]{15 ,10, 19 ,10, 5,18 ,7},5));


//        int[] nums1 = {1,2,3,0,0,0};
//        int[] nums2 = {2,5,6};
//        merge(nums1,3,nums2,nums2.length);
    }

public static int findPages(int arr[], int k) {
    // code here
    System.out.println(Arrays.toString(arr));

    int n = arr.length;
    if(n < k) return -1;
    int sum = 0;
    for (int i=0;i < n; i++)
    {
        sum += arr[i];
    }


    if(k == 1) return sum;
    int currentSum = 0;
    int minMaxPages = Integer.MAX_VALUE;
    for (int i = 0; i < n-1; i++) {
        currentSum += arr[i];

        if(i % k == 0)
        {

        }

        int maxPages = Math.max(currentSum,sum-currentSum);

        System.out.println("maxPages " + maxPages);

        minMaxPages = Math.min(maxPages, minMaxPages);
    }

    return minMaxPages;

}

    public int[] findMissingAndRepeatedValues(int[][] grid) {

        int[] result = new int[2];
        int n = grid.length * grid.length;

        int[] fixed = new int[n];

        int sum  = n*(n+1)/2;
        System.out.println("sum " + sum);

        int currentSum = 0;

        for (int i = 0; i < grid.length ; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if(fixed[grid[i][j] -1] != 0)
                {
                    result[0] = grid[i][j];
                }else
                {
                    fixed[grid[i][j] -1 ] = 1;
                    currentSum += grid[i][j];
                }
            }
        }
        System.out.println("current sum " + currentSum);
        result[1] = sum -currentSum;

       return result;
    }


    public  static  int maxProd(int[] arr)
    {
        int result = Integer.MIN_VALUE;
        int currentProd = 1;
        for (int i = 0; i < arr.length; i++) {

            currentProd = currentProd*arr[i];

            if(i == arr.length -1 && arr[i] > result)
            {
                result = Math.max(currentProd,arr[i]);
                return  result;
            }
            result = Math.max(result,currentProd);
            if(currentProd == 0)
            {
                currentProd = 1;
            }
        }
        return result;
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
