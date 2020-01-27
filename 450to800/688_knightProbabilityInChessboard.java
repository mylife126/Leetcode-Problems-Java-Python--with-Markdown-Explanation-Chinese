/*
Brutal Force
找到最后一步后能成立的所有的可能性 X， p = X / 8^k
每一步都有八种走法，那么每一种走法都可以检测是否valid， 如果valid，则说明再下一步是可以基于这一步继续走下去的。

所以思想是BFS：
1.每一步内走八种可能性，将这一步内合法Cell的放入Q中，当作下一步的starting。 k--
2.一直循环到最后一步的时候，将bfs内剩余的starting point拿出，并且以他走八个方向，记录valid的个数。

3. 最后我们得到了走到最后一步的时候所有的valid的走法， 除以8的k次方即可

例子： N = 3， start At 0 0， k = 2
-------
| | | |
-------
| | |X|   Step1   
-------
| |X| |
-------


-------
|xx| |X|
-------
|  | | |   Step2     总共四个合法
-------
|X | | |
-------


*/

class Solution {
    /*
    便于存放每一个cell的坐标
    */
    class Cell{
        int x;
        int y;
        public Cell(int i, int j){
            x = i;
            y = j;
        }
    }
    //八个方向
    int[][] dirs = {{-2,-1},{-2,1},{-1,-2},{1,-2},{-1,2},{1,2},{2,-1},{2,1}};
    
    public double knightProbability(int N, int K, int r, int c) {
        if (N == 0 ||r > N || c > N) return 0;
        //corner case： 如果一步都不用走，那valid的概率就是1
        if (K == 0) return 1;
        
        Queue<Cell> q = new LinkedList<>();
        //init Q，将起始位置放入q中
        q.offer(new Cell(r,c));
        //记录走到每一步下对应的合法走法
        //但是其实我们只需要最后一步内所有的合理走法，因为最后一步内的走法是基于之前的合法statring，所以这
        //一步内的所有合法的走法是总合法的走法
        int[] eachStepCount = new int[K];
        int whichStep = 0;
        double Kcopy = (double) K;
        //BFS
        while(!q.isEmpty() && K > 0){
            int size = q.size();
            int countValid = 0;
            //将这一步内所有的合理的node拿出
            for (int i = 0; i < size; i++){
                Cell currLocation = q.poll();
                int x = currLocation.x;
                int y = currLocation.y;
                //尝试八个方向
                for (int[] dir : dirs){
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    //如果合法，则放入q中，作为下一步的可能的起始点
                    if (isValid(newX, newY, N)){
                        q.offer(new Cell(newX, newY));
                        
                        //记录这一步的合理的走法
                        countValid +=1;
                    }
                }
            }
            eachStepCount[whichStep] = countValid;
            whichStep +=1;
            K-=1;
        }
        
        double ans = 1;
        //最后一步内的所有合理走法是全部的能走的走法
        return (double) eachStepCount[eachStepCount.length - 1] / Math.pow(8.0, Kcopy);
    }
    private boolean isValid(int x, int y, int N){
        if (x < 0 || x > N - 1 || y < 0 || y > N - 1) {
            return false;
        } else {
            return true;
        }
    }
}

/*
solution2： DP
N = 3, rs= 0, cs = 0, k = 2
State function represents the probability of each grid being occupied with the knit

init：the start point's probability is 1, because the knit would be definitly put there
1 0 0 
0 0 0
0 0 0

Transfer function：每一步到下一步valid的都是liability的算法，所以是连乘，那么假设我们走到了一步合法的位置，这一步合法的位置的概率不就是 上一步的概率 乘以 1/8么
step1:
0   0   0
0   0  dp[start] * 1/ 8 = 1/ 8
0  1/8   0

step2:
在第一步的时候有两个起始点， 我们先走右边那个
dp[lastEnd]* 1/8 = 1/8 * 1/8        0        0
0                                   0        0
dp[lastEnd] * 1/8 =1/8 *1/8         0        0

再走下面那个
1/8 * 1/8 + dp[lastEnd] * 1/8       0   dp[lastEnd] * 1/8 = 1/8 * 1/8
0                                   0        0
1/8 * 1/8                           0        0

最后的ans 就是所有的sum即可
*/

class Solution {
    private int[][] dirs = {{-2,-1},{-2,1},{-1,-2},{1,-2},{-1,2},{1,2},{2,-1},{2,1}};
    public double knightProbability(int N, int K, int sr, int sc) {
        if (K == 0) return 1;
        if (N == 0 || sr > N || sc > N ) return 0;
        //state function: 每一个格子代表了chess落在这个地方的概率
        double[][] dp = new double[N][N];
        //第0步的时候， starting point的概率就是1
        dp[sr][sc] = 1;
        //开始更新每一步
        for (; K > 0; K--){
            //对于新的一步，我们有一个新的stateFunction来记录这一步下的每一个格子的概率
            double[][] nextDP = new double[N][N];
            /*
            更新每一个state grid，因为我们不知道到底哪一个格子是start point 我们遍历每一个
            如果当前的grid的概率是0，则说明他不可能成为start point， 所以直接跳过
            
            直到遇到了可以作为start point的地方 我们再对他做Transfer function：
            1. 八个方向走一遍，验证合法性
            2. 如果合法， dpNext[newx][newy] += dp[r][c] * 1 / 8 
            */
            for (int r = 0; r < N; r++){
                for(int c = 0; c< N; c++){
                    //只有当我们找到了start point 才开始 transfer function
                    if (dp[r][c] != 0){
                        for (int[] dir : dirs){
                            int newX = r + dir[0];
                            int newY = c + dir[1];
                            if (newX >= 0 && newX < N && newY >=0 && newY < N ){
                                //liability 联乘， 但是我们很可能这个grid已经有值了，
                                //根据概率论 我们得做加法
                                nextDP[newX][newY] += dp[r][c] * 1/8;
                            }
                        }
                    }
                } 
            }
            //将oldDp 赋值 nextDp
            dp = nextDP;
        }
        //最后加上所有的概率即可
        double ans = 0.0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                ans+= dp[i][j];
            }
        }
        return ans;
    }
}
