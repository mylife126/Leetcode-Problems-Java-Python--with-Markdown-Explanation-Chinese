/*
0     0  = 0                  
1     1  = 1
2   1*1  = 1
3   1*2  = 2
4   2*2  = 4
5   2*3  = 6
6   3*3  = 9
7   3*4  = 12     
8   4*4  = 16
9   3*3*3= 3 * 9 = 27   , dp[9-3] =dp[6] = 9  
10  3*4*3= 3 * 12 = 36  
*/
class Solution {
    public int integerBreak(int n) {
        /*
        1. state function: 每一个i代表着 n = i的时候它的max乘积
        */
        int[] dp = new int[n + 1];
        
        /*
        2. init function: dp[0] = 0, dp[1] = 1, 因为当n = 0 的时候只能是0， 当n = 1的时候，只能是1
        */
        dp[0] = 0;
        dp[1] = 1;
        /*
        3. transfer function: 当i = 6的时候，我们的pivot尝试到了3，有两个option：
        a. remaining是3， dp[3] =2   => 3 * 2 = 6
        b. 3 * remaining = 9 
        c. 最后我们取大的即可。
        
        但重点是， 我们还得比较dp[i]自己，因为在内循环的时候可能已经dp[i]有值了
        */
        for (int i = 1; i <= n; i++){
            //尝试当前数字之前的所有的可能的pivot
            for (int pivot = 1; pivot <= i; pivot++){
                int option1 = pivot * (i - pivot);
                int option2 = pivot * dp[i - pivot];
                dp[i] = Math.max(Math.max(dp[i], option1), option2);
            }
        }
        return dp[n];
        
    }
}

/*
Solution2, 找规律的dp， On
从上面的规律我们可以看到 up to 6的时候都是特殊case， 
但是当n =7 开始，都是 3 * dp[remaining]
*/
class Solution{
    public int integerBreak(int n ){
        //define all the special cases
        if (n == 1) return 1;
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        if (n == 5) return 6;
        if (n == 6) return 9;
        
        int[] special ={1,1,2,4,6,9};
        int[] dp = new int[n + 1];
        /*
        init the dp functions and transfer
        */
        for (int i = 1; i <= n; i++){
            if (i <= 6) {
                dp[i] = special[i - 1];
            } else {
                dp[i] = dp[i - 3] * 3;
            }           
        }
        return dp[n];       
    }
}
