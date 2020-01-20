class Solution {
    private class UF{
        int[] parent; int[] size; int mRows; int nCols; int count;
        public UF(int m, int n) {
            parent = new int[m * n];
            size = new int[m * n];
            mRows = m;
            nCols = n;
            for (int i = 0; i < parent.length; i++){
                parent[i] = -1;
                size[i] = 1;
            }
        }
        public void update(int[] newAdded){
            /*
            Corner Case: [[0,0],[0,1],[1,2],[1,2]], 倒数两个加入的岛是重复的，不该重复加，
            不然会让答案多一个岛出来！！
            */
            if (parent[nCols * newAdded[0] + newAdded[1]] < 0) {
                parent[nCols * newAdded[0] + newAdded[1]] = nCols * newAdded[0] + newAdded[1];
                size[nCols * newAdded[0] + newAdded[1]] = 1;
                count +=1;
            }
        }
        public boolean isValid(int node){
            return parent[node] >= 0;
        }
    
        public int findRoot(int node){
            int root = node;
            while(root != parent[root]){
                parent[root] = parent[parent[root]];
                root = parent[root];
            }
            return root;
        }
        
        public void union(int nodeA, int nodeB){
            int rootA = findRoot(nodeA);
            int rootB = findRoot(nodeB);
            /*
            重点！！判断是否同一个root 既可以减少计算，同时可以避免重复减少计算！ 看下面的cornercase
            就出现了重复减少的情况！
            */
            if (rootA != rootB) {
                if (size[rootA] >= size[rootB]){
                    parent[rootB] = rootA;
                    size[rootA] += size[rootB];
                } else {
                    parent[rootA] = rootB;
                    size[rootB] += size[rootA];
                }
                --count;                
            } 
        }
    }
    private int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if (positions == null || positions.length == 0){
            return new ArrayList<Integer>();
        }
        //建里这个岛屿的map
        int[][] grid = new int[m][n];
        
        UF uf = new UF(m, n);
        List<Integer> ans = new ArrayList<>();
        //遍历每一次加入的岛屿
        for (int i = 0 ; i < positions.length; i++){
            int[] position = positions[i];
            int x = position[0];
            int y = position[1];
            //加入这个新的岛屿去union find里
            uf.update(position);
            //修改图
            grid[x][y] = 1;
            int nodeA = x * n + y;
            //记录这一轮加入岛屿后 有多少上下左右的岛屿与他重复
            for (int[] dir : dirs){
                int neiX = x + dir[0];
                int neiY = y + dir[1];
                int nodeB = neiX * n + neiY;
                if (neiX >= 0 && neiX < m && neiY >=0 && neiY < n && grid[neiX][neiY] == 1){
                    uf.union(nodeA, nodeB);
                }
            }
            ans.add(uf.count);
        }
        return ans;
    }
}
// // /*
// // 0 0 0
// // 0 0 0
// // 0 0 0

// // 0 1 0
// // 0 0 0    1
// // 0 0 0

// // 0 1 0
// // 0 0 1    2
// // 0 0 0

// // 0 1 0
// // 0 0 1     3
// // 0 1 0

// // 0 1 0
// // 1 0 1     4
// // 0 1 0

// // 0 1 1
// // 1 0 1     5 - 1 -1 = 3
// // 0 1 0

// // 1 1 1
// // 1 0 1     3 + 1 - 1- 1 = 2
// // 0 1 0

// // 1 1 1
// // 1 1 1    2 + 1 - 1 - 1 - 1 = -1
// // 0 1 0

// // */