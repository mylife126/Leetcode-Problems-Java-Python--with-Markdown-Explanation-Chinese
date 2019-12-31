/*
    5
   / \
  1   6
     / \
    3   7

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

常见错误是 只对比了 当前node的 左value 和 右value， 但是一个合法的bst要求是，每一个subtree都是bst。 那么合法的bst的要求就是 
left < middle < right

也就是说递归的时候， 假设我们到了左子树node1，我们应该对比的是 node1.val 和right as upper bound，
也就是它上面一层的5,以及它以前的left as lowerbound
而这个时候left init为min。 所以 left < 1 < right 成立

但对于左边的node4来说，他需要对比的是 node6.val和上一层的left = 5 as lowerbound 以及上层的right as upperbound, 而此刻right init
as max 所以成立

然后我们又以6为节点递归到左子树，rightUpper = 6, leftLower = 5 nochange。 发觉不合理，false

1. 先对比左node，将此刻的node的val作为maxbound传入， min不变
2. 再对比右node， 将此刻的nodeval作为lowerbound传入, max不变

                                         进入 null null
                                               5 <-
                        1,max =5 <-                                          -> 6, min = 5 
    null return true <-    -> null return true    3, max = 6, min = 5 false<-


*/
class Solution{
    private boolean dfs(TreeNode root, long higherBound, long lowerBound){
        if (root == null) return true;
        int rootVal = root.val;
        //如果此刻的rootval比上一层的进来的higherbound要大，或者比lowerbound小了 都是不合法的
        if (rootVal >= higherBound || rootVal <= lowerBound){
            return false;
        }
        boolean checkLeft = dfs(root.left, rootVal, lowerBound);
        if (!checkLeft){
            return false;
        }
        boolean checkRight = dfs(root.right,higherBound, rootVal);
        if (!checkRight){
            return false;
        }
        return true;        
    }
    public boolean isValidBST(TreeNode root){
        return dfs(root, Long.MAX_VALUE, Long.MIN_VALUE);
        
    }
}