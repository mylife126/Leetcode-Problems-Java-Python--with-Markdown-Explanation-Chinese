/*
Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
*/

class Solution {
    public void reverseWords(char[] s) {
        // Three step to reverse
        /*1, reverse the whole sentence
        [e,u,l,b, ,s,i, ,y,k,s, ,e,h,t] */
        reverse(s, 0, s.length - 1);


        /*2, reverse each word。现在我们需要把每一个wordreverse，所以找到空的地方，从空的前一位开始
        reverse即可*/
        int start = 0;
        int end = -1;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                //一旦找到空的地方，就说明可以reverse了，但是是从空的前一位reverse
                reverse(s, start, i - 1);
                //然后下一位word是从空之后开始的
                start = i + 1;
            }
        }
        /* 3, 还差最后一个单词，很明显上述循环没法到最后一个word，因为最后的word没有space
        所以需要单独处理， 但是start在循环的时候就已经更新过了*/
        reverse(s, start, s.length - 1);
    }

    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}