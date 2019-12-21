/*
和remove duplicate I类似，只是现在允许重复两次的数字为valid的，那现在思想就是，依旧从idx= 1 开始init slow和fast pointer，
1. for 循环 fast； 如果此刻 nums[fast - 1] == nums[fast]， 那么我们的count +=1 
    1.1 我们只能允许重复一次，所以只有当count <= 1的时候我们的 nums[slow] 被 nums[fast]赋值，不然原地等待
2. 但如果说 此刻的 nums[fast - 1] != nums[fast] 那说明我们遇到了一个新的数字，所以count 需要清零， 且此刻 slow的位置上也该被fast来赋值。
4. 最后也是ruturn slow即可，表示总共有多少此类的数字，且在该indx之前都是合法的。

 init              for loop start

   F                 F                  F                   F                  F                  F                  F 
 1 1 1 1 2 2 3  -> 1 1 1 1 2 2 3 -> 1 1 1 1 2 2 3 - > 1 1 1 1 2 2 3 -> 1 1 2 1 2 2 3 -> 1 1 2 2 2 2 3 -> 1 1 2 2 3 2 3
   S                 S                  S                 S                  S                  S                  S           
                   count = 1        count = 2          count = 3        count = 0         count = 1       count = 0

return 5, 意味着总共5个这样的数字 且有且仅有一次重复。
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 0;
        //init slow pointer
        int slow = 1;        
        for (int fast = 1; fast < nums.length; fast++){
            //case 1， 如果出现了重复
            if (nums[fast - 1] == nums[fast]){
                //那么count +=1
                count+=1;
                //当且仅当这个重复只有1次或者0次，我们的slow才会前进
                if (count <= 1){
                    nums[slow] = nums[fast];
                    slow++;
                }
            } else {//不然说明我们的fast跑到了一个新的数字了，
                //那么对于这个数字来说重复的count为0次
                count = 0;
                //所以也该让slow前进一次，且在该位置上赋值fast的找到的合理的数值
                nums[slow] = nums[fast];
                slow++;
            }
        }        
        return slow;  
    }
}
