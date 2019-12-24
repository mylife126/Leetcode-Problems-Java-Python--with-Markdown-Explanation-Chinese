/*
因为是加法，所以，我们需要维持一个globalMax，和一个runningSum。 每次遍历每一个数字的时候更新runningSum。 因为存在负数，所以很可能此刻的
runningSum < nums[i], 这个情况下，说明runningSum应该摒弃这个数字前的所有数字，而取此刻的数字为新的开始。同时还需要用一个gloablMAx来不断对比，
这样循环结束后，globalMAx一定是所有runningSum中最大的。
*/

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        /*
        重点，如果是init都为0的话会有一个case [-1]出现bug。 所以init runningSum和globalMax都为数组第一项即可
        */
        int runningSum = nums[0];
        int globalMax = nums[0];
        
        for (int i = 1; i < nums.length; i++){
            int currentSum = runningSum + nums[i];
            //如果currentSum都没法大于一个数自己，说明这个数之前的runningSum并不会做任何贡献
            runningSum = Math.max(currentSum, nums[i]);
            globalMax = Math.max(runningSum, globalMax);
        }
        return globalMax;
    }
}