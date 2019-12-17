class Solution{
    public int[] twoSum(int[] nums, int target){
        int[] ans = new int[2];
        if (nums == null || nums.length == 0){
            return ans;
        }
        //建立map 将所有int所对应的indx 存入hash中
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int thisNum = nums[i];
            map.put(thisNum,i);
        }
        
        for (int i = 0; i < nums.length; i++){
            int thisNum = nums[i];
            int remainder = target - thisNum;
            //如果该数的remainder存在于hash，表示我们找到了一pair，但还得确保这个数不会是自己，所以用indx来判断是不是
            //找到了自己，那么判断的依旧是 这个indx需要大于i
            if (map.containsKey(remainder) && map.get(remainder) > i){
                ans[0] = i;
                ans[1] = map.get(remainder);
            }
        }
        return ans;
    }
}