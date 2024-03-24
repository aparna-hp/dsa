package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KClosestElement {

    public static void main(String[] args){
        KClosestElement kClosestElement = new KClosestElement();
        int[] nums = {1,2,3,4,5};
        List<Integer> result = kClosestElement.findKClosest(nums, 4, -1);
        System.out.println(Arrays.toString(result.toArray()));
        //2126753390, 1702766719
    }

    public List<Integer> findKClosest(int[] nums, int k, int x){
        int start =0;
        int end = nums.length -1;
        while(end - start >= k) {
            if(Math.abs(nums[start]-x) > Math.abs(nums[end]-x)) {
                start++;
            } else {
                end--;
            }
        }

        List<Integer> result = new ArrayList<>(k);
        for(int i= start; i<=end; i++) {
            result.add(nums[i]);
        }

        return result;
    }
}
