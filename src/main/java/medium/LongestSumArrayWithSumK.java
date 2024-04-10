package medium;

import java.util.*;

/*
https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
 */
public class LongestSumArrayWithSumK {

    public static void main(String[] args){
        int[] A = {-13, -9, 16, 10, 19, 15, 5, 17, 10, 5, 6};
        int out = lenOfLongSubarr(A, 11, 4);
        System.out.println("Out = " + out);
    }

    // Function for finding maximum and value pair
    public static int lenOfLongSubarr (int A[], int N, int K) {
        //Complete the function

        if(N == 0){
            return 0;
        }

        int preSum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, -1);

        int max = Integer.MIN_VALUE;

        for(int i=0; i<N; i++){
            preSum +=  A[i];
            if(map.containsKey(preSum-K)){
                int len = i - map.get(preSum -K);
                System.out.println("Len from i to map.get = " + i + "," +  map.get(preSum -K));
                max = Math.max(max, len);
            }

            //Store only the first occurrence
            if(!map.containsKey(preSum)){
                map.put(preSum, i);
            }
        }

        return Math.max(0, max);
    }
}
