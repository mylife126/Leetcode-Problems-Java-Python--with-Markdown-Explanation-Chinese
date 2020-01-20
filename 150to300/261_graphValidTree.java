/*
概念：
图和tree的区别是，
1。 tree是无环的，图是可以有环的, 
2. 其次对于edges = [] node ==1来说也是valid tree
3. 最后tree的parent 只能有一个，也就说所有的node的parent最后只能有一个

例子：
given n = 5 表示有5个nodes， edges = [[0,1],[0,2],[0,3],[1,4]]
                   2-0-1-4
                     |
                     3


given n = 5, 表示有5个nodes, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
                        4
                        |
                      0-1-2-3
                        \   /
                         \ /
这个情况就不满足要求

union find的方法找环就很便捷。 一旦在unionFind的环节发觉两个node的root都是一样的，则说明出现环则return false。
如果循环完所有edge了，我们则需要最后判断是不是parent是唯一的， 如果是唯一则return true 不然就是false
*/


class Solution {
    private class UF{
        public int[] parent;
        public int[] size;
        
        public UF(int nNodes){
            parent = new int[nNodes];
            /*
            一开始对每一个node的parent都设为自己
            而每一个root对应的size == 0
            */
            for (int i = 0; i < parent.length; i++){
                parent[i] = i;
            }
            size = new int[nNodes];
            for (int i = 0; i < size.length; i++){
                size[i] = 0;
            }
        }
        /*
        找到每一个传入的node的对应的parent
        */
        private int findRoot(int node){
            int root = node;
            while(root != parent[root]){
                parent[root] = parent[parent[root]];
                root = parent[root];
            }
            return root;
        }
        
        private boolean union(int nodeA, int nodeB){
            int rootA = findRoot(nodeA);
            int rootB = findRoot(nodeB);
            
            if (rootA == rootB){
                return true;
            } else {
                if (size[rootA] >= size[rootB]){
                    parent[rootB] = rootA;
                    size[rootA] +=1;
                } else {
                    parent[rootA] = rootB;
                    size[rootB] +=1;
                }
                return false;
            }
        }
    }
    
    public boolean validTree(int n, int[][] edges) {
        UF unionFind = new UF(n);
        /*
        corner Case: 
        1. 如果edges的长度为0， 但是只有一个node， 那他是valid的
        2. 如果edges的长度为0，但是nodes不止一个，那就invalid
        */
        if ((edges == null || edges.length == 0) && n == 1){
            return true;
        }
        if (((edges == null|| edges.length == 0) && n >=1)){
            return false;
        }
        /*
        对于每一个边进行union，因为边就已经说明了这两个node是有关系的
        */
        for (int[] edge : edges){
            int nodeA = edge[0];
            int nodeB = edge[1];
        
            boolean circle  = unionFind.union(nodeA, nodeB);
            //如果unionfind说找到了环
            if (circle) {
                return false;
            }
        }
        //不然我们就没有环
        /*
        但是有一个corner case那就 [0, 1] [2,3] 这两个是完全的disjoint的
        所以即便没有环但依旧不满足root的size唯一的这个判断，所以需要看看parent是否size == 1
        */
        int theParent = unionFind.findRoot(0);
        for (int i = 1; i < n; i++){
            int rootI = unionFind.findRoot(i);
            if (rootI != theParent) return false; 
        }
        return true;
    }
}