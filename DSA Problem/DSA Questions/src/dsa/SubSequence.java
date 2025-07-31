package dsa;

public class SubSequence {
    public static void main(String[] args) {
        SubSequence ss = new SubSequence();
        ss.FindSubSequence("abcde",new String[]{"a","bb" , "acd","ace"});
    }

    public   void  FindSubSequence(String str, String[] abc)
    {
        int count = 0;
        for (int i = 0; i<abc.length;i++)
        {
            if(isSubSequence(str,abc[i]))
            {
                count++;
            }
        }

        System.out.println("final count " + count);
    }

    private  boolean isSubSequence(String s1, String s2)
    {
        if(s1.length()<s2.length()) return false;
        int a = 0;
        int b = 0;


        while (a < s1.length() && b <s2.length())
        {
            if(s1.charAt(a) == s2.charAt(b))
            {
               b++;
            }
            a++;
        }

        return b == s2.length();
    }

}
