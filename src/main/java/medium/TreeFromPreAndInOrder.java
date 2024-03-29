package medium;

import java.util.*;

public class TreeFromPreAndInOrder {

    /**
     * Definition for a binary tree node.
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/submissions/1217500594/
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    int n = 0;
    Map<Integer, Integer> pos = new HashMap<>();
    public static void main(String[] args){
        int[] preorder = {1,2};
        int[] inorder = {1,2};

        TreeFromPreAndInOrder tree = new TreeFromPreAndInOrder();
        tree.buildTree(preorder, inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        n = preorder.length;

        for(int i=0; i<n; i++){
            pos.put(inorder[i], i);
        }

        return build(preorder, inorder, 0, n-1, 0, n-1);
    }

    public TreeNode build(int[] preOrder, int[] inOrder, int preOrderLow, int preOrderHigh, int low, int high){
        if(low > high || preOrderLow > preOrderHigh){
            System.out.println("Invalid");
            return null;
        }

        int rootVal = preOrder[preOrderLow];
        TreeNode node = new TreeNode(rootVal);
        if(low == high){
            return node;
        }

        int rootIndexInOrder = pos.get(rootVal);
        int leftNodeSize = rootIndexInOrder - low;
        int rightNodeSize = high - rootIndexInOrder;
        System.out.println("LeftNode size =" + leftNodeSize + " rightNodeSize=" + rightNodeSize);

        if(leftNodeSize > 0) {
            System.out.println("Left Preorder low =" + (preOrderLow+1) + " high=" + (preOrderLow+leftNodeSize));
            System.out.println("Left Inorder low = " + low + " high "+ (rootIndexInOrder-1));
            node.left = build(preOrder, inOrder, preOrderLow+1, preOrderLow +leftNodeSize, low, rootIndexInOrder-1 );
        }

        if(rightNodeSize > 0) {
            System.out.println("Right Preorder low =" + (preOrderLow+leftNodeSize+1) + " high=" + preOrderHigh);
            System.out.println("Right Inorder low = " + (rootIndexInOrder+1) + " high "+ high);
            node.right = build(preOrder, inOrder, preOrderLow+leftNodeSize+1, preOrderHigh, rootIndexInOrder+1, high);
        }
        return node;
    }

}
