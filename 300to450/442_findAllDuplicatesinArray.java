/*
solution 1, one pass with hashSet
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
*/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        
        if (nums == null || nums.length == 0){
            return ans;
        }
        
        for (int i : nums){
            if (seen.contains(i)) ans.add(i);
            else seen.add(i);
        }
        return ans;
    }
}

/*
solution 2, sort 
1 2 2 3 3 4 7 8 
*/
class Solution{
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length - 1; i++){
            int curr = nums[i];
            int next = nums[i + 1];
            if (curr == next) {
                ans.add(curr);
                i = i + 1;
            }
        }
        return ans;
    }
}