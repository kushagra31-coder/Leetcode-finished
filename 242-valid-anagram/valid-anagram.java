// LeetCode 242. Valid Anagram (Easy)
//
// Pattern: frequency map — count letters of s, subtract letters of t;
// anagrams iff every count returns to zero.
//
// Time: O(n), Space: O(1) (26-letter alphabet)

class Solution {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

}
