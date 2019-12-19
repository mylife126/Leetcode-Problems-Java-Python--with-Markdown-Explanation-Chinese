/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

类似于binary search的思路，我们sort array， 遍历每一个数并且以这个数当作a， 然后呢在其之后找b 和 c
但是由于array是sorted了，那么我们可以对于b init在 a之后一位， 而c在末尾， 这样的如果sum < target， 则说明
b小了，则move pointer to right； 反之move pointer to left。 

1. sort
2. 遍历 a from i = 0 to length - 2, 因为b c 要在a之后来确保答案唯一性，所以防止越界
3. b = i + 1, c = length - 1, 
4. while b < c, 如果sum 》 target, b++, 不然 c --
5. 一直维护一个result 变量

-4 -1 1 2 
    
-4 + -1 + 2 = -3 < 1 - > move pointer b to right
-4 + 1 + 2 =  -1 < 1 - > move pointer b to right again but could not

-1 + 1 + 2 = 2 > target  -> move pointer c to left, but could not

*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++){
            int a = nums[i];
            //j starts after i
            int j = i + 1;
            //set k starts from the end
            int k = nums.length - 1;
            
            //similar to binary search
            while (j < k){
                int b = nums[j];
                int c = nums[k];
                int sum = a + b + c;
                if (sum > target){
                    k--;
                } else {
                    j++;
                }
                if (Math.abs(result - target) > Math.abs(sum - target)){
                    result = sum;
                }
            }
        }
        return result;
    }
}




    
