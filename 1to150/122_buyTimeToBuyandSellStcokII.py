'''
无限次交易， 只要今天的价格高于昨天的价格，我们就有获利， 所以running += pricc[i] - price[i - 1]

'''
class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if not prices:
            return 0
        
        profit = 0
        for i in range(1, len(prices)):
            if prices[i] > prices[i - 1]:
                profit += (prices[i] - prices[i - 1])
        
        return profit