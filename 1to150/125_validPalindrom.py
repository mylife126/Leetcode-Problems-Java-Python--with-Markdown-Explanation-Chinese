'''
思路依旧还是two pointer
但需要判断此刻的char是不是 alpha or number 不然需要修改index


Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
'''

class Solution(object):
    def isPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        if not s:
            return True
        
        start = 0
        end = len(s) - 1
        
        while(start < end):
            while(start < end and not s[start].isalnum()):
                start +=1
            
            while(start < end and not s[end].isalnum()):
                end -= 1
            
            #需要全部小写，不然会出错
            headchar = s[start].lower()
            endchar = s[end].lower()
            # print(headchar, endchar)
            if headchar != endchar:
                return False
            
            start +=1
            end -=1
            
        return True