'''
1. state: 2d array, 每一个位置代表走到这个grid能走的总走法
2. init： 第一row机器人只能走左走到右，所以每一个grid都是1
          同理，第一列的，机器人只能从上走到下面， 所以也是1
          
3. transfer：
   1 1 1 1 1 
   1   X
   1 X ？       想要走到这地方，机器人可以从上走下来，也可从左走来dp[i - 1][j] + dp[i][j - 1] 可见我们需要的是过去的信息
   1            topDown
 
4. return 最后的dp得到走到这个位置的所有走法
''' 

class Solution(object):
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        dp = [[0 for j in range(n)] for i in range(m)]    
        
        #init
        for i in range(m):
            dp[i][0] = 1
            
        for j in range(n):
            dp[0][j] = 1
        
            
        #transfer
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                
        return dp[m - 1][n - 1]