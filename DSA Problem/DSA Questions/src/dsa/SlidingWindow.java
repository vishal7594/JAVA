package dsa;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlidingWindow {
    public static void main(String[] args) {
//        findMaxSUbStringLength();
        Distinct();
    }
    // Find the maximum length of a substring with non repeating characters
    public static   void  findMaxSUbStringLength()
    {
        Set<Character> set = new HashSet<>();
        String s = "pwwqr";
        int max = 0;
        int left = 0, right = 0;

       while (right < s.length())
       {
           char ch = s.charAt(right);
          if(!set.contains(ch))
          {
              set.add(ch);
              max = Math.max(max, set.size());
              right++;
          }else
          {
              set.remove(s.charAt(left));
              left++;
          }
       }
        System.out.println("Max length of substring with non repeating characters: " + max + " for string: " + s);
    }

    public  static void  Distinct()
    {
        String s = "aacfssa";

        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("right: " + i + " left: " + left + " set: " + set);
            set.add(s.charAt(i));
            if(i >=2)
            {
                if(set.size() == 3) {
                    result++;
                    System.out.println(set);
                    while (set.size() ==3)
                    {
                        set.remove(s.charAt(left));
                        System.out.println("set after removing left: " + set);
                        left++;
                    }
                }
            }
        }
    }

}
