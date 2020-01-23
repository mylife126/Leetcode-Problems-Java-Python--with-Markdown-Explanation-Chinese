/*
给定一个数组，看看是否有一组数字的两个数字间的差 at most t， 而坐标之差是at most 3

Solution1: linear search O(n * k) 遍历每一个数字作为pivot，然后取它之后的所有数字来测一次
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0){
            return false;
        }
        
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                long numDiff = Math.abs((long) nums[i] - (long) nums[j]);
                int idDiff = j - i;
                if (numDiff <= t && idDiff <= k) return true;
            }
        }
        return false;
    }
}

/*
solution 2 Binary Search Tree
思想是： 题目要求两个数字的差尽可能小，这样才能有可能 <= k。 那么如何在 传入一个数字的时候 快速找到 它的比它大的最小数， 或者 比他小的最大的数呢？ 我们就可以用TreeSet。
因为一个bst的左子树和右子树一定确保了他们和自己的parent的差值最小。 

例子：  [11, 20, 5, 30 ,1]
   idx [0,  1 , 2,  3, 4]    K = 3, T = 5
   idx              num                tree
   0                 11                  11        no left, no right
   1                 20                  11
                                           \
                                           20      对于20来说它的largest small num 就是11
   2                 5                   11
                                        /  \
                                       5    20     对于5来说它的smallest large num 是11， 但是依旧没有满足 t 《= 5
   3                 30            （11） dropped   因为indx >= 3了， 类似于sliding window的思想，upto现在还没有答案，那超过windowsize的数字就可以扔掉
                                         20
                                        /  \
                                       5    30      依旧没有满足条件
   4                  1            （20） dropped，  
                                          5
                                        /   \
                                      1       30    5 - 1 =5 《= 5， 且indx满足条件
                                      
  Java有TreeSet这个数据结构可以直接建立上述。 
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0){
            return false;
        }
        
        TreeSet<Integer> bst = new TreeSet<>();
        for (int i = 0 ; i < nums.length; i++){
            int curr = nums[i];
            //找到对于curr来说比他大的最小的那个数字
            Integer ceiling = bst.ceiling(curr);
            if (ceiling != null && Math.abs((long)ceiling - (long) curr) <= t) return true;
            
            //再试一试找curr的比他小的最大的那个数字
            Integer floor = bst.floor(curr);
            if (floor != null && Math.abs((long)floor - (long)curr) <= t) return true;
            
            //如果上述都没有，例如一开始的时候ceiling和floor其实都是null， tree还没建立起来
            bst.add(curr);
            
            //现在如果当前的i 大于等于k了，且一直没有return true，则说明，i - k的那个个数没用了，因为下一轮的时候，新加的数字距离 i - k一定大于k了
            if (i >= k) bst.remove(nums[i - k]);
        }
        return false; 
    }
}

