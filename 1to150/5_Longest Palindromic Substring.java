/*
Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
*/
/* b a b a d    bab那一项之所以是T，是因为 首先 b == b， 首位相等， 其次，中间部分a自己是true
 b T
 a F T
 b T   T
 a       T
 d         T 
 
1. state[][] = 从i 到 j的pali的情况， T or F
2.  init： ii项都为正
3.  transfer function： state[i][j] = char(left) == char(right) && dp[i + 1][j - 1] == true || right - left < 2
4. 如果以上判断成立，则dp[left][right] == true, 且判断此刻长度是否大于之前的，如果是则记录下当下的最佳left 和 right
 */

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() ==0 ){
            return "";
        }
        //最差情况就是自己， 那长度也是1
        int maxLength = 1;
        //建立size为2的array来记录最佳的left 和 right pointer
        int[] bestID = new int[2];
        bestID[0] = 0;
        bestID[1] = 0;
        //state function
        boolean[][] dp = new boolean[s.length()][s.length()];
        //init
        for (int left = 0; left < s.length(); left++){
            dp[left][left] = true;
        }
        //transfer function
        for (int right = 0; right < s.length(); right++){
            //注意 是 left < right 因为我们要逐渐从左到右的增加长度
            for (int left = 0; left < right; left++){
                if (s.charAt(left) == s.charAt(right) && (right - left < 2 || dp[left + 1][right - 1] == true)){
                    dp[left][right] = true;
                    int currentLen = right - left + 1;
                    if (currentLen > maxLength){
                        maxLength = currentLen;
                        bestID[0] = left;
                        bestID[1] = right;
                    }
                    
                }
            }
        }
        return s.substring(bestID[0], bestID[1] + 1);
    }
}