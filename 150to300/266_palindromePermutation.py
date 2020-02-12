'''Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true

能组成palindrome的前提就是，存在仅存在一个char的出现的次数为奇数！
aaabbb不可能， 但是aabbb就可以， 同理aabbe可以
'''

from collections import defaultdict 
class Solution(object):
    def canPermutePalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        if not s:
            return False

        myHash = defaultdict(int)
        for char in s:
            myHash[char] += 1
            
        count = 0 
        for char in myHash:
            if myHash[char] % 2 != 0:
                count +=1
        if count > 1:
            return False
        
        return True
        
        
            
        