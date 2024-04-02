package medium;

import java.util.*;

public class TreeKDistanceFromTarget {


    /**
     * Definition for a binary tree node.
     * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        if (root == null) {
            return new ArrayList<>();
        }

        Queue<TreeParent> queue = new LinkedList<>();
        queue.add(new TreeParent(root));
        TreeParent targetParent = null;
        while (!queue.isEmpty() && targetParent == null) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeParent curr = queue.poll();
                if (curr.getNode().val == target.val) {
                    //System.out.println("Found target " + curr.getParents().size());
                    targetParent = curr;
                    break;
                }

                if (null != curr.getNode().left) {
                    TreeParent left = new TreeParent(curr.getNode().left);
                    left.getParents().addAll(curr.getParents());
                    left.getParents().add(curr.getNode());
                    queue.add(left);
                }

                if (null != curr.getNode().right) {
                    TreeParent right = new TreeParent(curr.getNode().right);
                    right.getParents().addAll(curr.getParents());
                    right.getParents().add(curr.getNode());
                    queue.add(right);
                }
            }
        }

        if (targetParent == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();

        if (k == 0) {
            ans.add(target.val);
            return ans;
        }

        //Add children below target with K distance away
        Set<Integer> result = new HashSet<>();
        List<Integer> excludeList = new ArrayList<>();
        excludeList.add(target.val);
        addChildren(result, target, k, excludeList);

        //Print all the elements away from target up wards
        int dept = targetParent.getParents().size();
        for (int i = dept - 1; i >= 0; i--) {
            TreeNode parent = targetParent.getParents().get(i);
            excludeList.add(parent.val);
            int distance = k - dept + i;
            if (distance == 0) {
                result.add(parent.val);
                continue;
            }
            if (distance < 0) {
                continue;
            }
            addChildren(result, parent, distance, excludeList);
        }

        ans.addAll(result);
        return ans;
    }

    public void addChildren(Set<Integer> result, TreeNode node, int dist, List<Integer> excludeList) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(node);
        int level = 0;
        while (!q.isEmpty() && level < dist) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.val != node.val && excludeList.contains(curr.val)) {
                    continue;
                }
                if (curr.left != null) {
                    q.add(curr.left);
                }

                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            level++;
        }

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (!excludeList.contains(curr.val)) {
                result.add(curr.val);
            }
        }
    }

    public static class TreeParent {
        TreeNode node;
        List<TreeNode> parents;

        public TreeParent(TreeNode node) {
            this.node = node;
            parents = new ArrayList<>();
        }

        public List<TreeNode> getParents() {
            return this.parents;
        }

        public TreeNode getNode() {
            return this.node;
        }
    }
}
