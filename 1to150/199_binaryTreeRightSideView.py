# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

'''
   1
 2   3
4

要求返回[1,3] 所以我们可以bfs，每次从左到右的放入queue里
，所以每一层pop的时候，最后pop出来的一定是最右边的node。

那么我们需要维护一个count，当count == 这一层的长度的时候就append，因为我们到了最后一个node了

'''
class Solution(object):
    def rightSideView(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if not root:
            return []
        q = []
        ans = []
        q.append(root)
        while(q):
            size = len(q)
            # print(size)
            count = 0
            for i in range(size):
                node = q.pop(0)
                count += 1
                if count == size:
                    ans.append(node.val)  
                if node.left != None:
                    q.append(node.left)
                if node.right != None:
                    q.append(node.right)
        
        return ans
                
        