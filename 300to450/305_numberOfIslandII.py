'''思路是，每次添加一个岛屿的时候，我们就更新union find里的parent的记录，并且假设多了一个额外的岛屿count++
然后只有当这个岛屿相邻的四个方向上存在岛屿，才会trigger union的操作，将这些岛屿连在一起，count--
[0, 0]
'''

class UF():
    def __init__(self, m, n):
        self.parent = [-1] * (m * n)
        self.size = [0] * (m * n)
        self.count = 0
        self.mRows = m
        self.nCols = n
    
    def findRoot(self, nodeA):
        root = nodeA
        while (root != self.parent[root]):
            self.parent[root] = self.parent[self.parent[root]]
            root = self.parent[root]
        return root
    
    def updateParent(self, newAdded):
        '''only when this newly added islands coordinate is truly added first time, shall we update'''
        x = newAdded[0]
        y = newAdded[1]
        if (self.parent[x*self.nCols + y] < 0):
            self.parent[x * self.nCols + y] = x * self.nCols + y
            self.size[x * self.nCols + y] = 1
            self.count += 1
    
    def union(self, nodeA, nodeB):
        parentA = self.findRoot(nodeA)
        parentB = self.findRoot(nodeB)
        
        if (parentA != parentB):
            if (self.size[parentA] >= self.size[parentB]):
                self.parent[parentB] = parentA
                self.size[parentA] += self.size[parentB]
            else:
                self.parent[parentA] = parentB
                self.size[parentB] += self.size[parentA]
            self.count -= 1
            
class Solution(object):                    
    def numIslands2(self, m, n, positions):
        """
        :type m: int
        :type n: int
        :type positions: List[List[int]]
        :rtype: List[int]
        """
        grid = [[0 for i in range(n)] for j in range(m)]
        unionFind = UF(m,n)
        directions = [[1,0],[-1,0],[0,1],[0,-1]]
        ans = []
        for whereAdded in positions:
            unionFind.updateParent(whereAdded)
            x = whereAdded[0]
            y = whereAdded[1]
            grid[x][y] = 1
            nodeA = x * n + y
            
            for dirs in directions:
                newX = dirs[0] + x
                newY = dirs[1] + y
                if (newX >= 0 and newX < m and newY >= 0 and newY < n and grid[newX][newY] == 1):
                    nodeB = newX * n + newY
                    unionFind.union(nodeA, nodeB)
            ans.append(unionFind.count)
        
        return ans
            