'''
1. state function dp[i][j] 代表了string1[:i] 和 string2[:j]的最短edit distance
2. init function， 当string1_i = 0为空的时候，那dp[i][j] = j
   同理， 当strin2_j = 0为空的时候，要将string2变为string[:i]则就需要添加i个字符

    0 1  2  3  4  5   str2
    / h  o  r  s  e
0 / 0 1  2  3  4  5
1 r 1  

2 o 2

3 s 3

3. transfer function： 

    dp[2][2] 代表了从ro 变换到ho的距离，可见我们可以直接改变r -> ho, 则需要dp[i-1][j] + 1 => r->h + o + o = 3
                                          也可以直接改变h -> ro, 则需要dp[i][j - 1] + 1 => h->r + o + o = 3
                                          同样也可以直接修改最后一位： dp[i- 1][j - 1] + [末尾是否相等] 
                                          如果末尾相等则只需要修改末尾之前的所有，所以直接是dp[i-1][j-1]
                                          但是末尾要是不等则需要修改末尾一次，所以dp[i][j] + 1

    0 | 1 | 2 | 3  4  5   str2
    / | h | o | r  s  e
0 / 0 | 1 | 2 | 3  4  5
------|---|---|--------
1 r 1 | 1 | 2 |
------|---|---|--------
2 o 2 | 2 | ？|                      
------|---|---|--------
3 s 3 |   |   |
'''
class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        
        mRows = len(word1)
        nCols = len(word2)
        
        #state function, dp[i][j] meaning string1[:i] to string2[:j]'s minimal distance'
        dp = [[-1 for j in range(nCols + 1)] for i in range(mRows + 1)]
        
        #init state function
        #if one of the string is empty then the distance is just equal to the other's len
        for i in range(mRows + 1):
            dp[i][0] = i
            
        for j in range(nCols + 1):
            dp[0][j] = j
            
        #transfer function
        for i in range(1, mRows + 1):
            for j in range(1, nCols + 1):
                changeWord2 = dp[i][j - 1] + 1  #+1 是在str2的末尾加上此刻i的这一位字符
                changeWord1 = dp[i - 1][j] + 1
                changeTheEnd = dp[i - 1][j - 1]
                
                if (word1[i - 1] != word2[j -1]):#如果此时两个字符的末尾不等，则仍然需要将最后一位修改
                    changeTheEnd +=1
                
                dp[i][j] = min(changeWord2, changeWord1, changeTheEnd)
        
        
        return dp[mRows][nCols]
        
        