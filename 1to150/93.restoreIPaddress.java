/*思路是 对于一个 ip 地址 他有四个段落组成， 长度在 4 ~ 12，
1. 它可以由 单个数字组成 1.1.1.1
2. 它可以由 两个数字形成 11.11.11.11
3. 它可以由 三个数字形成 但是 三位数不能超过255  且 二位数三位数的时候 开头不能是 0 

所以可以有三个dfs操作（由于是string的问题，bt是不需要回溯操作的）。
1. 首先出口是，当count of parts == 4 且 此时string的长度也为0，则表示找到了一组可以形成的ip address string， 放入ans array中
2. 失败出口则是，当count of parts == 4 但是 此时还有剩余的string没有尝试过，或者 string结束了但是count ！= 0 则直接return
3. 递归部分
   a. 每次递归call，s += string.substring(0,1),并且传入剩余的string = string.substring(1)
   b. 当string的长度大于等于2的时候，可以尝试两个两个放， 但必须满足 首位不等于0。 s+=string.substring(0, 2), 传入剩余的string = string.substring(2)
   c. 当string的长度大于等于3的时候，可以尝试三个三个放，但必须满足首位不等于0， 且三位数小于等于255， s+=string.substring(0,3),传入剩余的string = string.substring(3);
*/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0){
            return ans;
        }
        String madeString = "";
        bt(ans, madeString, s, 0);
        return ans;
    }
    
    private void bt(List<String> ans, String string, String s, int count){
        //定义成功的出口，直到所有string的字母都用完了，再return
        if (count == 4 && s.length() == 0){
            ans.add(string.substring(1));
            return;
        }
        //failure exist
        if (count == 4 || s.length() == 0){
            return;
        }
        
        bt(ans, string + "." + s.substring(0,1), s.substring(1), count + 1);
        //当双位数和三位数来进行组合的时候，需要保证一个先决，那就是起始值不能为0
        if(s.charAt(0) != '0'){
            if (s.length() >= 2){
                bt(ans, string + "." + s.substring(0,2), s.substring(2), count + 1);
            }
            if (s.length() >=3 && Integer.parseInt(s.substring(0,3)) <= 255){
                bt(ans,string + "." + s.substring(0,3), s.substring(3), count + 1);
            }
        }
    }
}