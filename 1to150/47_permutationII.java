/*
以下是用combination sum的思路， 每次for loop从上一层的idx开始
->1 idx = 0
    -> 1 1 idx = 1
          -> 1 1 2 idx = 2
    -> 1 2 idx = 2
          -> 由于idx =2 ， for loop从2开始，这直接结束了， 所以不能用idx来让每次递归往当前数往下一位查找来避重。 正确的方案是一直从0开始，但是呢需要用hash来避免重复，遇到过的indx，就跳过。

但现在的array有了重复值， [1,1,2]， 所以用permutation1的思路的话，会出现重复 2 1 1', 2 1' 1,也就是说我们在第一层递归 2 + ？的时候，需要
避免一次 1 or 1'. 那么 
-> 2
     -> 1 
          - > 1' return
     此刻回溯的时候hash里没有1了，也就是 indx = 1没有了
     -> 发觉 nums[2] = 1 == nums[2 -1] 且 hash中没有idx=1，这说明此刻的1'是重复项。跳过，直接continue      
*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0){
            ans.add(new ArrayList<Integer>());
            return ans;
        }
        
        Set<Integer> indexSeen = new HashSet<>();
        List<Integer> temp = new ArrayList<>();

        Arrays.sort(nums); //重点！！ 去重的第一步是sort
        bt(nums, indexSeen, temp, ans);
        return ans;
    }
    
    private void bt(int[] nums, Set<Integer> indexSeen, List<Integer> temp, List<List<Integer>> ans){
        //if the temp array already holds the same numbers of elements in the given nums
        //that just means we have find one combination
        if (temp.size() == nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (!indexSeen.contains(i)){
                //所以用indexSeen.contains(i-1)来判断，第0层第一次返回后，hash里没有 1(0)了，然后
                //pointer跑去了1’， 这个时候发掘 1‘ == 1 且hash里没有0这个index，这个意味着这一层已经被查找过一次
                //而不是第一次查找， 这就取得了上面的那个同样的功效： 第一次查找不避重，第二次才避重
                if (i > 0 && nums[i] == nums[i-1] && !indexSeen.contains(i - 1)) {
                    continue;
                }
                indexSeen.add(i);
                temp.add(nums[i]);
                bt(nums, indexSeen, temp, ans);
                temp.remove(temp.size() - 1);
                indexSeen.remove(i);
            }
        }
    }
}


    