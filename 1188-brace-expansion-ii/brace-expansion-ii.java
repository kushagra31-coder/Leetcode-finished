import java.util.*;

class Solution {
    private int i;

    public List<String> braceExpansionII(String expression) {
        i = 0;

        Set<String> result = parseExpression(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    // expression = term (',' term)*
    private Set<String> parseExpression(String s) {
        Set<String> result = new HashSet<>();

        while (true) {
            result.addAll(parseTerm(s));

            if (i >= s.length() || s.charAt(i) == '}') {
                break;
            }

            // Current character is ','
            i++;
        }

        return result;
    }

    // term = factor factor factor...
    private Set<String> parseTerm(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (i < s.length()
                && s.charAt(i) != ','
                && s.charAt(i) != '}') {

            Set<String> factor;

            if (s.charAt(i) == '{') {
                i++; // skip '{'

                factor = parseExpression(s);

                i++; // skip '}'
            } else {
                factor = new HashSet<>();
                factor.add(String.valueOf(s.charAt(i)));
                i++;
            }

            result = combine(result, factor);
        }

        return result;
    }

    // Cartesian product = concatenation
    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}