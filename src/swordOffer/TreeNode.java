package swordOffer;

/**
 * 2024年8月24日11:14:59
 */
public class TreeNode{

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(){}

    public TreeNode(int value){
        this.val = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right){
        this.val = value;
        this.left = left;
        this.right = right;
    }

}