/**
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

        1
       / \
      2   5
     / \   \
    3   4   6

  
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
          
          
顺序是： root 到 左 到左 再到右 所以可以用stack，对于每一个node进行先push right 再push left的操作，然后再对当前node进行一个node.right = stack.peek()的操作，stack在之前的push后，最上面的一定是最左的node，所以我们peek得到了第一个最左的node后连接到此刻的node上， 然后下一刻循环对peek的node进行同样的操作：

node1 -> push right [1.right = 5->6] then push left[1.left, 5->] = [ 2   5-6
                                                                    3  4     ]
      -> node1.right = stack.peek() => 1 -> 2
    
          -> pop node2, push right [4,5-6] then push left [3,4, 5 -6]
          -> node2.right = stack.peek（） =>  2 - > 3
                -> pop node 3, no right no left to push,  [4, 5-6]
                -> node3.right = stack.peek() => 3 -> 4
                    -> pop node4, no right no left to push   [5 - 6]
                    -> node4.right = peek()  => 4 -> 5 -6
                         -> pop node5, push right [6], no left
                         -> node5.right = peek() => 5 -6
                               -> pop node 6, no riht no left to push. 
                               -> nothing to add after 6 over
    1 - 2 
          2 - 3
                3 - 4
                      5 - 6
                            6 - nothing
 */
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            
            /*
            push right first then the left, so that in the next loop, we would get the left first 
            */
            
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
            
            /* 
            链接现在的node 和 第一个left. 需要用peek，这样只是把此刻的right 调出一个stack里的最top的left的reference链接，而
            stack里那个node 是需要到下一次循环来pop 进行这样的操作的
            */
            if (!stack.isEmpty()){
                curr.right = stack.peek();
            }
            //题目要求，最后falttern的只有一个right，没有left
            curr.left = null;
        }
        
    }
}