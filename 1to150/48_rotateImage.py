'''
00 01 02
11 12 13
21 22 23

Transpose

00 11 21
01 12 22
02 13 23

Reverse

21 11 00
22 12 01
23 13 02

'''

class Solution(object):
    def transpose(self, matrix):
        mRows = len(matrix)
        nCols = len(matrix[0])
        #newMatrix = [[0 for j in range(nCols)] for i in range(mRows)]
        for i in range(mRows):
            for j in range(i, nCols):
                temp = matrix[j][i]
                matrix[j][i] = matrix[i][j]
                matrix[i][j] = temp

    def reverseRow(self, matrix):
        #注意这里必须用index来reference ，如果是 for row in matrix 这个不是reference
        for i in range(len(matrix)):
            matrix[i] = matrix[i][::-1]
            
    def rotate(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: None Do not return anything, modify matrix in-place instead.
        """
        self.transpose(matrix)
        self.reverseRow(matrix)
        return matrix
        
        
        
        
        