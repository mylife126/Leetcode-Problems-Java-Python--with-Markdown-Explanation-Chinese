class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return ans;
        }
        Map<String, String> phone = new HashMap<String, String>();
        phone.put("2", "abc");
        phone.put("3", "def");
        phone.put("4", "ghi");
        phone.put("5", "jkl");
        phone.put("6", "mno");
        phone.put("7", "pqrs");
        phone.put("8", "tuv");
        phone.put("9", "wxyz");
        
        bt(phone, ans, "", digits);
        return ans;
    }
    /*234 - > '' + a + d + g (recussively)-> append to ans, return to ad -> ad + h -> append to ans - > 
    return to ad -> ad + i -> append to ans return to a + e -> ae + g... so on so forth
    */
    public void bt(Map<String, String> phone, List<String> ans, String combination, String nextDigit){
        //we will always firstly add the first char of each digit, 
        //and iterate through all the chars in each digit's corresponding letters set
        //thus, a + d + g, is one answer, and this is the exist of the recurrsion 
        // if (nextDigit == null){ 这里不能判断为null， 因为到最后是一个空的string，不是null
        if (nextDigit.length() == 0){
            ans.add(combination);
            return;
        }
        //get the first digit so that we can have the map
        String thisDigit = nextDigit.substring(0,1); 
        String letters = phone.get(thisDigit);
        
        for (int i = 0; i < letters.length(); i++){
            String neededToAdd = letters.substring(i,i+1);
            bt(phone, ans, combination + neededToAdd, nextDigit.substring(1));
        }    
    }
}
