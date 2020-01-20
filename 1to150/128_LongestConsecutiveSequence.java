/*
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

如何判断consecutive的edge：
1. 建立map，key是num， value的对应的indx
2. one pass所有nums array的element，然后建立map

那么one pass的时候 找到 above 和 below， 分别等于num + 1, num - 1。 然后看这两数字是不是在mapkey里，如果在找出他们的对应的indx。 传入 union find中
用union find找到每个边对应的parent 代表的是 consecutive的cluster。
最后对每一个node 找它对应的 parent， 然后用hashmap记录每一个parent对应的counts来表示有多少个数字是与其联系的，也就是consecutive的数量。


*/
class Solution {
    private class UF{
        private int[] parent;
        private int[] size;
        
        public UF(int n){
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++){
                /*
                用indx表示每一个element
                */
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
        }·
        private void union(int nodeA, int nodeB){
            int rootA = findRoot(nodeA);
            int rootB = findRoot(nodeB);
            
            if (size[rootA] >= size[rootB]){
                parent[rootB] = rootA;
                size[rootA] += 1;
            } else {
                parent[rootA] = rootB;
                size[rootB] +=1;
            }
        } 
    }
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        UF uf = new UF(nums.length);
        Set<Integer> seen = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++){
            int currentNum = nums[i];
            int below = currentNum - 1;
            int above = currentNum + 1;
            /*
            重点： union find里面传入的是 index！！而不是数字本身！！
            */
            if (map.containsKey(below)){
                int whereLow = map.get(below);
                uf.union(i, whereLow);
            }
            
            if (map.containsKey(above)){
                int whereAbove = map.get(above);
                uf.union(i, whereAbove);
            }
        }
        
        Map<Integer, Integer> count = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            int currentNum = nums[i];
            /*
            避重 [0, 0 , -1]这个case， 第二个0， 会在找一此0的root，就会让count 多加一次
            */
            if (seen.contains(currentNum)) {
                continue;
            }
            seen.add(currentNum);
            int root = uf.findRoot(i);
            int newCount = count.getOrDefault(root, 0) + 1;
            count.put(root, newCount);
            max = Math.max(max, newCount);
            
        }
        return max;
    }
}