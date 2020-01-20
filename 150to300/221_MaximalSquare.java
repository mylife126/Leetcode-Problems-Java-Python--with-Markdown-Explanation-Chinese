/*
1 1 1 1
1 1 1 1
1 1 1 1
0 0 0 0

正向思路是，以每一个grid作为topLeft corner来画square，那么对于最上面第一个格子来看我们要以他为leftCorner的话，就得先知道
右边，右下，下面的grid的以它们作为leftCorner的size：

？ 3
2  2   那只能是 1 + min(2,3,2), 因为我们要求的是正方形，那么如果是 1 + 3， 是不能满足的，因为最后一排全是0，达不到要求。

所以可以bottom Up dp：
1. stateFunction: dp[m][n] 每一个grid代表的是以这个grid为topLeft的pivot的时候能取得的正方形的最大size
2。 init：最后一row，和最后一列都是0 or 1
3. transfer， dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
4. 同时维护globalMaxSize
*/

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        /*state function. 多一位的话，便于处理一个corner case ["1"], 
        因为如果只是原来的size， 后期transfer的循环从 m - 2 和 n - 2开始，然后maxSize更新也是在循环里才开始，
        但如果是倒数第二才开始循环，这个case进不去。 maxsize 就会一直为0
        
        且如果多一位的好处是，initi function其实在循环里做了。因为这多出来的一位全部得init为0
        */
        int[][] dp = new int[m + 1][n + 1];
        /* 以下是对 size 不加1 的 init the state function 
        for (int j = n - 1; j >= 0; j -- ){
            int size = (int) matrix[m - 1][j] - '0';
            dp[m - 1][j] = size; 
        }
        for (int i = m - 1; i >=0; i--){
            int size = (int) matrix[i][n - 1] - '0';
            dp[i][n - 1] = size;
        } */
        //transfer function
        int maxSize = 0;
        for (int i = m - 1; i >=0; i--){
            for (int j = n - 1; j>=0; j--){
                if (matrix[i][j] == '1'){
                    int itsRight = dp[i][j + 1];
                    int itsBottom = dp[i + 1][j];
                    int itsRightBottom = dp[i + 1][j + 1];
        
                    int size = Math.min(Math.min(itsRight, itsBottom), itsRightBottom) + 1;
                    maxSize = Math.max(maxSize, size);
                    dp[i][j] = size;
                }
            }
        }
        return maxSize*maxSize;
    }
}