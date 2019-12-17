/*
想法和threeSum一致，先取a，然后余下的threeSumTarget来做3sum
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
                List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0 || nums.length < 3){
            return ans;
        }
        //需要将array sort， 这样才能使得重复项连续
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int thisNum = nums[i];
            map.put(thisNum, i);
        }
        
        for (int i = 0; i < nums.length - 1; i++){
            //以当下的i作为a，pivot，然后对他后面的数进行twosum遍历
            int a = nums[i];
            //取新的target来做3sum
            int threeSumTarget = target - a;
            //3sum， j项在i之后
            for (int j = i + 1; j < nums.length - 1; j++){
                int b = nums[j];
                //取新的2sumTarget来做2sum
                int twoSumTarget = threeSumTarget - b;
                //2sum
                for (int k = j + 1; k < nums.length - 1; k++){
                    int c = nums[k];
                    int remainder = twoSumTarget - c;
                    //同样边界条件是map里这一项不能是自己，也就是为了避免重复选到自己  -1 0 2, 不避重的话hash里存在-1这一项，那答案就成了 -1 -1 2
                    if (map.containsKey(remainder) && map.get(remainder) > i && map.get(remainder) > j && map.get(remainder) > k){
                        List<Integer> added = new ArrayList<>();
                        added.add(a);
                        added.add(b);
                        added.add(c);
                        added.add(remainder);
                        ans.add(added);
                    }
                    //剪枝，去重
                    while (k < nums.length - 1 && nums[k] == nums[k + 1]){
                        k++;
                    }
                }
                //剪枝，去重
                while (j < nums.length - 1 && nums[j] == nums[j + 1]){
                    j++;
                }
            }
            //去重
            while (i < nums.length - 1 && nums[i] == nums[i + 1]){
                i++;
            }
        }
        return ans;
    }
}