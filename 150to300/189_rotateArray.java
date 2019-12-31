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