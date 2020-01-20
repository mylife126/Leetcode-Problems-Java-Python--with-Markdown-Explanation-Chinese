/*
1. state function: dp[m + 1][n + 1]. 表示的是word1 从0 ~ i位 与 word2从0~j位比，最短的edit distance
2. init function： dp[0][0] = 0， 0~ 0 vs 0~0 是无需edit的
                   dp[0][j] = j, word1为空，对应j位的word2， 那每一次都是需要edit j次
                   dp[i][0] = i， 同上
3. transfer function：每一个word 从第1位开始，
                    case1: word1的这一位修改 => dp[i - 1][j] + 1
                    case2: word2的这一位修改 => dp[i][j - 1] + 1
                    case3: word1 word2等长，那么就看末尾一样与否 dp[i - 1][j - 1] + [bool Ai== Bj]
                    dp[i][j] = min(case1,case2,case3)
                    
4. return 末尾
*/
class Solution {
    public int minDistance(String word1, String word2) {
        //word1 : 0 ~ i; word2: 0 ~ j
        //word1 from 0 ~ i vs word2 from 0 ~ j, the minimal edit distance
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int j = 0; j < word2.length() + 1; j++){
            dp[0][j] = j;
        }
        for (int i = 0; i < word1.length() + 1; i++){
            dp[i][0] = i;
        }
        int notsame = 0;
        for (int i = 1; i < word1.length() + 1; i++){
            for (int j = 1; j < word2.length() + 1; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    notsame = 0;
                } else {
                    notsame = 1;
                }
                int case1 = dp[i-1][j - 1] + notsame;
                int case2 = dp[i - 1][j] + 1;
                int case3 = dp[i][j - 1] + 1;
                dp[i][j] = Math.min(Math.min(case1, case2), case3);
            }
        }
        return dp[word1.length()][word2.length()];
    }
}