/*
solutio 1: brutal force
以每一个i的位置作为pivot，计算所有pivot之后的subarray的sum的情况，
[1,3,4,5]
1 ~ 1, 1 ~ 3, 1 ~ 4, 1 ~ 5
3 ~ 3, 3 ~ 4, 3 ~ 5
O(n2)
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            int runningSum  = 0;
            for (int j = i; j < nums.length; j++){
                runningSum += nums[j];
                if (runningSum == k) count+=1;
            }
        }
        return count;
    }
}

/*
但是上述的方法有一个很大的问题， 那就是重复计算， 
[1,2,3,4]
1 + 2 + 3的时候其实 1 + 2已经存在了。 那么我们是不是可以建立一个prefixSum的array来记录从 第零位 到 第i位的所有sum呢？



可见prefixSum[i]    = nums[0] + nums[1] +    nums[j] + nums[j+1]...nums[i]
那么prefixSum[j]    = nums[0] + nums[1] +    nums[j]                           suppose i > j
所以subarray[j, i]的sum = prefixSum[i] - prefixSum[j]

那么现在问题转化为， prefixSum[i] - prefixSum[j] = k ? => prefixSum[i] - k = prefixSum[j] ? 即为我现在的runningSum的位置下，减去K的rest，是否有一个prefixSum[j]？ 如果有的话，则count ++， 没有则说明上述等式不成立

所以我们可以用hashmap来记录这样的情况，我们走过的 ith的 prefixSum[i] - k 一定发生在 prefix[j]之后 所以这就保证了连续性. 其次hash记录的是该prefixSum出现了几次

例子：
               [0, 1,1,1，1]
             [0,0, 1,2,3，1]
出现的次数    [1,2, 1,1,1, 1] 
可见满足的情况有[0,1,1] [1,1], 二者需要找的prefixSum[j] 都等于0， 而0 出现了两次
*/

class Solution{
    public int subarraySum(int[] nums, int k){
        if (nums == null || nums.length == 0){
            return 0;
        }
        //map记录 prefixSum[j]出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int prefixSumI = 0;
        int count = 0;
        for (int i : nums){
            prefixSumI += i;
            int prefixSumJ = prefixSumI - k;
            count += map.getOrDefault(prefixSumJ, 0);
            map.put(prefixSumI, map.getOrDefault(prefixSumI, 0) + 1);
        }
        return count;
    }
}