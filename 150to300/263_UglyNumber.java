/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example 1:

Input: 6
Output: true
Explanation: 6 = 2 × 3
Example 2:

Input: 8
Output: true
Explanation: 8 = 2 × 2 × 2

思路类似余 power of 2这道题。 只是现在我们有三个不同的 divider。
那么一样的，只要现在的num能够整除这三个数中的任意一个，我们就对他进行 整除的操作。 如果能通过整除这
3个数中的任意一个到底，最后的结果一定是1. 

所以只需要return 是否 num == 1即可


*/

class Solution {
    public boolean isUgly(int num) {
        if (num == 0) return false;
        while (num % 2 == 0 || num % 3 == 0 || num % 5 == 0){
            if (num % 2 ==0){
                num = num / 2;
            } else if (num % 3 == 0){
                num = num / 3;
            } else {
                num = num / 5;
            }
        }
        return num == 1;
    }
}