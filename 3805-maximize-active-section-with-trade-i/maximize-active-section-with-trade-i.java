class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";
        int m = t.length();

        // Run-length encoding
        int[] len = new int[m];
        char[] type = new char[m];
        int cnt = 0;

        int i = 0;
        while (i < m) {
            int j = i;
            while (j < m && t.charAt(j) == t.charAt(i)) {
                j++;
            }
            type[cnt] = t.charAt(i);
            len[cnt] = j - i;
            cnt++;
            i = j;
        }

        int ans = ones;

        // Check every 1-block surrounded by 0-blocks
        for (int k = 1; k + 1 < cnt; k++) {
            if (type[k] == '1' &&
                type[k - 1] == '0' &&
                type[k + 1] == '0') {

                int gain = len[k - 1] + len[k + 1];
                ans = Math.max(ans, ones + gain);
            }
        }

        return ans;
    }
}