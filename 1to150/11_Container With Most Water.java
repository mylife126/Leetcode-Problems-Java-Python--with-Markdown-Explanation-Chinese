/*
Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
1,8,6,2,5,4,8,3,7
(8 - 0) * min(7,1) = 8 * 1 = 8
(8 - 1) * min(8, 7) = 7 * 7 = 49
这意味着我们每次greedy的取最长长度，然后计算一下此刻的area，但是我们不知道后期会不会有更高的bar来compensate长度的减少，
所以我们保留高的那一方，然后移动矮的那一方来测试一次。
*/
class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right){
            int leftBar = height[left];
            int rightBar = height[right];
            //先greedy得拿出最长的长度
            int length = right - left;
            maxArea = Math.max(maxArea, Math.min(leftBar, rightBar) * length);
            //将高度小的那一方移动一次，说不定后面的会有更高得高度来弥补长度的减小
            if (leftBar <= rightBar){
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}


