class Solution {

    public int uniqueXorTriplets(int[] nums) {

        int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int val : nums) {

            boolean[][] next = new boolean[4][MAX];

            // Copy previous states
            for (int i = 0; i <= 3; i++) {
                System.arraycopy(dp[i], 0, next[i], 0, MAX);
            }

            for (int used = 0; used <= 3; used++) {

                for (int xor = 0; xor < MAX; xor++) {

                    if (!dp[used][xor])
                        continue;

                    // Take current index once
                    if (used + 1 <= 3)
                        next[used + 1][xor ^ val] = true;

                    // Take current index twice
                    if (used + 2 <= 3)
                        next[used + 2][xor] = true;

                    // Take current index three times
                    if (used + 3 <= 3)
                        next[used + 3][xor ^ val] = true;
                }
            }

            dp = next;
        }

        int ans = 0;

        for (boolean b : dp[3]) {
            if (b)
                ans++;
        }

        return ans;
    }
}