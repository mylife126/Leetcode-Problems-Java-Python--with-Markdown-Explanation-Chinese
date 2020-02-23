'''
state function: dp[i + 1], 每一项代表了 upto这一项是否breakable， 
breakable的条件是： word[x : i] in the wordset, 而第x位是breakable的

init： dp[0] = True,  因为第零位是空

transfer： upto ith, 检测它之前的 x ~ ith是否在word list中，且检测dp[x]是否true

'''
class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        if not s or not wordDict:
            return False
        
        wordSet = set()
        for word in wordDict:
            wordSet.add(word)
            
        #1 state function
        dp = [False for i in range(len(s) + 1)]
        
        #2 init the state function
        dp[0] = True
        
        for i in range(1, len(s) + 1):
            #倒叙
            wordIdx = i - 1
            for j in range(i, -1, -1):
                subString = s[j : i]
                if subString in wordSet and dp[j]:
                    dp[i] = True
        
        return dp[len(s)]
                
            
        