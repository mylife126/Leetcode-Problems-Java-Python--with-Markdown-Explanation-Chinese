'''
Solution1, DFS
Input:
11000
11000
00100
00011

Output: 3


每一个节点都是一个call，只要这个节点是1， 那么call的逻辑是看这个节点的上下左右是否有1，如果是1，则说明这几个island是连在一起的。直到这个call结束了，说明我们把所有的island群落找了一遍了， count+1.

但是我们要注意避重，因为11000
                      11000
                      00100
                      00011    当我们走到了grid[0][0]的时候，我们进入了一次dfs，以这个位pivot把他周围都扫了一遍，如果不标记，我们循环会走到grid[0][1] 又call了一次dfs，但其实这个已经再第一次dfs的时候就call过了，所以我们可以mark走过的1为*即可。

'''

class Solution:
    def __init__(self):
        self.dir = [[0, 1], [1, 0], [0,-1], [-1, 0]]
        
    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid:
            return 0
        
        count = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == '1':
                    self.dfs(grid, i, j)
                    count += 1
        return count
    
    def dfs(self, grid, x, y):
        if (x < 0 or x > len(grid) - 1 or y < 0 or y > len(grid[0]) - 1 or grid[x][y] != '1'):
            return
        grid[x][y] = '*'
        
        for dirs in self.dir:
            newX = x + dirs[0]
            newY = y + dirs[1]
            self.dfs(grid, newX, newY)
            
'''
Solution 2:
Union Find

思路是，
1. init UF: 第一次one pass扫所有的grid， 只要是1，我们的count + =1， 且对于这个grid我们一开始都默认为自己是一个岛屿群落，所以parent 为自己的idx ： i * nCols + j, size = 1
2. 第二次one pass的时候同样的上下左右查看是否存在关联的岛屿， 如果存在则call union，且count -= 1 因为我们把两个岛屿放到了一起。



'''

class Solution:
    class UF(object):
        def __init__(self, grid):
            self.nRows = len(grid)
            self.nCols = len(grid[0])
            self.parent = [None for i in range(self.nRows * self.nCols)]
            self.size = [0 for i in range(self.nRows * self.nCols)]
            
            self.count = 0
            
            for i in range(self.nRows):
                for j in range(self.nCols):
                    if grid[i][j] == '1':
                        node = i * self.nCols + j
                        self.parent[node] = node
                        self.size[node] = 1
                        self.count += 1
                        
                        
        def findRoot(self, node):
            root = node
            while(root != self.parent[root]):
                self.parent[root] = self.parent[self.parent[root]]
                root = self.parent[root]
            return root
        
        def union(self, nodeA, nodeB):
            rootA = self.findRoot(nodeA)
            rootB = self.findRoot(nodeB)
            
            if rootA != rootB:
                sizeA = self.size[rootA]
                sizeB = self.size[rootB]
                
                if (sizeA >= sizeB):
                    self.parent[rootB] = rootA
                    self.size[rootA] += sizeB
                else:
                    self.parent[rootA] = rootB
                    self.size[rootB] += sizeA
                
                self.count -= 1
                    
        
    def numIslands(self, grid: List[List[str]]) -> int:
        if not grid:
            return 0
        
        dirs = [[0, 1], [1, 0], [0,-1], [-1, 0]]
        nCols = len(grid[0])
        nRows = len(grid)
        
        uFind = Solution.UF(grid)
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == '1':
                    grid[i][j] = '*'
                    nodeA = i * nCols + j
                    
                    for d in dirs:
                        newX = i + d[0]
                        newY = j + d[1]
                        if newX >= 0 and newX < nRows and newY >= 0 and newY < nCols and grid[newX][newY] == '1':
                            nodeB = newX * nCols + newY
                            uFind.union(nodeA, nodeB)
        return uFind.count
                    
        
            
            
        
        