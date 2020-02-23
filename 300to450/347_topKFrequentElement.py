'''
solution1: O(n2logn)

用heap和hash的操作，hash{num : cell<num, count>}; 然后heap用count的顺序来做priorityqueue的key
hash调取heap里所存的Cell的指针，然后修改cell里所对应的该num出现的次数，

最后heappop k次即可
'''
#用cellclass记录是那一个number 以及 出现的次数
class Cell(object):
    def __init__(self, which):
        self.val = which
        self.count = 0
        
    def __lt__(self, that):
        return self.count > that.count
    
import heapq
class Solution(object):
    def topKFrequent(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """
        
        ans = []
        if not nums:
            return ans
        pq = []
        myHash = {}
        
        for num in nums:
            #如果number没有出现过 就新建一个内存，然后存入number以及其出现的次数 再放入pq中
            if num not in myHash:
                newCell = Cell(num)
                newCell.count +=1
                myHash[num] = newCell
                heapq.heappush(pq, newCell)
            #不然就修改内存里的cell，然后重新排序pq
            else:
                thisCell = myHash[num]
                thisCell.count += 1
                myHash[num] = thisCell
                heapq.heapify(pq)      #重新更新一次heap
        
        
        for i in range(k):
            ans.append(heapq.heappop(pq).val)
        
        return ans
    
'''better hash and pqsolution， two pass 
o(nlogn)

two pass: 
1. 存入hash和counts
2. 将 将(-counts, num) 放入pq   (python里的heap是min heap，所以我们想要递减排序，可以把key放在第一位然后取负就可以了)
3. pop k 次
'''
import heapq
from collections import defaultdict
class Solution(object):
    def topKFrequent(self, nums, k):
        ans = []
        if not nums:
            return nums
        
        pq = []
        myHash = defaultdict(int)
        
        for num in nums:
            myHash[num] +=1
        
        for key in myHash.keys():
            heapq.heappush(pq, (-myHash[key], key))
            
        for i in range(k):
            ans.append(heapq.heappop(pq)[1])
        return ans
            
        
