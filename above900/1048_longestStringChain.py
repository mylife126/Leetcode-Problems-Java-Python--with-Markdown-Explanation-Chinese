'''
Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".

1. 将原来的wordslist 按照长度重新排序， 这样确保再后期递归的时候每一次新加入的字符一定是长度大于前一个数的
2. 每次递归于之前的indx + 1， 因为我们找到了a之后，肯定要往a之后的找
    那么，两个情况：
        a。 temp长度为零，则说明没有需要比对的，直接添加这一个string
        b。 不然我们需要测试上一刻的string和这一刻的string是不是chain的关系，如果是再放入temp里
        c。 这里是重点，这说明每次递归call的时候我们并不是每次都会加入新的string，所以不能每次return的时候就temp.pop（）， 我们需要
            记录一个boolean added来表示我们在这一层加了新的东西， 
    所以递归出口是startIndx == len(words)，
    这个时候就将temp里全部加入的合法的list《string》 放入collection array中
    如果boolean added == true， 我们回溯上一刻加入的东西，不然不用操作删除

3. 找到所有合法的list《string》后比对每一个list的长度，输出最大长度即可
'''
class Solution(object):
    def longestStrChain(self, words):
        """
        :type words: List[str]
        :rtype: int
        """
        #第一步是将原来的数组sort
        words.sort(key = lambda x : len(x))
        
        collection = []  #list<List<String>> 记录的是所有合法的chain strings set
        temp = []        #记录递归过程里可能的chain set
        start = 0        #pivot point
        self.helper(words, start, temp, collection)
        
        maxLen = 0
        for item in collection:
            maxLen = max(maxLen, len(item))
        
        return maxLen
        
    def helper(self, words, start, temp, collection):
        #当pivot 到了尾， 则return
        if start == len(words):
            collection.append(temp[::])
            return 
        #遍历每一个pivot，以及其以后的所有
        for i in range(start, len(words)):
            selectionWord = words[i]
            added = False
            #case1， 此刻的temp是空的，则没有比对的，直接将这一刻的放入即可
            if len(temp) == 0:
                temp.append(selectionWord)
                added = True
            else:
                #case2， 不然则需要对比上一刻和这一刻是不是满足chain的关系
                lastWord = temp[len(temp) - 1]
                if self.isValid(lastWord, selectionWord):
                    temp.append(selectionWord)
                    added = True
            
            self.helper(words, i + 1, temp, collection)
            #只有当这一层递归add过东西了，我们才能回溯
            if added:
                temp.pop()
        
    def isValid(self, s1, s2):
        '''满足chain的两个条件， s2长度大于s1， 且s2中的字符不能有多于1个不存在于s1中'''
        charSet = set()
        if len(s2) <= len(s1):
            return False
        for c in s1:
            charSet.add(c)
        count = 0
        for c in s2:
            if c not in charSet:
                count += 1
            if count > 1:
                return False
        return True


'''DP solution
Sort the words by word's length. (also can apply bucket sort)
For each word, loop on all possible previous word with 1 letter missing.
If we have seen this previous word, update the longest chain for the current word.
Finally return the longest word chain.


Complexity
Time O(NlogN) for sorting,
Time O(NSS) for the for loop, where the second S refers to the string generation and S <= 16.
Space O(NS)
'''  
from collections import defaultdict
class Solution:
    def longestStrChain(self, words):
        if not words:
            return 0
        
        words.sort(key = lambda x : len(x))
        dp = defaultdict(int)
        length = 0
        for word in words:
            dp[word] = 1
            for i in range(len(word)):
                subWord = word[:i] + word[i + 1 :]
                # print(subWord, word)
                if subWord in dp:
                    dp[word] = max (dp[word], dp[subWord] + 1)
            length = max(length, dp[word] )
        
        return length
            
        
                
                
        
        