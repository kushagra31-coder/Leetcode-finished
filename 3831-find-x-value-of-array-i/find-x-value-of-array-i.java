class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];

            int mod = num % k;

            // Start a new subarray: [num]
            newDp[mod] = 1;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                int newRemainder = (int)((long) r * mod % k);

                newDp[newRemainder] += dp[r];
            }

            // Add all subarrays ending here to the final answer
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}