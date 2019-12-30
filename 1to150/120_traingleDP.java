/*
top down 思路就是每一位的runningSum = 自己 + Min(lastSum[j], lastSum[j - 1]), 但如果是j= 0,只能取lastSum[0], 如果j= nCols -1
只能取到lastSum[mcols - 1];

                                        [2]   

                                [3 + 2=5]   [3 + 2=5]
                            
                        [6 + 5= 11]  [5 + min(5 5) = 10] [7 + 5 = 12]
                        
               [4+ 11 = 13]  [1 + min(11 10) = 11] [8 + min(10, 12) = 18] [3 + 12 = 15]
所以最后sort最后一列，就能得到最小的runningSum = 11
也就是说，双循环，外循环是row， 内循环是col， 遍历每一行的col的时候对这个col的runningsum用上一刻的sum的取值来计算。

和机器人找路径是一样的，这一位的pathsum只能由上一刻的runningSum中的最小值来决定。只是这次是只能从相邻的位置走而已。

1. state function dp array[m][n], 记录的是每一行在第j列下的总pathSum，且是最小可能的pathSum
2. transfer function， 往下走到的每一行的每一列的sum = 自己 + min(lastSum[j], lastSum[j - 1])，但对于在0点和末点需要特殊处理
3. dp: 取dp最后一row，也就是走完后的pathSum的情况，取这一row的最小值即可。
*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        //分配一个dp array，来记录pathsum，每一位代表着这一位下最小的pathsum
        List<List<Integer>> dp = new ArrayList<>();
        //init dp array，最开始的sum只能是原数组的第一列
        dp.add(triangle.get(0));
        //从第二列开始走
        for (int i = 1; i < n; i++){
            //先拿到这一行的值
            List<Integer> thisRow = triangle.get(i);
            //取到上一刻的runningsum
            List<Integer> lastSum = dp.get(i - 1);
            int mCols = thisRow.size();
            
            //分配新的内存给这一刻的runningSum， trdpfer function= 自己 + min(sum[j], sum[j - 1]);
            List<Integer> sum = new ArrayList<>();
            for (int j = 0; j < mCols; j++){
                //先拿到这一刻每一数位自己
                int current = thisRow.get(j);
                //边界情况特殊处理
                if (j == 0){
                    //在0点的时候，它此刻的pathsum只能从自己的同一位走下来
                    current = current + lastSum.get(j);
                } else if ( j == mCols - 1){
                    //在末尾的时候，它此刻的pathsum只能从自己的前一位走下来
                    current = current + lastSum.get(j - 1);
                } else {
                    //trdpferfunction，取上一刻合法的二者中的最小值
                    current =current + Math.min(lastSum.get(j - 1), lastSum.get(j));
                }
                //将这一刻的runningSum放入dp array中
                sum.add(j,current);
            }
            //将这一行的所有sum的情况放入大dparray中
            dp.add(sum);  
        }
        
        //最后的答案就是在最后一row
        List<Integer> lastRow = dp.get(dp.size() - 1);
        Collections.sort(lastRow);
        return lastRow.get(0);
    }
}

/*
    [2]             
  [3, 4]
 [6, 5, 7]
[4, 1, 8, 3]

我们可以发觉，topdown的话我们的转移方程不好写，因为从头开始一定只有2这一个起点往下走。但是另一个observation是，我们从头出发一定回到bottom的某一个出口，例如这个例子是从 col = 1， 也就是1这个数字出去。那我们可以bottom up， 反推回去，也就是说从头到尾，和从尾到头应当是一致的。

那么我们的dp[i]则代表了col i这一列的最小path sum，代表着从col i作为出口下的pathsum
2
3 4
6 5 7
4 1 8 3
| | | |
v v v v

那么init的话就可以看到dp[0] = 4, dp[1] = 1, dp[2] = 8, dp[3] = 3
往上走的话，6这个元素只能从4 或者1 走过来，因为题目规定只能相邻的走。 所以dp[0] = 6 + min(dp[0], dp[1]) = 6+min(4,1) = 10这意味着

*/
