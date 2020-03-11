class Solution:
    def longestPalindrome(self, s: str) -> str:
        '''
               0 1 2 3 4
               b a b a b
           0 b T
           1 a   T   ?              想知道 aba 是否为palindrome，我们得先确定string[i] = 'a' == string[j] = 'a'
           2 b    [T]               然后我们就得确定aba之间的string是否为palindrome，也就是 string[i + 1 ~ j -1]
           3 a       T              dp[i + 1][j - 1]的信息，所以对于dp[i + 1]来说是未来的信息，我们可以bottom up来做即可
           4 b         T
        '''
        
        if not s:
            return s
        length = len(s)
        
        dp = [[False for j in range(length)] for j in range(length)]
        for i in range(length):
            dp[i][i] = True
            
            
        bestID = [0, 0]
        maxLen = 0
        #i 是start char
        for i in range(len(s) - 1, -1, -1):
            #我们从start char 往后找， 所以从i开始
            for j in range(i, len(s)):
                if i == j:
                    continue
                charStart = s[i]
                charEnd = s[j]
                
                if charStart == charEnd and (j - i < 2 or dp[i + 1][j - 1] == True):
                    dp[i][j] = True
                    currLen = j - i + 1
                    if currLen > maxLen:
                        maxLen = currLen 
                        bestID[0] = i
                        bestID[1] = j
                        
        return s[bestID[0] : bestID[1] + 1]
                
                
        