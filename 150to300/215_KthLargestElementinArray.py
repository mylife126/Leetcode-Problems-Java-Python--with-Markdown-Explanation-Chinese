'''
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5


思路是， 他要第k个最大的数，那我们就heap将数据放进去，然后pop k次即可
'''
import heapq
class Solution(object):
    def findKthLargest(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        if not nums:
            return -1
        
        pq = []
        for num in nums:
            #需要递减排序，因为python是min heap。
            heapq.heappush(pq, -num)
            
        count = 0
        which = None
        while(pq):
            num = heapq.heappop(pq)
            count += 1
            which = num
            if count == k:
                break
                
        return -which
        