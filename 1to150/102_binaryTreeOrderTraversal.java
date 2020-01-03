/**
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

order traversal意思是从左到右的traverse：
node3 -> queue[3]
              -> poll 3, 9 -> queue, then 20 -> queue[9,20]
                 -> poll 9, 9 is end; 
                     -> then poll 20 -> 15 -> queue, 7 in queue -> queue[15, 7]
                         -> then poll 15, 15 is end, queue[7]
                            -> then poll 7, 7 is end, queue empty, over
[3, 9, 20, 15, 7] is the answer
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        Queue<TreeNode> q = new LinkedList<>();
        //init q with the first root
        q.offer(root);
        
        while(!q.isEmpty()){
            int qSize = q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < qSize; i++){
                TreeNode thisNode = q.poll();
                temp.add(thisNode.val);
                
                if (thisNode.left != null){
                    q.offer(thisNode.left);
                }
                
                if (thisNode.right != null){
                    q.offer(thisNode.right);
                }
            }
            ans.add(temp);
        }
        
        return ans;
    }
}