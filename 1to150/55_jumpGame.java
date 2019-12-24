/*
维护一个global变量 reachPoint, 说的是在每次跳跃后的最远能去的地方。
[2 3 1 1 4]              reachpoint
 ^                            0 +2 =2   那就意味着我们i可以去 i=1 或者 i= 2
   ^                          1 + 3 =4  那就意味着i可以去 2 3 4
     ^                        2 + 1 = 3 可见当我们选择只跳i = 1步的时候，能去的最远距离被之前的4涵盖了，也就是说 i <= 4的话，依旧能涵盖所有的数位
所以我们循环只需要保证 i <= maxReachPoint就能保证for 循环的时候取到所有应该取到的数字，并且跳跃后继续维护这个maxReachPoint即可。

         [2,3,1,1,4]
    i =0  ^   ^= idx2 reachpoint = 2， i<= 2 （现在可以跳到 1 or 2上面）
    i =1    ^     ^ = idx 1 + 3 = 4 reachpoint = 4 >= nums.length - 1 return true
         
         [3,2,1,0,4]
    i =0  ^     ^ = idx 0 + 3 = 3 reachpoint = 3 i <= 3
    i = 1   ^   ^ = idx 1 + 2 = 3 reachpoint = 3
    i = 2     ^ ^ = idx 2 + 1 = 3 reachpoint = 3  for loop is over, because i must <= reachpoint, because i is how much it could jump from the current position.这意味着greedy，因为如果此刻能让我们跳的更远，我们的i循环的范围一定是包含了之前的小范围，所以for loop下i依旧会遍历每一个位置去找可能性。


*/
public class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0){
            return true;
        }
        int reachPoint = 0;
        //让i可以跳到目前最远的reachpoint，来找每一个数位，来看每一个数位是否能满足要求
        for (int i = 0; i < nums.length && i <= reachPoint; i++){
            int currentPos = i;
            int jumpSteps = nums[currentPos];
            int newPlace = jumpSteps + currentPos;
            reachPoint = Math.max(reachPoint, newPlace);
            if (reachPoint >= nums.length - 1) return true;
        }
        return false;
    }
}