/*
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 

所以用stack的思路来做，每次比对 last = stack.peek() 和 curr 以及 next。
但是要注意stack的init， 因为题目说了nums[-1] = -infiniti。 所以stack一开始放入integerMINVALUE
然后循环里也需要注意越界问题，同样，题目说明了末尾的读值是负无穷，所以当i == length - 1的时候无需判断next。

其余的情况都是比对 last  curr next

最后返回-1 说明不存在。


*/


class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        //corner case 如果只有一个数字，那根据定义这个数的indx就是peak
        if (nums.length == 1){
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MIN_VALUE);
        
        for (int i = 0; i < nums.length; i++){
            int curr = nums[i];
            int last = stack.peek();
            if (i == nums.length - 1){
                if (curr > last){
                    return i;
                }
            } else {
                int next = nums[i + 1];
                if (curr > last && curr > next){
                    return i;
                }
            }
            stack.push(curr);
        }
        return -1;
    }
}