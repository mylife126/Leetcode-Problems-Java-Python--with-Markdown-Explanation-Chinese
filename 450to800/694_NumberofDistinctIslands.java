/*
Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.


Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.


用string记录dfs走过的路径，然后查看有几个不同的String

[[1,1,0],
 [0,1,1],
 [0,0,0],
 [1,1,1],
 [0,1,0]]
 
 注意这个corner case， 如果不在下述代码中加入 dir += path +"failed" 字符串会使得 num = 1
 因为 两个island的 走法都是 right - down - right.  所以我们需要加入所有的path的信息才可以！
*/

class Solution {
    String dir;
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        Set<String> path = new HashSet<>();
        
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    dir = "";
                    dfs(i, j, grid, "");
                    path.add(dir);
                }
            }
        }
        return path.size();
    }
    private void dfs(int x, int y, int[][] grid, String path){
        if (x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1 || grid[x][y] != 1){
            dir += path + "failed";
            return;
        }
        grid[x][y] = -1;
        dir += path;
        dfs(x + 1, y, grid, "down");
        dfs(x - 1, y, grid, "up");
        dfs(x, y + 1, grid, "right");
        dfs(x, y - 1, grid, "left");
    }
}

