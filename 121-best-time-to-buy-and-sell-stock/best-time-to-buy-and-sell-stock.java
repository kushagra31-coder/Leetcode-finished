// LeetCode 121. Best Time to Buy and Sell Stock (Easy)
// Pattern: one pass — track the minimum price so far, best profit = price - min so far.
// Time: O(n), Space: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}
