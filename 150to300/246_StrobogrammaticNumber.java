/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true

想要一个数字是上下颠倒可以读取的话， 只有几个数字可以成立： 6 9； 88； 00； 11； 96.
且需要是前后对应 1691.

所以可以建立一个map， 然后twopointer来前后扫， start pointer调取map对应的value， 用这个value来对比endpointer
指向的值，看是否是pair的/

*/


public class Solution{
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        int start = 0;
        int end = num.length() - 1;
        
        while (start <= end) {
            char startChar = num.charAt(start);
            if (!map.containsKey(startChar)){
                return false;
            }
            
            char paired = map.get(startChar);
            char endChar = num.charAt(end);
            if (paired != endChar){
                return false;
            }
            start++;
            end--;

        }

        return true;
    }    
}
