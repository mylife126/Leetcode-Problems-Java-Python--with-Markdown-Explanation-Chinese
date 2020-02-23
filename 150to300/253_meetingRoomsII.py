'''
[[0,30], [5,10], [15,20]]
我们能够有一个屋子空出的前提是，这个屋子的end < 下一个屋子的start。 例如[5,10] and [15,20]就可以作为一个屋子来使用

那么逻辑就是：
1. 首先将intervals sort一遍，按照start来递增排序
2. 其次是用priorityqueue维护一个最先结束的room， 因为有一个case：
   [[0,30],[],[],[],[],[20，21],[31,40]], 距离第一个room和最后一个room见有很多可以merge的interval， 
   
3. 循环每一个interval，如果pq为空 count +=1， pq放入这个屋子
   如果pq最先结束的屋子依旧没法被现在循环到的屋子里用， count += 1， push现在的屋子
   不然pop这个屋子，
'''

import heapq
class wrapper(object):
    def __init__(self, interval):
        self.interval = interval
        self.start = self.interval[0]
        self.end = self.interval[1]
        
    def __lt__(self,that):
        return self.end < that.end
        
class Solution(object):
    def minMeetingRooms(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: int
        """
        
        if not intervals:
            return 0
        intervals.sort(key = lambda x : x[0])
        pq = []
        count = 0
        for i in range(len(intervals)):
            curr = intervals[i]
            currStart = curr[0]
            if len(pq) == 0:
                count += 1
            else:
                firstEndInterval = pq[0]
                firstEnd = firstEndInterval.end
                if firstEnd <= currStart:
                    heapq.heappop(pq)
                else:
                    count += 1
            heapq.heappush(pq, wrapper(curr))
            
        return count 
                
            
        