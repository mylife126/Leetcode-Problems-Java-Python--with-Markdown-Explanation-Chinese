'''
R   G   B
17  2   17    house1
16  16  5     house2
14  3   19    house3 


1. state function dp[3], 每一个位置代表upto这个屋子的时候， 在rgb上的总cost最小
2。init， dp = price[0], 因为一开始只有一个屋子，而他的rgb的cost就是一开始的
3. transfer function： 到了屋子二：
    如果我选择红色， 则house1只能涂抹G B，所以R + min(dp[b], dp[g])
    如果我选了绿色， 则house1只能涂抹R B，所以G + min(dp[r], dp[b])
    如果我选了蓝色， 则house1只能涂抹R G，所以B + min（dp[r], dp[g])
    
    所以此时的dp= [16 + 2, 16 + 17, 5 + 2] 这就保证了此时的相邻的屋子不是同一个颜色的要求

4. return, 最后array里存的就是所有情况下的三个屋子的总cost， 选取最低的即可
'''

class Solution(object):
    def minCost(self, costs):
        """
        :type costs: List[List[int]]
        :rtype: int
        """
        if not costs:
            return 0
        
        dp = costs[0]
        
        for i in range(1, len(costs)):
            thisPrice = costs[i]
            lastSum = dp[::]
            dp = [None for i in range(3)]
            
            dp[0] = min(lastSum[1] + thisPrice[0], lastSum[2] + thisPrice[0])
            dp[1] = min(lastSum[0] + thisPrice[1], lastSum[2] + thisPrice[1])
            dp[2] = min(lastSum[1] + thisPrice[2], lastSum[0] + thisPrice[2])
            
        return min(dp)
            
        