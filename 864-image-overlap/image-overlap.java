class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        // Store positions of all 1s
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    ones1.add(new int[]{r, c});
                }

                if (img2[r][c] == 1) {
                    ones2.add(new int[]{r, c});
                }
            }
        }

        Map<String, Integer> count = new HashMap<>();

        int answer = 0;

        for (int[] a : ones1) {
            for (int[] b : ones2) {

                int dr = b[0] - a[0];
                int dc = b[1] - a[1];

                String key = dr + "," + dc;

                int frequency = count.getOrDefault(key, 0) + 1;

                count.put(key, frequency);

                answer = Math.max(answer, frequency);
            }
        }

        return answer;
    }
}