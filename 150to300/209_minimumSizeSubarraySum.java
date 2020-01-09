/*
给定一个array， 和一个target。要求找到一个subarray，连续的，使得subarray里的和大于等于target。

那么我们思考一个case
[2,3,7,2], target = 7

brutal force是以每一个数字作为start，然后找ending，最后看长度
那么我们从2开始
-> 2 + 3 + 7 = 12， length = 3
     ->3 + 7 = 10,  length = 2
         ->7 = 7,   length = 1
你会发觉，这里存在的重复运算是从2开始的，因为计算2的时候我们就计算过 3 + 7了。 那么我们是不是可以用
two pointer来解决这个重复的问题呢？

1. 我们的left 一开始在0， 我们的right找到了7这个地方满足了2， 那么这个时候我们的sum很可能是远远大于target的，也就是说现在是safe的，

2. 那么while（sum is safe) 我们可以把左指针往右走一位，sum -= nums[left], 相当于在safe的情况下我们看看3是不是也满足了，因为3作为起点的sum我们已经算过了 = sum -= nums[left] => 2 + 3 + 7 - 2 = 3 + 7
   
   发觉又满足了，且此刻的sum还是大于等于target的，我们再左指针右移动一次走到了2.
   
3. 最后return最小的那个length即可

可见上述的算法中，每一个数字 最多被visit了2次，所以还是o（n）的算法。 而brutal force，最多会被访问n2次。
*/


class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        //初始化subarray的长度，为最长
        int length = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        //移动右指针
        for (int right = 0; right < nums.length; right++){
            sum += nums[right];
            //当此刻的sum safe了，我们可以右移左指针来看看他的情况，因为他的情况其实已经被计算过了
            while (sum >= s){
                length = Math.min(length, right - left + 1);
                if (length == 1){
                    //这个属于最优情况，无需再遍历下去
                    return length;
                }
                sum -= nums[left];
                left++;
            }
        }
        //corner case，很可能没有找到一个答案，所以需要判断length有没有改变过
        if (length != Integer.MAX_VALUE){
            return length;
        } else {
            return 0;
        }
    }
}