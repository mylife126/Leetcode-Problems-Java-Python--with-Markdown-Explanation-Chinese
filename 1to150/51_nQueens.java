/*

每一行只能有一个queen， 
每一列也只能有一个queen， 
同时对角线上， 从右上往左下走 i+j == i + j，要保证对角线不会出现重复
对角线上，从左上网友下走， j - i == j - i, 要保证对角线不出现重复

 00 01 02 03
 10 11 12 13
 20 21 22 23
 30 31 32 33
 
 bt row来保证每一个row只有一个
 hashCol保证col只有一个
 然后i + j 也是hash
 j - i也是hash来保证。 
 
 出口则是 如果 rowId == n, 则表示走完了，因为 假设n == 4, 对于indx来说是3， 所以 0 ~ 3的时候已经走完， 3到4说明结束了，答案放入list。
 不然就会回溯，尝试不同的col摆法。
 
 1. 和suduku类似，每一行作为递归层，保证row不重复
 2. 在递归层里for loop column 0 ~ j：
            a. 检测 col是否重复，
            b 检测是否 i + j 重复
            c 检测是否 j - i 重复
            如果以上通过，则建立 temp stringbuilder， 在 j的位置放入Q， 其余放.
            回溯： hash清除， 并且temp里清除这一刻加入的string

*/
class Solution {   
    private List<List<String>> ans = new ArrayList<>();
    
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return ans;
        
        Set<Integer> colSeen = new HashSet<>();
        Set<Integer> sumSeen = new HashSet<>();
        Set<Integer> deductSeen = new HashSet<>();
        
        int rowId = 0;
        List<String> tempArray = new ArrayList<>();
        bt(colSeen, sumSeen, deductSeen, rowId, n, tempArray);
        return ans;
    }
    
    private void bt(Set<Integer> colSeen, Set<Integer> sumSeen, Set<Integer> deductSeen, int rowId, int n, List<String> tempArray){
        
        //出口，如果走到了最后一行继续往下的话说明成功了
        if (rowId == n){
            // System.out.println("here");
            ans.add(new ArrayList<>(tempArray));
            return;
        }
        //for loop尝试所有j的可能
        for (int j = 0; j < n; j++){
            int sum = rowId + j;
            int deduct = j - rowId;
            //检测合理性
            if (!colSeen.contains(j) && !sumSeen.contains(sum) && !deductSeen.contains(deduct)){
                colSeen.add(j);
                sumSeen.add(sum);
                deductSeen.add(deduct);
                
                StringBuilder sb = new StringBuilder();
                //对于不是该j的地方放入 '.' 对于是 j 的地方放入 'Q'
                for (int col = 0; col < n; col ++){
                    if (col == j){
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                    
                }
                // System.out.println(sb.toString());
                //将这一行新添加的string放入tempArray中
                tempArray.add(sb.toString());
                //去下一行放Queen
                bt(colSeen, sumSeen, deductSeen, rowId + 1, n, tempArray);
                
                //回溯
                colSeen.remove(j);
                sumSeen.remove(sum);
                deductSeen.remove(deduct);
                tempArray.remove(tempArray.size() - 1);
            }
        } 
    }
}