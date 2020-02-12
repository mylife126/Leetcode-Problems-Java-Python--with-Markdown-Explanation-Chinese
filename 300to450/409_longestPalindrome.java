class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        boolean hasOdd = false;
        Map<Character, Integer> map = new HashMap<>();
        int runningSum = 0;
        
        for (int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        //找到哪一个奇数次char出现了最多次
        char whichOddMost = '*';
        int oddTime = 0;
        for (char c : map.keySet()){
            if (map.get(c) % 2 != 0){
                hasOdd = true;
                if (map.get(c) > oddTime){
                    whichOddMost = c;
                }
            }
        }
        //如果存在odd的，我们需要修复里面的count，对于最大的那个奇数次可以不管，其余的-1
        if (hasOdd){
            for (char c : map.keySet()){
                if (c != whichOddMost && map.get(c) % 2 != 0){
                    map.put(c, map.get(c) - 1);
                }
            }
        }
        //这个时候的hash里所有char出现的次数都是偶数加一个唯一的奇数次
        for (char c : map.keySet()){
            runningSum += map.get(c);
        }
        return runningSum;
    }
}