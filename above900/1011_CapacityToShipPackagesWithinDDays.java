/*
我们的 capacity的range 一定是在 1 ~ sum(weights) 之间的。
所以用二分查找发来找一个合适的capacity, 然后验证这个capacity是否满足要求：
循环找出每个batch， sum(batch) < cap, 每找完一次batch， day --， 这个循环直到days == 0， 最后检测是否将所有的货物都遍历到了:

while(days > 0)：
    while(i < nums.length - 1 && runninSum + nums[i] < cap):
        runningSum += nums[i]
        i+=1
    days--
    
return i == nums.length

如果满足了要求说明我们可以在尝试将capacity减低一些， 否则应该增加capacity

例子： [3,2,2,4,1,4], D = 3
1 ~ 16
mid = 8

3 + 2 + 2= 7  D - 1 = 2
4 + 1 = 4     D - 1 = 1
4             D - 1 = 0 成功， 那么我们再试一试减少一点看看

end = mid - 1 = 7
mid = 4
3             D - 1 = 2
2 + 2 = 4     D - 1 = 3
4             D - 1 = 0 失败，则我们需要增加一点看看

start = mid + 1 = 5 
mid = 6
3 + 2 = 5     D - 1 = 2
2 + 4 = 6     D - 1 = 1
1 + 4 = 5     D - 1 = 0 成功， 则我们可以试一试减少一点看看

end = mid - 1 = 5
mid = 5， 
3 + 2 = 5     D - 1 = 2
2             D - 1 = 1
4 + 1 = 5     D - 1 = 0 测试发觉失败， 则增大看看， 发觉start = 6 > end 退出search
*/

class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int sumWeights = 0;
        for (int i : weights){
            sumWeights += i;
        }
        
        int start = 1;
        int end = sumWeights;
        int minChoice = Integer.MAX_VALUE;
        
        while(start <= end){
            int mid = (start + end) / 2;
            int tryCap = mid;
            System.out.println(tryCap);
            // System.out.println("xxxxxxxxxxxxxx");
            //如果这一个capacity满足了要求，则可以试一试减少capacity
            if (success(weights, tryCap, D)){
                // System.out.println("Success, end = mid - 1");
                
                //并且需要记录runnin Min
                minChoice = Math.min(minChoice, tryCap);
                end = mid - 1;
            } else {
                //反之则要增大capacity
                // System.out.println("failed, start = mid + 1");
                start = mid + 1;
            }
        }
        return minChoice;
        
    }
    private boolean success(int[] weights, int cap, int days){
        int runningSum = 0;
        int i = 0;
        //days == 0的时候就是找完了，所以该循环要在 大于 0 的时候
        while(days > 0){
            //保证不越界，同时保证加入下一个货物的时候小于等于capacity
            while(i < weights.length && (runningSum + weights[i]) <= cap ){
                runningSum += weights[i];
                i += 1;
            }
            // System.out.println(runningSum);
            days-=1;
            runningSum = 0;
        }
        return i == weights.length;
    }
}