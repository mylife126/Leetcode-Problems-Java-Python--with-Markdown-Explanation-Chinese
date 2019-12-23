/*
扫一次字符串，如果遇到左括号就放入stack， 如果是右括号， 有两个情况：1. stack空，则说明没法成为pair，忽略 2. stack 不为空 
则stack pop出一次左括号，并且count +=1

但这个问题在于只是找到了有几个合法的括号，而不是最长的字串里包含合法括号。 因为这个算法没法判断连续性这个问题 

*/
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        Set<Character> set = new HashSet<>();
        set.add('(');
        
        int count = 0;
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++){
            if (stack.isEmpty() && !set.contains(s.charAt(i))){
                continue;
            }
            if (set.contains(s.charAt(i))){
                stack.push(s.charAt(i));
            }
            
            if (!stack.isEmpty() && !set.contains(s.charAt(i))){
                stack.pop();
                count+=1;
            }
        }

        
        return count * 2;
    }
}

// // )()(()
// // (()


/*
(()()) ()) ()
可见上面最长的长度为六的字串。
需要init一个leftMost的变量，这个变量为-1， 因为我们要的是长度，且这个长度一定是2的倍数，（（）（））当5 - (-1)时 为6，
相当于补了一位。
那么我们需要用一个stack来记录每一刻 '('的index，每当遇到了')'表示找到了一个pair， 
那么就pop最近的那个'('，然后我们目的是要找最长字串，所以不是用当下的indx 减去 pop出来的那个indx，而是减去stack里剩下的最上面那个index。

但是当我们扫到了 ')' 且stack为空，说明中断了， leftmost需要被更新至此时的位置

idx    leftmost    stack    pop     max
0        -1         0
1        -1         0 1 
2        -1         0        1       2 - 0 = 2
3        -1         0 3  
4        -1         0        3       4 - 0 = 4
5        -1         E        0       5 - (-1) = 6
6        -1         6          
7        -1         E         6       7 - （-1） = 8
8         8         E
9         8         9        
10        8         E       9        10 - 8 = 2 

也就是说用indx来记录左括号出现的位置，同时引入了此段最头上的位置为-1，相当于补了一位。遇到了右括号，由于目标是找最长字串，我们需要看pop的之前那一位的idx， ((), 2 - 1 = 1 很明显不是我们要的答案， 但 2 - 0 = 2则是，所以也是补一位的思想。 那么（（））这个情况，在最后那一位的时候 stack里已经空了，这个时候leftMost就能派上用场了， 3 - -1 = 4.

这个算法解决连续性的关键是indx的记录，每次遇到了 )的时候都会pop一次，这就确保了当一段不连续了，进入下一段的时候，stack最底部是另一端的"最开头"， 然后计算距离的方法是 rightIndx - stack.peek()的方法，而不是简单的pop了就count++
*/

public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int leftMost = -1; //补一位
        int max = 0;
        for (int i = 0; i < s.length(); i++){
            char current = s.charAt(i);
            if (current == '('){
                //record every ('s location
                stack.push(i);
            }
            if (!stack.isEmpty() && current == ')'){//case 1, we have found one pair
                //pop this )'s ( location as we actually need 这位)对应的(的前一位(的位置，这样又是相当于补了一位
                stack.pop();
                //case 1: stack 此时不为空
                if (!stack.isEmpty()){
                    max = Math.max(i - stack.peek(), max);
                } else {
                    //case 2, stack is empty, (), like this case, we need to use the left most 
                    max = Math.max(i - leftMost, max);
                }
            } else if (stack.isEmpty() && current == ')'){//case2, 连续中断了
                //update the left most
                leftMost = i;
            }
            
        }
        return max;
    }
}