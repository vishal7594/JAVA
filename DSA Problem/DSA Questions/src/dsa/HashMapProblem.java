package dsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HashMapProblem {

    public static void main(String[] args) {
            HashMapProblem hashMapProblem = new HashMapProblem();
//            hashMapProblem.printDuplicate();
//            hashMapProblem.mostFrequent();
            hashMapProblem.isArraysAreSame();
    }

    public  void  mostFrequent()
    {
        int[] arr = {1,2,5,3,2,5,6,3,3,3};
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int maxCount = 0;
        int maxElement = 0;
        for (int i = 0; i < arr.length; i++) {

           hashMap.put(arr[i],hashMap.getOrDefault(arr[i],0) +1);
           int count = hashMap.get(arr[i]);
           if(count > maxCount)
           {
               maxElement = arr[i];
               maxCount = count;
           }
        }
        System.out.println("max count " + maxCount + " max elements " + maxElement);
    }

    public  void  isArraysAreSame()
    {
        int[] a1 = {1,2,3,6,5,6};
        int[] a2 = {6,6,2,1,3,4};

        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < a1.length; i++) {
            hashMap.put(a1[i],hashMap.getOrDefault(a1[i],0) +1);
        }

        for (int i = 0; i < a2.length; i++) {
            int count = hashMap.getOrDefault(a2[i],0);
            if(count == 0)
            {
                System.out.println("not same");
              return;
            }else
            {
                hashMap.put(a2[i],hashMap.get(a2[i]) -1);
            }
        }

        System.out.println("same");

    }

    public  void  printDuplicate()
    {
        int[] arr = {1,2,5,3,2,5,6,3,3,3};
        Set<Integer> set = new HashSet<>();
        Set<Integer> dupSet = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if(!set.contains(arr[i]))
            {
                set.add(arr[i]);
            }else  dupSet.add(arr[i]);
        }

        for (int i : dupSet) System.out.print(i + " ");


    }

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














































