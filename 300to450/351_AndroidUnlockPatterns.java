/*
题目意思是，给定一个3 by 3的数字键盘，任务是找到有几种解密的方法，对于揭秘方法的数字的counts的要求是从m 到 n位。

所以思想是对于每一个长度的key做一次 bt

bt的内核：
1. 对于1 ~ 9个数字检测：
    a 这个新添加的数字不能出现过
    b 新添加的数字满足valid的以下情况：
       1. valid： lastMove 和这一次move是column wise or rowwise，那么他们的和一定为odd
       2. valid： 对角线情况，我们要检查 last + nextmove / 2 的mid， 如果mid走过，则合法
       3. 走了相邻一位的对角线
       4. 最后检测 同一个 row， 容易个col里跳跃的情况 0 - 2 or 6 - > 0
2. 如果该数字是valid， 则seen里将这个数字作为true
  
   然后 call自己again， 但是此时要让leftLength - 1， 表示我们还剩几位密码
   如果  leftLength == 0 则表示结束了， return 1
*/

public class Solution {
    private boolean used[] = new boolean[9];

    public int numberOfPatterns(int m, int n) {	        
        int res = 0;
        for (int len = m; len <= n; len++) {	            
            res += calcPatterns(-1, len);         
        }
        return res;
    }

    private boolean isValid(int index, int last) {
        if (used[index])
            return false;
        // first digit of the pattern    
        if (last == -1)
            return true;
        // knight moves or adjacent cells (in a row or in a column)	       
        if ((index + last) % 2 == 1)
            return true;
        
        // indexes are at both end of the diagonals for example 0,0, and 8,8          
        int mid = (index + last)/2;
        if (mid == 4)
            return used[mid];
        
        // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
        if ((index%3 != last%3) && (index/3 != last/3)) {
            return true;
        }
        // all other cells which are not adjacent    0 - > 2
        System.out.println(index + "   " + last);
        return used[mid];
    }

    private int calcPatterns(int last, int len) {
        if (len == 0)
            return 1;    
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(i, last)) {
                used[i] = true;
                sum += calcPatterns(i, len - 1);
                used[i] = false;                    
            }
        }
        return sum;
    }
}

