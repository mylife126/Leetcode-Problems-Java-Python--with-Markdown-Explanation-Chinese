/*
Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

所以可以看出只需要找到存在的common的int，且每一个数只需要存一次。那么首先one pass nums1去set中来去重。

然后第二个one pass linear search num2中的数字，找到匹配的则放入第二个set中。

最后one pass 放入ans array中即可


*/

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        //建立num1的unique set
        for (int i : nums1){
            set.add(i);
        }
        //建立重复值set
        Set<Integer> list = new HashSet<>();
        for (int num : nums2){
            if (set.contains(num)){
                list.add(num);
            }   
        }
        
        int size = list.size();
        int[] ans = new int[size];
        int i = 0;
        //最后one pass 重复值set中的答案
        for (int num : list){
            ans[i] = num;
            i+=1;
        }
        return ans;
    }
}


