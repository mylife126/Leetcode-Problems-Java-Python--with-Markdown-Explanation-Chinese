/*
因为需要找到最后一个单词的长度，所以用stack来存贮这个string里所有的char，然后从后往前的pop，每次pop就检查一下之后的char是不是空格，如果是的话说明最后这个词结束了。

但是要避免cornercase：
1. 只有一个单词没有空格，这个就得要求while loop的判断是 !stack.isEmpty()
2. 在循环里判断几个case ：
    a. 只有一个单词且加了一个空格， 或者 压根就是空格 "a " or " " 判断的根据是idx ==0 && poped == ‘ ’ 此类情况直接continue掉
    这样由于ans init为0， 对于” “ 这个情况 如果continue了，loop直接结束 答案返回0
    b. 正常的string  ”hello word“， 那这个情况就是每次pop后检查一下peek的下一个string是不是空，如果是也可以直接返回
3. 在while结束后，返回ans， 这个对应的是情况1，当只有一个单词的时候，我们必须在while结束后才能得到所有的长度
*/
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()){
            stack.push(c);
            
        }
        int ans = 0;
        int idx = 0;
        while(!stack.isEmpty()){
            char poped = stack.pop();
            //Corner case "a " 最后一位是空格 或者 本身单词就是个空 “ ”
            if (idx == 0 && poped == ' '){
                continue;
            }
            idx++;            
            //不然就一直count++
            ans+=1;
            
            //一直pop直到遇到了空格说明 last word已经结束  "hello word"  'd' 'r' 'o' 'w' ' '
            //且这个判断一定是在ans+=1之后不然会少一位
            if (!stack.isEmpty() && stack.peek() == ' '){
                return ans;
            }
        }
        //corner case, 上述都没有满足 “abc” 一个单一的单词，没有空格，所以需要一直循环结束才可以
        return ans;
    }
}