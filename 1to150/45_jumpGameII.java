/*
给定一个array， 每一个element代表在这个element下你可以跳步多少间隔。 例如 [2,3,4,5,6]当你在2的时候你能跳最远2步，也就是到4
问，需要多少步，才能到达最后一个element，且一定会到达。

既然题目说明一定会到达，则我们可以用greedy的思想来做，永远找最远的那一个跳步。 在这一步内允许的范围内遍历所有grid，并且
更新下一步可以跳的最远的步数。 然后这一步全部尝试完了，step++。 检测下一步内是否能达到终点，如果是的，那就直接返回step。

在每一步内，尝试所有边界下的走法并找到下一步的最大距离，然后在下一刻以之前找到的最大距离为边界继续尝试所有走法并找到新的最远地点，每次找到一次 step ++。
那么。有一刻的最远距离>=arrayLength， 则说明在此时的步数内我们就能走到终点，return step即可。

解释： 在每一步内，每一步内尝试所有走法比如说一开始init的时候 currentMax = 0, i <= 0, 在第零步的时候我们所能尝试得到的最远能到idx= 2。然后将reachpoint的值赋予currentMax， 表示在下一步（第一步内）能有 i<= newCurrentMax个走法，在第一步内走完所有可能后找到新的nextMax地点后将这一新的boundary给currentMax。此时开始第二步，在第二步内依旧是 i<=newCurrentMax个走法，尝试所有找到下一步（第三步）能走到的最远距离， 我们发觉是6，这就表示第三步内就能走到终点，return即可。

Index
                 0              1              2            3          4         5            6
                 2              1              3            1          1         1            1       
                 ^                                            
 idx             0 尝试：        ^1             ^2   尝试：  ^3          ^4        ^5尝试：    ^
 step            0 -> 1                             ->2                              ->3                            
        
 reachPoint      0 -> 2        =2 noChange     =5   ->5    =4noChange  =5noChange =6 ->6      7 >= 6 return 3
                    |                                |                               | 
                    V                                V                               V
 currentMax      0 -> 2                  currMax=idx->5                   currMax=idx->6
    
*/

class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1){
            return 0;
        }
        /* 
        init at step 0, the furthest reachpoint is 0 
        */
        int step = 0;
        int reachPoint = 0;
        int currMax = reachPoint;
        boolean keepSearch = true;
        int idx = 0;
        while (keepSearch){
            //在每一步内开始尝试所有jump的可能性并且找到下一步的能去的最远的地方
            while(idx <= currMax){
                int jump = nums[idx];
                int nextLocation = jump + idx;
                reachPoint = Math.max(nextLocation, reachPoint);
                //在尝试中idx不断随着array的遍历增加，直到它走到了此步内允许的最大距离。
                idx++;
            }
            //当找完了，我们要去下一步了
            step++;
            //更新下一步内能尝试的所有可能更新
            currMax = reachPoint;
            //我们还需要检测是否下一步内就能达到终点
            if (currMax >= nums.length - 1){
                keepSearch = false;
                return step;
            }

        }
        System.out.println("done");
        return step;
    }
}

