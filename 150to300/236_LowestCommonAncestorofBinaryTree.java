/**
重点是 题目说明 all values are unique 且 一定有解！

用递归的思想：
1. 如果 一个母节点，在递归返回后，它的left 返回了一个node == p || q 并且 它的right返回了一个node == p|| q，
是不是可以说明这个母节点则为LCA了呢。

2. 相反的，如果一个母节点在递归返回后，发觉它的left == null， right == null 则说明它的左tree和右tree都没有找到
p与q，则这个节点不合理。

3. 另一个case是一个节点 == p || q的时候，返回到了它的母节点， 这个时候母节点的另一边是null，那根据题目说明，一定有一个解，则是不是可以说明，解一定存在于 那个 == p || q的节点呢？ 所以只需要返回那个非null的节点即可！


示例1， p = 5， q= 1. 先走left， 后走right
3.left = 5 == p return 
then goes to 3.right == 1 == q return 

发觉 对于node3来说 它的left ==5 且 right == 1， 都不为null，则说明它的left 找到了p 而right找到了q

              3
re 5 to 3.l /   \ return 1 to 3.right
           5     1
          / \   / \
         6   2 0   8
            / \
           7   4
           

示例2. p = 5,  q = 4
  
              3  => 3.left == 5 and 3.riht == null  => return 5.
 re 5 to 3.l/   \ return null to 3.right 
           5      1
          / \  n/   \n to 1.right
         6   2 0     8
            / \
           7   4
           
           
           
示例3. p = 6,  q = 2
  
                      3  => 3.left == 5 and 3.riht == null  => return 5.
 re 5 to 3.l /                \ return null to 3.right 
            5                  1
re 6 to 5.l/ \re 2 to 5.r    n/   \n to 1.right
         6   2 0     8
            / \
           7   4
          
         
 */  
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //定义left or right的出口
        if(root == null || root == p || root == q) {
            return root;
        } 
        //对每一个可能的node.left找是否能找到p or q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //case 1: 当递归返回后，判断这个母节点的左右是否存在找到的情况，如果是则说明母节点就是LCA
        if(left != null && right != null) {
            return root;
        }
        //case 2：当递归返回后，发觉母节点只有一个node找到了，那根据题目意思，一定存在解于这个node
        if (left == null && right != null){
            return right;
        } else if (left != null && right == null){
            return left;
        } else {
            //最坏情况就是null，例如走到了leaf，left.right == null leaf.left == null
            return null;
        }
    }
}