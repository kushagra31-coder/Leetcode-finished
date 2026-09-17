class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1_000_000_000;

        // best[i] = shortest target-sum subarray
        // completely inside arr[0 ... i-1]
        int[] best = new int[n + 1];

        Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink while sum is too large
            while (sum > target) {
                sum -= arr[left++];
            }

            // Carry forward previous best
            best[right + 1] = best[right];

            // Found a target-sum subarray
            if (sum == target) {
                int len = right - left + 1;

                // Need a previous non-overlapping subarray
                if (best[left] != INF) {
                    answer = Math.min(
                        answer,
                        best[left] + len
                    );
                }

                // This is the best subarray ending at/before right
                best[right + 1] = Math.min(
                    best[right + 1],
                    len
                );
            }
        }

        return answer == INF ? -1 : answer;
    }
}