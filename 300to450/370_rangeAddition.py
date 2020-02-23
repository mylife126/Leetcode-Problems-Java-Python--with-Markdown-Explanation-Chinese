'''
题目要求：
给定一个array， 都是0 然后长度为n
有给定了updates array， 每一个update规定了startidx 和 endidx 以及从 startidx~ endidx间的increaments

要求返回最后修改过的array
'''

class Solution(object):
    def getModifiedArray(self, length, updates):
        """
        :type length: int
        :type updates: List[List[int]]
        :rtype: List[int]
        """
        array = [0 for i in range(length)]
        for update in updates:
            startIdx = update[0]
            endIdx = update[1]
            increase = update[2]
            
            for i in range(startIdx, endIdx + 1):
                array[i] += increase
        return array
    

'''
approach 2  updates = [[1,3,2],[2,4,3],[0,2,-2]]
[0 0 0 0  0 ]  init
[0 2 0 0 -2 ]  update1 将start 上赋值2， 而end + 1 = -2
[0 0 3 0  0 ]  update2 同上
[-2 0 0 2 0 ]

[-2 2 3 2 -2]  最后得到的

[-2 0 3 5 3]   

'''        
class Solution(object):
    def getModifiedArray(self, length, updates):
        array = [0 for i in range(length)]
        for update in updates:
            start = update[0]
            end = update[1]
            inc = update[2]
            array[start] += inc
            if end + 1 < length:
                array[end + 1] += -inc
                
        for i in range(1, length):
            array[i] = array[i] + array[i - 1]
            
        return array