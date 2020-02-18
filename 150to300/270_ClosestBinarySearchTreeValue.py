# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
'''

      1
 null    2        target = 3.428571
 
一下方案是遍历所有tree， linear time， 来搜索最佳答案
判断最佳的node是 diff(nodeVal - target)， 如果diff更小了，则赋值给答案，否则不变，
然后递归其余的node，并把更新过的diff放入函数里
'''
class Solution(object):
    def __init__(self):
        self.min = float('inf')
        
    def closestValue(self, root, target):
        """
        :type root: TreeNode
        :type target: float
        :rtype: int
        """
        if not root:
            return -1
        
        self.helper(root, target, float('inf'))
        return self.min
        
    def helper(self, root, target, diff):
        if (root == None):
            return 
        
        thisVal = root.val
        diffNew = abs(thisVal - target)
        
        if diffNew <= diff:
            self.min = thisVal
            diff = diffNew
            
        self.helper(root.left, target, diff)
        self.helper(root.right, target, diff)
        
        
'''Binary Search的方案
如果target小于当前的val， 那唯一可能的答案就在当前的node的左边
反之亦然
然后保存runningMin
'''        
class Solution(object):
    def __init__(self):
        self.min = float('inf')
        
    def closestValue(self, root, target):
        """
        :type root: TreeNode
        :type target: float
        :rtype: int
        """
        if not root:
            return -1
        
        self.helper(root, target, float('inf'))
        return self.min
        
    def helper(self, root, target, diff):
        if (root == None):
            return 
        
        thisVal = root.val        
        diffNew = abs(thisVal - target)
        if diffNew <= diff:
            self.min = thisVal
            diff = diffNew       #更新diff 以便于下一次递归对比
            
        if thisVal > target:
            self.helper(root.left, target, diff)
        else:
            self.helper(root.right, target, diff)
        
            
        
        
        
        