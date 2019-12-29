/*
类似于大西洋流那道题，我们从边界出发。

很明显现在题目有两个情况下的O， 一个是不在边边上的，且被X包围的。 另一个是边边上的以及与其相连的
X X X X
X O X X
X X O O
X O O X
X X X X
很明显上面的case，第三排出现了一个边边上的O，那么与其相邻的O都会因为这个边边上的O流出边界。那么如果只是单纯的DFS遇到O就上下左右来找，会很难写边界条件，递归找的是X， 但遇到了相邻的X该怎么处理。

所以我们可以对于边边上的O进行dfs，我们确定的是，边边上的O是肯定不能被包围的，所以我们只需要标记处边边上的O以及与其相邻的所有O， 那么剩下的一定就是被包围的O了。

所以 dfs 遍历row边边上的所有O，标记成 #， dfs遍历col上的边边上的O 标记成 #
最后one pass，遇到O就改写成X， 遇到# 在改写回来O即可。
*/


class Solution {
    private int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        
        //对于第一row和最后row进行dfs查找边边上的O
        for (int j = 0; j < board[0].length; j++){
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            } 
            
            if (board[board.length - 1][j] == 'O'){
                dfs(board, board.length - 1, j);
            }
        }
        
        //对于第一列和最后列进行dfs查找边边上的O
        for (int i = 0; i < board.length; i++){
            if (board[i][0] == 'O'){
                dfs(board,i, 0);
            }
            
            if (board[i][board[0].length - 1] == 'O') {
                dfs(board, i, board[0].length - 1);
            }
        }
        //最后one pass 找到其余的O,也就是不在边上且被包围的，因为被包围的一定不会和边边相连
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                
                if (board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int x, int y){
        if (x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1 || board[x][y] == '#'){
            return;
        }
        //我们要找的是O， 如果这一方向不对了，那就返回去
        if (board[x][y] != 'O'){
            return;
        }
        //不然就是找到了，然后标记这个地方为 # 
        board[x][y] = '#';
        
        for (int[] dir : dirs){
            dfs(board, x + dir[0], y + dir[1]);
        }
    }
}