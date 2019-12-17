/*
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21

Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
思路是two pointer, 首pointer的值等于末pointer的值，然后left++, right--

*/
class Solution {
    public int reverse(int x) {
        //先将int转化为string便于操作
        String stringInt = Integer.toString(x);
        //但string是immutable的，需要用sb来变成mutable
        StringBuilder sb = new StringBuilder(stringInt);
        
        int left = 0;
        //如果第一项是负号，那我们就不用管他，leave it as it was， 所以将left换成idx= 1
        if (stringInt.charAt(0) == '-'){
            left = 1;
        }
        
        int right = stringInt.length() - 1;    
        while (left < right){
            char firstDigit = stringInt.charAt(left);
            char lastDigit = stringInt.charAt(right);
            sb.setCharAt(left, lastDigit);
            sb.setCharAt(right, firstDigit);
            left++;
            right--;
        }
        //注意这里得用long来转化 防止overflow
        long ans = Long.parseLong((sb.toString()));
        if (ans < Math.pow(-2,31) || ans > Math.pow(2, 31) - 1){
            return 0;
        }
        return (int) ans;
    }
}