/*
想法则是， 我们想要找到的是 minBuy 和 maxSell，且maxSell 出现的时间要在 minBuy之后。
那么可以存两个变量，一个是globalMax, 另一个是minBuy， 
1. 每次循环我们查看一下此刻的value是不是可以构成最小的minbuy，如果是，则赋值minBuy为此刻的value，因为如果后面有一个很大的卖出价格，这个获益和时间无关，只和minBuy有关，所以greedy的思想就是不断更新最小的minBuy。 

2. 然后每次循环更新一次globalMax = max(globalMax,  valuenow - minBuy). 同理就算此刻我们拿到了最小的buy，但很可能此刻之后的sell都很小，远远不如之前的获益。所以即使保证了minBuy 并不能保证maxprofit，所以需要不断更新，直到循环结束
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0){
            return 0;
        }
        
        int minBuy = Integer.MAX_VALUE;
        int globalMax = 0;
        
        for (int i = 0; i < prices.length; i++){
            minBuy = Math.min(minBuy, prices[i]);
            globalMax = Math.max(globalMax, prices[i] - minBuy);
        }
        return globalMax;
    }
}