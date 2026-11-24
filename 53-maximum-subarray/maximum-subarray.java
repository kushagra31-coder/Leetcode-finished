// LeetCode 53. Maximum Subarray (Medium)
// Pattern: Kadane's algorithm — best subarray ending here either extends the previous
// run or starts fresh at nums[i]; keep the global best.
// Time: O(n), Space: O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int bestEndingHere = nums[0];
        int bestSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            bestEndingHere = Math.max(nums[i], bestEndingHere + nums[i]);
            bestSoFar = Math.max(bestSoFar, bestEndingHere);
        }
        return bestSoFar;
    }
}
