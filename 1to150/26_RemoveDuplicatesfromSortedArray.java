/*
题目意思是 1 1 2 2 3 变成 1 2 3 x x x， xxx不用管是什么，你只需要return index= 3， 3代表了在数组index =3
这个位置之前都是unique的，且3也意味着有多少个unique的
所以是two pointer来做， slow等待fast找到一个位置上的数字不等于fast- 1上的数字，那这个时候slow的值就可以等于
fast上的值，然后slow去下一个位置， 不然就一直原地等待

  F              F            F              F              F            F
1 1 2 2 3 -> 1 1 2 2 3 -> 1 2 2 2 3 -> 1 2 2 2 3 -> 1 2 2 2 3 -> 1 2 3 2 3   return 3
  S            S            s              S            S              S 
 */
  

class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 1;
        for ( int fast = 1; fast < nums.length; fast++){
            if(nums[fast] != nums[fast - 1]){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}

