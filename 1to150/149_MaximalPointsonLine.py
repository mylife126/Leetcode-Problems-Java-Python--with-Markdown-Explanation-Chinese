'''Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o  o      o
|        o  o
|  o        o
+------------------->
0  1  2  3  4  5  6



两点组成一条线，而能表示线段（segmentation）的标量则是每个线段的斜率。 所以natively会想到以每一个点作为pivot，然后遍历所有的点来构成线段，然后求取线段的斜率，key = 斜率， value = count。 但是这有一个问题，那就是上述情况， 有两条线段是平行的， 那这个斜率是一样的，但点不在同一条线上。

所以我们可以以每一个点建立一个map，这个点下有多少个共线的是可以由唯一的斜率组成的，我们再保持一个globalMax，和一个localMax。 localMax记录的是以i为pivot的时候构成了很多线段，在不同线段下有多少点是共线的。但是这个localMax不等同于全局最优，所以每次一个pivot结束后我们比对globalMax和这一个pivot下的最大localMax就避免了上述的情况。

计算斜率也有讲究：
1. 存在斜率： y2 - y1/ x2 - x1
2. 斜率不存在：那一定是x1 == x2， 我们设他为无限大


还有一个情况可以剪枝，那就是给的数组里，有重复的点[[1,1],[1,1,]]那我们就需要跳过第二个[1,1]来作为pivot， 但是同时在inner loop里找同线的时候，第二个[1,1]直接让localMax++， 因为同点， 我们把同样的点位数 + whateverMaxLocally都是合理的。

算法：
1. init globalMax = 1， 因为至少肯定有一个点在一个直线上
2. 判断corner case，如果给定数组的长度小于2， 则只能是数组的长度
3. set，来记录哪些pivot已经算过了，可以用 string来存， "coordX + coordY"
4. 外循环每一个点位作为pivot：
       inner loop计算所有其余的点位构成的线段斜率， 相同斜率的count++
       如果有点位和pivot一直，same++
       比对此刻的localMax
   最后比对以这个i项为pivot的时候最大共线点个数和globalMAx
  
'''
from collections import defaultdict
from decimal import *     #[[0,0],[94911151,94911150],[94911152,94911151]] 这个是corner case！所以要提高精度！！！
class Solution(object):
    def maxPoints(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        if (len(points) < 2):
            return len(points)
        
        globalMax = 1   #set the globalMax counts colinear points
        seen = set()    #set the hashset to save which coord has been calculate already 
        # allGroup = defaultdict(dict)
        
        for i in range(len(points)):
            pivot = points[i]
            #如果这个点作为过pivot了则省略计算
            if (str(pivot[0]) + '-' + str(pivot[1])) in seen:
                continue
            
            
            # withSameSlope = defaultdict(list)
            myHash = defaultdict(int)
            samePoint = 1   #record howmany other points are the same as the pivot, 
                            #this would be used to accumulate the number of colinear counts
            localMax = 0    #注意，这里得是0，因为我们最后会用 localMax + same，如果有一个cornercase就是[[0,0],[0,0]]
                            #我们会执行same++这操作，然后最后localMax + same， 这就会多加一项
            
            #calculate other all slope constructed by other points
            #剪枝，我们从i + 1后计算，因为如果存在最大值，在这之前一定计算过[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]], 当我们计算[3,2]
            #的时候就已经将所有的其余的points都计算完毕了
            for j in range(i + 1, len(points)):
                    
                secondPoint = points[j]
                if self.isSame(pivot, secondPoint):
                    samePoint +=1
                    continue
                    
                slop = self.getSlope(pivot, secondPoint)
                # print(i, j, slop)
                myHash[slop] += 1
                localMax = max(localMax, myHash[slop])
                
                # withSameSlope[slop].append(secondPoint)
            
            seen.add(str(pivot[0]) + '-' + str(pivot[1]))
            totalSame = localMax + samePoint   #after looping through every other point, we accumulate the samepoint
            
            globalMax = max(globalMax, totalSame)
            print(myHash)
            # print("totalSame", totalSame)
            
        # print(allGroup)
        return globalMax
    
    def getSlope(self, pointA, pointB):
        #corner case, where the slope is not existed
        if (pointA[0] == pointB[0]):
            return float('inf')
        
        # (yDiff) / (xDiff）
        slope = (Decimal(pointB[1] - pointA[1])) / (Decimal(pointB[0] - pointA[0]))
        return Decimal(slope)
        
    def isSame(self, pointA, pointB):
        return pointA[0] == pointB[0] and pointA[1] == pointB[1]
                
        