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
    private int ans = 0;
    public int totalNQueens(int n) {
        if (n <= 0){
            return ans;
        }
        Set<Integer> colSeen = new HashSet<>();
        Set<Integer> sumSeen = new HashSet<>();
        Set<Integer> deductionSeen = new HashSet<>();
        int rowIndex = 0;
        bt(n, colSeen, sumSeen, deductionSeen, rowIndex);
        return ans;
     }
    
    private void bt(int n, Set<Integer> colSeen, Set<Integer> sumSeen, Set<Integer> deductionSeen, int rowIndex){
        if (rowIndex == n){
            ans++;
            return;
        }
        //对每一个col开始进行遍历，且start == 0， 因为每一层都有相同的combinations
        for (int j = 0; j < n; j++){
            int currentSum = j + rowIndex;
            int currentDeduction = j - rowIndex;
            if (!colSeen.contains(j) && !sumSeen.contains(currentSum) && !deductionSeen.contains(currentDeduction)){
                //meaning this is a valid move
                colSeen.add(j);
                sumSeen.add(currentSum);
                deductionSeen.add(currentDeduction);
                bt(n, colSeen, sumSeen, deductionSeen, rowIndex + 1);
                colSeen.remove(j);
                sumSeen.remove(currentSum);
                deductionSeen.remove(currentDeduction);
            }
        }
    }
}















//以下是有些问题的实现，不该用stack
// class Solution {
//     public int ans = 0;
//     public int totalNQueens(int n) {
//         if (n <= 0){
//             return ans;
//         }
        
//         Set<Integer> colSeen = new HashSet<Integer>();
//         Stack<Integer> summation = new Stack<Integer>();
//         Stack<Integer> deduction = new Stack<Integer>();
//         int rowIndex = 0;
//         int colIndex = 0;
//         bt(n, colSeen, summation, deduction, rowIndex, colIndex);
//         return ans;
//     }
//     private void bt(int n, Set<Integer> colSeen, Stack<Integer> summation, Stack<Integer> deduction, int rowIndex, int colIndex){
//         if (rowIndex == n){
//             System.out.println("here");
//             ans += 1;
//             return;
//             // return true;
//         }
        
//         for (int j = 0; j < n; j++){
//             if (!colSeen.contains(j)){
//                 int currentSum = j + rowIndex;
//                 int currentDeduction = j - rowIndex;
//                 //如果我们现在往右边走，那就得判断是否此刻的差等同于上一刻的差
//                 if (!summation.isEmpty() && j > colIndex && currentDeduction == deduction.peek()) continue;
//                 //如果我们现在往左走，那就得判断是否此刻的和等同于上一刻的和
//                 if (!deduction.isEmpty() && j < colIndex && currentSum == summation.peek()) continue;
//                 //如果上面的判断都可以了，那说明这一刻走的路子是对的
//                 //将其这一列放入hash里
//                 colSeen.add(j);
//                 //同样需要记录这一刻的 col + row 和 col - row
//                 summation.add(currentSum);
//                 deduction.add(currentDeduction);
//                 bt(n, colSeen, summation, deduction, rowIndex + 1, j);
//                 colSeen.remove(j);
//                 summation.pop();
//                 deduction.pop();   
//             }
//         }
//     }
// }