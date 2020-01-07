/*
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.


1. state function, dp[length + 1], 代表着 string在ith这个位置是否是breakable的
例如dp["leetcode".length()] == true。 很明显最后是breakable的

2. init dp[0] == true 表示当string为空的这个位置，依旧是true

3. transfer function的思考：以上述 applepenapple为例，
假设我们现在走到了 n这个地方， 也就是applepen这个，很明显它这里dp[7]也是breakable的。推导是，我们从n这个位置看，n + applepe， 很明显 n不在wordDict，applepe = dp[6] != true. 
但当往前推导到了pen + apple就可以看出 pen在dict里且dp[4]== true。 
所以 dp[i] = dp[j - 1] && substring(j, i), j from i ~ 0

4. 最后只需要return dp[length] 代表着全部的string是否是breakable
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0){
            return true;
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //建立set便于后期快速验证
        Set<String> wordSet = new HashSet<>();
        for (String word : wordDict){
            wordSet.add(word);
        }
        
        for (int i = 0; i <= s.length(); i++){
            //开始判别这个数位是否breakable， 后续往前遍历所有substring upto ith
            for (int j = i; j >=0; j--){
                //i == 5的时候只能取到4
                String subString = s.substring(j,i);
                /*
                重点，dp里的j比string多一位，所以dp[0]是string.charAt(-1)
                */
                if (wordSet.contains(subString) && dp[j]){
                    dp[i] = true;
                    //break很重要，不然内循环还会继续，有可能下一个j就让dp[i] == false了
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}