/*
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

可见如果s是t的subsequence，满足的条件是 s的所有char都按照出场的顺序在t中出现。
那么可以twoPointer来解决。 一个是point在s的，另一个是遍历t的。
每当t找到了一个s的char， s的pointer指向下一个。
如果能在t循环结束前 SPointer == s.length() 则表示在找到了 返回
不然在循环结束返回false

*/

class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0){
            return true;
        }
        
        int sPointer = 0;
        for (int tPointer = 0; tPointer < t.length(); tPointer++){
            char currS = s.charAt(sPointer);
            char currT = t.charAt(tPointer);
            
            if (currT == currS){
                sPointer += 1;
                if (sPointer == s.length()){
                    return true;
                }
            }
        }
        return false;
    }
}