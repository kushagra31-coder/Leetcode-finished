// LeetCode 424. Longest Repeating Character Replacement (Medium)
//
// Pattern: sliding window — keep the count of the most frequent char
// in the window; the window is valid while (length - maxFreq) <= k,
// i.e. at most k replacements turn the whole window into one char.
//
// Time: O(n), Space: O(1) (26-letter alphabet)

class Solution {

    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0;
        int maxFreq = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

}
