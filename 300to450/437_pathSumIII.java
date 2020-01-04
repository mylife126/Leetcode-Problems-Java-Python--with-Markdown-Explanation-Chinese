/**
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

核心是要以不同的 节点做一次 dfs， 这样就能确保以不同的点作为起始点来看是否以他为起始点能不能找到一条路子使得sum成立
所以建立好helper函数后，我们可以对主函数进行递归，每次递归的是left， 然后再是right。
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        //先以当前root为root做一次递归
        int countRoot = dfs(root, sum);
        //再不断递归root.left 以不同的left为root做一次上述的 dfs
        int countLeft = pathSum(root.left, sum);
        //同理不断递归 root.right 以不同的right为root做一次上述的 dfs
        int countRight = pathSum(root.right, sum);
        
        return countRoot + countLeft + countRight;
    }
    
    private int dfs(TreeNode root, int sum){
        if (root == null){
            return 0;
        }
        sum -= root.val;
        /*
        不能在某一层直接因为sum ==0 就return 1，这样会导致不能走到底，但也有可能再往下走也有成立的path，
              10
             /  \
            5   -3
           / \    \
          3   2   11
         / \   \
        3  -2   1
        
        举例，假设走到了3这个node，我们发觉 sum ==0了，直接return到node5这个节点，然后我们再往又走。
        但很明显我们就没法走到3 和 -2了。 假设 3 和 -2 分别都改为0， 那我们就少了【两个】可能的path了。
        */
//         if (sum == 0){
//             return 1;
//         } 
        
        int countLeft = dfs(root.left, sum);
        int countRight = dfs(root.right, sum);
        /*
        所以应该是每次return到这一层的时候判断一次这一层是不是等于0了，再加上去
        以上面的为例，假设我们走到了3这一层 sum ==0， 然后我们继续走到了3，发觉不行return 0回到3， 
        然后再去3.right =-2 发觉还是不行
        return了0， 走到函数尾部的return (sum == 0 ? 1 : 0) + countLeft + countRight == 1 + 0 + 0 = 1
        然后这一个1 return给了 5这一层的countleft. 再走到5.right...以此类推
        */
        return (sum == 0 ? 1 : 0) + countLeft + countRight;
    }
}