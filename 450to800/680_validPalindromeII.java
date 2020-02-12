class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        int left = 0;
        int right = s.length() - 1;
        
        while(left <= right){
            char leftC = s.charAt(left);
            char rightC = s.charAt(right);
            if (leftC == rightC) {
                left ++;
                right --;
            } else {
                boolean checkLeft = check(s, left + 1, right);
                boolean checkRight = check(s, left, right - 1);
                return checkLeft || checkRight;
            }
        }
        return true;
    }
    
    private boolean check(String s, int left, int right){
        while(left <= right){
            if (s.charAt(left) == s.charAt(right)){
                left +=1;
                right -=1;
            } else {
                return false;
            }
        }
        return true;
    }
}