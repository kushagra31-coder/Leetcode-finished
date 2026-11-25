// LeetCode 11. Container With Most Water (Medium)
//
// Pattern: two pointers — start at both ends, move the shorter side
// inward; the area is min(height[l], height[r]) * (r - l).
//
// Time: O(n), Space: O(1)

class Solution {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, h * width);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

}
