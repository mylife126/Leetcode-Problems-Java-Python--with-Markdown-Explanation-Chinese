/*
题目的本身意思是： 一张牌可以为： 上半部分A[i] 和 下半部分 B[i]
A = [3,5,1,2,3], B = [3,6,3,3,4]

2,1,2,4,2,2     所以 2  1 .. 才是一张牌
5,2,6,2,3,2          5  1 ..

这意味着一张牌的取值要么是top half 要么是 bottom half。所以要想要A，也就是top一致，这意味着A row必须全部都是2 or 5， 
A[0] or B[0]. 因为我们可以确定的就是这一row的头。
或者根据题目要求将Barray转换成同样的一row， 这同样意味着B row必须要全部都是 2 or 5.
那么，这意味着两个array 都需要对两个 target做一次转换的count， 最后结果取最小即可。

helerFunction to find out how many rotations：

    based on Target1, Swap A from B || Swap B from A
    based on Target2, Swap B from A || Swap A from B
                                             
    1. one pass given Array, if Array[i] != target：
                                    then if (ArrayAnother[i] == target) : has +=1 (表示从第二个array到第一个                                                                             array是可以的)
                                    else return MAX_Value.  
    (the max value helps to make a quike comparison when doing Min operation to eliminate the invalid solution)
   
主函数部分 solution 1 基于min(target1)
          solution 2 基于min（target2）
          
最后如果  min（solution1， solution2）！= maxvalue （全部无解的情况），就return min的结果，不然return -1
   
*/

class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        if (A.length != B.length){
            return -1;
        }
        int choice1 = A[0];
        int choice2 = B[0];
        
        //题目要求可以从B变A，也可以从A变B, 所以对于同一个target，我们得算两次，取最小值。 如果都无解，就是max
        int basedChoice1 = Math.min(swap(choice1, A, B), swap(choice1, B, A));
        //同理
        int basedChoice2 = Math.min(swap(choice2, A, B), swap(choice2, B, A));
        
        int ans = -1;
        //所以如果说只要有一个非最大值的解，我们就找到了一个解答，取最小即可
        if (Math.min(basedChoice1, basedChoice2) != Integer.MAX_VALUE){
            ans = Math.min(basedChoice1, basedChoice2);
        }
        return ans;
    }
    
    private int swap(int target, int[] A, int[] B){
        int has = 0;
        for (int i = 0; i < A.length; i++){
            if (A[i] != target){
                if (B[i] == target){
                    //如果B能在这一刻帮助A转换，则count++
                    has +=1;
                } else {//不然已经说明这个转换不成立了
                    return Integer.MAX_VALUE;
                }
            }
        }
        return has;
    }
}