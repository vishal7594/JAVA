package hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapProblem {

    public void twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int t = target - arr[i];
            if (hashMap.containsKey(t)) {
                System.out.println("find pair " + t + " || " + arr[i]);
                System.out.println("find pair " + hashMap.get(t) + " || " + i);
                return;
            } else {
                hashMap.put(arr[i], i);
            }
        }

        System.out.println("no pair");
    }

    public void firstNonRepeatCharacter(String str) {
        // geeksforgeeks

        HashMap<Character, Integer> hashMap = new HashMap<>();

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            hashMap.put(chars[i], hashMap.getOrDefault(chars[i], 0) + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (hashMap.get(chars[i]) == 1) {
                System.out.println("find " + chars[i]);
                return;
            }
        }

//        for (Map.Entry entry : hashMap.entrySet())
//        {
//            System.out.println("key " + entry.getKey() +" value "+ entry.getValue());
//        }
    }

}














































