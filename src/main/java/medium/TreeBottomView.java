package medium;//{ Driver Code Starts
//Initial Template for Java


//Contributed by Sudarshan Sharma
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

class TreeBottomView {

    public static void main(String[] args) throws IOException {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.right = new TreeNode(2);

        TreeBottomView treeBottomView = new TreeBottomView();
        ArrayList<Integer> result = treeBottomView.bottomView(node);
        System.out.println(Arrays.toString(result.toArray()));
    }


    //Function to return a list containing the bottom view of the given tree.
    public ArrayList<Integer> bottomView(TreeNode root) {
        Map<Integer, Integer> bottomMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            bottomMap.put(pair.val, pair.node.val);

            if (pair.node.left != null) {
                queue.add(new Pair(pair.node.left, pair.val - 1));
            }

            if (pair.node.right != null) {
                queue.add(new Pair(pair.node.right, pair.val + 1));
            }
        }

        return new ArrayList<>(bottomMap.values());
    }


    public class Pair {
        TreeNode node;
        int val;

        public Pair(TreeNode node, int val) {
            this.node = node;
            this.val = val;
        }
    }
}


