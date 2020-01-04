/*
重点是 BST的特征
left < root < right.

那么题目说明，每一个数字是unique，且一定存在一个解。
所以就出现了三个可能性：
1. p和q都大于 root， 那只能现在去找tree的右边
2. p和q都小于 root， 那就只能去找tree的左边
3. 剩下的情况就是 p < root <=q || p <= root < q || q <= root< p || q < root <= p。那根据题目说明一定存在解就可以判断这个时候的root就似乎解

solution 1， iterative

*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        
        TreeNode curr = root;
        
        while (curr != null){
            int currVal = curr.val;
            //Case 1, p q都在当前node的右边，
            if (pVal > currVal && qVal > currVal){
                curr = curr.right;
                
            //case 2， pq都在当前node的左边
            } else if (pVal < currVal && qVal < currVal){
                curr = curr.left;
                
            //最后的case就是 当前node在两个pq之间
            } else {
                return curr;
            }
        }
        return null;
    }
}


/*
recurrsively
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //首先如果但凡有一个等于了，那根据题目说明 一定又唯一解，则说明已经找到了
        if (root.val == p.val || root.val == q.val){
            return root;
        }
        //不然我们需要继续深度搜索
        int currVal = root.val;
        int pVal = p.val;
        int qVal = q.val;
        //case 1： 两个值都在node的右边
        if (pVal > currVal && qVal > currVal){
            if (root.right != null){//且需要保证当前node有right
                return lowestCommonAncestor(root.right, p, q);
            }   
        } 
        //case 2， 都在左边
        else if (pVal < currVal && qVal < currVal){
            if (root.left != null){//且需要保证当前node有left
              return lowestCommonAncestor(root.left, p, q);  
            }
        } else {//最后的case则是 加载中间
            return root;
        }
        //最后需要返回找不到的情况，因为很有可能有一个case是进入了前两个if，但是已经无路可走了，那只能null
        return null;
    }
}