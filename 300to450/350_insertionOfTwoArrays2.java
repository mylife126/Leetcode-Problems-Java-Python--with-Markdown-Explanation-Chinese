/*
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]

用哈希map来做， 因为nums2是决定性array，相当于nums1 one pass后每一个数字需要存在于nums2中才可以放入ans中。
那么onepass将nums2中的数字出现的次数放入map。

第二个one pass来看num1的数字是否存在于mapKey中 如果存在则append一次，且key对应的value - 1. 如果count == 0，则需要将其从map中删除，以防止重复的问题。

*/

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        //记录每一个在nums2中出现的数字的次数
        for (int num : nums2){
            map.put(num, map.getOrDefault(num, 0 ) + 1);
        }
        
        for (int num : nums1){
            /*
            如果存在这个key，则添加这num于array中，且需要追踪这个数字出现的次数，如果次数已经等于0了
            则说明这个数字匹配完毕了，就从map中删除即可
            */
            if (map.containsKey(num)){
                ans.add(num);
                int left = map.get(num) - 1;
                map.put(num, left);
                if (left == 0){
                    map.remove(num);
                }
            }
        }
        
        int size = ans.size();
        int[] solution = new int[size];
        for (int i = 0; i < size; i++){
            solution[i] = ans.get(i);
        }
        return solution; 
    }
}
    
//     private boolean biSearch(int num, int[] array){
//         int start = 0;
//         int end = array.length - 1;
        
//         while (start <= end){
//             int mid = (start + end) / 2;
//             int midNum = array[mid];
            
//             if (midNum == num){
//                 return true;
//             }
            
//             if (midNum > num){
//                 end = mid - 1;
//             }
            
//             if (midNum < num){
//                 start = mid + 1;
//             }
//         }
//         return false;
//     }
