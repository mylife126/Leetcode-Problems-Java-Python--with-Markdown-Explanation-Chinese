/* 
相比于没有重复值的rotated array来说，有了重复值会出现一个worst case， 那就是 mid == start， 那么这个时候我们就没法判断到底
sorted那部分是在左半边还是在右半边了。 如下情况：

1 1 1 1 1 1 1 2 2 2   -> 1 1 1 1 1 2 2 2 1 
                      ->   1 1 1 1 2 2 2 1 
                      ->     1 1 1 2 2 2 1  

所以我们可以检测一次，如果此刻的mid == start，那么我们就该start++, continue这一次，直到一刻我们的mid > start了即可。
*/
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            int midNum = nums[mid];
            
            if (midNum == target){
                return true;
            }
            
            //precheck if the mid == start or not
            if (midNum == nums[start]){
                start +=1;
                continue;
            }
            
            //case one, the left hand is sorted
            if (midNum > nums[start]) {
                /*case 1.1： 如果 small <= target <= mid (target在sorted这部分) 注意一定是加等于的，
                因为这个时候我们不知道target是不是== 下一个区间，
                例如，1 3 5，target = 1， 如果不加等号，那代码会执行else那句话了。
                */
                if (target <= midNum && target >= nums[start]){
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {//case 2， right 是sorted
                //同理如果target在sorted这部分
                if (target >= midNum && target <= nums[end]){
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }
}


