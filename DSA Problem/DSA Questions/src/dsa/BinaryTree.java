package dsa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

//        root.left.left.right = new TreeNode(8);

//        levelOrderTraversal(root);
//        preorderRecursion(root);
//        inorder(root);
//        postorder(root);
        System.out.println( countNode(root));
    }

    public  static  void  preorderRecursion(TreeNode root)
    {
        if(root == null) return;;
        System.out.print(root.data + " ");
        preorderRecursion(root.left);
        preorderRecursion(root.right);

    }

    public  static  int countNode(TreeNode node)
    {
        if(node == null) return  0;
        return 1 + countNode(node.left) + countNode(node.right);
    }

    public  static  void  postorder(TreeNode root)
    {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.add(root);
        while (!s1.empty())
        {
            TreeNode pop = s1.pop();
            s2.add(pop);

            if(pop.left != null) s1.add(pop.left);
            if(pop.right != null) s1.add(pop.right);
        }

        while (!s2.empty()) System.out.print(s2.pop().data + " ");
    }

    public  static  void  preorder(TreeNode root)
    {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty())
        {
            TreeNode node = stack.pop();
            System.out.print(node.data +" ");
            if(node.right != null) stack.add(node.right);
            if(node.left != null) stack.add(node.left);
        }
    }

    public  static  void  inorder(TreeNode root)
    {
       Stack<TreeNode> stack = new Stack<>();
       TreeNode currentNode = root;
       while (!stack.isEmpty() || currentNode != null)
       {
           while (currentNode != null)
           {
               stack.add(currentNode);
               currentNode = currentNode.left;
           }
           TreeNode node = stack.pop();
           System.out.print(node.data + " ");
           currentNode = node.right;
       }
    }

    public static void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            TreeNode node = queue.poll();
            System.out.print(node.data +" ");
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
//            System.out.println(queue.stream().map(x -> x.data).toList());
        }
    }

}

class TreeNode {
    public int data;

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode left;
    public TreeNode right;
}
