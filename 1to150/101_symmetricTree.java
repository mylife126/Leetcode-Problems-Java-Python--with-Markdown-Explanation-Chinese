/**
满足条件是：
1. rootleft.val == rootright.val
2. rootleft 和 rootright 同时递归到leaf == null
3. rootLeft.right 的上述 == rootRight.left 
   rootLeft.left == rootRight.right
  
举例子
    1
   / \
  2   2
 / \ / \
3  4 4  3

假设先走到 2 2， left2 走去left = 3， right2走去right =3. 验证成立，return true
又回到了 2 2， 现在去left2走去right =4， right2走去left = 4， 验证成立， return true
走到function底部，return true。
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        TreeNode left = root;
        TreeNode right = root;
        return helper(left, right);
    }
    private boolean helper(TreeNode left, TreeNode right){
        //如果同时到达null 则成立
        if (left == null && right == null){
            return true;
        }
        //失败，如果有一方先到了leaf 则不可能为symmetric
        if (left == null || right == null){
            return false;
        }
        //失败， 如果值不同失败
        if (left.val != right.val){
            return false;
        }
        //check 左node的左 和 右node的右
        boolean leftnRight = helper(left.left, right.right);
        if (!leftnRight) {
            return false;
        }
        //再check 左node的右 和 右node的左
        boolean rightnLeft = helper(left.right, right.left);
        if (!rightnLeft){
            return false;
        }
        
        //如果上述都成功就返回
        return true;
    }
}