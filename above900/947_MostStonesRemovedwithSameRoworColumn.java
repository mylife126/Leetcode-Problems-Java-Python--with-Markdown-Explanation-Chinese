/*

主要思路是， n个石头 - m个cluster即为答案
拿掉一块石头后，那个地方就没有石头了， 
case1:
[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
[[S, S , 0]
 [S, 0,  S]
 [0, S,  S]]
 

可见每一个石头是彼此相连的，这样我们就可以sequentially依次拿走一个石头，直到最后一个。也就是说我们有五条边，6个石头。 这意味着我们有一个cluster。 所以我们需要用union find找到多少个cluster后，用总石头数字减去边的个数，即为答案。

|  
|  S——S            
|  |  |
S  |  S
|  |
S——S————————

case2:
[[0,0],[0,2],[1,1],[2,0],[2,2]]
[[S, 0, S]
 [0, S, 0]
 [S, 0, S]]

可见下面有三个边是完全相连的，所以可以总共删掉三个石头，也就是说五个石头里 减去 两个cluster
|
S---S
| S |
|   |
S———S————
*/

class Solution {
    private class UF{
        int[] parent;
        int[] size;
        
        public UF(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int findRoot(int node){
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
            if (rootA != rootB){
                if (size[rootA] >= size[rootB]){
                    parent[rootB] = rootA;
                    size[rootA] += size[rootB];
                } else {
                    parent[rootA] = rootB;
                    size[rootB] += size[rootA];
                }
            }
            
        }
    }
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length ==0 ){
            return 0;
        }
        int n = stones.length;
        UF uf = new UF(n);
        
        for (int i = 1; i < n; i++){
            int[] stoneA = stones[i];
            for (int j = 0; j < i; j++){
                int[] stoneB = stones[j];
                if (stoneA[0] == stoneB[0] || stoneA[1] == stoneB[1]){
                    uf.union(i, j);
                }
            }
        }
        
        Set<Integer> parentSet = new HashSet<>();
        for (int i : uf.parent){
            int root = uf.findRoot(i);
            parentSet.add(root);
        }
        int size = parentSet.size();
        return n - size;
    }
}

