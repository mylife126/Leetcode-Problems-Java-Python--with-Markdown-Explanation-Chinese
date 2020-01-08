/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

可见题目要求Constant space complexity， 那么就不能如同majority Element I那样直接hash来做。
那么，我们可以用指针的方法，维护一个变量 count。 
思路就是每次查看这个指针指向的数字是不是等于上一课的数字，如果是，则count++，不然说明遇到了一个新的
数字，那么count 直接等于1。

那么要这样做的前提就是sort！

1. sort Array
2. 单指针遍历sorted array：
   a。 i = 0的时候没有前一刻的比对，单一做处理，count +=1
   b。 else， 我们取前一刻 和 这一刻，如果相等 count ++， 如果不等 count = 1
   上述做完后，我们需要判断是否count > n/ 3：
       如果是， 注意corner case： [2,2]，会出现重复项，所以添加到ans里还需要判断，
            a。 是否此刻ans array为空，如果为空直接添加
            b。 如果不为空，因为array sorted， 如果出现重复添加的情况，ans里最后一位添加的
                一定等于现在将要添加的数字，所以判断是否 lastAdded == thisAdding。
                如果不等，则可以添加。 不然略
                
所以这道题的重点在于 sort， 以及 n/ 3可以导致 哪怕count == 1 都可以添加，所以需要注意避重。而sort就使得添加重复的判别变得很简单，因为重复的永远连在一起。

*/
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return ans;
        }
        //corner case，如果只有一位数，那它一定是答案
        if (nums.length == 1){
            ans.add(nums[0]);
            return ans;
        }
        //步骤一， sort
        Arrays.sort(nums);
        int count = 0;
        int nThird = nums.length / 3;
        //单指针遍历
        for (int i = 0; i <nums.length; i++){
            int thisNum = nums[i];
            //特殊情况处理，如果indx == 0的情况
            if (i == 0){
                count +=1;
            } else {//不然就是idx > 1的情况
                int lastNum = nums[i - 1];
                //如果等于上一课的，count++
                if (thisNum == lastNum){
                    count +=1;
                } else {//不然说明遇到了新的数字，count == 1
                    count = 1;
                }
            }
            
            //无论任何情况都要判别是否count > n /3
            if (count > nThird){
                //避重 corner case [2, 2]
                if (!ans.isEmpty()){
                    //如果会添加重复的情况，一定此刻的adding == lastAdded
                    int lastAdded = ans.get(ans.size() - 1);
                    if (thisNum != lastAdded){
                        ans.add(thisNum);
                    }
                } else {//不然直接添加就好了
                    ans.add(thisNum);
                }
                count = 0;
            } 
        }
        return ans;
    }
}