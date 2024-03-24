package medium;

public class SearchInRotatedArraysWithDup {

    public static void main(String[] args) {
        SearchInRotatedArraysWithDup search = new SearchInRotatedArraysWithDup();
        int[] nums = {5,0,1,2,4};
        System.out.println(search.search(nums, 0));
    }

    public boolean search(int[] nums, int target){
        int startIndex =0;
        int endIndex = nums.length-1;

        while(startIndex <= endIndex) {

            int mid = startIndex + (endIndex-startIndex)/2;

            if(nums[mid] == target || nums[startIndex] == target || nums[endIndex] == target){
                return true;
            }

            //In case of duplicates, start, mid & end could be the same element.Just move to the next element
            // 4,1,2,3,4,4,4,4
            if(nums[startIndex] == nums[mid] && nums[mid] == nums[endIndex]) {
                startIndex++;
                endIndex--;
                continue;
            }

            //StartIndex and Mid are in sorted section
            if(nums[startIndex] <= nums[mid]) {
                if(target > nums[startIndex] && target < nums[mid]) {
                    endIndex = mid-1;
                } else {
                    startIndex = mid+1;
                }
            }

            else {
                //Mid and EndIndex is in sorted section
                if(target > nums[mid] && target < nums[endIndex]) {
                    startIndex = mid+1;
                } else {
                    endIndex = mid -1;
                }
            }
        }

        return false;
    }


}
