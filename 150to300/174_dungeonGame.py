'''
1. dp[m + 1][n + 1] 每一个位置代表了这个grid下最低需要的hp是多少
2. init: 我们知道要knight活着得hp至少为1， 所以最后那个位置即走完-5之后得是1, 而其余的lastCol和lastRow都设为max
3. transfer，我们得bottom up倒推


T x x x
x   ？X         ？ 我们需要的是血量最小 且 我们知道正向来走， 当前位置走到下面和右边，
x   6 1         所以倒着走我们这个grid的血量是从右 下反推回来
x x 1 x         ？ + grid[i][j] = 1  -> ? = 1- grid[i][j]
                min(dp[i + 1][j], dp[i][j + 1])来作为基准
                但是如果grid的读值是正向的，那么很可能dp[i + 1][j] - grid[i][j] <= 0, 这说明这个grid下的扣血量
                其实是正的加血量，而且加很大，这意味着怎样都不会死，所以设为1 ：
                例子： 30 -> -5 这里， 哪怕我走到30的时候血量是1， 31 - 5 >0 依旧
                
                这么设置是因为我们用min来操作，如果keep这个负的血量，这会导致选择错误
                后一层的推导出现问题
'''
class Solution(object):
    def calculateMinimumHP(self, dungeon):
        """
        :type dungeon: List[List[int]]
        :rtype: int
        """
        
        if not dungeon:
            return 1
        
        mRows = len(dungeon)
        nCols = len(dungeon[0])
        
        dp = [[0 for j in range(nCols + 1)] for i in range(mRows + 1)]
        
        #init 我们知道走完最后一格后血量肯定为1 无论是往下走还是往右走
        dp[mRows -1 ][nCols] = 1
        dp[mRows][nCols - 1] = 1
        
        for i in range(mRows- 1):
            dp[i][nCols] = float('inf')
            
        for j in range(nCols - 1):
            dp[mRows][j] = float('inf')
            
        for i in range(mRows - 1, -1, -1):
            for j in range(nCols - 1, -1, -1):
                minHPAfter = min(dp[i + 1][j], dp[i][j + 1])
                neededAtHere = minHPAfter - dungeon[i][j]
                if neededAtHere <= 0:
                    neededAtHere = 1
                dp[i][j] = neededAtHere
                
        return dp[0][0]
        
        