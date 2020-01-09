/*
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6


*/

/**
Approach 1. bfs, linear search
Slow but okay
 */
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        int count = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                TreeNode thisNode = q.poll();
                count +=1;
                if (thisNode.left != null){
                    q.offer(thisNode.left);
                }
                if (thisNode.right != null){
                    q.offer(thisNode.right);
                }
            }
        }
        return count;
    }
}

/*
solution 1: linear search recursively
*/
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        int itSelf = 1;
        int leftNodeCount = countNodes(root.left);
        int rightNodeCount = countNodes(root.right);
        
        return leftNodeCount + rightNodeCount + itSelf;
    }
}
