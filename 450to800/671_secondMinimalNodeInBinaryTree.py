# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

'''
Example 1:

Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.


思路是：
1. BFS traverse所有的treenode， 期间计算runningMinimal, 并且把每一个node val放入set里 因为我们有重复的数字
2. one pass所有的set里的数值，然后如果这个数能大于最小值，我们就比对secondMin和这个数
3. 最后返回second min即可

O(n)

'''

class Solution(object):
    def findSecondMinimumValue(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        
        valSet = set()
        q = []
        q.append(root)
        smallest = float('inf')
        while(q):
            size = len(q)
            for i in range(size):
                thisNode = q.pop(0)
                smallest = min(thisNode.val, smallest)
                
                valSet.add(thisNode.val)
                
                if thisNode.left != None:
                    q.append(thisNode.left)
                if thisNode.right != None:
                    q.append(thisNode.right)
                    
        second = float('inf')
        for v in valSet:
            if v > smallest:
                second = min(second, v)
                
        return second if second != float('inf') else -1
                
        
        