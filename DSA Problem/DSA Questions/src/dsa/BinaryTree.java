package dsa;

import java.util.*;

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
//        zigzag(root);
//        System.out.println(countNode(root));

        TreeNode rootNode = insertLevelOrder(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},0);

        levelOrderTraversal(rootNode);

    }

    public List<Double> averageOfLevels(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<Double> result  = new ArrayList<>();

        while (!queue.isEmpty())
        {
            int size  = queue.size();
            int sum = 0 ;
            while (size > 0)
            {
                TreeNode poll = queue.poll();
                if(poll.left != null) queue.add(poll.left);
                if(poll.right != null) queue.add(poll.right);
                sum += poll.data;

            }
            result.add((double) (sum / size));
            size--;
        }
        return result;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return null;

        if(root1 == null) return root2;
        if(root2 == null) return  root1;

        TreeNode node = new TreeNode(root1.data + root2.data);

        node.left = mergeTrees(root1.left,root2.left);
        node.right = mergeTrees(root1.right,root2.right);
        return  node;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {

        if(root == null) return 0;
        int sum = 0;
        sum = root.data;
        if(root.data <= high && root.data >= low)
        {
            return  sum + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }else if(root.data < low)
        {
            return rangeSumBST(root.right , low,high);
        }else  return  rangeSumBST(root.left,low,high);
    }

    public static TreeNode insertLevelOrder(int[] arr, int i) {
        TreeNode root = null;
        if (i < arr.length) {
            root = new TreeNode(arr[i]);
            root.left = insertLevelOrder(arr, 2 * i + 1);
            root.right = insertLevelOrder(arr, 2 * i + 2);
        }
        return root;
    }


    public static TreeNode insertLevelOrder() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int i = 0;
        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(arr[i++]);
        queue.add(root);

        while (i < arr.length && !queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (i < arr.length) {
                    poll.left = new TreeNode(arr[i++]);
                    queue.add(poll.left);
                }
                if (i < arr.length) {
                    poll.right = new TreeNode(arr[i++]);
                    queue.add(poll.right);
                }
                size--;
            }
        }

        return root;

    }

    public static void zigzag(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        boolean fromLeft = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            if (fromLeft) {
                while (size > 0) {
                    TreeNode poll = deque.pollFirst();
                    System.out.print(poll.data + " ");
                    if (poll.left != null) deque.addLast(poll.left);
                    if (poll.right != null) deque.addLast(poll.right);
                    size--;
                }
                fromLeft = !fromLeft;

            } else {
                while (size > 0) {
                    TreeNode poll = deque.pollLast();
                    System.out.print(poll.data + " ");
                    if (poll.right != null) deque.addFirst(poll.right);
                    if (poll.left != null) deque.addFirst(poll.left);
                    size--;
                }
                fromLeft = !fromLeft;
            }
        }

    }


    public static void preorderRecursion(TreeNode root) {
        if (root == null) return;
        ;
        System.out.print(root.data + " ");
        preorderRecursion(root.left);
        preorderRecursion(root.right);

    }

    public static int countNode(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNode(node.left) + countNode(node.right);
    }

    public static void postorder(TreeNode root) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.add(root);
        while (!s1.empty()) {
            TreeNode pop = s1.pop();
            s2.add(pop);

            if (pop.left != null) s1.add(pop.left);
            if (pop.right != null) s1.add(pop.right);
        }

        while (!s2.empty()) System.out.print(s2.pop().data + " ");
    }

    public static void preorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != null) stack.add(node.right);
            if (node.left != null) stack.add(node.left);
        }
    }

    public static void inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        while (!stack.isEmpty() || currentNode != null) {
            while (currentNode != null) {
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
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
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
