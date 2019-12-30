/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
idx = 0
-> 1  append, recurrsion at i = 0 + 1 = 1 
    -> 1 2 append, recurrsion at i = 1 + 1 = 2  
    -> 1 3 append, recurrsion at i = 2 + 1 = 3 -> idx out of bound, for loop over, call return
idx = 1
-> 2 append, recurrsion at i = 1 + 1 = 2
    -> 2 3 append, recurrsion at i = 2 + 1 = 3 -> idx out of bound, for loop over, call return
idx = 2
-> 3 append, recurrsion at i = 2 + 1 = 3 -> idx out of bount, for loop over, call return
return 
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<Integer>());
        
        if (nums == null|| nums.length == 0){
            return ans;
        }
        int idx = 0;
        bt(ans, nums, idx, new ArrayList<Integer>());
        return ans;
    }
    
    private void bt(List<List<Integer>> ans, int[] nums, int idx, List<Integer> temp){
        /*不同于之前的combination那道题，有一个size的检测，如过size == X了就append后再 retrun
        但是这道题不同于，我们不知道size是多少，而是每次放入temp一个数，就放入一次ans中。然后也不需要每次temp放图ans后就retrun，
        这样会导致，例如刚进去一个 1，apend到ans后就return，那就会执行function后的回溯部分，1就被删掉了。
        
        所以这一题的重点是要在每一层递归后再return， 那就可以用for loop直接来控制return
        [1 2 3] */
        for (int i = idx; i < nums.length; i++){
            temp.add(nums[i]);
            ans.add(new ArrayList<Integer>(temp));
            bt(ans, nums, i + 1,temp);
            temp.remove(temp.size() - 1);
        }
    }
}