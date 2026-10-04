class Solution {
    public boolean checkValidString(String s) {
        int minBalance = 0;
        int maxBalance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minBalance++;
                maxBalance++;
            } 
            else if (ch == ')') {
                minBalance--;
                maxBalance--;
            } 
            else { // '*'
                minBalance--;  // '*' acts as ')'
                maxBalance++;  // '*' acts as '('
            }

            // Even maximum possible balance is negative
            if (maxBalance < 0) {
                return false;
            }

            // Minimum balance cannot be negative
            minBalance = Math.max(0, minBalance);
        }

        return minBalance == 0;
    }
}