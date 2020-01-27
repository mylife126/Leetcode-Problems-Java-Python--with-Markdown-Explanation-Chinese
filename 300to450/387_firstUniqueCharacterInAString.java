/*
两个hash， 一个set Seen， 一个map。
如果seen看见过这个char， 就把map里的key value pair直接删了，
不然就把map放入 char 《-》 index

最后取最小的index
*/
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0){
            return -1;
        }
        
        Set<Character> seen = new HashSet<>();
        Map<Character, Integer> index = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            //如果seen有过这个c，直接把key删掉
            if (seen.contains(c)){
                index.remove(c);
            } else {//不然就是放入 char indx pair
                seen.add(c);
                index.put(c, i);
            }
        }
        //如果index是空的说明没有unique的char，直接return -1
        if (index.isEmpty()) return - 1;
        //最后找最小的index
        int where = Integer.MAX_VALUE;
        for (int i : index.values()) {
            where = Math.min(where, i);
        }
        return where;
    }
}