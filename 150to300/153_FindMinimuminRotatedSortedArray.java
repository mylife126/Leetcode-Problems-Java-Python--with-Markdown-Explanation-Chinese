/*
relfection point都是最小值，[5,1,2,3,4] ， 1 这个点就是relfection point

三个情况:
1. mid > start ， 说明从start 到 mid都是sorted，那么reflection point一定在右边
2. 不然就是上述情况， 从 mid - end都是sorted，所以reflection point在左边
3. 什么情况下是找到了reflection :
   a. nums[mid - 1] > nums[mid], 如果arraysorted，那不可能mid -1 小于 mid，出现这个可能只能是mid是最小值
   b。 nums[mid + 1] < nums[mid], 同理， mid +1只能为reflection point

    /
   /
  /
  ---------------
                /
               /
              /  
   
*/

class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        //corner case,当array只有一个数的时候
        if (nums.length == 1) return nums[0];
        int start = 0;
        int end = nums.length - 1;
        int mid = -1;
        //corner case，当array的转动pivot是num[0]，array就变成了原本的sorted array了
        if(nums[end] > nums[start]) return nums[start];
        
        while (start <= end){
            mid = (end + start) / 2;
            int midNum = nums[mid];
            int startNum = nums[start];
            int endNum = nums[end];
            /*
            1 2 3 4 5 6 7 => 4 5 6 7 1 2 3 
            */
            if (nums[mid] > nums[mid + 1]){
                return nums[mid + 1];
            }
            /*
            1 2 3 4 5 6 7  =>  6 7 1 2 3 4 5
            */
            if (nums[mid] < nums[mid - 1]){
                return nums[mid];
            }
            /*
            如果中间数大于头，说明从 start ~ mid都是sorted，那反转的地方在后面，且反转的地方才存在reflection point
            1 2 3 4 5 6 7 => 4 5 6 7 1 2 3
            */
            if (midNum > startNum){
                start = mid + 1;
            } else {//反之则是从mid  ~  end都是sorted，1 2 3 4 5 6 7  =>  6 7 1 2 3 4 5
                end = mid - 1;
            }
        }
        
        return -1;
    }
}

