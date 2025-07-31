package dsa;

import java.util.List;

public class SubArray {
    public static void main(String[] args) {
        longestSubArray();
    }

    public static int longestSubArray() {
        int[] arr = {1,2};
        int currentLength = 1;
        int maxLength = 1;
        if (arr.length == 1) return 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                currentLength++;
                maxLength = Math.max(currentLength, maxLength);
            } else {
                maxLength = Math.max(currentLength, maxLength);
                currentLength = 1;
            }
        }

        currentLength = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                currentLength++;
                maxLength = Math.max(currentLength, maxLength);
            } else {

                maxLength = Math.max(currentLength, maxLength);
                currentLength = 1;
            }
        }
        System.out.println(maxLength);
        return maxLength;
    }
}
