class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int depth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                // Don't add the outermost '('
                if (depth > 0) {
                    ans.append(ch);
                }
                depth++;
            } else {
                depth--;

                // Don't add the outermost ')'
                if (depth > 0) {
                    ans.append(ch);
                }
            }
        }

        return ans.toString();
    }
}