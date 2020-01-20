/*
twoPointer:
1. 两个指针同时指向每一个字符的第一位， 循环直到有一个越界
2. 如果同时满足相等，spointer和tpointer 同时加加
3. 如果不同，
   a。 我们允许一次不同，所以count 此刻加一，或者boolean表示已经出现了一次
       a.1接着如果s长度大于t的长度，这意味着此刻的我们需要提前检测s的第二位是否不同于t的第一位
       
          例子 "cab" "ab" 因为此刻两个字符的第一位已经不同了，potentially已经是有一位不同了，
          且我们知道他们的长度一定不超过一位，现在第一位也不同，
          那么，能使得potentially 二者距离只差只有1的可能性那必然是s下一位和t的第一位相同。
          cab
           ab
          然后祈祷最后一位是一致的。
          同样 "cbc" "ab" 我们可以看到s的第二位已经不满足等于t的第一位了，那肯定就是失败了。
          那么就sPointer++
       a.2反之tPointer++
       a.3长度相同的时候 一起++   “ab”  “bb”
*/

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1 || s.equals(t)){
            return false;
        }
        
        int count = 0;
        int sPointer = 0;
        int tPointer = 0;
        
        while(sPointer < s.length() && tPointer < t.length()){
            char charS = s.charAt(sPointer);
            char charT = t.charAt(tPointer);
            
            if (charS != charT){
                count +=1;
                if (count >= 2){
                    return false;
                }
                if (s.length() > t.length()){
                    sPointer +=1;
                } else if (s.length() < t.length()) {//case："cab" "ab" | "cac" "ab" 
                    tPointer +=1;
                } else {// cornerCase: "cb" "ab", 此刻长度相同就需要同时增加pointer
                    sPointer++;
                    tPointer++;
                }
            } else {
                sPointer++;
                tPointer++;
            }
        }
        return true;
    }
}
// cbc
// ab