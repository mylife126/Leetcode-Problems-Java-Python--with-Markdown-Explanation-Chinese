/*
重点是： 如果一个数字是happy的话，那他最后每一位数字相加一定是1， 不然这些数位平方和一定会再次出现。
例如 116 - 38 - 73 - 58 - 89 - 145 - 42 - 20 - 4 - 16 - 37 - 58. 

所以我们这是个循环加hashSet的问题，
1. 拿到一个数字的时候，先将数字get last digit = n % 10, n//10，然后计算平方和
2. 将平方和查看是否出现过，出现则return false， 不然就继续call函数， 直到平方和达到了1.
*/
class Solution{
    private Set<Integer> seen = new HashSet<>();
    
    public boolean isHappy(int n){
        boolean ans = dfs(n);
        return ans;
    }
    
    private boolean dfs(int n){
        /*
        每一层递归都有一个新的sqrSum
        */
        int sqrSum = 0;
        while (n > 0){
            //取10的余得lastDigit
            int lastDigit = n % 10;
            //然后加上这个的sqr
            sqrSum += lastDigit * lastDigit;
            n = n / 10;
        }
        //成功出口
        if (sqrSum == 1){
            return true;
        }
        //失败出口
        if (seen.contains(sqrSum)){
            return false;
        }
        //上述都不满足，我们还得继续递归下去
        seen.add(sqrSum);
        return dfs(sqrSum);
    }
}