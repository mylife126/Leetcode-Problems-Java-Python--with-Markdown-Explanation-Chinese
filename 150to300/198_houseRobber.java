/*
不能连续相加，只能间隔相加，问最大和是多少
Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
             
             
1. state function: dp[], 每一个位置代表upto这个屋子，我能最大偷多少钱

2. init, dp[0] = 0，没有屋子可以去偷就是0， dp[1] = nums[0]， 因为第一个屋子只能偷这么多

3. transfer function： 因为我到一个屋子 i， 要么是接上前前一个屋子的位置上能偷的最大金额： nums[i] + dp[i - 2]， 要么就是这一刻我不偷了，我跳过，这一刻的金额等于dp[i - 1]

4. 最后return 到了最后一个屋子的钱就可以了


-> dp[0] = 0, dp[1] = 1
   -> 2, max(dp[0] + 2, dp[1]) = dp[2] = 2
     -> 3， max(dp[1] + 3, dp[2]) = dp[3] = 4
        -> 4, max(dp[2] + 1, dp[3]) = dp[4] = 4
        
return dp[4] = 4
*/

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        //1. state function: 到达这个屋子的能偷的最大金额
        int[] dp = new int[nums.length + 1];
        //2.init
        dp[0] = 0;
        dp[1] = nums[0];
        
        //3. transfer function： 要么连着前前一个屋子继续偷，要么我这一刻不偷了继承前一刻的钱
        for (int i = 1; i < nums.length; i++){
            int thisHouseMoney = nums[i];
            int whichHouse = i + 1;
            
            dp[whichHouse] = Math.max(dp[whichHouse - 2] + thisHouseMoney, dp[whichHouse - 1]);
        }
        return dp[nums.length];
    }
}