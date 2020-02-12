/**
首先，我们知道一个tree是另一个tree的subtree的条件是：每一刻的母节点val相等，深度相同（即同时能left到leaf）

第一步建立 helper递归函数，当主函数里的母节点相同的时候判断candidate的subtree们的结构是否一直
helper递归判断, trigger的条件是此刻的s.val == t.val：
1. 母节点的值相同，左子树相同， 右子树相同。 注意这里不可以只判断左节点和右节点的值是否相同，因为很可能这个subtree的母节点的左边是另一颗树：
      3                 4
     4  5             1   2
    1 2              1 
    
如果只是判断 4.left == 4.left && 4.right == 4.right 上述会等于true，但其实我们没有到leaf。
2. 所以我们需要确保每一个s的子tree和t的子tree同时达到了leaf，即为s == null && t == null
3. 那么如果此刻的s. val != t.val 则说明不成立，返回false
4. 如果s == null || t == null 如上述的例子，也是false
5. 对于每一个节点/子字节， 都当作一个tree来递归判断上述，都需要满足他们的left是true 且 right是true


主函数部分，
思路是，我们需要一个helper函数，triger的条件则是 
1. 当此刻的s的母节点val 等于 t的母节点的val，
    这个时候就得check helper(s,t) || helper(s.right, t) || helper(s.left, t)
    因为会有一个corner case
    1           1
   1            
    如果值判断 helper(s, t) 则会为false，但其实 s的左子树等于t
    
2. 如果上述不等，则说明我们可以递归查看root的left是否能满足上述的情况，或者查看root的right是否能满足上述情况 isSubtree(root.left, t) || isSubtree(root.right, t) 

3. 那么如果一直尝试2中的递归，一直没有发现有一个母节点能等于t的母节点的val， 则直接返回false
   if (s == null) return false;
 */
class Solution {
    
    public boolean isSubtree(TreeNode s, TreeNode t) {
        /*只有当此刻的母节点的值一样了才有判断的可能性， 不然我们就不断递归s的左子树和右子树，
        如果一刻递归到了 null， 则无论如何都不可能匹配了，return false*/
        if (s == null) return false;
        
        if (s.val == t.val) {//trigger helper的条件
            //一旦母节值相同了我们才能查看他们的左子树和右子树
            return helper(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
        } else {
            //如果上述的判断不成立，则我们可以递归查看s的左边和s的右边
            return isSubtree(s.left, t) || isSubtree(s.right, t);   
        }
    }
    /*建立helper函数来check两个tree以及其subtree的结构和val
    helper函数来判断potential candidate Snode 和 t是否是subtree，主要用于同时递归至两颗树的leaf，
    如果中途有不对的地方直接false */
    private boolean helper(TreeNode s, TreeNode t){
        //只有当两个tree同时达到了leaf，才是tree
        if (s == null && t ==null) return true;
        //只要有一方提前到了leaf，则不可能是等同的tree结构了
        if (s == null || t == null) return false;
        //同理某一刻递归母节点的值不同 也不可能是一样的tree结构了
        if (s.val != t.val) return false;
        
        //同时check每一刻当前树的left 和 right
        boolean checkLeft = helper(s.left, t.left);
        boolean checkRight = helper(s.right, t.right);
        //必须左子树和右子树都满足才可以
        return checkLeft && checkRight;
    }
}