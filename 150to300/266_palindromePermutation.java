class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0){
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++){
            char thisChar = s.charAt(i);
            map.put(thisChar, map.getOrDefault(thisChar, 0) + 1);
        }
        
        int count = 0;
        for (char c : map.keySet()){
            int thisCount = map.get(c);
            if (thisCount % 2 != 0){
                count +=1;
            }
        }
        if (count > 1) return false;
        return true;
        
    }
}