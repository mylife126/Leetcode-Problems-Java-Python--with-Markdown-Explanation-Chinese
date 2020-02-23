class Solution(object):
    def __init__(self):
        self.dirs = [[-1,0], [1,0], [0, -1], [0, 1]]
        
    def numIslands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        
        if not grid:
            return 0
        count = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == '1':
                    self.dfs(grid, i, j)
                    count +=1
        
        return count
    
    def dfs(self, grid, x, y):
        
        if x < 0 or x > len(grid) -1  or y < 0 or y > len(grid[0]) - 1 or grid[x][y] != '1':
            return
        
        #visited
        grid[x][y] = '*'
        for direction in self.dirs:
            newX = x + direction[0]
            newY = y + direction[1]
            self.dfs(grid, newX, newY)

'''approach II union Find

当我们发觉有两个grid都是1，这就形成了联系，我们可以用unionFind将这两个岛屿连在一起（assign to the same parent）

1. UF class里 init parent： 假设每一个1的岛屿都是自己的parent
2. init size： “1” 的岛屿都为size = 1
3. 在init的时候遇到1就 count +=1
4. 在union的时候，每union一次就count -=1
'''
class UF(object):
    def __init__(self, grid):
        self.mRows = len(grid)
        self.nCols = len(grid[0])
        self.count = 0
        self.parent = [-1 for i in range(self.mRows * self.nCols)]
        self.size = [-1 for i in range(self.mRows * self.nCols)]
        
        for i in range(self.mRows):
            for j in range(self.nCols):
                if grid[i][j] == '1':
                    self.parent[self.nCols * i + j] = self.nCols * i + j
                    self.size[self.nCols * i + j] = 1
                    self.count += 1
    
    def getRoot(self, nodeA):
        root = nodeA
        while(root != self.parent[root]):
            self.parent[root] = self.parent[self.parent[root]]
            root = self.parent[root]
        return root
    
    def union(self, nodeA, nodeB):
        rootA = self.getRoot(nodeA)
        rootB = self.getRoot(nodeB)
        sizeA = self.size[rootA]
        sizeB = self.size[rootB]
        
        if rootA != rootB:
            self.count -= 1
            if sizeA >= sizeB:
                self.parent[rootB] = rootA
                self.size[rootA] +=self.size[rootB]

            else:
                self.parent[rootA] = rootB
                self.size[rootB] += self.size[rootA]

class Solution(object):
    def numIslands(self, grid):
        if not grid:
            return 0
        mRows = len(grid)
        nCols = len(grid[0])
        
        unionFind = UF(grid)
        
        directions = [[-1,0], [1,0], [0, -1], [0, 1]]
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == '1':
                    grid[i][j] == '0'
                    nodeA = i * nCols + j
                    for direction in directions:
                        newX = i + direction[0]
                        newY = j + direction[1]
                        #检测边界条件是否满足
                        if newX >= 0 and newX < mRows and newY >=0 and newY < nCols and grid[newX][newY] == '1':
                            nodeB = newX * nCols + newY
                            unionFind.union(nodeA, nodeB)
                            
        return unionFind.count
                            
        
                    
        
                    
                    
        