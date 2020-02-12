'''
思路还是： 对于一个panlindrome来说，至多只能有一个奇数个出现次数的字符， 那么我们可以用hash遍历所有的char，记录他们的
出现的次数，然后找出所有的奇数个出现的字符串，并且将次数最大那个保留，其余的全部-1使得他们的出现的次数为偶数。

然后这时候的hsh里所有的字符出现的次数都是偶数加一个最大奇数次
'''


class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        if not s:
            return ""
        
        from collections import defaultdict
        q = []
        myHash = defaultdict(int)
        for c in s:
            myHash[c] += 1
        
        hasOdd = False
        #将奇数次的都找出来，并且放入q中
        for c in myHash:
            if myHash[c] % 2 != 0:
                hasOdd =True
                q.append([myHash[c], c])
        #如果我们原来的字符里有奇数次的，那么我们需要修复这些次数，保留最大的，其余的减1
        if hasOdd:
            q.sort(key = lambda x: x[0], reverse = True)
            for i in range(len(q)):
                if i == 0:
                    myHash[q[i][1]] = q[i][0]
                else:
                    myHash[q[i][1]] = q[i][0] - 1

        runningSum = 0
        for c in myHash:
            runningSum += myHash[c]
        return runningSum