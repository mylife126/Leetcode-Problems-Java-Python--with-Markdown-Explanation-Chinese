/**
postOrder:  左 - > 右 -> root

所以我们会一直递归到最深层的左节点，然后对这个左节点进行 先左后右的操作发觉都是null return到自己后添加自己， 然后会return回到它的母节点，然后对于那个母节点往右走一次，然后再世对那个母节点的子tree进行一次  左 右 自己的操作

 */
class Solution {
    private List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return ans;
        helper(root);
        return ans;
    }
    private void helper(TreeNode root){
        if (root == null){
            return;
        }
        
        helper(root.left);
        helper(root.right);
        ans.add(root.val);
    }
}