/*
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

1 2 3
hash记录这一层出现过的数字，然后回溯删除hash，以及temp Array
-> 1
    -> 2
         ->3 ans.add, return
    -> 3
         ->2 ans.add, return
    for loop over return
-> 2 
    -> 1
         ->3 ans.add, return
    -> 3
         ->1 ans.add, return
    for loop over return
-> 3
    -> 1
         -> 2 ans.add, return
    -> 2
         -> 1 ans.add, return
    hashcontains 3, for loop over return
over
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return ans;
        }
        Set<Integer> set = new HashSet<>();
        
        bt(ans, set, new ArrayList<Integer>(), nums);
        return ans;
    }
    private void bt(List<List<Integer>> ans, Set<Integer> set, List<Integer> tempArray, int[] nums){
        //定义出口
        if (tempArray.size() == nums.length){
            ans.add(new ArrayList<>(tempArray));
            return;
        }
        //for循环每一个可能性，
        for (int i = 0; i < nums.length; i++){
            int curr = nums[i];
            //检测这个数字出现过没有
            if (set.contains(curr)){
                continue;
            }
            //在这一个层级加入hash里防止重复
            set.add(curr);
            tempArray.add(curr);
            
            bt(ans, set, tempArray, nums);
            //回溯
            set.remove(curr);
            tempArray.remove(tempArray.size() - 1);
        }
    }
}

