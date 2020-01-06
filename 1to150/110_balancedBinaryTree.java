/**
一个tree是balanced 有几个要求：
1. 它的左tree是balanced
2. 它的右tree也是balenced
3. 左高度右高度之差不大于1

那么其实我们可以递归每一个子tree，检查每一个子tree的情况，如果是balanced就把这个子tree的最大深度return给上一层mother root。

对于每一个子tree， 我们需要判断三个case：
1. left depth - right depth > 1   => return -1
2. left depth - right depth <= 1  => return这个tree的最大深度 max(leftD, rightD) + 1， 加1是因为自己的这一个node
3. 同理，假设一个parent node的left return了 -1， 表示它的左tree不是一个balanced tree， 同时它的right也return了 -1
   这个情况下 这个parent node也是return -1

最后有一个出口：当我们到达了leaf，return 0给leaf


Given the following tree [3,9,20,null,null,15,7]:

                                3 2      => 2 != -1 成立 
                              1/ \1
                              9  20
                            0/  /  \
                        null   15   7
                              0/     \0
                             null    null
Return true. 



 
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        
        
        int depth = depth(root);
        if (depth != -1){
            return true;
        } else {
            return false;
        }
    }
    
    private int depth(TreeNode root){
        //我们到达了leaf，return 0回去
        if (root == null){
            return 0;
        }
        
        int left = depth(root.left);
        int right = depth(root.right);
        //case 1, 对于这个节点它的left 和 right之差大于1
        //就return -1给这个node的母节点，表示说它这颗tree不是balanced
        if (Math.abs(right - left) > 1){
            return -1;
        } 
        //case2,对于这个节点它的left = -1， 或者 right = -1 则说明它的左右子树至少一个不balanced
        //那这棵树也是不balanced
        if (left == -1 || right == -1){
            return -1;
        }
        //case3, 对于这个节点它的left 和 right的高度差不大于一
        // if (Math.abs(right - left)<= 1){
        return Math.max(right, left) + 1;
        // }
    }
}