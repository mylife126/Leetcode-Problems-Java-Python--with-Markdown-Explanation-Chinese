/*
Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]


indxNew = i + moveRightK
例如 3 + 3 = 6，表示idx 跑去了 6这个地方

但有可能新的地点超过了边界， 例如7这个数字 在6的位置， 它加了3后 ==9， 超过了array的长度，
那么我们对 9 % length7 == 2.表示9转了x圈后落到了哪个位置
*/


class Solution {
    public void rotate(int[] nums, int k) {
        int[] copy = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++){
            int thisNum = nums[i];
            int whereTo = (i + k) % nums.length; 
            copy[whereTo] = thisNum;
        }
        
        for (int i = 0; i < nums.length; i++){
            nums[i] = copy[i];
        }
        
    }
}