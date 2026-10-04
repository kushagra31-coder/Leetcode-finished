class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();

        stack.push(-1);

        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    // Unmatched ')'
                    stack.push(i);
                } else {
                    // Length of valid substring
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}