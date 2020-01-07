/*
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true


哈希表，每一个char对应一个count。 双遍历
*/
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++){
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++){
            char thisChar = ransomNote.charAt(i);
            if (map.containsKey(thisChar)){
                int count = map.get(thisChar);
                count -= 1;
                map.put(thisChar,count);
                if (count == 0){
                    map.remove(thisChar);
                }
            } else {
                return false;
            }
        }
        return true;
    }
}