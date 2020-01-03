/**
   4 -> queue 
   poll, get 4.left, get 4.right
   swap
     4
   /   \
  7     2
 / \   / \
6   9 1   3
  then, manipulate node 7 with the same process
  then, manipulate the node 2 with the same process
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            TreeNode thisNode = q.poll();
            /*
            do not need to care if the left and right are null or not, we could stll declaare it as a TreeNode type
            */
            TreeNode tempLeft = thisNode.left;
            TreeNode tempRight = thisNode.right;
            // System.out.println(tempLeft);
            //swap the right to left, and the left to right
            thisNode.left = tempRight;
            thisNode.right = tempLeft;
            /*
            need to prevent the null pointer exception and if the left is null, this means, at least its left wise
            is leaf. which is the end
            */
            if (thisNode.left!= null){
                q.offer(thisNode.left);
            }
            
            if (thisNode.right != null){
                q.offer(thisNode.right);
            }
        }
        return root;
    }
}