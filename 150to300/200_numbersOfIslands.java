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