/*
题目意思是给定n，意味有 1 ~ n个rootnode供选择，问当选择选择 1 2 3 4 ... n为root的时候有几种构建bst的结构

重点是 bst的特征是 左子树小， 右子树大

例如 n = 4:
n= 0  | n= 1  |    n = 2            |       n = 3             |           n= 4
          1       1      or    2        1         2         3      1         2          3       4     
                    (1)     (1)           (2)  (1)  (1)   (2)        (3)  (1) (2)    (2) (1)  (3)

意思是当：
1. n =1 的时候，只有一种tree
2. n =2 的时候可以 root = 1 or =2， 那当root = 1的时候，只可能1的右子树有一个节点， 当root = 2， 只能2的右子树有一个节点
3. n =3 的时候，root = 1 || 2||3, 当root= 1的时候只能是它的右子树有两个节点， 当root =2时，可以右边一个左边一个
   当root = 3的时候，只能是左子树有3个节点， 右子树放一个节点。 
4. n =4 以此类推。

这是因为bst的特征， 当 root取3的时候， n = 3， 那只剩下 1和2， 1和2一定是比3小的所以只能存在于左子树，那么对于这连个节点的结构可能性，等同于 n = 2的时候。

那么当 n = n的时候规律是 ：
                                 n                  即为有 Sum(dp[j] * dp[(n - j - 1)]), 
                             0       n - 1
                             1       n - 2
                             3       n - 3
                             ...      ...
                             n - 1   0

所以我们可以构建DP array
1. state function dp[i] 指的是当i = n的时候有多少种解法
2. init dp[0] = 1; dp[1] = 1
3. transfer function则是, dp[i] = sum(dp[i] * dp[n - i - 1]), 说的是左子树的可能结构 * 右子树的可能结构， 且规律如上图
4. return dp[n]即可。
*/
class Solution {
    public int numTrees(int n) {
        if (n <=1){
            return 1;
        }
        //1. 构建dp array， 每一格state代表的是 i = X个选择的root的时候有几个构建方式
        int[] dp = new int[n + 1];
        //2. init， 当没有root的时候，结构也是一种，这个是为了方便transfer function来做的，因为要计算dp[0]
        dp[0] = 1;
        dp[1] = 1;
        //3. transfer function
        for (int i = 2; i <= n; i++){
            for (int j = 0; j <= i - 1; j++){
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        
        return dp[n];
    }
}