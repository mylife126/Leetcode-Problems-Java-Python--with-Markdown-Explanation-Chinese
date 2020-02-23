'''
1 state function: dp[m][n], 每一个位置代表了走到这个位置的时候，最小的path runningsum为多少
2 init function：第一row只能从左边走来，所以都是runningSum之前一位
                 第二咧同理
                 
3. transfer fuction
  1 -> 4 -> 5
  |
  2    ?            这个位置我们要选取最小的走法 所以 min(dp[i - 1][j】 + 自己， dp[i][j - 1] + 自己)
  |
  6
  
  但如果存在obstacle的情况，我们需要判断 dp[i - 1][j] 和 dp[i][j -1]是否为0， 如果为零说明有一条路不通， 那么就只加不为零的那部分/ 如果自己这个grid存在obtacle，那直接这里设为0 

'''


class Solution(object):
    def minPathSum(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        
        if not grid:
            return 0
        
        mRows= len(grid)
        nCols = len(grid[0])
        
        dp = [[0 for j in range(nCols)] for i in range(mRows)]
        
        #init
        for i in range(mRows):
            if i == 0:
                dp[i][0] = grid[0][0]
                continue
            dp[i][0] += dp[i - 1][0] + grid[i][0]
            
        for j in range(nCols):
            if j == 0:
                dp[0][j] = grid[0][0]
                continue
            dp[0][j] += dp[0][j - 1] + grid[0][j]
            
        for i in range(1, mRows):
            for j in range(1, nCols):
                dp[i][j] = min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j])
                
        return dp[mRows - 1][nCols - 1]
            
        