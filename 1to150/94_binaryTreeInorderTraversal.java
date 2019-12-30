/**
 inorder： 左child -> 自己 -> right child
 given [1 2 3 4 5 null null]
 
                               1
                            2     3
                         4    5
                       n   n n  n
                       
return [4 2 5 1 3]
1. root == null 后return
1. 先递归到左子树
2. 后加入此刻的val
3. 再递归右子树

当我们递归到最后一层leaf的时候，4， 4 -> null return -> +4  ->right null return back to 2 -> add 2 ->goes to right 5...

->
  1 
    -> 2 
         -> 4 
              -> null return  
         -> add(4) 
         ->goes to right null return 
         ->function over return 
         
    ->add(2)
    
    ->5
         -> null return 
    -> add 5
    -> goes to right null return
    -> funtion over return
    
    -> 3
         -> goes to left null return 
    ->add 3
    -> goes to right null return
    function over return 
->over  
 */
class Solution {
    public List<Integer> ans = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null){
            return ans;
        }
        
        dfs(root);
        return ans;
    }
    private void dfs(TreeNode root){
        //定义出口，如果递归到leaf了就return
        if (root == null){
            return;
        }
        //先一直找左子树，
        dfs(root.left);
        /*直到找完了就把最左的leaf的val放进去，这个时候会执行下一个代码，但同样因为是leaf，right==null直接return到上一层
        mother node的call，所以mother node的dfs return后又会执行这一句话，把中间的值加进去*/
        ans.add(root.val);
        //再去右子树
        dfs(root.right);
    }
}