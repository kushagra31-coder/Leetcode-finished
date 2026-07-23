class Solution {

    public String findPalindrome(String s, int left, int right) {

        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }

    public String longestPalindrome(String s) {

        if (s == null || s.length() < 2)
            return s;

        String longest = "";

        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome
            String odd = findPalindrome(s, i, i);

            // Even length palindrome
            String even = findPalindrome(s, i, i + 1);

            if (odd.length() > longest.length())
                longest = odd;

            if (even.length() > longest.length())
                longest = even;
        }

        return longest;
    }
}