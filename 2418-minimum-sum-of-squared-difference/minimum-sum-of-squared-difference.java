class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] freq = new int[100001];
        long total = 0;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            total += diff;
        }

        if (k >= total) {
            return 0;
        }

        for (int i = 100000; i > 0 && k > 0; i--) {
            long moves = Math.min(k, freq[i]);

            freq[i] -= moves;
            freq[i - 1] += moves;
            k -= moves;
        }

        long ans = 0;

        for (int i = 1; i <= 100000; i++) {
            ans += (long) i * i * freq[i];
        }

        return ans;
    }
}