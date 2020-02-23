'''
step1 or 2,  4

1 step1 => 1
2 step1 , remaining 1 => dp[1]; or step2 => += dp[0]   = 2
3 step1, remaining 2 dp[2]; or step1 => +=dp[1]  = 2 + 1 = 3
'''


class Solution(object):
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        
        if not n:
            return 0
        
        dp = [0 for i in range(n + 1)]
        dp[1] = 1
        dp[0] = 1
        
        for i in range(2, n + 1):
            #choose step1 
            remaining = i - 1
            if remaining >= 0:
                dp[i] = dp[remaining]
                
            if i >= 2:
                remaining = i - 2
                dp[i] += dp[remaining]
        return dp[n]
                    
        