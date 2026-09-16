class Solution {
    static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];

        // factorials
        fact[0] = 1;

        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        // inverse factorials
        invFact[N] = modPow(fact[N], MOD - 2);

        for (int i = N - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        // C(N, R)
        long ans = fact[N];

        ans = ans * invFact[R] % MOD;
        ans = ans * invFact[N - R] % MOD;

        return (int) ans;
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exp >>= 1;
        }

        return result;
    }
}