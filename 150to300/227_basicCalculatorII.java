/*
思想是 既然存在 乘法和除法，则我们肯定需要知道这个符号前后的数字，所以stack。

第一步是将每一个数字全部找到 ： num = num * 10 + int(currentChar)
第二步是：每当遇到符号了，则需要将num 做处理后再放入stack中， 例如 3 + 2 * 2,当我们循环到*了，sign = +， 则只需要将 2 放入 stack中，sign更新成 *
                        如果循环到了第二2，则需要 pop stack上一刻放入的2， 进行相乘后再放入stack， 
        也就是说我们每次遇到一个符号的时候是先对之前的数字用再上一课的符号来处理，再更新符号
        所以需要对于第一个数字init一个 + 
*/

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        //init the sign with a plus, because its positive in front of the first digit
        //+3+2*2
        char sign = '+';
        /*
        很可能是 32+333 * 22, 所以我们需要将所有数字放一起
        */
        int fullDigit = 0;
        for (int i =0; i < s.length(); i++){
            char c =s.charAt(i);
            if (Character.isDigit(c)){
                int currentDigit = (int) (c - '0');
                // System.out.println(currentDigit);
                fullDigit = fullDigit * 10 + currentDigit;
                // System.out.println(fullDigit);
            }
            
            //然后我们需要对这一位数字做操作，32 + 333，当我们遇到了 333， 则需要 push +333 into stack
            //同时对于最后一位数字，他不满足于 not digit的这个条件，所以需要加另一个边界条件使得它被加stack
            //但对于第一位数我们没有sign， 所以需要init一个sign
            if( c != ' ' && !Character.isDigit(c) || i == s.length() - 1){
                if (sign == '+'){
                    fullDigit *= 1;
                    stack.push(fullDigit);
                } else if (sign == '-'){
                    fullDigit *= -1;
                    stack.push(fullDigit);
                } else if (sign == '*'){
                    int lastDigit = stack.pop();
                    stack.push(lastDigit * fullDigit);
                } else if (sign == '/'){
                    int lastDigit = stack.pop();
                    stack.push(lastDigit / fullDigit);
                }
                //最后将此刻的sign更新，以便于下一次使用
                sign = c;
                //并且将digit 复原
                fullDigit = 0;
            }
        }
        
        //当上述的one pass结束了，则说明现在stack里的数字都是处理好的了， 直接全部加起来就可以了
        int ans = 0;
        while (!stack.isEmpty()){
            ans += stack.pop();
        }
        return ans;
    }
}