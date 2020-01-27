/*
friendcircle意味着 i, j ; j i 是同一个情况。 所以对于这两个情况我们如果能有一个方法使得这二者都归于同一个cluster，则说明放到了同一个朋友圈。

union find的想法是：
1. init是重点，对于 i ==j 来说 表示是自己， 所以自己一定是一个circle，也就是自己是自己的parent
2. 那么到了循环所有grid的时候 如果 grid[i][j] == 1 且 i！= j 则说明我们发现了 ith 和 jth person是朋友 也就是一个    边。 那么我们对他们进行一次union，把这两个人放到一起： union函数要么把j的root给了i， 或者把i的root给了j
3.最后我们检测parent有几个是不同的即可. 但需要注意的是，需要调用uf.findRoot的函数才可以。 因为parent array不一定是每个element就是其对应的parent
*/


class Solution {
    private class UF{
        int[] parent;
        int[] size;
        
        public UF(int n){
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < parent.length; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        private int findRoot(int node){
            int root = node;
            while (root != parent[root]){
                parent[root] = parent[parent[root]];
                root = parent[root];
            }
            return root;
        }
        public void union(int nodeA, int nodeB){
            int rootA = findRoot(nodeA);
            int rootB = findRoot(nodeB);
            if (size[rootA] >= size[rootB]){
                parent[rootB] = rootA;
                size[rootA] +=1;
            } else{
                parent[rootA] = rootB;
                size[rootB] +=1;
            }
        }
    }
    
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0){
            return 0;
        }
        int n = M.length;
        UF uf = new UF(n);
        for (int i = 0; i < M.length; i++){
            for (int j = 0; j< M[0].length; j++){
                //如果i j是朋友且不是自己比自己
                if (M[i][j] == 1 && i != j){
                    uf.union(i, j);
                }
            }
        }
        Set<Integer> parents = new HashSet<>();
        for (int i = 0; i < n; i++){
            // System.out.println(uf.parent[i]);
            int parent = uf.findRoot(i);
            // System.out.println(parent + "\n" + "****");
            parents.add(parent);
        }
        return parents.size();
    }
}