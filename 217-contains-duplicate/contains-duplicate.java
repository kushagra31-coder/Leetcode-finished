// LeetCode 217. Contains Duplicate (Easy)
// Pattern: hashing — a value seen twice means a duplicate; a HashSet tracks what we have seen.
// Time: O(n), Space: O(n)
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }
}
