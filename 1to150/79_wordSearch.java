/*
思路就是dfs 上下左右四个方向 找每个词语的首字母。 但这里不同的一点给定的board可能会有很多个与单词最开始首字母一样的地方，
这些都会trigger dfs。 而在dfs里 我们都会标记flag，表示这个grid走过，就不该再走回来。 所以这就牵扯到了backtrack。 因为很可能
此刻的trigger的dfs并不能找到一个solution，那为了不影响后面的计算我们需要将走过的地方复原也就是backtrack。

这就牵扯了dfs的写法了，首先是两个出口验证：
1. 如果越界或者这个方向下的board ！= 首字母 或者这个地方走过， 说明这个路子不对。 那就return false
2. 重点： 如果nextword的后一个字串 word.substring(1)的长度为零，那说明我们递归成功了，直接返回TRUE
3. 和数岛一样，上下左右四个方向，但现在牵扯如何return true false给主函数。 所以我们需要将bfs作为一个if statement，
        - 如果bfs reture true， 在四个方向里的search中 再return true 作为出口。
            - 不然什么都不用做！ 直到四个方向走完，这样就会执行之后的backtrack的部分的代码
4. 当四个方向走完，都没能达到出口的true，我们就可以backtrack修改过的地方， 然后return false出去。
*/


class Solution {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public boolean exist(char[][] board, String word) {
        if (board == null){
            return false;
        }
        
        if (word == null){
            return true;
        }
        
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == word.charAt(0)){
                    if (dfs(board, i, j, word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int x, int y, String nextWord){
        if ( x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1 || 
            board[x][y] == '#' || board[x][y] != nextWord.charAt(0)){
            return false;
        }
        /*
        重点！！ 不能判断 nextWord.substring(1) == null 因为最后是一个长度为0的空，不是null！！！
        */
        if (nextWord.substring(1).length() == 0){
            return true;
        }

        char originalChar = board[x][y];
        board[x][y] = '#';
        
        for (int[] dir : dirs){
            if (dfs(board, x + dir[0], y + dir[1], nextWord.substring(1))){
                // System.out.print("ha"+ nextWord.substring(1));
                return true;
            }
            /*
            重点！！！！
            下面绝对不能else，不然就会直接返回答案了，我们要的是，如果一条路能走就一直往那条路走，
            不然就换个方向，最后所有方向尝试后，能有结果才return TRUE。 不然
            我们应该先去 backtrack之前修改过的地方，再去return这个false
            */
//             else{
//                 return false;}
        }
        //如果dfs没法找到个solution的话，就需要backtrack
        board[x][y] = originalChar;
        return false;
    }
}