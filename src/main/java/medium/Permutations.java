package medium;

import java.util.*;

public class Permutations {

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {1,2,3};
        permutations.permute(nums);
    }

    public void permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        getPermutations(nums,  new ArrayList<>(), visited, ans);
        ans.forEach(list -> System.out.println(Arrays.toString(list.toArray())));
    }

    public void getPermutations(int[] nums, List<Integer> curr, boolean[] visited, List<List<Integer>> ans) {
        if(curr.size() == nums.length){
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int i=0; i<nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curr.add(nums[i]);
                getPermutations(nums, curr, visited, ans);
                curr.remove(curr.size()-1);
                visited[i] = false;
            }
        }
    }
}
