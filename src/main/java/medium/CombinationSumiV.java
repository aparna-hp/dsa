package medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CombinationSumiV {

        public static void main(String[] args) {
            CombinationSumiV combinationSumiV = new CombinationSumiV();
            int[] nums = {1,2};
            System.out.println(combinationSumiV.combinationSum4(nums, 10));
            System.out.println(combinationSumiV.getCountDp(nums, 10));
        }

        public int combinationSum4(int[] nums, int target) {
            Set<String> paths = new HashSet<>();
            int a=  findCount(nums, 0, target, "", paths) ;
            int b = findCountRev(paths);
            System.out.println("Forward " + a + " Reverse " + b);
            return a+b;
        }

        public int findCount(int[] nums, int index, int target, String path, Set<String> paths){
            if(0 == target){
                System.out.println("Path " + path);
                paths.add(path);
                return 1;
            }

            if(target < 0 || index > nums.length-1) {
                return 0;
            }

            return findCount(nums, index, target-nums[index], path+index + ",", paths)
                    + findCount(nums, index+1,target, path, paths);
        }

    public int findCountRev(Set<String> paths){
       int count =0;
       for(String path: paths){
          String[] temp = path.split(",");
           System.out.println("Sorted " + Arrays.toString(temp));
           for(int i=1; i<temp.length; i++){
               int j = i;
               while(j > 0 && temp[j-1].equals(temp[j])){
                   j--;
               }
               count += j;
           }
           System.out.println("count " + count);
       }
       return count;
    }

    public int getCountDp(int[] nums, int target) {
            int[] dp = new int[target+1];
            dp[0]=1;
            for(int i=1; i<= target; i++) {
                int count =0;
                for(int j=0; j<nums.length; j++) {
                    if(i-nums[j] <0) {
                        continue;
                    }
                    count += dp[i-nums[j]];
                }
                dp[i] = count;
            }
            return dp[target];
    }

}
