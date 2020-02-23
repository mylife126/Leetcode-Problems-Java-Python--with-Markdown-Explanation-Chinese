'''
和substring不同的是subsequence的情况时，二者可以不连续， 例如 abc  and  ab 如果是substring，末尾必须要一样，然后取二者之间的substring做比对，所以substring来说这个共同的长度为0. 但是subsequence无所谓， abc和ab的情况就是ab 和 ab对比 取值为2。

再例如 abcde, axcxde, substring必须是连续的，所以只能是de对比de，所以取2， 而subsequence无所谓， ace 对比 ace，取3


1. state function dp[m][n], 代表了text【0 ~ i】 对比 text2【0 ~ j】这两个字符串中最长的common subsequence的长度
2. init， dp[0][i] 和 dp[i][0] 都是0，因为一个空对任何的string都是为0的common
3. transfer，当末尾相等的时候，直接+1，然后看两个string的各自前一位的长度加起来就可以
   如果不等，说明我们要么string1的末尾多了一项，要么是string2的末尾多了一项，要么各自都多了一项，所以对比
   max(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1])
   
   例如： string1 = abcdx, string2 = abxd, 那么我们的末尾不等，abcd 对比 abxd， or abcdx 对比 abx， or abcd 对比 abx
   
    0 1 2 3 
    / y b y 
 0/ 0 0 0 0
 1b 0 0 1 1 
 2l 0 0 1 1
'''
class Solution(object):
    def longestCommonSubsequence(self, text1, text2):
        """
        :type text1: str
        :type text2: str
        :rtype: int
        """
        if not text1 or not text2:
            return 0
        m = len(text1)
        n = len(text2)
        dp = [[0 for j in range(n + 1)] for i in range(m + 1)]
        count = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if text1[i - 1] == text2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + 1  
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                count = max(count, dp[i][j])
        print(dp)
        return count
    
    
          
            
        