class Solution {
    int[][] memo;
    
    int minLen23(int r2, int r3) {
        r2 = r2 > 0 ? r2 : 0;
        r3 = r3 > 0 ? r3 : 0;
        if (r2 == 0 && r3 == 0) return 0;
        if (r2 < 60 && r3 < 40 && memo[r2][r3] != -1) return memo[r2][r3];
        
        int best = 1000;
        int limit = r2 < r3 ? r2 : r3;
        for (int c6 = 0; c6 <= limit; c6++) {
            int rem2 = r2 - c6;
            int rem3 = r3 - c6;
            rem2 = rem2 > 0 ? rem2 : 0;
            rem3 = rem3 > 0 ? rem3 : 0;
            int cur = c6 + (rem2 + 2) / 3 + (rem3 + 1) / 2;
            if (cur < best) best = cur;
        }
        if (r2 < 60 && r3 < 40) memo[r2][r3] = best;
        return best;
    }
    
    int minLen(int r2, int r3, int r5, int r7) {
        return r5 + r7 + minLen23(r2, r3);
    }
    
    static final int[][] FACTORS = {
        {0,0,0,0}, {0,0,0,0}, {1,0,0,0}, {0,1,0,0}, {2,0,0,0},
        {0,0,1,0}, {1,1,0,0}, {0,0,0,1}, {3,0,0,0}, {0,2,0,0}
    };
    
    public String smallestNumber(String num, long t) {
        memo = new int[60][40];
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 40; j++) {
                memo[i][j] = -1;
            }
        }
        
        int[] req = new int[4];
        long temp = t;
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (temp % primes[i] == 0) {
                req[i]++;
                temp /= primes[i];
            }
        }
        if (temp > 1) return "-1";
        
        int firstZero = num.indexOf('0');
        if (firstZero == -1) firstZero = num.length();
        
        int[][] prefixReq = new int[firstZero + 1][4];
        prefixReq[0][0] = req[0];
        prefixReq[0][1] = req[1];
        prefixReq[0][2] = req[2];
        prefixReq[0][3] = req[3];
        
        for (int i = 0; i < firstZero; i++) {
            int[] f = FACTORS[num.charAt(i) - '0'];
            for (int j = 0; j < 4; j++) {
                int r = prefixReq[i][j] - f[j];
                prefixReq[i+1][j] = r > 0 ? r : 0;
            }
        }
        
        if (firstZero == num.length()) {
            if (prefixReq[firstZero][0] + prefixReq[firstZero][1] + 
                prefixReq[firstZero][2] + prefixReq[firstZero][3] == 0) {
                return num;
            }
        }
        
        int startI = num.length() - 1 < firstZero ? num.length() - 1 : firstZero;
        
        for (int i = startI; i >= 0; i--) {
            int[] rem = prefixReq[i];
            int K = num.length() - 1 - i;
            
            int foundD = -1;
            int[] bestNewReq = null;
            
            int startD = (num.charAt(i) - '0') + 1;
            for (int d = startD; d <= 9; d++) {
                int[] f = FACTORS[d];
                int r0 = rem[0] - f[0], r1 = rem[1] - f[1], r2 = rem[2] - f[2], r3 = rem[3] - f[3];
                int n0 = r0 > 0 ? r0 : 0, n1 = r1 > 0 ? r1 : 0, n2 = r2 > 0 ? r2 : 0, n3 = r3 > 0 ? r3 : 0;
                
                if (minLen(n0, n1, n2, n3) <= K) {
                    foundD = d;
                    bestNewReq = new int[]{n0, n1, n2, n3};
                    break;
                }
            }
            
            if (foundD != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(num.substring(0, i));
                sb.append(foundD);
                int[] currReq = bestNewReq;
                for (int pos = 0; pos < K; pos++) {
                    int left = K - 1 - pos;
                    for (int d = 1; d <= 9; d++) {
                        int[] f = FACTORS[d];
                        int r0 = currReq[0] - f[0], r1 = currReq[1] - f[1], r2 = currReq[2] - f[2], r3 = currReq[3] - f[3];
                        int n0 = r0 > 0 ? r0 : 0, n1 = r1 > 0 ? r1 : 0, n2 = r2 > 0 ? r2 : 0, n3 = r3 > 0 ? r3 : 0;
                        if (minLen(n0, n1, n2, n3) <= left) {
                            sb.append(d);
                            currReq = new int[]{n0, n1, n2, n3};
                            break;
                        }
                    }
                }
                return sb.toString();
            }
        }
        
        int ml = minLen(req[0], req[1], req[2], req[3]);
        int L_new = num.length() + 1 > ml ? num.length() + 1 : ml;
        StringBuilder sb = new StringBuilder();
        int[] currReq = req;
        for (int pos = 0; pos < L_new; pos++) {
            int left = L_new - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                int[] f = FACTORS[d];
                int r0 = currReq[0] - f[0], r1 = currReq[1] - f[1], r2 = currReq[2] - f[2], r3 = currReq[3] - f[3];
                int n0 = r0 > 0 ? r0 : 0, n1 = r1 > 0 ? r1 : 0, n2 = r2 > 0 ? r2 : 0, n3 = r3 > 0 ? r3 : 0;
                if (minLen(n0, n1, n2, n3) <= left) {
                    sb.append(d);
                    currReq = new int[]{n0, n1, n2, n3};
                    break;
                }
            }
        }
        return sb.toString();
    }
}
