/**
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1

类似于 backtrack，
1. 如果遇到leaf了, leaf.left == null and leaf.right = null and tempsum == target, return true
2. 不然我们先走left， 走到每一个subtree的left的leaf时 如果能找到直接return true 不然执行return后 执行 right
3. 再走right


-> 5 sum = 5
     -> 4, sum = 9
        -> 11 sum = 20
           -> 7 sum = 27, leaf, not equal return false, back to the node 11, then we go to right
           -> 2 sum = 22,  leaf, equal return true, then return ture
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        int tempSum = root.val;
        boolean ans = bt(root, sum, tempSum);
        return ans;
    }
    
    private boolean bt(TreeNode root, int sum, int tempSum){
        if (root.left == null && root.right == null && tempSum == sum){
            return true;
        }
        
        /*now we need to check the left 重点是需要提前加下一个node的值*/
        if (root.left != null){
            boolean checkLeft = bt(root.left, sum, tempSum + root.left.val);
            //如果left 能行就直接return 不然我们bt到上一个node 执行右边
            if (checkLeft){
                return true;
            }
        }
        
        if (root.right != null){
            boolean checkRight = bt(root.right, sum, tempSum + root.right.val);
            if (checkRight){
                return true;
            }
        }
        return false;
    }
}