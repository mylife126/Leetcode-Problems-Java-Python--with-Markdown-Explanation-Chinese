/*
最brutal就是linear的方案，对于正power -> ans = 1, ans *= value, n times
                         对于负power -> ans = 1, ans *= value, n times, ans = 1/ ans
                         
但是可以二分法来做， power存在奇数偶数问题，我们直接用奇数来做
2^4 = (2^2)^2 = 4 ^ 2 = 16 ^ 1

2^9

ans    value    power
1       2        9
2       2        8     遇到奇数power， ans *= 2
2       4        4
2      16        2
2      256       1     power == 1   ->  return ans *= value

*/


class Solution {
    public double myPow(double x, int n) {
        if (n == 0){
            return 1;
        }
        
        if (x == 1){
            return 1;
        }
        /*
        重点，对于一个负数转换成正数存在overflow的情况， 因为 [−2^31, 2^31 − 1]
        -2147483648 没法直接转换成正数，因为 2147483648 > (2147483648 - 1)
        所以需要先把n转换为double， 再取absolute value
        */
        double ans = helper(1, x,  Math.abs((double) n));
        if (n < 0){
            ans = 1 / ans;
        }
        
        return ans;
    }
    
    private double helper(double ans, double value, double power){
        //出口，当power == 1的时候，直接return ans = ans * value就可以了
        if (power == 1){
            return ans * value;
        }
        
        //不然的话有两个case。case1，power是奇数，那么我们就对ans 做一次操作 将ans = ans * value 一次，这样power - 1 就为偶数
        //相当于用将ans 提前乘一次 value来换取偶数的power。  2^9 = 2 * 2^8
        if (power % 2 != 0){
            return helper(ans * value, value, power - 1);
        } else {
            //不然当power为偶数的时候，可以直接对 value取一次二次方，而power 对半分
            //2 ^ 8 = (2 ^ 2)^4 = 4 ^ 4....
            return helper(ans, value * value, power / 2);
        } 
    }
}
