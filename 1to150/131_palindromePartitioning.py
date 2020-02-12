'''
aab
以每一个字符为pivot，遍历所有的可能，并且检验是不是pali

a  -> yes
      a  -> yes
             b  -> yes
             return back to second a 
      ab -> no
     循环结束 return back to first a
aa -> yes
   -> b yes 
   return back to aa
   -> aab no
   循环结束
'''


class Solution(object):
    def partition(self, s):
        """
        :type s: str
        :rtype: List[List[str]]
        """
        if not s:
            return []
        ans = []
        temp = []
        startPivot = 0
        self.bt(s, ans, temp, startPivot)
        return ans
    
    def bt(self,s, ans, temp, startPivot):
        if startPivot == len(s):
            ans.append(temp[::])
            return
        
        for i in range(startPivot, len(s)):
            subString = s[startPivot: i + 1]
            if (self.isPali(subString)):
                temp.append(subString)
                self.bt(s, ans, temp, i + 1)
                temp.pop()
                
    def isPali(self, string):
        start = 0
        end = len(string) - 1
        while(start <= end):
            cStart = string[start]
            cEnd = string[end]
            if (cStart == cEnd):
                start +=1
                end -= 1
            else:
                return False
        return True