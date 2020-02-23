'''
sliding window
1. 按照start 来sort，[[1,3],[2,6],[8,10],[15,18]]
2. 双指针，第一个指针指在基array， 第二个找合法的end array (pivotPointer + 1)， 满足的条件是 endarray的start <= 基array的end
   找到合法的end array后，pivotPointerr++， 
   
   其中我们的end 需要用max（end, endArray[1])来匹配，因为很有可能endArray的end小于前一刻的end。 我们要保证end最大化来覆盖所有
   
   循环结束后，还有一组会没有添加到ansarray里
'''

class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: List[List[int]]
        """
        if not intervals:
            return []
        
        startPointer = 0
        ans = []
        
        intervals = sorted(intervals, key = lambda x : x[0])
        
        while(startPointer < len(intervals)):
            pivotArray = intervals[startPointer]
            start = pivotArray[0]
            end = pivotArray[1]
            #一直往后面看一位，如果满足了合法的条件，我们就一直往后找, 更新end
            while((startPointer + 1)< len(intervals) and end >= intervals[startPointer + 1][0]):
                #[[1,4],[2,3]]  CornerCASE!!!!
                #end = intervals[startPointer + 1][1]  
                end = max(end, intervals[startPointer+ 1][1])
                startPointer +=1
                
            ans.append([start, end])
            startPointer += 1
            
        return ans