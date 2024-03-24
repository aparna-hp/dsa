package Easy;

class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] nums = {2,5};
        System.out.println(binarySearch.search(nums, 0));
    }

    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }

    public int binarySearch(int[] nums, int start, int end, int target) {
        if(start > end) {
            return -1;
        }

        if(start == end && nums[start] != target) {
            return -1;
        }

        int mid = (start+end)/2;

        if(nums[mid] == target) {
            return mid;
        }

        if(nums[mid] < target) {
            return binarySearch(nums, mid+1, end, target);
        }
        else {
            return binarySearch(nums, start, mid-1, target);
        }
    }
}