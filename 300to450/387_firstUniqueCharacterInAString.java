/*

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.

Hashtable来存贮每一个char出现的次数，然后linear pass一次stirng，看谁出现过一次，返回即可。
*/
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0){
            return -1;
        }
        char[] charArray = s.toCharArray();
        
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (int i = 0; i < charArray.length; i++){
            if (map.get(charArray[i]) == 1){
                return i;
            }
        }
        return -1;
    }
}