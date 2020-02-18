# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
'''
Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
'''
import heapq
class Cell(object):
    def __init__(self, val, diff):
        self.val = val
        self.diff = diff
        
    def __lt__(self, that):
        return self.diff < that.diff  #升排列
    
class Solution(object):
    def __init__(self):
        self.pq = []
        
    def closestKValues(self, root, target, k):
        """
        :type root: TreeNode
        :type target: float
        :type k: int
        :rtype: List[int]
        """
        if not root:
            return -1
    
        self.helper(root, target)
        ans = []

        #pop 前k个最小的距离的cell，然后取出其中的val
        for i in range(k):
            ans.append(heapq.heappop(self.pq).val)
        return ans
    
    def helper(self, root, target):
        if (root == None):
            return
        
        thisVal = root.val
        diff = abs(thisVal - target)
        heapq.heappush(self.pq, Cell(thisVal, diff))
        self.helper(root.left, target)
        self.helper(root.right, target)
        
        
    
    
        
        