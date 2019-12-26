/*
1. state function依旧是 dp[][] 每一格代表到达这一格，能有的走法

2. init，对于第一列和第一行都是1，但是，由于我们不知道这个obstacle是否也在第一行或者第一列，如果遇到了 1 ，则说明
   dp[0][i] or dp[o][j] == 0, 且，这之后的都是0。 因为第一行或者第一列只能从一个方向走，一旦遇到了obstable，那就走不下去了
   
3. transfer function 类似于init的思想，遇到obstacle，dp[i][j] == 0 otherwise 等于 dp[i][j-1] + dp[i-1][j]
*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0][0] == 1){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        
        //init 1st column
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
            //once found one obstacle, set this position to be 0, and everything after is also 0
            if (obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
                //set everything after to be 0
                while(i < m){
                    dp[i][0] = 0;
                    i++;
                }
            }
        }
        //init the first row
        for (int j = 0; j < n; j++){
            dp[0][j] = 1;
            if (obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
                while (j < n){
                    dp[0][j] = 0;
                     j++;
                }
            }
        }
        
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        
        return dp[m-1][n-1];
    }
}