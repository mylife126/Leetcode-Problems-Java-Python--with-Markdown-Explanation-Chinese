/*
On的解法，linear search：
1. 从第二位数字开始循环 作为pivot
2. 每一位pivot 都对比前一位
  a/ 如果 pivot - last > 1 则 我们需要将ans更新直至 pivot - ans的差值大于， 同时k --， 来记录我们找到了
  第几位element
      a.1 如果此刻k ==0， 表示我们找到了，则直接return
  b/ 不然我们就继续循环到循环结束。 那么我们的第k位数字是从数组的最后一位开始不断加1，知道k == 0
*/

// class Solution {
//     public int missingElement(int[] nums, int k) {
//         if (nums == null || nums.length == 0) return -1;
//         // corner case， 当数组只有一位数，则答案就是 第一位数 + 1
//         if (nums.length == 1) return nums[0] + 1;
//         //定义runnning ans
//         int ans = -1;
//         //开始以每一位数字作为pivot
//         for (int i = 1; i < nums.length; i++){
//             int curr = nums[i];
//             int last = nums[i - 1];
//             //当当前的pivot - 上一位 大于1了，则里面存在missing element
//             if (curr - last > 1){
//                 ans = last;//missing element 一定是从上一位开始
//                 while (curr - ans > 1){
//                     ans += 1;
//                     k -= 1;
//                     if (k == 0) return ans;
//                 }
//             }
//         }
//         //如果没能在上述return则missing element是从数组最后一位开始
//         ans = nums[nums.length - 1];
//         while (k > 0){
//             ans += 1;
//             k -=1;
//         }
//         return ans;
//     }
// }

/*
Better O(n) Solution:
上面的方法是，只要发觉有gap 就去尝试将ans = lastNum + 1, 直到k == 0
但是，其实这里存在无效计算：
4 7 9 10， k = 3
上述方法中， 4 7之间有一个gap， 那我们就会计算 ans = last = 4, ans += 1, 直到6， 但是我们发觉这个gap
里面并不能找到第k个，因为gap不够。

那么我们是不是可以直接找到一个合适的gap一次性找到这个数字呢， 我们可以建立一个array记录每一个数字与其之前第一个数字间有多少个missing value
id   0 1 2  3
nums 4 7 9 10
miss 0 2 3  3
计算的方法是    num[i] - (num[0] + indxDiff) 因为 num[0] + indxDiff是本应该到达的数位，那么如果有missing， numI减去这个应该到达的数位就得到了还差多少

这样假设k = 3， 我们只需要遍历每一个数字的相应的missArray，找到 一个gap >= K即可，例如上述情况，我们的位置发生在9这个地方，表示9之前有三个missing。 那么我们需要用 7 + （k - missing（7））， 因为upto 9 的missing是包含了upto7的missing，所以我们以7为starting pivot的话需要减去7之前的missing的个数 

*/
// class Solution {
//     public int missingElement(int[] nums, int k) {
//         //Step 1 建立missing Array
//         int[] missing = new int[nums.length];
//         for(int i = 0; i < nums.length; i++){
//             if (i == 0){
//                 missing[i] = 0;
//                 continue;
//             }
//             missing[i] = countMissing(nums, i);
//         }
//         //corner case: 如果upto最后一位的missing个数依旧小于k，则说明第k个存在于最后一位数之后
//         if (missing[nums.length - 1] < k){
//             return nums[nums.length - 1] + (k - missing[nums.length - 1]);
//         }
//         //step2, 开始寻找合适的gap
//         int where = 0;
//         for (int i = 0; i < nums.length; i++){
//             int gap = missing[i];
//             if (gap >= k){
//                 where = i;
//                 break;
//             }
//         }
//         return nums[where - 1] + (k - missing[where - 1]);
//     }
//     private int countMissing(int[] nums, int i){
//         return nums[i] - (nums[0] + i - 0);
//     }
// }



/*
solution 3： binary search
但是solution2 中其实我们可以binary search直接找到这个pivot，使得missing[pivot] >=k
id   0 1 2  3
nums 4 7 9 10
miss 0 2 3  3

mid = start + end / 2
1. mid = 2 , missing[2] = 3 >=k 则right == 2
   =》 mid = 1， missing[1] = 2 < k ， 则left = pivot + 1 = 2  找到了这个pivot了，return

所以pivot = 2
*/
class Solution {
    private int countMissing(int[] nums, int i){
        return nums[i] - (nums[0] + i - 0);
    }
    
    public int missingElement(int[] nums, int k) {
        int[] missing = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            if (i == 0) {
                missing[i] = 0;
                continue;
            }
            missing[i] = countMissing(nums, i);
        }
        
        //corner case:
        if (missing[nums.length - 1] < k){
            return nums[nums.length - 1] + (k - missing[nums.length - 1]);
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while(start != end){
            int mid = (start + end) / 2;
            int gap = missing[mid];
            if (gap < k) start = mid + 1;
            else end = mid;
        }
        return nums[start - 1] + (k - missing[start - 1]);
    }
    
}


