/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false

那对于一个边长来说它的范围就是 1 ~ 整数最大范围。
我们可以binary search来看这个边长的二次方是不是num。 

但要注意integer越界的问题！ mid一开始= 1 + MAXINT, 所以mid会越界，area也会越界，所以需要claim成为long。同理start也需要定义为long


*/

class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 0) return false;
        long start = 1;
        int end = Integer.MAX_VALUE;
        
        while(start <= end){
            long mid = (end + start) / 2;
            long area = mid * mid;
            if (area == num){
                return true;
            }
            
            if (area < num) {
                start = mid + 1;
            }
            
            if (area > num){
                end = (int) mid - 1;
            }
            
        }
        return false;
    }
}