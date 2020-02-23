'''
1. state function, dp[m][n] 依旧代表这个走到这个grid能有多少个走法
2. init， 第一row和第一列理应都是1，但是一旦发觉这个位置下有obstacles，那当前位置直接为零，且bool记录是否发现了obstable
   如果发现了这之后的grid也都是0
   
3. transfer： 上面 加 左边, 但是如果当前位置有一个obs， 那它走不通，所以为0
'''


class Solution(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """
        
        if obstacleGrid[0][0] == 1:
            return 0
        
        mRows = len(obstacleGrid)
        nCols = len(obstacleGrid[0])
        
        dp = [[0 for j in range(nCols)] for i in range(mRows)]
        
        #init
        for i in range(mRows):
            if obstacleGrid[i][0] == 1:
                dp[i][0] = 0
                #the rest are all 0
                break 
            dp[i][0] = 1
            
        for j in range(nCols):
            if obstacleGrid[0][j] == 1:
                dp[0][j] = 0
                #rest of them are all zero already
                break
            dp[0][j] = 1
            
        for i in range(1, mRows):
            for j in range(1, nCols):
                if obstacleGrid[i][j] == 1:
                    #if this location is a 
                    dp[i][j] = 0
                    continue
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                
        return dp[mRows - 1][nCols - 1]
                
            
        