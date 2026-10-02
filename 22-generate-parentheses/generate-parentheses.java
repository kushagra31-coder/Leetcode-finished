class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        backtrack(ans, new StringBuilder(), 0, 0, n);

        return ans;
    }

    private void backtrack(
        List<String> ans,
        StringBuilder current,
        int open,
        int close,
        int n
    ) {
        // Complete valid string
        if (current.length() == 2 * n) {
            ans.add(current.toString());
            return;
        }

        // Add '('
        if (open < n) {
            current.append('(');

            backtrack(ans, current, open + 1, close, n);

            current.deleteCharAt(current.length() - 1);
        }

        // Add ')'
        if (close < open) {
            current.append(')');

            backtrack(ans, current, open, close + 1, n);

            current.deleteCharAt(current.length() - 1);
        }
    }
}