/**
两棵树一直需要满足：
1. 同时递归到leaf， root = null
2. 每一个节点下的值一样
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return helper(q,p);
    }
    private boolean helper(TreeNode root1, TreeNode root2){
        //case 1, 成功
        if (root1 == null && root2 == null){
            return true;
        }
        //case2, 有一个提前到leaf了，这肯定不对了
        if (root1 == null || root2 == null){
            return false;
        }
        //case3, 有一个值不等于另一个值了
        if (root1.val != root2.val){
            return false;
        }
        
        boolean checkLeft = helper(root1.left, root2.left);
        if (!checkLeft){//可以提前结束 如果 left都不对了
            return false;
        }
        boolean checkRight = helper(root1.right, root2.right);
        if (!checkRight){//同样，提前结束
            return false;
        }
        
        //不然就是完全一样的tree
        return true;
    }
}   