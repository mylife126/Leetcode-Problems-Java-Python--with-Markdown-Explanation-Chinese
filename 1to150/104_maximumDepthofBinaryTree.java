/*
Solution, BFS: 
1. 将每一层的node poll后 depth+=1
*/
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0;
        
        while(!q.isEmpty()){
            int qsize = q.size();
            //逐层遍历
            for (int i = 0; i < qsize; i++){
                TreeNode thisNode = q.poll();
                if (thisNode.left != null) q.offer(thisNode.left);
                if (thisNode.right != null) q.offer(thisNode.right);
            }
            depth+=1;
        }
        return depth;
    } 
}