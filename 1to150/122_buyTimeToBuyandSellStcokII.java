/*
既然说明可以tansanction多次，那就意味着我可以今天卖今天再买。

所以只要今天的价格大于昨天的 我们就有获利，所以加上获利，最后得到的是最大获利
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }
        
        int profit = 0;
        
        for (int i = 1; i < prices.length; i++){
            if (prices[i] > prices[i - 1]){
                profit += prices[i] - prices[i - 1];
            }
        }
        
        return profit;
    }
}