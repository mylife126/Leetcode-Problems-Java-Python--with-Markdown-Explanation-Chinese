
/*
所以这道题的核心是避重，也就是说需要先把array sort一次，然后套路和combination sum2 的思路一致， 每次递归都有一个 idx 作为for loop的起始值，那么在同一层内，例如 for i = 0，当进入第一层递归 idx = 1 nums[idx] = 2这一层结束后， 我们会进入下一层递归 idx = 2 nums[idx] = 2', 那这个时候 我们就可以判断， 此刻的 i > idx 且 nums[i] == nums[i - 1]。 所以就可以跳过这一项，避免又加入一次 [1 2]进ans list里
idx = 0
-> 
    idx = 0
    ->1 append, recurssion @ idx = 1
        -> 1 2 append, recurssion @ idx = 2
        -> 1 2 2' append, rcurssion @idx = 3, out of bound return
        -> 1 2', 2' i = 2 > idx = 0, and nums[i] = nums[i - 1] continue
        for loop end return 

    idx = 1
    -> 2 append, recurrsion @idx = 2
        -> 2 2' append recurssion @ idx = 3, out of bound return

    idx = 2
     -> 2', i = 2 > idx = 0, and nums[i] = nums[i - 1 continue]
    for loop over return 
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<Integer>());
        if (nums == null){
            return ans;
        }
        int idx = 0;
        //避重必做sort
        Arrays.sort(nums);
        bt(ans, new ArrayList<Integer>(), nums, idx);
        return ans;
    }
    
    private void bt(List<List<Integer>> ans, List<Integer> temp, int[] nums, int idx){
        /*
        同理subset1， 我们不需要额外的if来做出口，for loop会自己结束out bound的情况
        */
        for (int i = idx; i < nums.length; i++){
            //如果在同一层内， nums[i] == nums[i-1]就leap。 
            if (i > idx && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            ans.add(new ArrayList<Integer>(temp));
            bt(ans, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}