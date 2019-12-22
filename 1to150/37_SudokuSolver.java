/*
Solution1: 最快方案，不用hash来检测该数字是否为valid，而是直接对row 和 col以及 subGrid进行linear search，扫到了这个数字则为不合法，
不然就是合法.

思路是，
    我们的递归是先每一列每一列放，然后每一行的每一列放好后再递归到下一行。
    所以，其中我们需要检测的是：
                                如果colId = maxCol， rowId++， 这表示这一列填写完毕
                                如果，此刻的rowId == rowLength, 说明数独写完了，return true， 出口；
                                递归部分：我们数独只能放1-9， 那么在for 循环里，如果当前grid不为空 （'.'），则重新递归一次，col+1
                                同时检测该数字是否合法，需要注意 integer转char的做法是 (char) int + '0'
                                如果合法的话，我们填入这个数字去board，然后递归到下一列，且如果这个递归函数return true，那这个就是
                                解答，所以直接return true，结束了。 
                                不然，回溯到上一层，换一个数字试一试。
*/
class Solution {
    public void solveSudoku(char[][] board) {
        int rowIdx = 0;
        int colIdx = 0;
        solveHelper(board, rowIdx, colIdx);
    }
    
    private boolean solveHelper(char[][] board, int rowIdx, int colIdx){
        //我们要先col遍历 再 深度往下走，所以先判断一下是否 此刻的 colIdx == col.length
        if (colIdx == board[0].length){
            rowIdx++;
            colIdx = 0; //这样下一次递归的时候又从第一列开始
        }
        //出口，当我们的rowIdx到底了说明没有问题了，那就return true
        if (rowIdx == board.length){
            return true;
        }
        
        //对于每一个位置上有9种尝试
        for (int value = 1; value <= 9; value++){
            //验证这个地方可以不可以填写
            if (board[rowIdx][colIdx] != '.'){
                //那我们得去下一列
                /*
                注意
                这里要return solver（）因为我们最后判断这个数独填写完毕需要if(solver())也就是说这一层的call有可能在此刻的
                判断不为空这里return回一个true。 例如 我们走到了最后一行的最后一个空，这个空已经有数字被填写进去了，那么
                for循环里那个 if(solver)的函数call到后会跑到这里的，所以这里必须return一个答案回去。
                */
                return solveHelper(board, rowIdx, colIdx + 1);
            }
            //convert the int to char
            char valueChar = (char) (value + '0');
            
            //不然我们得验证这个数字的合法性
            if (isValid(valueChar, board, rowIdx, colIdx)){
                board[rowIdx][colIdx] = valueChar;
                //然后我们开始递归，去下一列 尝试同样的操作
                //如果能成功，那就直接返回true， 不然我们回溯到上衣列尝试别的数字然后继续递归
                if (solveHelper(board, rowIdx, colIdx + 1)){
                    return true;
                }
                board[rowIdx][colIdx] = '.';
            }
        }
        return false;
    }
    
    private boolean isValid(char input, char[][] board, int rowIdx, int colIdx){
        //先检测这一行用没有重复的
        for (int j = 0; j < board[rowIdx].length; j++){
            if (board[rowIdx][j] == input) {
                return false;
            }
        }
        
        //再看这一列有没有重复的
        for (char[] row : board){
            if (row[colIdx] == input){
                return false;
            }
        }
        //重点！！
        //再看这组 （rowIdx, colIdx）所在的九宫格里有没有重复的，我们要找到这个index所属的九宫格的top left的 x y
        int subSize = (int) Math.sqrt(board.length);
        
        int howManySizeH = rowIdx / subSize;
        int howManySizeV = colIdx / subSize;
        
        int topLeftX = howManySizeH * subSize;
        int topLeftY = howManySizeV * subSize;
        
        for (int i =0; i < subSize; i++){
            for (int j =0; j < subSize; j++){
                if (board[i + topLeftX][j + topLeftY] == input) {
                    return false;
                }
            }
        }
        //如果以上检测都通过了 那就是 true
        return true;
    }
}



class Solution {    
    private Map<Integer, Set<Character>> rowMap;
    private Map<Integer, Set<Character>> colMap;
    private Map<String, Set<Character>> gridMap;
    
    public void solveSudoku(char[][] board) {
        rowMap = new HashMap<>();
        colMap = new HashMap<>();
        for (int i = 0; i < 9; i++){
            rowMap.put(i, new HashSet<Character>());
            colMap.put(i, new HashSet<Character>());
        }
        
        gridMap = new HashMap<>();
        gridMap.put("00", new HashSet<Character>());
        gridMap.put("03", new HashSet<Character>());
        gridMap.put("06", new HashSet<Character>());
        gridMap.put("30", new HashSet<Character>());
        gridMap.put("33", new HashSet<Character>());
        gridMap.put("36", new HashSet<Character>());
        gridMap.put("60", new HashSet<Character>());
        gridMap.put("63", new HashSet<Character>());
        gridMap.put("66", new HashSet<Character>());
        
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                char thisDigit = board[i][j];
                if (thisDigit != '.'){
                    int topX = i / 3 * 3;
                    int topY = j / 3 * 3;
                    String whichGrid = Integer.toString(topX) + Integer.toString(topY);
                    
                    rowMap.get(i).add(thisDigit);
                    colMap.get(j).add(thisDigit);
                    gridMap.get(whichGrid).add(thisDigit);
                }
            }
        }
        
        int colId = 0;
        int rowId = 0;
        solver(colId, rowId, board);
    }
    /*
    我们的递归是先每一列每一列放，然后每一行的每一列放好后再递归到下一行。
    其中我们需要检测的是，如果colId = maxCol， rowId++
    如果，此刻的rowId == rowLength, 说明数独写完了，return true， 出口；
    我们数独只能放1-9， 那么在for 循环里，如果当前grid不为空 （'.'），则重新递归一次，col+1
    */
    private boolean solver(int colId, int rowId, char[][] board){
        //检测是否col摆放完毕
        if (colId == board[0].length){
            rowId += 1;
            //且colId清零
            colId = 0;
        }
        //如果已经摆放完毕了，说明找到一种数独解法了，那就出口
        if (rowId == board.length){
            return true;
        }
        
        //不然我们需要填写数字,且所有可能性在1 - 9
        for (int i = 1; i <= 9; i++){
            //检测这个格挡能不能放数字
            if (board[rowId][colId] != '.'){
                //如果这个格挡其实已经有数字了，那么就该去下一列 
                return solver(colId + 1, rowId, board);
            }
            
            //不然这个地方可以放数字 且将其转换成char形式
            char value = (char) (i + '0');
            int topX = rowId / 3 * 3;
            int topY = colId / 3 * 3;
            String whichGrid = Integer.toString(topX) + Integer.toString(topY);
            if (isValid(value, rowId, colId, whichGrid)){
                board[rowId][colId] = value;
                rowMap.get(rowId).add(value);
                colMap.get(colId).add(value);
                gridMap.get(whichGrid).add(value);
                
                //如果当前这个数字满足了所有递归，直接return出去
                if (solver(colId + 1, rowId, board)){
                   return true;
                }
                //不然bt, 然后for loop到另一个数字
                board[rowId][colId] = '.';
                rowMap.get(rowId).remove(value);
                colMap.get(colId).remove(value);
                gridMap.get(whichGrid).remove(value);
            } 
        }
        return false;
    }
    
    private boolean isValid(char value, int rowId, int colId, String whichGrid){        
        if (!rowMap.get(rowId).contains(value) && !colMap.get(colId).contains(value) && !gridMap.get(whichGrid).contains(value)){
            return true;
        }
        return false;  
    }
}