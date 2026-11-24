// LeetCode 167. Two Sum II - Input Array Is Sorted (Medium)
//
// Pattern: two pointers on a sorted array — if the pair sums too low, move the
// left pointer right; too high, move the right pointer left. The answer is
// 1-indexed.
//
// Time: O(n), Space: O(1)

class Solution {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[] { -1, -1 };
    }

}
