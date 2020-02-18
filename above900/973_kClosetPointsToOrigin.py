'''
Approach 1:
将point 和 dist存入cell中，
然后用heap来存放这个cell，总动排序

最后pop前K的cell里的point即可
'''
import math
import heapq
class Cell(object):
    def __init__(self, point, dist):
        self.point = point
        self.dist = dist
    
    def __lt__(self, that):
        return self.dist < that.dist

class Solution(object):
    def kClosest(self, points, K):
        """
        :type points: List[List[int]]
        :type K: int
        :rtype: List[List[int]]
        """
        if not points :
            return []
        
        ans = []
        pq = []
        
        for point in points:
            thisDist = self.getDist(point)
            thisCell = Cell(point, thisDist)
            heapq.heappush(pq, thisCell)
            
        
        for i in range(K):
            ans.append(heapq.heappop(pq).point)
        
        return ans
    
    def getDist(self, point):
        return math.sqrt(point[0] **2 + point[1] **2)
       
'''
Approach 2
计算所有的dist， 然后排序。 找到第K个大的dist，
然后再做一次one pass 计算每一个点位距离原点的距离，如果能小于第k个则合法，放入ans中

'''
import math
class Solution(object):
    def getDist(self, point):
        return math.sqrt(point[0]**2 + point[1]**2)
        
    def kClosest(self, points, K):
        if not points:
            return []
        
        ans = []
        dist = [self.getDist(x) for x in points]
        dist.sort()
        
        kTh = dist[K - 1]
        for point in points:
            thisDist = self.getDist(point)
            if thisDist <= kTh:
                ans.append(point)
        
        return ans
        