/*
[[1 3 1]
 [1 5 1]
 [4 2 1]
 [4 5 2]]
 
很明显，走到每一格的sum基于前面从哪里走过来的，所以对于第一row和第一列，走到每一格的大小是固定的，因为规定了只能从上往下走或者从左往右走

对于  [1 3]
      [1 5], right bottom 5 那里来说， 走到他的这个位置可以从 3 往下走到它，或者从1往右走到他。 很明显 min(3 + 5, 1 + 5)选择6
      
      
1. state function, dp[m][n], 每一个位置代表走到这位置的sum是多少
2. init，对于dp[0][0] 来说 就是自己，然后第一行和第一列的第二位数开始，都是dp[i -1 ] + 自己
3. transfer function: dp[i][j]= min(上+自己， 左+自己)
4. return dp[i-1][j-1]
*/
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null){
            return 0;
        }
        //1 state function, each grid represents the sum needed to go up to this grid
        int[][] dp = new int[grid.length][grid[0].length];
        
        //2 init, the first grid is just itself, and the rest of the first column and first row is the itself + dplast
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        
        for(int j = 1; j <grid[0].length; j++){
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        
        //transfer function
        for (int i = 1; i < grid.length; i++){
            for (int j = 1; j < grid[0].length; j++){
                int itself = grid[i][j];
                dp[i][j] = Math.min(dp[i - 1][j] + itself, dp[i][j - 1] + itself);
            }
        }
        
        return dp[grid.length - 1][grid[0].length - 1];
    }
}