/*
题目很容易想到greedy，永远走当前node上下左右的后一个节点的最大值，来确保我们这条路里走过的所有的
node中的最小值是最大的。
但看下面这个case
(3) (3)  1  1 
2   (3) (3) 1
2   1   1   1
2   2    2  2  
会发现，如果这样操作的话，我们会一直走 3 - 3- 3 - 3， 到达了最后那个3后只能走 1了，那这会使得这条路径
上的最小值是1， 很明显这不是所有可能path里的最大的。 

但是我们要是能够在走到最后这个3的时候意识到我们其实可以返回到第一个3这个节点的时候，直接走第二大的值，也就是2这个点
那么我们就能一直走到末尾 3 - 2 - 2 - 2- 2 - 2 - 2 然后这条路径里的最小值就是所有路径里最小的了。

那么如何做到这一点呢， 我们可以用priorityQueue来记录每一个node 上下左右的值，并且按照降序排列， 这样遇到上述例子
我们走到3的最后，可以“track back” to 第一个 3 的下面的2， 然后按着2 一直走下去。

那么按照这个逻辑，我们随着pq的运行，我们每次poll出来的都是可能“回溯”到某一时刻后的
最大node，所以 一路维护一个runningmin，这个runningMin一定就是所有可能路径里最小的。
*/


class Solution {
    private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    private class Cell{
        int x; int y; int val;
        public Cell(int i, int j, int value){
            x = i;
            y = j;
            val = value;
        }
    }
    public int maximumMinimumPath(int[][] A) {
        PriorityQueue<Cell> pq = new PriorityQueue<>(new Comparator<Cell>(){
            @Override
            public int compare(Cell a, Cell b){
                return b.val - a.val;
            }
        });
        
        int min = A[0][0];
        //需要记录走过的地方，以避免在后期上下左右找path的时候重复查找
        int[][] visited = new int[A.length][A[0].length];
        //init priorityQueue的时候，放入第一个node
        visited[0][0] = 1;
        pq.offer(new Cell(0,0,A[0][0]));
        //开始bfs
        while(!pq.isEmpty()){
            //每次拿出最佳的， 也就是value最大的grid出来，这样可以确保这一条path是可能“回溯”过后的最大值
            Cell best = pq.poll();
            int x = best.x;
            int y = best.y;
            int val = best.val;
            //维护这个runningMin
            min = Math.min(val, min);
            if (x == A.length - 1 && y == A[0].length - 1){
                return min;
            }
            //对这个节点后继的上下左右四个方向的节点进行查找
            for (int[] dir : dirs){
                int newX = x + dir[0];
                int newY = y + dir[1];
                //确保不越界的同时后继节点也没有被访问过
                if (newX >=0 && newX < A.length && newY >= 0 && newY < A[0].length && 
                    visited[newX][newY] != 1){
                    visited[newX][newY] = 1;
                    Cell next = new Cell(newX, newY, A[newX][newY]);
                    pq.offer(next);
                }
            }
        }
        return -1;
    }
}