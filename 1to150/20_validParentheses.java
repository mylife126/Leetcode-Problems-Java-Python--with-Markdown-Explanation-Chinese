/*
given a string ((())), set up a hash to save the left bracket & right bracket pair

iterate through the string -> ( into stack -> ( into stack --> ( into stack -> ) is right bracket, pop the last left bracket in the stack, and check if the paired right bracket in the hash is the same of the current right bracket, if not return false -> finish the loop and check if the stack is empty or not -> else return true;

Corner Case:
1. the first character is right bracket, this happens when stack is empty and hash does not have this key
2. the loop is over, but there is still thing in stack ((())
*/

class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0){
            return true;
        }
        
        Map<Character, Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[', ']');
        map.put('{','}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            //corner case, the first character is right bracket, return false
            char current = s.charAt(i);
            if (stack.isEmpty() && !map.containsKey(current)){
                return false;
            }
            if (map.containsKey(current)){
                stack.push(current);
            } else {
                char lastLeft = stack.pop();
                char itsPaired = map.get(lastLeft);       
                if (itsPaired != current){
                    return false;
                }
            }
        }       
        if (!stack.isEmpty()){
            return false;
        }
        return true;
    }
}