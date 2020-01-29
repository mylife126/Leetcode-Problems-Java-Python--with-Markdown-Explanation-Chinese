/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */



/*
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

                    10
                   /  \
                  5   15
                 / \  / \
               3    7 n  18
思想是：因为是BST，所以 每个节点的左边一定小于自己， 而右边一定大于自己，所以，我们的递归的时候可以判断节点的val来做是对它左子树搜索还是对它的右子树搜索的判断。
*/
class Solution {
    private int runningSum = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        dfs(root, L, R);
        return runningSum;
    }
    
    private void dfs(TreeNode root, int l, int r){
        if (root == null) return;
        // if (root.val < l){
        //     dfs(root.right, l ,r);
        // }
        // if (root.val > r){
        //     dfs(root.left, l, r);
        // }
        if (root.val >= l && root.val <= r) {
            runningSum += root.val;
        }
        if (root.val > l) dfs(root.left, l, r);
        if (root.val < r) dfs(root.right, l, r);
    }
}