// O(n * klogk) , n is the one pass of the strings, and k is the length of the largest string's length
/*
我们只需要每次将sort后的string放入同一个key value pair即可
*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs == null || strs.length == 0){
            return ans;
        }
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs){
            char[] thisArray = str.toCharArray();
            Arrays.sort(thisArray);
            //conver the char array back to string
            String sorted = String.valueOf(thisArray);
            // if (map.containsKey(sorted)){
            //     map.get(sorted).add(str);
            // } else {
            //     List<String> thisList = new ArrayList<>();
            //     thisList.add(str);
            //     map.put(sorted, thisList);
            // }
            map.putIfAbsent(sorted, new ArrayList<String>());
            map.get(sorted).add(str);
        }
        
        for (List<String> list : map.values()){
            ans.add(list);
        }
        
        return ans;
     }
}

