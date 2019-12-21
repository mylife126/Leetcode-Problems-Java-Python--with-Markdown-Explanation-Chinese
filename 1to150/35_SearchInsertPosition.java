/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1

Binary Search Directly
*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int small = 0;
        int large = nums.length - 1;
        while (small <= large){
            int mid= (small + large) / 2;
            int midNum = nums[mid];
            int smallNum = nums[small];
            int largeNum = nums[large];
            
            if (target == midNum){
                return mid;
            }
            
            if (midNum < target){
                small = mid + 1;
            } else {
                large = mid - 1;
            }
        }
        return small;
    }
}