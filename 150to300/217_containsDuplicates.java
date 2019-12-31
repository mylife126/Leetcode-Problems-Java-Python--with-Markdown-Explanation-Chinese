/*
217. Contains Duplicate
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
*/


class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }
        Arrays.sort(nums);
        boolean ans = false;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i - 1]){
                ans = true;
            }
        }
        return ans;
    }
}
