class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long power = 1000;

        while (power <= n) {
            ans += n - power + 1;
            power *= 1000;
        }

        return ans;
    }
}