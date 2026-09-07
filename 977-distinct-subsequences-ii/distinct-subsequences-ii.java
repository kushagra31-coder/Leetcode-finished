class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long[] dp = new long[26];
        long total = 0;

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long newCount = (total + 1) % MOD;

            total = (total - dp[index] + newCount + MOD) % MOD;

            dp[index] = newCount;
        }

        return (int) total;
    }
}