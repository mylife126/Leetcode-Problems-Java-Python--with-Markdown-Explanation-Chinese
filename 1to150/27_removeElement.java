/*
题目意思是：
[3 2 2 3] val = 3， 目标是 [2 2 x x]我们只需要返回的是2，2意味着在index 2之前的都是valid的，且2也代表了有多少个valid。 
那么我们就可以新建一个array 取到原来index = 2的位置即可。 做法是 two pointer, fastPointer slowPointer

1. fastpointer for loop 扫array， 如果nums[fast] == target， slow 不变，
2. 如果nums[fast] != target, nums[slow] = nums[fast], slow++

 F               F            F              F              F  
[3 2 4 3]  -> [3 2 4 3] -> [2 2 4 3] -> [2 4 4 3] -> [2 4 4 3]  
 S             S            S              S              S 
 
发觉，在S的之前的都是合理的数字，return s即可
意思就是slowPointer只会保存合理的数字，当且当fastpointer指的数字不是target的时候，这个时候slow才会走向下一位，然后在下一位等到fast又找到了合理数字。
*/


class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++){
            if (nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            } 
        }
        return slow;
    }
}