class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        if x < 0:
            return False
        stringNum = str(x)
        start = 0
        end = len(stringNum) - 1
        while (start < end):
            headChar = stringNum[start]
            endChar = stringNum[end]
            
            if headChar != endChar:
                return False
            
            start += 1
            end -= 1
        return True
        
        