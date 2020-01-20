/**
BFS 加 priorityQueue
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<TreeNode> pq = new PriorityQueue<>(new Comparator<TreeNode>(){
            @Override
            public int compare(TreeNode a, TreeNode b){
                return a.val - b.val;
            }
        });
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode thisNode = q.poll();
                pq.offer(thisNode);
                if (thisNode.left != null) q.offer(thisNode.left);
                if (thisNode.right != null) q.offer(thisNode.right);
            }
        }
        
        int count = 1;
        while(!pq.isEmpty()){
            TreeNode node = pq.poll();
            if (count == k){
                return node.val;
            }
            count +=1;
        }
        return -1;
    }
}