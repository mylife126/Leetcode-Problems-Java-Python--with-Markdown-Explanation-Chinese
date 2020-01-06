/*
每两个连续的加号 变成 减号， 找出所有可能的变换
Input: s = "++++"
Output: 
[
  "--++",
  "+--+",
  "++--"
]

思路是 one pass，且遍历到倒数第二个char，因为要连续的两个，所以不需要遍历到最后一个char。
然后判断此刻的char 和下一刻的char是不是"+"， 如果是则用stringbuilder 建立一个新的string，遇到i and i+1的位置
都append “-”， 其余都是“+”
*/

class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0){
            return ans;
        }
        
        char[] cArray = s.toCharArray();
        for (int i = 0; i < cArray.length - 1; i++){
            if (cArray[i] == '+' && cArray[i + 1] == '+'){
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < cArray.length; j ++){
                    if(j == i || j == i + 1){
                        sb.append('-');
                    } else {
                       sb.append(cArray[j]); 
                    }
                        
                }
                ans.add(sb.toString());
            }
        }
        return ans;
    }
}