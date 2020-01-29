/*
Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

两个pass， 第一个将所有nums里的数字放入set中，第二个从 1  到  n， 看有谁不在hash里就放入
*/


class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        
        int length = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) set.add(i);
        
        for (int i = 1; i <= length; i++){
            if (!set.contains(i)) ans.add(i);
        }
        
        return ans;
    }
}