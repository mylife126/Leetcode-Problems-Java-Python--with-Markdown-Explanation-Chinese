'''
Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb". 

subsequence,并不是子串，所以可以不连续

state function: dp[i][j] 代表substring(i,j)的最长subsequence
分析：
1.当substring的长度为1的时候
"b" dp[0][0] = 1
"b" dp[1][1] = 1
"b" dp[1][1] = 1
"a" dp[2][2] = 1
"b" dp[3][3] = 1

2.当substring的长度为2的时候
“bb” dp[0][1] = 2
"bb" dp[1][2] = 2
'ba' dp[2][3] = 发觉i ！= j， 那么我们要么取b 要么取 a上的最长subsequence， 所以我们调取max(dp[2][2], dp[3][3]) = 1
'ab' dp[3][4] = 发觉i ！= j， 那么我们要么取a 要么取 b上的最长subsequence， 所以我们调取max(dp[3][3], dp[4][4]) = 1

3. 当substring的长度为3的时候
'bbb' i = 0, j =2, 发觉 i == j, 则我们知道无论i j之间怎样，至少i~j的最长长度里一定有2，然后再加入dp[i+1][j-1]的中间的长度，也就是b【b】 b 圈起来的那部分
'bba' i = 1, j =3, 发觉 i != j, 则我们知道要么是多加了j这一项，要么是多加了i那一项，所以我们可以取max(dp[i][j - 1], dp[i + 1][j])

所以transfer function推导完毕

然后保存runningmax即可
return runningMax

    0 1 2 3 4
    b b b a b
0 b  
1 b
2 b     X ?      假设我们现在i = 2, j = 3, 我们需要知道的是 dp[i + 1][j - 1], dp[i][j - 1], dp[i + 1][j]
3 a     X X      所以我们得提前知道后面的信息，那我们可以后序遍历i，，bottomup！
4 b
'''
class Solution(object):
    def longestPalindromeSubseq(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return 0
        maxlen = 1
        dp = [[0 for i in range(len(s))] for j in range(len(s))]  # n by n int[][]
        
        for i in range(len(s)):
            dp[i][i] = 1
            
        for left in range(len(s) - 1, -1, -1):
            for right in range(left + 1, len(s)):
                if s[left] == s[right]:
                    #如果头尾相等，那么肯定此刻的length有一个2了，那么再加上中间部分的所有长度即可
                    dp[left][right] = 2 + dp[left + 1][right - 1]
                else:
                    #不然，aab这个情况，我们要么是多加了b这一项，要么是多加了a，我们不知道，所以取max
                    #baa，就是多加了头的情况。
                    dp[left][right] = max(dp[left + 1][right], dp[left][right - 1])
                
                maxlen = max(maxlen, dp[left][right])
            
        return maxlen
        
        
        
        
        