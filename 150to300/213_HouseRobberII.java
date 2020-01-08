/*
说的是 房子的最后一位和第一位相连，那么如果偷了第一个屋子，最后一个屋子就不能偷。 如果不偷第一个屋子我就能偷最后一个屋子。

那么我们可以建立两个dpArray，robFirst[] 和noRobFirst[]。 

1. statefunciton: robFirst[length + 1] noRobFirst[lenth + 1], 
每一个state都是我到了这个屋子时能偷盗的的最大金额
2. init, robFirst[0] =0, robFirst[1] = nums[0]
        noRobFirst[0]= 0, noRobtFirst[1] = 0
        
3. transfer function：从第二个屋子起就是一样了，我此刻要么偷这个屋子即为我可以连着前前一刻：curr+ dp[i -2], 要么我不偷了继承上一刻偷盗的前。
    a. 但是对于robFirst来说 当我们到了最后一个屋子，我们是不能偷的
    b。对于noRobtFirst来说，上述两个max判断是成立的
    
4. return max（robfirst[last], noRobfirst[last])

*/
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        //1. state function: 依旧是代表到达这个屋子的时候我能抢的最多的钱
        int[] robFirst = new int[nums.length + 1];
        int[] noRobFirst =new int[nums.length + 1];
        //2. init： 对于robFirst的来说它0和1都不能有数值， 对于noRobFirst可以抢第一个屋子
        robFirst[0] = 0;
        robFirst[1] = nums[0];
        
        noRobFirst[0] = 0;
        noRobFirst[1] = 0;
        
        int n = nums.length;
        /*3. transfer function：对于robFirst来说到了最后一个屋子，它是特殊情况，它只能是不抢最后一个屋          子，继承上一个屋子的最大值。 其余情况一样： 要么我抢这个屋子我就可以接上前前一刻的钱，要么我这         个屋子不抢了，继承上一个屋子的*/
        for (int i = 1; i <nums.length; i++){
            int thisHouseMoney = nums[i];
            int whichHouse = i + 1;
            
            noRobFirst[whichHouse] = Math.max(noRobFirst[whichHouse - 2] + thisHouseMoney, 
                                     noRobFirst[whichHouse - 1]);
            robFirst[whichHouse] = Math.max(robFirst[whichHouse - 2] + thisHouseMoney, 
                                   robFirst[whichHouse - 1]);
            //特殊情况
            if (i == nums.length - 1){
                robFirst[whichHouse] = robFirst[whichHouse - 1];
            }
        }
        return Math.max(robFirst[n], noRobFirst[n]);   
    }
}

    