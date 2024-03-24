package medium;

import java.util.Arrays;
import java.util.List;

public class LIS {

    int[][] dp;
    public static void main(String[] args){
        int[] nums = {4,10,4,3,8,9};
        LIS lis = new LIS();
        System.out.println(lis.lengthOfLIS(nums));

        System.out.println("Approach 2 :: " + lis.getLISApp2(nums));

        System.out.println("Approach List :: " + lis.lis(List.of(85, 80, 27)));
    }

    public int lengthOfLIS(int[] nums) {
        if(nums.length <= 1) {
            return nums.length;
        }

        dp = new int[nums.length][nums.length+1];
        for(int i=0; i<nums.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return getLIS(nums, 0, -1);
    }

    public int getLIS(int[] nums, int index, int prev) {
        if(index > nums.length-1) {
            return 0;
        }

       if(dp[index][prev+1] != -1) {
            return dp[index][prev+1];
       }

        int a=0, b=0;

        if(prev==-1 || nums[index] > nums[prev]) {
            a = 1 + getLIS(nums, index+1, index);
        }

        b += getLIS(nums, index+1, prev);

        dp[index][prev+1] = Math.max(a,b);
        return Math.max(a,b);
    }

    public int getLISApp2(int[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int max = 1;

        for(int i=1; i<arr.length; i++) {
            for(int j=0; j<=i-1; j++){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], 1+ dp[j]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }


    public int lis(final List<Integer> A) {

        dp = new int[A.size()][A.size()+1];
        for(int i=0; i<A.size(); i++){
            Arrays.fill(dp[i], -1);
        }

        if(A.size() < 2){
            return A.size();
        }

        return compute(A, 0, -1);
    }

    public int compute(List<Integer> A, int index, int prev){
        if(index > A.size()-1 || prev > A.size()-2){
            return 0;
        }

        if(dp[index][prev+1] != -1){
            return dp[index][prev+1];
        }

        System.out.println("i = " + index + " prev " + prev);

        int a = Integer.MIN_VALUE;
        if( (prev == -1) || A.get(prev) < A.get(index)){
            a = 1 + compute(A, index+1, index);
        }

        int b = compute(A, index+1, prev);

        dp[index][prev+1] = Math.max(a,b);

        return Math.max(a, b);
    }
}
