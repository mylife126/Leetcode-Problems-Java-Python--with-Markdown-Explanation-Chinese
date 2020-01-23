/*
题目的意思是：
1. s3一定是由s1以及s2组成，所以 len(s1) + len(s2) == len(s3)
2. s3的每一个字符都是由s1和s2的substring alternatively组成的
  s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
  aa from s1,  s1 left = bcc    =>  db from s2,  s2 left= bca
  bc from s1,  s1 left = c      =>  bca from s2, s2 left = []
   c from s1,   s1 left = []
  
  所以都是从头到尾的顺序，不能跳步
 
所以 aa + db 组成了 aadb， aabc + db 组成了 aadbbc。 虽然组成的新字符的顺序和原来的有所不同，但我们只想知道是否能组成。 所以可以用DP来做

1. state function  dp[m][n] m = len(s1), n = len(s2)， 每一个状态代表了从 s1[0: x] 与 s2[0: y]的这两个substrings是否能组成 s3[0 : x+y]的字符串

2. init function： dp[0][0] = true， 意味着空的s1 加上 空的s2 能组成一个空的s3

3. transfer function：  
     s1  c1 c2 c3 c4
   s2        |  
  c'1        v
  c'2    ->  ?
  c'3
  c'4
  
  可见对于dp[2][2]来说，意味着 s1[0:2]的substring + s2[0:2]的substring是否能组成现在的s3[0:4]
  那么我们从上往下看 意味着 原来的if dp[2][1] = true => s3 = c1c2 + c'1, 那么如果c'2 == s3.charAt(4)就说明dp[2][2] == true.
  我们再从左往右看， 意味着 原来的if dp[1][2] = true => s3 = c + c'1c'2， 那么如果c2 == s3.charAt(4)就说明dp[2][2] == true;

4. return dp[m][n] 表示所有的s1 和 s2是否能组成s3
*/


class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2== null || s3== null) return false;
        if (s1.length() + s2.length() != s3.length()) return false;
        /*
        1. state function: 多一项是因为我们要记录s1长度0 和 s2长度0的情况
        */
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        /*
        2. init function：当s1为空 s2为空的时候 能组成一个空的s3
        */
        dp[0][0] = true;
       /*
        transfter function
        */
        for (int i = 0; i <= s1.length(); i++){
            for (int j = 0; j <= s2.length(); j++){
                //0 0 项不需要考虑，直接过
                if (i==0 && j==0) continue;
                //找到s3对应的位置， 注意， 此刻的i 和 j是dp里的index， 其实比string里的长度多一位
                int whereS3 = i + j - 1;
                char s3Char = s3.charAt(whereS3);
                dp[i][j] = false;
                //transfer， 从上往下传递时，看上一项是否为true 以及 s1此刻的char是否等于s3的char
                if (i > 0 && dp[i - 1][j] && s1.charAt(i - 1) == s3Char){
                    dp[i][j] = true;
                    //不然就是看从左往右传递，看上一项是否为true，以及 s2此刻的char是否等于s3的char
                } else if (j > 0 && dp[i][j - 1] && s2.charAt(j - 1) == s3Char){
                    dp[i][j] = true;
                }
            }
        }
        
        return dp[s1.length()][s2.length()];
    }
}