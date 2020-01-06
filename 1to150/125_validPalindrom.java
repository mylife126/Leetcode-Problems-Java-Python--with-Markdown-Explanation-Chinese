/*
Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true

two pointer扫一次首尾，遇到非字母的跳过，Character.isDigitOrLetter()
遇到字母的首先lower case他们 Character.toLowerCase()
然后比对首位字母是否相同，如果不同则return false
不然在最后return true
*/

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0){
            return true;
        }
        
        int start = 0;
        int end = s.length() - 1;
        
        while(start < end){ 
            //首先判断是否是letter如果不是则一直改正           
            while(start < end && !Character.isLetterOrDigit(s.charAt(start))){
                start++;
            }
            //同理对end character做同样的处理
            while(start < end && !Character.isLetterOrDigit(s.charAt(end))){
                end --;
            }
            //上述修改结束后，记录此刻的startCharacter 和 endCharacter
            char cStart = s.charAt(start);
            char cEnd = s.charAt(end);
            
            if (Character.toLowerCase(cStart) != Character.toLowerCase(cEnd)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}