import java.util.*;

public class SubSet {

    int size;

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        SubSet subSet = new SubSet();
        subSet.size = nums.length;
        subSet.getSubArray(nums, result,0);

        for(List<Integer> subsets : result) {
            System.out.println(Arrays.toString(subsets.toArray()));
        }

        int[] num = {1,2,2};
        result = subSet.subsetsWithDup(num);

        for(List<Integer> subsets : result) {
            System.out.println(Arrays.toString(subsets.toArray()));
        }
    }

    /*public List<List<Integer>> subsetsWithDup(int[] nums) {
        size = nums.length;
        Map<Integer,Integer> map = new HashMap();
        for(int i=0; i < size; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], 1 + map.get(nums[i]));
            } else {
                map.put(nums[i],1);
            }
        }

        List<List<Integer>> result = new ArrayList();
        List<Integer> list = new ArrayList();

        getSubArray(map, list, result);
        return result;
    }
*/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        size = nums.length;
        Map<Integer,Integer> map = new HashMap();
        for(int i=0; i < size; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], 1 + map.get(nums[i]));
            } else {
                map.put(nums[i],1);
            }
        }

        List<List<Integer>> result = new ArrayList();
        result.add(new ArrayList<>());
        for(Integer key : map.keySet()){
            getSubArray(map, result, key);
        }
        return result;
    }

    public void getSubArray(int[] nums, List<List<Integer>> result, int n ){
        if(n == size) {
            return;
        }

        int var = nums[n];
        List<List<Integer>> existingSubSets = new ArrayList<>(result);
        for(List<Integer> subset : existingSubSets) {
            List<Integer> newSubset = new ArrayList<>(subset);
            newSubset.add(var);
            result.add(newSubset);
        }

        getSubArray(nums, result, n+1);
    }

    public void getSubArray(Map<Integer, Integer> map, List<List<Integer>> result, int key ){

       int value = map.get(key);
        List<List<Integer>> existingSubSets = new ArrayList<>(result);
        for(List<Integer> subset : existingSubSets) {
            List<Integer> newSubset = new ArrayList<>(subset);
            int temp = value;
            while(temp > 0) {
                newSubset = new ArrayList<>(newSubset);
                newSubset.add(key);
                result.add(newSubset);
                temp --;
            }
        }
    }

}