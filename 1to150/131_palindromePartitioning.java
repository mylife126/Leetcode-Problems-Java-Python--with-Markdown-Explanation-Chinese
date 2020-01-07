/*
目标是找到所有可能的分割: a a b
str[start : end]
start = 0, end = 1, 2, 3 => a aa aab
start = 1, end = 2, 3    => a ab
start = 2, end = 3       => b 

start = 0
-> a, i = 0  [a]
   -> start = 1, i = 1 = a [a, a, b]
      -> start = 2, b , 
      -> i = 3 == length => [a,a,b] append to ans return 
   -> start = 1, i = 2 = b, ab not valid function ends return
-> a, i = 1, [aa]
    -> i = 2, b, [aa, b]
    -> i ==3 == length , [aa,b] append to ans return
-> b, i = 2, aab not valid, end of function return
   
所以依旧是用 startIdx这个trick， 类似于 combinationSum，这也就可以以每一位数作为这一层的新的起始点。
*/


class Solution {
    public List<List<String>> ans = new ArrayList<>();
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0){
            return ans;
        }
        int start = 0;
        bt(ans, new ArrayList<String>(), start, s);
        return ans;
    }
    
    private void bt(List<List<String>> ans, List<String> temp, int start, String s){
        //如果现在的新的起始点都到了length了，所以表示已经深度搜索结束了，append ans即可
        if (start == s.length()){
            ans.add(new ArrayList<String>(temp));
            return;
        }
        
        for (int i = start; i < s.length(); i++){
            //取从该start为头 到 ith的substring
            String subString = s.substring(start, i + 1);
            /*
            必须改string是pali才能放入temp中
            */
            if (isPali(subString)){
                temp.add(subString);
                //去往 在ith之后一位来做partition
                bt(ans, temp, i + 1, s);
                //回溯
                temp.remove(temp.size() - 1);
            }
        }
    }
    
    private boolean isPali(String s){
        int start = 0;
        int end = s.length() - 1;
        while (start < end){
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (startChar != endChar){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}