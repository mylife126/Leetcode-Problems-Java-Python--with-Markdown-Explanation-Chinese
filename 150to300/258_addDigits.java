/*
Approach 1, recurrsion:
1. 每次拿到一个新的数字，转换成string 判断长度 如果==1 直接返回 不然执行下面的指令
2. 将数字拆分 取余10 拿到最后一个数字 加给新的数字
3. 上述while做完后recur on这个新的数字

*/
class Solution {
    public int addDigits(int num) {
        String numStr = String.valueOf(num);
        if (numStr.length() == 1){
            return num;
        }
        int newDigit = 0;
        while(num > 0){
            int last = num % 10;
            newDigit += last;
            num = num / 10;
        }
        
        return addDigits(newDigit);
    }
}

/*
数学方法：
一个数字的相加后的单一情况就是 对9取模即可
*/
public class Solution {
    public int addDigits(int num) {
        if (num == 0){
            return 0;
        }
        if (num % 9 == 0){
            return 9;
        }
        else {
            return num % 9;
        }
    }
}