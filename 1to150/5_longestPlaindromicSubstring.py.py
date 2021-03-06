'''
1. state function: dp[i][j] 代表着string[i ~ j]是否为pali

2. init dp[i][i] true 因为自己就是一个palindrome

3. transfer
    
   0  1  2  3  4
   b  a  b  a  d
 b T                  bab dp[0][3] => 首尾相等 且 dp[1][1] = a是palindrome，则也是true 同时如果 j - i < 2 相差一位 aa 也是true
 a    T
 b ?     T
 a          T
 d            T
  
但我们发觉，我们需要未来的信息，也就是 transfer function : dp[left][right] = true if dp[left +1][right- 1] 我们再算irow的时候需要知道i + 1，
那么我们可以bottom up

'''

class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        
        if not s:
            return s
        
        #state function dp[i][j] 代表了 substring i~ j是否为palindrome的情况
        dp = [[False for i in range(len(s))] for j in range(len(s))]
        
        #init the state function, every letter itself is a pali
        for i in range(len(s)):
            dp[i][i] = True
        
        #init the best id array, assume the first letter is the longest
        bestID = [0, 0]
        length = 1
        
        for left in range(len(s) - 1, -1, -1):
            for right in range(left + 1, len(s)):
                if s[left] == s[right] and (right - left < 2 or dp[left + 1][right - 1]):
                    dp[left][right] = True
                    if right - left + 1 > length:
                        bestID[0] = left
                        bestID[1] = right
                        length = right - left + 1
        return s[bestID[0] : bestID[1] + 1]

        