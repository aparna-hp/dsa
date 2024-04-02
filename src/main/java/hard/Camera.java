package hard;

public class Camera {

    /**
     * Definition for a binary tree node.
     * https://leetcode.com/problems/binary-tree-cameras/
     */
    public static class TreeNode {
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

    public static void main(String[] args){
        Camera camera = new Camera();
        //[0,null,0,null,0,null,0]
        //[0,0,null,0,0]
        TreeNode root = new TreeNode(0, null, new TreeNode(0,null, new TreeNode(0, null, new TreeNode(0))));
        //TreeNode root = new TreeNode(0, new TreeNode(0, new TreeNode(0),  new TreeNode(0)), null);
        System.out.println(camera.minCameraCover(root));
    }

    int count = 0;

    public int minCameraCover(TreeNode root) {
        if(null == root){
            return 0;
        }

        if(null == root.left && null == root.right){
            return 1;
        }

        int camera = compute(root);
        if(camera == -1){
            count++;
        }
        return count;
    }

    public int compute(TreeNode node) {
        //Post order traversal
        // Install Camera -> 1
        // Covered --> 0
        //Need Camera --> (-1)

        if(null == node){
            return 0;
        }

        if(null == node.left && null == node.right){
            //Leaf node always need camera
            return -1;
        }

        int left = compute(node.left);
        int right = compute(node.right);

        if(left == -1 || right == -1){
            //If left or right node require camera, install camera
            count++;
            return 1;
        }

        if(left == 1 || right == 1){
            //If left or right node has camera, node is covered.
            return 0;
        }

        //Require need camera if camera not installed at children or at node.
        return -1;
    }


    /**
     * Original idea. Doesn't work for few use cases.
     * @param root
     * @return
     */
    public int minCameraCover2(TreeNode root) {
        if (null == root) {
            return 0;
        }

        if (null == root.left && null == root.right) {
            return 1;
        }
        boolean[] cameraAtRoot = new boolean[2];
        cameraAtRoot[0] = true;
        int a = place(root, cameraAtRoot);
        cameraAtRoot[1] = true;
        int b = 1 + place(root, cameraAtRoot);

        return Math.min(a, b);
    }

    public int place(TreeNode node, boolean[] camera) {

        if (null == node) {
            return 0;
        }

        boolean[] newCamera = new boolean[2];
        newCamera[0] = camera[1];

        if (camera[1]) {
            newCamera[1] = false;
            System.out.println("No 1");
            return place(node.left, newCamera) + place(node.right, newCamera);
        }

        if (camera[0] && (null != node.left || null != node.right)) {
            newCamera[1] = false;
            int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
            System.out.println("No 2");
            a = place(node.left, newCamera) + place(node.right, newCamera);
            newCamera[1] = true;
            System.out.println("Yes 1 ");
            b = 1 + place(node.left, newCamera) + place(node.right, newCamera);
            System.out.println("Min of a, b = " + Math.min(a,b));
            return Math.min(a, b);
        }

        newCamera[1] = true;
        System.out.println("Yes 2 ");
        return 1 + place(node.left, newCamera) + place(node.right, newCamera);
    }


}
