/*
DFS的思路， 我们用hash来保存的是 《深度， val》。 
那么hash的特征是每一个key只能对应一个值，所以，我们可以深度先遍历每一个root的左节点，再到右节点，每次都map.put(depth，节点val)。

这样最后遍历的都是最右节点，同样的depth下 val也就只保存了right的。
*/

class Solution{
    private void dfs(TreeNode root, Map<Integer, Integer> map, int depth){
        if (root == null) return;
        //preorder
        map.put(depth, root.val);
        dfs(root.left, map, depth + 1);
        //in order
         // map.put(depth, root.val);
        dfs(root.right, map, depth + 1);
        //post order
        map.put(depth, root.val);
        }
    
    public List<Integer> rightSideView(TreeNode root) {
        //we need to set the depth <-> value, 因为hash的特征是只保留最后更新的value
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null){
            return ans;
        }
        int depth = 0;
        //init the hash and the ans array
        dfs(root, map, depth);
        
        for (int value : map.values()){
            ans.add(value);
        }
        return ans;
    }
}


/*
BFS解法：
从上到下的遍历每一层。 永远queue里放入的是先左边后右边的node
然后广度便利的时候，当遍历到了最后一个node，ans里放入这个node值即可。
*/

class Solution{
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            int layerSize = q.size();
            for (int i = 0; i < layerSize; i++){
                TreeNode thisNode = q.poll();
                //当我们到了这一层的最后一个node了，由于我们添加方式是先左后右，这一个一定是最右边的
                if (i == layerSize - 1){
                    //那就放入答案中
                    ans.add(thisNode.val);
                }
                //不然我们就按照先左后右的顺序放入就好
                if (thisNode.left != null){
                    q.offer(thisNode.left);
                }
                if (thisNode.right!= null){
                    q.offer(thisNode.right);
                }
            }
        }
        return ans;
    }
}