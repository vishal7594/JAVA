package dsa;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackProb {
    public static void main(String[] args) {
//        prenthesisbalance();
//        prefixToInfix();
        nextGreater();
    }

    public  static  int[] arr = {10,5,15,2,8,6,4};
    public  static  void  nextGreater()
    {
        Stack<Integer> stack = new Stack<>();

        Queue<Integer> queue = new LinkedList<>();

        int[] result = new int[arr.length];

        for (int i = arr.length-1; i >=0; i--) {
            if(stack.isEmpty()) {
                result[i] = -1;
                stack.push(arr[i]);
            } else
            {
                while (!stack.isEmpty() && arr[i] >= stack.peek())
                {
                    stack.pop();
                }
                result[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(arr[i]);
            }
        }

        System.out.println("result " + Arrays.toString(result));

    }

    public  static void prefixToInfix()
    {
        String s = "*+ab-cd";
        Stack<String> stack = new Stack();
        for (int i = s.length()-1; i>=0; i--) {
            Character ch = s.charAt(i);
            if(ch == '*' || ch == '/' || ch == '+' || ch == '-')
            {
                String s1 = "("+ stack.pop() + ch + stack.pop() + ")";
                stack.push(s1);
            }
            else
            {
                stack.push(ch.toString());
            }
        }

        System.out.println(stack.pop());

    }

    public  static void  prenthesisbalance()
    {
        String str = "[{}()][]";

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);

            if(ch == '[' || ch == '{' || ch == '(')
            {
                stack.push(ch);
            }else
            {
                Character preCh = stack.peek();
                if(ch == ']' && preCh == '[' || ch == '}' && preCh == '{' || ch == ')' && preCh == '(')
                {
                    stack.pop();
                }
            }
        }

        System.out.println("is balance " + stack.isEmpty());

    }
}
