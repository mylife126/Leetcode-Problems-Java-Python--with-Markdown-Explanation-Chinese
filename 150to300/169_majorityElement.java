/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

HashMap count
*/


class Solution {
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int nHalf = nums.length / 2;
        int thisNum = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
            int thisCount = map.get(i);
            if (thisCount > nHalf){
                thisNum = i;
                break;
            }   

        }
        return thisNum;
    }
}