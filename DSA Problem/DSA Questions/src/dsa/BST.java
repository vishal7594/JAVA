package dsa;

public class BST {
    public static void main(String[] args) {

        TreeNode root = null;

        root = insert(root, 1);
        root = insert(root, 2);
        root = insert(root, 3);
        root = insert(root, 4);

        BinaryTree.levelOrderTraversal(root);
    }

    public static TreeNode insert(TreeNode root, int value) {
        if (root == null) return new TreeNode(value);

        if (value > root.data) {
            root.right = insert(root.right, value);
        } else root.left = insert(root.left, value);
        return root;
    }

    public static boolean search(TreeNode root, int target) {
        if (root == null) return false;
        if (root.data == target) return true;
        if (target > root.data) {
            return search(root.right, target);
        } else return search(root.left, target);
    }

    public static TreeNode delete(TreeNode root, int target) {
        if(root ==  null ) return null;

        if(target > root.data)
        {
            root.right = delete(root.right,target);
        }else if(target < root.data) root.left = delete(root.right,target);
        else if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
        else {

            root.data = findMin(root.right);
            root.right = delete(root.right,root.data);
        }
        return root;
    }

    public  static int findMin(TreeNode root)
    {
       TreeNode node = root;
       while (node.left != null)
       {
           node = node.left;
       }
       return node.data;
    }

}
