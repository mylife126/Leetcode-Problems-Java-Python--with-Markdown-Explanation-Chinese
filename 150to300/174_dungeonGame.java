/*
这一题的重点是，理解什么叫做 ”optimal path“，要求的是每一步下需要的血量都可以最少， 反例：
-2 -> -5， 这个时候你会发觉走到-5，想要骑士继续活着，ph至少是8.
但是我们走-2 -3 3 1 -5这一步，会发觉，走到每一步都是最optimal的。

所以这道题的重点是bottom up， 因为我们知道走到最后一格之后至少要ph == 1. 也就是说 当我们刚到 -5 那一格的地方的时候 
ph == 1 - （-5） = 6. 

那么我们反推走到每一格上的需要的最少血量 dp[i][j]，其实一样，是基于”上一时刻“ 也就是走过这一格后的最少血量 dp[i + 1][j] or dp[i][j +1]

正向思考， 假设 在我们到最后一格的血量为 X， 那么走过 -5 之后， hp = x + (-5); 
=> 所以dp[i][j] = min(dp[i+1][j], dp[i][j+1]) - currentConsumption =》 同时也知道走过最后一格的时候血量最少为1， 这就是init dp的规则。

1. state function dp[m + 1][n + 1], 每一格代表的是走到这个一格的时候的hp，所以我们需要比原来的matrix大一位，最后一位记录的是
  走完最后一格后的血量
2. init state function： dp[m + 1][n] && dp[m][n+1] == 1也就是说 init了从-5最后一格能走去的方向上的hp。 其余的都是max integer来作为init， 因为我们dp transfer的时候需要对比”上一时刻“ （走过这一格之后 either 往右 or 往下 到达后的hp）的最小hp， 但除了-5之后的1是已知的，其余其实都是不知道的，直接设为 max
3. transfer function: currentHp = min(dp[i+1][j], dp[i][j + 1]) - itself. 但注意，如果currentHp <= 0, 则说明在这一格原来的consumption是一个正的consumption，所以对于这一格来说 hp needed就是1.

   [-2 -3 -3]  [7 5  2 M]
   [-5 -10 1]  [6 11 5 M]
   [10 30 -5]  [1 1  6 1]
               [M M  1 1]

*/

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null){
            return 1;
        }
        
        int m = dungeon.length;
        int n = dungeon[0].length;
        //state function， each grid represents how much HP minimal are when it reaches this grid
        int[][] dp = new int[m + 1][n + 1];
        
        //init
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
                //反推计算到达这个格子的血量
                int minUpponThisGrid = minAfterThisGrid - dungeon[i][j];
                //但是如果这个血量 <= 0， 则说明这一格的consumption 其实是加血，那就意味着怎样都不会死，所以neededHp = 1
                if (minUpponThisGrid <= 0) {
                    minUpponThisGrid = 1;
                }
                //不然就是我们计算出来的血量
                dp[i][j] = minUpponThisGrid;
            }
        }
        //最后的结果就是dp的第一位，也就是到达第一格的needed hp
        return dp[0][0];
    }
}