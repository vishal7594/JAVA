package dsa;

import java.util.HashMap;

public class DSA {
    public static void main(String[] args) {
        HashMap<Character,Character> map1 = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();

        String s1 = "ab";
        String s2 = "cc";

        if(s1.length() != s2.length())
        {
            System.out.println("Not Isomorphic");
            return;
        }

        for (int i = 0; i < s1.length(); i++) {
            Character ch = s1.charAt(i);

            if(map1.containsKey(ch))
            {
                if(map1.get(ch) != s2.charAt(i))
                {
                    System.out.println("Not Isomorphic");
                    return;
                }
            }
            else
            {
                if(map2.containsKey(s2.charAt(i)))
                {
                    if(map2.get(s2.charAt(i)) != ch)
                    {
                        System.out.println("Not Isomorphic");
                        return;
                    }
                }
                map2.put(s2.charAt(i), ch);
                map1.put(ch, s2.charAt(i));
            }

        }
        System.out.println("Isomorphic");
    }
}
