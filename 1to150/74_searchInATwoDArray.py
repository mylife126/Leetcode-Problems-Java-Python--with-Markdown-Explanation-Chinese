'''
题目意思是，给一个旋转上升排序的数组，找一个target
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true

我们发觉特征是每一行最大的数是最后，而它又是所有row中最小的。 

所以我们就右上角做pivot， 如果pivot都小于target了，那就没有必要搜索这个row了 直接往下走。 同理，如果小了，那只能再当前row搜索了。
'''
class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if not matrix:
            return False 
        
        mRows = len(matrix)
        nCols = len(matrix[0])
        
        founded = self.bisearch(matrix, 0, nCols - 1, target)
        return founded
    
    def bisearch(self, matrix, x, y, target):
        #如果我门搜到了最最左边 或者 搜到了最最底，也没搜到，那就是false了
        if y < 0 or x > len(matrix) - 1:
            return False 
        
        curr = matrix[x][y]
        if curr == target:
            return True
        
        elif curr < target:
            return self.bisearch(matrix, x + 1, y, target)
        
        else:
            return self.bisearch(matrix, x, y - 1, target)