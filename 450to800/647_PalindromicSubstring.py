'''
aabbabba
1. state function dp[left][right] 指的是substring left ~ right是否是palindrome
2. 每一个字串自己是palindrome， dp[i][i] = 1
3. 'bab'为例子 dp[3][5] 首尾相等，那么我们得判断 中间的是不是palindrome，则判断dp[4][4]， 【a】这个位置，
我们发觉我们需要未来的信息，则我们可以bottom up来坐这个dp
transfer: s[left] == s[right] and right - left < 2 or dp[left + 1][right - 1]：
            dp[left][right] = True

4.返回所有True的个数即可

'''

class Solution(object):
    def countSubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return 1
        
        dp = [[False for i in range(len(s))] for j in range(len(s))]
        howMany = 0
        for i in range(len(s)):
            dp[i][i] = True
            howMany += 1
        #bottom up来做dp， 遍历所有可能的左起点
        for left in range(len(s) - 1, -1, -1):
            #然后遍历所有左起点右边的所有右起点
            for right in range(left + 1, len(s)):
                if s[left] == s[right] and (right - left < 2 or dp[left + 1][right - 1]):
                    dp[left][right] = True
                    howMany +=1 
        
        return howMany
                
            
        
        