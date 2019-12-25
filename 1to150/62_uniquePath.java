/*
1. state function: dp[m][n], each dp[i][j] represents how many different ways a robot could reach here

2. init state function： dp[0][n] = 1 and dp[m][0] = 1 因为对于第一row，机器人只能从左边走过来 对于第一列机器人只能从上面走下来

3.transfer function: 对于 i j项， 假设第二行 第三列， 机器人想要走过来势必要么是从上走下来，或者从左走过来，那么对于这一个的位置的走法，就是 dp[i][j - 1] + dp[i - 1][j]， 相当于有多少种走到上面那一格

4.result, dp[m-1][n-1] 最后一位的总走法
*/
class Solution {
    public int uniquePaths(int m, int n) {
        /* each grid represents how many ways it could get to this point*/
        int[][] dp = new int[m][n];
        
        //init 
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++){
            dp[0][j] = 1;
        }
        
        //transfer function,注意这里都是从第二项开始走，因为第一项都已init过了
        for (int i = 1; i < m; i ++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m-1][n-1];
    }
}