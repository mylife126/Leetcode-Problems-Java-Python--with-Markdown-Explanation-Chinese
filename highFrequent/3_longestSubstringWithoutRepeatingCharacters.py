from collections import defaultdict
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        '''
        p w w k e w
        ^
            ^  w:2 重复，需要缩短start pointer，直到这个重复的只有一次了。
        ->->^  w:1 
        思路是，用sliding window配合hash来做。 在end pointer不断延伸的时候，我们track每一个char出现的次数，一旦出现了重复，我们就需要缩短这个window，
        不断把hash里面的start pointer指向的char删掉，直到重复的那个char的次数只有1次了。
        
        在上述检测结束后我们测一次runningMax
        '''
        if not s:
            return 0
        myHash = defaultdict(int)
        maxLen = 0
        start = 0
        end = 0
        
        while(end < len(s)):
            currChar = s[end]
            myHash[currChar] += 1 #不断保存end pointer扩展时遇到的char的计数
            
            #做一次修正，如果这个char的次数大于1了，我们则需要缩短window 直到这个char的次数回到1
            #但要保证start 《 end的边界条件
            while(start <end and myHash[currChar] > 1):
                myHash[s[start]] -= 1    #缩短window
                start += 1               
            #修正过后算一次此刻的runningMax
            maxLen = max(maxLen, end - start + 1)
            end +=1  #继续扩张end pointer
            
        return maxLen
        