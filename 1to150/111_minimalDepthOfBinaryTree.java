/**
我们可以 BFS 每一层， 看着一层的node是否出现了一个leaf (leaf.left == null && leaf.right ==null)
如果出现了就比对一下此刻的depth 和 globalMin 谁小, 直到最后遍历完所有depth。

但是这里可以优化的地方是，既然要找最短的depth，那是不是一旦出现了一个leaf，此刻的depth就绝对是最小了呢。所以
不用遍历完所有的depth，然后不断比对， 而是可以一旦找到了一个leaf就return即可。 
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        //可以略去，优化后的算法不需要不断比对每一层的depth of the leaf
        // int min = Integer.MAX_VALUE;
        int depth = 0;
        while(!q.isEmpty()){
            int size = q.size();
            depth +=1;
            for (int i = 0; i< size; i++){
                TreeNode thisNode = q.poll();
                if (thisNode.left != null){
                    q.offer(thisNode.left);
                } 
                
                if (thisNode.right != null){
                    q.offer(thisNode.right);
                } 
                
                if (thisNode.left == null && thisNode.right == null){
                    /*
                    以下可以直接改为 min = depth
                    */
                    // min = Math.min(min, depth);
                    int min = depth;
                    return min;
                }
            }
        }
        return depth;
    }
}