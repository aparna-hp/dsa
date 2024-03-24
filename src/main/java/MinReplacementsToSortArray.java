class MinReplacementsToSortArray {

    long count = 0L;

    public long minimumReplacement(int[] nums) {
        findReplacements(nums, nums.length-2);
        return count;
    }

    public void findReplacements(int[] nums, int index) {
        if (index < 0) {
            return;
        }

        if (nums[index] > nums[index + 1]) {

            int numOfSplits = nums[index] / nums[index + 1];
            if (nums[index] % nums[index + 1] != 0) {
                numOfSplits++;
            }
            count += numOfSplits -1;
            nums[index] = nums[index] / numOfSplits;
        }
        findReplacements(nums, index - 1);
    }
}