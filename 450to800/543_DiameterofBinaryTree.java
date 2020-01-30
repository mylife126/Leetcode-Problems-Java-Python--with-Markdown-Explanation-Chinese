/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 
 Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
思想是，每一个node 都有自己的leftDepth 和 rightDepth，其次我们知道最长路径一定是 一个 node的左子树的depth 到它的右子树的depth 成 一个 ^ 字形状。

所以我们可以treat每一个node为一个tree， 我们首先找到每一个tree的leftDeth 和 rightDepth， 然后用一个全局的max变量，每次都比对一次此刻这个 tree的max(maxPath, leftDetph + rightDepth) 即可
 */
class Solution {
    //global pathDiameter
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        //其实这个depth我们用不到，但是在递归函数里需要返回每一个树的左depth 和 右depth
        helper(root);
        return max;
    }
    private int helper(TreeNode root){
        if (root == null){
            return 0;
        }
        //先找到这个树的左depth
        int leftDepth = helper(root.left); 
        //再找到这个树的右depth
        int rightDepth = helper(root.right); 
        //那么可能的pathDiameter = leftDepth + rightDepth
        max = Math.max(max, leftDepth + rightDepth);
        
        //最后返回upto这个树的深度
        return 1 + Math.max(leftDepth, rightDepth);
    }
}