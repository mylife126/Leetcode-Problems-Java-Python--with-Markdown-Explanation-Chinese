/*
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

1.那么第一步就是 将trailing space移除： s.strip()
2.然后我们用sb来记录每一个非空格的char， 然后一旦遇到了空格就表示word找完了， 那么
    a 先放入stack
    b stack里需要放入一个空格来分割word
    c 跳过其余的所有空格
3. 但是for loop结束后，最后一个word是没有添加的，需要添加最后的word放入stack里
4. pop所有的word去新的string里， 并在最后再做一次 strip处理即可
*/
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        //去除两头的空
        s.strip();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length();i++){
            //只要当前i不是空格，则就是word的一部分
            if (s.charAt(i) != ' '){
                sb.append(s.charAt(i));
            }
            //一旦遇到了空格 表示word都找到了 放入stack里
            if (s.charAt(i) == ' '){
                stack.push(sb.toString());
                //恢复runningString 到空
                sb = new StringBuilder();
                //并且加入一个空格来分割下一个词语
                stack.push(" ");
                //跳过所有其余空
                while ((i + 1) < s.length() && s.charAt(i + 1) == ' '){
                    i++;
                }
            }
        }
        //将最后的word也放入stack里
        stack.push(sb.toString());
        StringBuilder newString = new StringBuilder();
        while (!stack.isEmpty()){
            newString.append(stack.pop());
        }
        /*
        Input
        "  hello world!  "
        Output
        " world! hello "
        Expected
        "world! hello"
        
        这是因为上述while里是看i + 1是否是空格，这个例子里！后只有一个空格，所以 i + 1不是空格，那么就不会被跳过。 所以进入下一次for loop的时候会多加这个空格
        */
        return newString.toString().strip();
    }
}