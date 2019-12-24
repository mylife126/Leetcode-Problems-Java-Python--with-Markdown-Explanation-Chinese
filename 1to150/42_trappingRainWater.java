/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6


对于一个当前的bar的位置，能存贮多少水是和它左边&右边的bar的高度有关系   2 0 1，这样的，对于0来说它能存的就最多是1 - 自己。 因为
木桶原理，水位跟着最低的走

所以我们可以建立两个array分别存贮当前位置的左边的bar的高度， 以及当前位置的右边的bar的高度
最后one pass linear 扫水位array的时候， 对比该水位的左bar 和右bar，去最小值。 能增加的水位等于 min（left, right） - 自己。但如果小于0了，则直接为0
*/

class Solution {
    public int trap(int[] height) {
        int rain = 0;
        if (height == null || height.length == 0){
            // System.out.println("oh no");
            return rain;
        }
        
        int leftMax = 0;
        int rightMax = 0;
        
        //init从左到右的低零项的对应的左边的最高值
        int[] left2right = new int[height.length];
        left2right[0] = 0;
        //init从右到左的最后一项的对应的右边的最高值
        int[] right2left = new int[height.length];
        right2left[height.length - 1] = 0;
        //找到当前位置下的左边的最高值
        for (int i = 1; i < height.length; i++){
            leftMax = Math.max(leftMax, height[i - 1]);
            left2right[i] = leftMax;
        }
        //找到当前位置下的右边的最高值
        for (int i = height.length - 2; i >= 0; i--){
            rightMax = Math.max(rightMax, height[i + 1]);
            right2left[i] = rightMax;
        }
        
        for (int i = 0; i < height.length; i++){
            int itself = height[i];
            int itsLeft = left2right[i];
            int itsRight = right2left[i];
            
            int elevation = Math.min(itsLeft, itsRight);
            int collectedRain = (elevation - itself > 0) ? (elevation - itself) : 0;
            rain += collectedRain;
        }
        return rain;  
    }
}