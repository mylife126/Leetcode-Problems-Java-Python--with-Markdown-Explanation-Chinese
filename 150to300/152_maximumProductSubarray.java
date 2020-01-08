/*
[-1 2 -8]
curr curr* currentMin curr*currentMax currentMin currentMax globalMax  
                                         -1         -1     
2         -2               -2            -2          2         2
3         -6               6             -6          6         6
-8        48               -48           -48        48         48


维护三个变量：
因为上一课的min 很可能是负数，再乘上现在的curr是负数，他就是可能是一个最大值
1. runningMin：min(runningMin * curr, curr, runningMax)  
2. runningMax: max(runingMax * curr, curr, runningMin)
3. globalMax: max(runningMax, globalMax)
*/

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int currentMax = nums[0];
        int currentMin = nums[0];
        int globalMax = nums[0];
        
        for (int i = 1;i < nums.length; i++){
            int curr = nums[i];
            int productWithMin = currentMin * curr;
            int productWithMax = currentMax * curr;
            
            //update the max and min
            currentMax = Math.max(Math.max(productWithMin, curr), productWithMax);
            currentMin = Math.min(Math.min(productWithMin, curr), productWithMax);
            
            //update the global
            globalMax = Math.max(currentMax, globalMax);
        }
        return globalMax;
    }
}