'''
Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

题目要求找到最长的长度的pali， 用的char是这个里面的，那么这就意味着我们只需要找到所有的chars满足pali的要求即可，即为唯一个奇数项
1. 记录所有char的出现的次数
2. 找到所有odd次数的char， 保留最长的那个char， 其余的全部-1
3. 最后所有char的总字数加起来就是最长的pali
'''

from collections import defaultdict
class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return 0
        
        myHash = defaultdict(int)
        for char in s:
            myHash[char] +=1  
        odds =[]
        length = 0
        for key in myHash.keys():
            count = myHash[key]
            if count % 2 != 0:
                #key value pair
                odds.append((key, count))
            else:
                length += myHash[key]
                
        #处理奇数项的所有
        if len(odds) != 0 :
            #按照次数的大小倒叙排列
            odds.sort(key = lambda x: x[1], reverse = True)
            for i in range(len(odds)):
                if i == 0:
                    #第一项一定是最大奇数次
                    length += myHash[odds[i][0]]
                    continue
                #其余的减一使得他们的长度位偶数
                length += myHash[odds[i][0]] - 1
                
        return length 
                    
        
        