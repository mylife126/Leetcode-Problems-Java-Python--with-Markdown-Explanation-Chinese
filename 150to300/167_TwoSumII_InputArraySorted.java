/*
O(n)
two pointer
既然是sorted，那么最后一位肯定大于第一位。所以 两个指针 big 和 small
1. 计算 big + small
2. 如果sum > target, 那说明就得把big缩小，所以bigpointer --
3. 反之small pointer++
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int bigPointer = numbers.length - 1;
        int smallPointer = 0;
        int[] ans = new int[2];
        while(smallPointer < bigPointer){
            int numSmall = numbers[smallPointer];
            int numBig   = numbers[bigPointer];
            int sum = numSmall + numBig;
            if (sum == target){
                ans[0] = smallPointer + 1;
                ans[1] = bigPointer + 1;
                break;
            }
            if (sum > target){
                bigPointer -=1;
            }
            
            if (sum < target){
                smallPointer +=1;
            }
        }
        return ans;
    }
}