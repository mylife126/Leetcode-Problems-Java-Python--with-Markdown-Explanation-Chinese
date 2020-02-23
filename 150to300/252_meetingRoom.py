'''
[[2,4], [7,10]]
[[0,30],[5,10],[15,20]]

需要满足下一位的interval的start 》 前一位的end

'''


class Solution(object):
    def canAttendMeetings(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: bool
        """
        if not intervals:
            return True
        
        intervals.sort(key = lambda x: x[0])
        for i in range(len(intervals) - 1):
            curr = intervals[i]
            nextInter = intervals[i + 1]
            
            end = curr[1]
            start = nextInter[0]
            
            if start < end:
                return False
            
        return True
            
        
        