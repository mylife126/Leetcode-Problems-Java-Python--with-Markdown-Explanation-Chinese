/*
1204

对于 第一位数 1来说 它的decodeway只能1种，
对于 第二位数 12， 它可以要么 2自己而1为pre 或者 _ + 12作为整体而空为pre。 
第一种情况， 1 + 2，的解法个数取决于 1 的解法个数， 
第二种情况， _ + 12的解法个数取决于 _的解法个数。 

所以我们的
state function： dp[n +1] 指的是从开头开始含有i位数字的解法个数，例如i = 3，则说的是 从1~0， 120这三个数的解法个数

init： dp[0] = 0, dp[1] = 1; 其中dp[0]是为了补一位，因为transfer function需要用到 dp[i - 2]

transfer function：对于dp[i]来说， i指的是从从头开始到含有第i位数字的解法个数，例如 1231, i = 3, 123这数字。 那么对于他来说，12 + 3 
or 1 + 23. 所以123的解法无非就是 12 + 3 或者 1 + 23，那么第一个情况来看，解法取决于dp[i - 1] == dp[2] 也就是 12 这个数字有几个解法， 而第二个情况 1 + 23取决于dp[i - 2] == dp[1] 也就是1这个数字有几个解法。 

但要注意corner case：
1. 当考虑dp[i-2]的先决条件是 当前这个数字加上前面一个数字 <= 26
2. 当遇到0的时候，0 不能单独出现， 所以必须考虑的是 X0 作为整体 pre + x0这个情况， 所以也是dp[i - 2]。 例如1220，只能考虑的是 2 + 20，所以dp[i - 2]
3. 当i项数字前是0， 1204， i =4时，我们要计算总共四位数的解法的时候，4只能单独出现，也就是dp[i - 1]的情况，而04不满足要求。

result： dp[n]即为从头到尾 所有数字放一起的总共解法。
*/
class Solution {
    public int numDecodings(String s) {
        if (s == null){
            return 0;
        }
        //state function, dp[i]指的是从头到含有i项数字的总共的解法
        int[] dp = new int[s.length() + 1];
        
        //init, 零位数的解法1个，一位数的解法只有一个
        dp[0] = 1;
        dp[1] = (s.charAt(0) != '0') ? 1 : 0; 
        //注意这里的idx取值，i是跟着dp的i走的，i ==2 指的是从两位数开始取，一直取到n位数
        //所以对应的string的indx要减去1. 例如 123, 我们其实idx = 2， 对应string的idx = 1
        for (int i = 2; i <= s.length(); i++){
            char oneDigit = s.charAt(i - 1);
            char digitBefore = s.charAt(i - 2);
            int twoValue = Integer.parseInt(s.substring(i - 2, i));
            //corner case 100, 所以判断当前数位前那个数字是否为零的同时还得确保自己也不是0
            if (digitBefore == '0' && oneDigit != '0'){
                dp[i] = dp[i - 1];
            } else if (oneDigit == '0'){//不然就是当自己是0
                //必须保证自己加上前一位数字的组合是有效的 0 ~ 26
                if (0 < twoValue && twoValue <= 26){
                    dp[i] = dp[i - 2];
                } else {//不然如果是个无效数字，那解法为0， CORNER CASE： 100 -> 1 + 00
                    dp[i] = 0;
                }
            } else {
                //最后两个cornercase解决了那就是最普通的transfer function了，要么是前一位数的解法+'x',要么是前两位数的解法+'yx'
                dp[i] = dp[i - 1];
                if (twoValue > 0 && twoValue<=26){
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[s.length()];
    }
}