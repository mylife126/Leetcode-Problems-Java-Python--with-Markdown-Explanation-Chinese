/*
        3
   /         \
  9            20
 /  \         /  \
10  12       15   7
核心是：
1. 在left to right traverse的时候， 我们是从linkedlist里从头开始poll，然后把每一个node 按照先左后右的顺序在list尾端放入
这样下一层 right to left traverse的时候，我们是list末端poll，你会发觉最后面那个node正好就是我们现在最后放入的node

2. 在right to left traverse的时候，我们是从linkedlist的末尾开始poll，然后把每一个node 按照 先右后左的顺序放入list的头，这样，下一层 left to right traberse的时候，我们是从list头开始poll，会发觉头上正好是这一层最后放入的left

poll from head 3 left2riht ->  [] -> [9] -> [9,20] 
                                     add left then right starting from start
                    -> r2l: poll from end 20 -> [7,9] -> [15,7,9] then poll from end 9 -> [12, 15,7] -> [10,12,15,7]
                       add right then left in front of the last 
                        ->poll from start: 10 12 15 7 
*/

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        //set a flag to control if we need left2right or right2left
        boolean left2right = true;
        //set the linkedlist as our queue
        LinkedList<TreeNode> q = new LinkedList<>();
        //init
        q.add(root);
        
        while(!q.isEmpty()){
            int qSize = q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < qSize; i++){
                if (left2right){
                    TreeNode leftMostNode = q.pollFirst();
                    temp.add(leftMostNode.val);
                    /*in the left2right traversal, we add the left and then right of each node at the end of the
                    linkedlist*/
                    if (leftMostNode.left!= null){
                        q.offerLast(leftMostNode.left);
                    } 
                    
                    if (leftMostNode.right != null){
                        q.offerLast(leftMostNode.right);
                    }
                } else {//now it is the right to left 
                    TreeNode rightMostNode = q.pollLast();
                    temp.add(rightMostNode.val);
                    
                    /*
                    in the right to left traversal we add right and then left of each node at the start of the list
                    */
                    if (rightMostNode.right != null){
                        q.offerFirst(rightMostNode.right);
                    }
                    
                    if (rightMostNode.left != null){
                        q.offerFirst(rightMostNode.left);
                    }
                }
            }
            ans.add(temp);
            left2right = !left2right;
        }
        return ans;
    }
}