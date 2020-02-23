'''
1. map 找到所有 key = 先修课 val[后修课]
2. 每一门后修课有了一门先修课，它的入读就加1， 用array【n】来记录，每一个n对应了一门课
2. que 放入先修课的入度为零的课
4. bfs遍历每一个que里面的课：
    把此时入读为零的后修课全部拿出来，他们的入读减一，如果入读也为零了，就放入que中
    每poll了一次， finished +=1
5. 最后return n == finished
'''

from collections import defaultdict
class Solution(object):
    def canFinish(self, numCourses, prerequisites):
        """
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: bool
        """
        if not prerequisites:
            return True
        
        pre2after = defaultdict(list)
        indegree = [0 for i in range(numCourses)]
        
        for courses in prerequisites:
            pre = courses[1]
            after = courses[0]
            indegree[after] += 1
            pre2after[pre].append(after)
        
        whichToTake = None
        q = []
        
        for i in range(len(indegree)):
            if indegree[i] == 0:
                whichToTake = i
                q.append(whichToTake)
                
        finished = 0
        while(q):
            size = len(q)
            whichFinished = q.pop(0)
            finished +=1

            afterCourse = pre2after[whichFinished]
            #最后一门课是没有后修课的
            if len(afterCourse) == 0:
                continue
            #对所有的后修课进行修改他们的入度
            for after in afterCourse:
                indegree[after] -= 1
                if indegree[after] == 0:
                    q.append(after)
                        
        return finished == numCourses
            
                
                
        
        
        