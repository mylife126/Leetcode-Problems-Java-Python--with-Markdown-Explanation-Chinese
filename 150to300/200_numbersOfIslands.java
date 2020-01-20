/*
DFS:
1. trigger dfs的条件就是当我们扫到了一个位置 == '1' 我们就说明发现了一个岛屿的开始， 
2. 这个时候进入dfs 开始 上下左右的以每一个节点为开端进行搜索
3. 我们的目标是找1， 所以判断出口的地方的边界条件可以加入 如果该grid ！= 1 直接return
4. 那么上述判断能过后就一定意味着我们找到了一个1，那这个时候就把这个1标记，不然我们一个节点返回后
   很可能这个节点的母节点还有一个方向没有走过，且这个方向就是刚刚没有标记的1，这样就会重复查找
   
   同理，我们的外部循环时，也会重新进入一次这个1，那就重复了
   
5. 每次dfs退出一次，就说明找完了一个岛屿，我们count++
*/


class Solution {
    private int[][] dirs = {{1,0},{-1,0},{0,-1},{0,1}};
    public int numIslands(char[][] grid) {
        if (grid == null){
            return 0;
        }
        int count = 0;
        for (int i = 0; i <grid.length; i++){
            for(int j=0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    dfs(grid,i,j);
                    count+=1;
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid, int x, int y){
        if (x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1 ||
            grid[x][y] != '1'){
            return;
        }
        grid[x][y] = '#';
        for (int[] dir : dirs){
            dfs(grid, x + dir[0], y + dir[1]);
        }
    }
}


/*
Union Find Solution:
思路是，依旧是简历 UF class，建造每一个grid的对应的1D parent array， 以及每一个相对应的rank array，对印的是每一个grid与其相连的node大小，or weighting。这个因为在后期merge两个grid到同一个cluster时候，我们要把weighting小的那一个放入大的cluster。

1. 建立1D array： 2D - > 1d的做法是 parent[] = int[m * n], 例如要取第二row 第三col的grid对应的parent就是
   parent[nCols * 2+ c], c= 3
   size[] = int[m * n]
   
2. trigger union find 的机制是grid[i][j] 是1， 那么我们对这个grid 上下左右进行neighor的查找

3. 最后我们查看parent array中的 有几个unique的id，即为有几个unique的parent，也就是有多少个cluster

*/
class Solution{
    private class UF{
        public int[] id;
        public int[] size;

        public UF(char[][] grid){
            int mRows = grid.length;
            int nCols = grid[0].length;
            
            id = new int[mRows *  nCols];
            size = new int[mRows * nCols];
            /*now only when the current grid shows 1, that means its initial parent is hiself, 
            similarly, its corresponding size would be initialized */
            for (int i = 0; i < mRows; i++){
                for (int j = 0; j < nCols; j++){
                    if (grid[i][j] == '1'){
                        id[i * nCols + j] = i * nCols + j;
                        size[i * nCols + j] = 1;
                    }
                    
                }
            }
        }

        private int getRoot (int node){
            while(node != id[node]){
                id[node] = id[id[node]];
                node = id[node];
            }
            return node;
        }

        public void union(int nodeA, int nodeB){
            
            int rootA = getRoot(nodeA);
            int rootB = getRoot(nodeB);
            // System.out.println(String.valueOf(rootA) + " " + String.valueOf(rootB));
            if (size[rootA] >= size[rootB]){
                id[rootB] = rootA;
                size[rootA] +=1;
            } else {
                id[rootA] = rootB;
                size[rootB] +=1;
            }
            // System.out.println(String.valueOf(id[rootA]) + " " + String.valueOf(id[rootB]));
        }    
    }
    
    public int[][] dirs = {{1,0},{-1,0},{0,-1},{0, 1}};
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0){
            return 0;
        }
        
        UF unionFind = new UF(grid);
        int mRows = grid.length;
        int nCols = grid[0].length;
        
        for (int i = 0; i < mRows; i++){
            for (int j = 0; j < nCols; j++){
                if (grid[i][j] == '1'){
                    //重点走过的地方标记一下这样可以省去下一次多余的union的计算
                    grid[i][j] = '0'; 
                    /*
                    now we need to unionfy this grid's up bottom left right
                    */
                    int nodeA = i * nCols + j;
                    for (int[] dir : dirs){
                        int x = i + dir[0];
                        int y = j + dir[1];
                        
                        if (x < mRows  && x >= 0 && y < nCols  && y >= 0 && grid[x][y] == '1'){
                            int nodeB = nCols * x + y;
                            // System.out.println(String.valueOf(x) + " " + String.valueOf(y) + "is 1");
                            unionFind.union(nodeA, nodeB);
                            // System.out.println("*****************");
                        }
                    }
                }
            }
        }
        //有几个不同的parent就是有几个不同的islands
        Set<Integer> uniqueId = new HashSet<>();
        for (int i : unionFind.id){;
            int root = unionFind.getRoot(i);
            if (unionFind.size[root] >=1){
                uniqueId.add(unionFind.getRoot(root));
            }
        }
        return uniqueId.size();
    }
}
/*
[["1","1","0","0","0"],
 ["1","1","0","0","0"],
 ["0","0","1","0","0"],
 ["0","0","0","1","1"]] */
/*


[["1","0","1","1","1"],
 ["1","0","1","0","1"],
 ["1","1","1","0","1"]] */