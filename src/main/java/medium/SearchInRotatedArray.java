package medium;

public class SearchInRotatedArray {


    public static void main(String[] args){
        SearchInRotatedArray search = new SearchInRotatedArray();
        int[] nums = {3,4,5,6,1,2};
        int target = 2;
        int pivot = search.findPivot(nums, 0, nums.length-1);
        System.out.println("Pivot " + pivot);
        int ans = -1;
        if(pivot ==0 || pivot ==-1){
            ans = search.findTarget(nums, 0, nums.length-1, target);
        }
        else if(target == nums[pivot]) {
            ans = pivot;
        }
        else if(target >= nums[0]) {
            ans = search.findTarget(nums, 0, pivot, target);
        } else if(target <= nums[nums.length-1] ){
           ans = search.findTarget(nums, pivot, nums.length-1, target);
        }
        System.out.println(ans);
    }

    public int findPivot(int[] nums, int startIndex, int endIndex) {
        if(startIndex > endIndex) {
            return -1;
        }

        int mid = startIndex + (endIndex-startIndex)/2 ;
        System.out.println("Mid " + nums[mid]);
        if(mid < nums.length-1 && nums[mid] > nums[mid+1]) {
            System.out.println("Mid +1 " + nums[mid+1]);
            return mid+1;
        }

        if(mid >0 && nums[mid-1] > nums[mid]){
            System.out.println("Mid-1" + nums[mid-1]);
            return mid;
        }

        if(nums[mid] < nums[startIndex]) {
            return findPivot(nums, startIndex, mid - 1);
        }

        return   findPivot(nums, mid + 1, endIndex);
     }

    public int findTarget(int[] nums, int startIndex, int endIndex, int target) {
        if(startIndex > endIndex) {
            return -1;
        }

        int mid = startIndex + (endIndex-startIndex)/2;
        if(nums[mid] == target) {
            return mid;
        }

        if(startIndex == endIndex) {
            return -1;
        }

        if(target < nums[mid] ) {
            return findTarget(nums, startIndex, mid-1, target);
        }

        if(target > nums[mid]) {
            return findTarget(nums, mid+1, endIndex, target);
        }

        return -1;
    }
}
