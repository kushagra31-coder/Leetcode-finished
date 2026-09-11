class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];

        // Count each digit
        for (int digit : digits) {
            count[digit]++;
        }

        int ans = 0;

        // Hundreds digit: 1-9 (cannot be 0)
        for (int a = 1; a <= 9; a++) {

            // Tens digit: 0-9
            for (int b = 0; b <= 9; b++) {

                // Units digit: must be even
                for (int c = 0; c <= 8; c += 2) {

                    int[] need = new int[10];
                    need[a]++;
                    need[b]++;
                    need[c]++;

                    boolean possible = true;

                    for (int d = 0; d <= 9; d++) {
                        if (need[d] > count[d]) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}