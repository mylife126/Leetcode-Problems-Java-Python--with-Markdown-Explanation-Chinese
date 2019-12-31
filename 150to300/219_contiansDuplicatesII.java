/*
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true

*/


class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++){
            int thisNum = nums[i];
            if (map.containsKey(thisNum)){
                int where = map.get(thisNum);
                int diff = i - where;
                if (diff <= k) return true;
            } 
            map.put(thisNum, i);
        }
        
        return false;
    }
}