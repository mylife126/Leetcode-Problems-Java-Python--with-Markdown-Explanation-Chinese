/*
对于：
-4 -1 -1 -1 0 1 2 来说，
先取到i项作为第一个数，那么问题就转化成 0 - a = -a, target = -a

这个时候，我们对i之后的所有项进行twosum，j = i+1，且原始target是0，这意味着三个数不可能是相同的，所以在j这一层循环里
可以进行剪枝，当nums[j+1] = nums[j], j++，跳过多余的计算。另一个角度是重复， 例如a = 第一个-1. 那么 答案是 -1 - 1 2，但是j可以取两次不同的-1，分别在idx2 和 idx3上。

但是有一个问题，那就是这里不同于twoSum是，twosum说明了每一个array有一个唯一解，意味着没有重复的问题。 举例 a = 第一个-1， 答案是 idx: 1, 2, 5; 但同样当a = 第二个-1时， 答案是 2 3 5， 但其实重复了。 所以在i这层循环，需要做的是遇到重复跳过
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
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
            int a = nums[i];
            int target = 0 - a;
            //以当下的i作为a，pivot，然后对他后面的数进行twosum遍历
            for (int j = i + 1; j < nums.length - 1; j++){
                int b = nums[j];
                int remainder = target - b;
                //hash里有c还不足以，必须要保证c的idx不是i自己，同时也不能是j自己，例如 -1 0 2， 如果不加这一个条件
                //答案就会成为  -1 -1 2
                if (map.containsKey(remainder) && map.get(remainder) > i && map.get(remainder) > j){
                    List<Integer> added = new ArrayList<>();
                    added.add(a);
                    added.add(b);
                    added.add(remainder);
                    ans.add(added);
                }
                //剪枝
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