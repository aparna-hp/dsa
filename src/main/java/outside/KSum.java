package outside;

import java.util.HashMap;
import java.util.Map;

// Longest Subarray With Sum K
public class KSum {

    public static void main(String[] args){
        KSum kSum = new KSum();
        int[] a = {0,2,5,3,3,4,4,3,0,5,5,4,4,4,3,2,0,2,3,1,3,0,4,3,1,4,5,2,4,3,1,4,5,0,3,4,0};
        int[] b = {1,2,1,0,1};
        int[] c = {1,-1,1};
//        System.out.println(KSum.onlyPositive(a,52));
//        System.out.println(KSum.onlyPositive(b,4));
//        System.out.println(KSum.getLongestSubarray(c, 1));

        int[] d = {-1, 0, 1, 1, -1, -1, 0};
        int[] e = { 100000, -999812, -218};
        System.out.println(KSum.getLongestSubarray(d, 0)); //6
        System.out.println(KSum.getLongestSubarray(e, 0)); //0


    }

    public static int onlyPositive(int[] a, long k) {
        int start = 0;
        int sum = 0;
        int maxLen = Integer.MIN_VALUE;
        for(int i=0; i<a.length; i++){

            sum += a[i];

            if(sum == k){
                maxLen = Math.max(maxLen, i-start+1);
            }

            while(sum > k && start < i){
                sum -= a[start++];
                if(sum == k){
                    maxLen = Math.max(maxLen, i-start+1);
                }
            }
        }
        return maxLen;
    }

    //With positive, negative & zero
    public static int getLongestSubarray(int []nums, int k) {
        int sum = 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            sum += nums[i];

            if(sum == k){
                maxLen = Integer.max(maxLen, i+1);
            } else {
                if(map.containsKey(sum-k)){
                    maxLen = Integer.max(maxLen, i - map.get(sum-k));
                }
            }

            if(!map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return maxLen;
    }


}
