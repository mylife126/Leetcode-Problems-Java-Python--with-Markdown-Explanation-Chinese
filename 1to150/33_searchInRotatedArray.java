
/*
由于array被rotate了 这就意味着如果我们用了binary search的话，位于中间pivot来说有两个可能性:

1. 它比头要小，说明在他这个位置往后是sorted，已经rotate
0 1 2 3 4 5 6 7 8 -> 6 7 8 0 1 2 3 4 5
对于这个case来说，如果 mid < target < tail, 那还是和正常的binarysearch一样，small = mid + 1， 也就是往后区间那部分sorted的找
不然，如果 target 就是在unsorted的那部分，large = mid - 1


2. 它比头还是大，说明在他这个位置之前还是sorted 尚未rotate
0 1 2 3 4 5 6 7 8 -> 3 4 5 6 7 8 0 1 2
对于这个case来说，先看sort那部分 如果 head < target < mid, large = mid - 1, 不然就是在unsorted那部分，所以small = mid + 1

所以里用mid 作为pivot可以找到一段sorted的部分，然后以那一段作为第一个判断基准，来做binarysearch的 small和large的相应更新，那么不在sorted的那一段的更新就可以取反就好了。
*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int small = 0;
        int large = nums.length - 1;
        while (small <= large){
            int mid = (small + large) /2;
            int smallNum = nums[small];
            int largeNum = nums[large];
            int midNum = nums[mid];
            
            if (midNum == target){
                return mid;
            }
            
            if (midNum >= smallNum){
                if (smallNum<= target && target <= midNum){
                    large = mid - 1;
                } else {
                    small = mid + 1;
                }
            } else {
                if (midNum <= target && target <= largeNum){
                    small = mid + 1;
                } else {
                    large = mid - 1;
                }
            }
        }
        return -1;
    }
}