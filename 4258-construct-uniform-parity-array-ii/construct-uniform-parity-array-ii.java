class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean hasOdd = false;

        for (int x : nums1) {
            min = Math.min(min, x);

            if (x % 2 != 0) {
                hasOdd = true;
            }
        }

        if (min % 2 == 0 && hasOdd) {
            return false;
        }

        return true;
    }
}