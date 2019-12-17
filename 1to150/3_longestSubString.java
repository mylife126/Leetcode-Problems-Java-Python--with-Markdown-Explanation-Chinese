/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

思路是Sliding window： 
1. right pointer不断增大且记录此刻的char的出现的次数
2. 如果出现大于了1次，说明这个char重复了，那我们就需要移动左窗口。 eabcab这个case， 假设此刻right pointer在第二个a那里，虽然left在e这里，但依旧
意味着此刻的"eabca"是invalid，就需要将左窗口向右移动，且删除hash里左窗口一开始的出现的次数 因为那个char已经不再窗口里面了
3. 循环直到重复项的次数不在大于1
4. 最后记录maxlength

*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() ==0){
            return 0;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++){
            char currentPointedChar = s.charAt(right);
            map.put(currentPointedChar, map.getOrDefault(currentPointedChar, 0) + 1);
            //因为我们只能允许重复一次
            while (map.get(currentPointedChar) > 1){
                //如果出现了大于一次的重复，那就说明无论怎样此刻的子串都需要把left bound往右移动一次
                //直到移动到重复的那一项，然后删掉它，再进一位
                //eabca, 这个时候我们会先把e 删掉， 然后走到a， 把a删掉， 走到了b 满足了终止条件
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
