/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

思路和reversed integer一样，将一个int转化为string后reverse 然后比对二者是否一致
*/


class Solution {
    public boolean isPalindrome(int x) {
        String stringInt = Integer.toString(x);
        StringBuilder sb = new StringBuilder(stringInt);
        
        int left = 0;
        int right = stringInt.length() - 1;
        
        while (left < right){
            char leftChar = stringInt.charAt(left);
            char rightChar = stringInt.charAt(right);
            sb.setCharAt(left, rightChar);
            sb.setCharAt(right, leftChar);
            right--;
            left++;
        }
        
        String reversed = sb.toString();
        return stringInt.equals(reversed);
    }
}