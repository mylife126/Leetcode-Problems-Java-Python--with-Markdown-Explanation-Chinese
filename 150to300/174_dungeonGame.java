class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null){
            return 1;
        }
        
        int m = dungeon.length;
        int n = dungeon[0].length;
        //state function， each grid represents how much HP minimal are when it reaches this grid
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++){
            for (int j = 0; j <= n; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[m - 1][n] = 1;
        dp[m][n - 1] = 1;
        
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j --){
                int minAfterThisGrid = Math.min(dp[i + 1][j], dp[i][j + 1]);
                int minUpponThisGrid = minAfterThisGrid - dungeon[i][j];
                if (minUpponThisGrid <= 0) {
                    minUpponThisGrid = 1;
                }
                
                dp[i][j] = minUpponThisGrid;
            }
        }
        
        return dp[0][0];
    }
}