/**
preorder:
自己 -》 左  -》 right

每一层递归先将此刻的node值放入list 再去left， 直到走到leaf 再回到最下层左node，然后去这个node的right。 最后返回到它的母节点的上一层的母节点，然后对那个母节点的右操作
 */
class Solution {
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return ans;
        helper(root);
        return ans;
    }
    private void helper(TreeNode root){
        if (root == null) {
            return;
        }
        
        ans.add(root.val);
        helper(root.left);
        helper(root.right);
    }
}