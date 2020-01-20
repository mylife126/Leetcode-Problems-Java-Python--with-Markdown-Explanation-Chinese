/*
概念：
图和tree的区别是，tree是无环的，图是可以有环的, 其次对于edges = [] node ==1来说也是valid tree

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

union find的方法找环就很便捷。 一旦出现环则return false， 直到循环结束就return true


最后，因为题目规定了每一个数字对应的就是 0 ~ n-1 也就是说 每一个数字对应了一个indx， 无需对于unionfind来修改传入的参量。 回顾128题，这道题就需要转换num和indx
*/


class Solution {
    private class UF{
        public int[] parent;
        public int[] size;
        
        public UF(int nNodes){
            parent = new int[nNodes];
            for (int i = 0; i < parent.length; i++){
                parent[i] = -1;
            }
            size = new int[nNodes];
            for (int i = 0; i < size.length; i++){
                size[i] = 0;
            }
        }
        
        private int findRoot(int node){
            int root = node;
            while(parent[root] != -1){
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
        if ((edges == null || edges.length == 0) && n == 1){
            return true;
        }
        if (((edges == null|| edges.length == 0) && n >=1)){
            return false;
        }
        
        for (int[] edge : edges){
            int nodeA = edge[0];
            int nodeB = edge[1];
            boolean circle  = unionFind.union(nodeA, nodeB);
            if (circle) {
                return false;
            }
        }
        
        Set<Integer> parents = new HashSet<>();
        for (int i = 0; i < n; i++){
            int rootI = unionFind.findRoot(i);
            parents.add(rootI);
        }
        if (parents.size() == 1) {
            return true;
        } else {
            return false;
        }        
    }
}