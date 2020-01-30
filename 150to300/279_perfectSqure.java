/*
n           num      squre          breakdown
0            0                         init
1            1          1              init
2            2        1 + 1           dp[1] + dp[1]
3            3        2 + 1           dp[2] + dp[1]
4            1          4             squre = 1
5            2        1 + 4           dp[4] + dp[1]
6            2        2 + 4           dp[4] + dp[2]
7            3        1 + 2 + 4       dp[4] + dp[3]

所以我们建立一个dp array，
1. state function： dp[i] 代表了 n = i的时候 需要的最少的squre组成的数量
2. init， 当n = 0 的时候dp i = 0， 当 n = 1的时候 等于1
3. transferfunction： 当当前的i 是一个 perfect suqre，直接dp[i] = 1 不然我们需要倒叙查找每一个比当前数小的dp以及 i - j = remain的dp，并加起来后取最小值
4. return最后一位dp即可
*/

class Solution {
    public int numSquares(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        //dp的每一项代表了 upto i这个数，需要的最少的squre的数量
        int[] dp = new int[n + 1];
        //init： 这一步很重要，需要将每一格init为最大值，因为后期需要做比对
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        //transfer function
        for (int i = 2; i <= n; i++){
            //case1， 这个数已经是个squre了，直接return1
            if (isSqure(i)){
                dp[i] = 1;
            } else {
                //不然我们从后往前找 dp[j] + dp[remain] 取最小值
                for (int j = i - 1; j > (i - 1)/2; j--){
                    int remain = i - j;
                    dp[i] = Math.min(dp[i], (dp[j] + dp[remain]));
                }
            }
        }
        return dp[n];
    }
    private boolean isSqure(int num){
        double sr = Math.sqrt(num);
        return ((sr - Math.floor(sr)) == 0);
    }
}

