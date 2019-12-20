/*
the key of being valid is that the left counts == right counts -》 the return condition now becomes if the len(string) == 2 * n

in the recurrsion:
1. see if the length of the current string is 2n, if then return
2. judges if the count of the left bracket is still greater than 0, if yes then it means at this layer we could add one more left bracket
3. if the above runs to 0, then we go to the next judge if right brack counts are stil greater 0, if we add the right bracket

ie. n = 2 
( -> (( -> left counts = 0 but right is still 2 -> (() - > (()) we reaches to the exist, append the string to the ans list -> return all the recurrsion to the layer after we have called the function first time, now ( and left count is 1, and right count is 1 -> since this function is returned we go to right, and call the function to add ) -> ()+( - > ()() -> return to the right part and now the whole function is run over -> return; 

*/
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        int leftCount = n;
        int rightCount = 0;
        
        bt(ans, leftCount, rightCount, "", n);
        return ans;
    }
    
    public void bt(List<String> ans, int leftCount, int rightCount, String str, int n){
        if (str.length() == 2 * n){
            ans.add(str);
            return;
        }      
        if (leftCount > 0){
            bt(ans, leftCount - 1, rightCount + 1, str+"(", n);
        }
        
        if (rightCount > 0){
            bt(ans, leftCount, rightCount - 1, str+")", n);
        }
    }
}