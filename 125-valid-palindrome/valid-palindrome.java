// LeetCode 125. Valid Palindrome (Easy)
//
// Pattern: two pointers — walk in from both ends, skipping non-alphanumeric
// characters and comparing case-insensitively.
//
// Time: O(n), Space: O(1)

class Solution {

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

}
