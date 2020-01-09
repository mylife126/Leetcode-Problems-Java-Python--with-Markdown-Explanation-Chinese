/**
Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

一样的思路，只是这次用到了bfs
state function: dp[], 每一个位置代表我到这个root的能偷到的最大值

init：dp[0]= 0; dp[1] = root.val

transfer：我到了这一层 要么接着上上层继续偷，要么我这一层不偷了，继承上一层的最大偷盗金额

return： 最后一层时的能偷到的最大金额

但注意 这个解法其实不是题目要求的，题目说的并不是不能偷相邻的两层，而是不能偷相邻的节点。
 */
class Solution {
    public int rob(TreeNode root) {
        if (root == null){
            return 0;
        }
        //1. state function, each position represents upto this layer, the max value I could rob
        List<Integer> dp = new ArrayList<>();
        //2. init, the 0 layer has no house, the first layer has the house one
        dp.add(0,0);
        dp.add(1,root.val);
        //corner case, 只有一个node的时候
        if (root.left == null && root.right == null){
            return dp.get(dp.size() - 1);
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        //注意对于第一层的添加的方法，避免null pointer
        if (root.left != null){
            q.offer(root.left);
        }
        if (root.right != null){
            q.offer(root.right); 
        }
        //因为tree不能知道现在的位置，所以需要维护这个变量
        int whichLayer = 1;
        while(!q.isEmpty()){
            int size = q.size();
            int thisLayerValue = 0;
            int whichHouse = whichLayer + 1;
            //开始对这一层的val计算
            for (int i = 0; i < size; i++){
                TreeNode thisNode = q.poll();
                thisLayerValue += thisNode.val;
                
                if (thisNode.left != null){
                    q.offer(thisNode.left);
                }
                if (thisNode.right != null){
                    q.offer(thisNode.right);
                }
            }
            //同理要么偷这一层 要么不偷这一层
            int thisHouseValue = Math.max(dp.get(whichHouse - 2) + thisLayerValue,
                                         dp.get(whichHouse - 1));
            dp.add(whichHouse, thisHouseValue);
            whichLayer+=1;
        }
        return dp.get(whichLayer);
    }
}



/*
https://www.youtube.com/watch?v=-i2BFAU25Zk
bottom up的解法，用递归来做。 递归就是从底部晚上返回。

那么思路相似，对于某一个node， 它要么偷要么不被偷，
所以：
1. state function dp[2], dp[0] = 这一个node要是不偷的话我能拿到的最多的钱 dp[1]= 这一个node要是偷了我能拿到最多的钱。  然后这个dp会层层往上返回

2. init，或者时dfs退出，也就是当我们深度遍历到最后一层了，null， dp[0] = 0, dp[1] = 0

3. transfer function: 我们每一层都会拿到自己的值，和递归回来的自己的left 和 right的动态情况
                      那么我要是偷了自己就不能拿left和right 所以 dp[1] = 自己 + left[0] + right[0]
                      
                      要是我不偷自己的我就能拿我下面的left和right 
                      所以dp[0] = max(left[0],left[1]) + max(right[0],right[1])
4. 最后递归完毕后我们就取到了最头上的结果， 那么我们只需要对比 偷 和 不偷下的最大值即可

*/
class Solution {
    public int rob(TreeNode root) {
        /*state function: dp[0] 代表着递归到最上层节点的时候，我这一层要是不偷能有多少钱
        dp[1]代表着我要是偷了这个节点我能有多少钱
        */
        int[] robNoRob = dfs(root);
        return Math.max(robNoRob[0], robNoRob[1]);
    }
    
    private int[] dfs(TreeNode root){
        /*
        走到了叶子或者某一层为null， 那对于这一nullnode来说 我偷或者不偷都是0元钱
        */
        if (root == null){
            int[] money = new int[2];
            //因为走到底了，不偷== 0
            money[0] = 0;
            //偷也是0
            money[1] = 0;
            return money;
        }
        /*递归每一个node，每一个node都有一个自己的 偷或者 不偷的最大金额
        如果我偷了自己的node，那我就不能偷left和right 也就说我money[1] = curr + left[0] + right[0]
        
        但我要是不偷自己的node，那我就可以偷left或者right，所以我只需要取 
        left中偷或着不偷的最大 + right中偷或者不偷的最大即可
        */
        int thisRootMoney = root.val;
        int[] money = new int[2];
        //每一个node都会递归回来自己下一层的left or right的dp情况， 包含了偷或着不偷
        int[] rootsLeft = dfs(root.left);
        int[] rootsRight = dfs(root.right);
        
        //1.我这个node不偷, 意味着我可以拿我下面一层的left 和 right
        money[0] = Math.max(rootsLeft[0], rootsLeft[1]) + 
                           Math.max(rootsRight[0], rootsRight[1]);
        //2. 我这node偷了，意味着我只能拿自己的 且 我下面的left 和 right都不能偷
        money[1] = thisRootMoney + rootsLeft[0] + rootsRight[0];
        //把这一层的动态情况返回给自己的parent
        return money;
    }
}
