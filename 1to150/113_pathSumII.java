/*
Backtrack即可。 但是要注意的是，以下错误写法：

class Solution {
    public List<List<Integer>> ans = new ArrayList<>();
    public Set<List<Integer>> seen = new HashSet<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null){
            return ans;
        }
        
        int tempSum = 0;
        List<Integer> tempArray = new ArrayList<>();       
        bt(root,sum, tempSum, tempArray);
        return ans;
    }
    
    private void bt(TreeNode root, int sum, int tempSum, List<Integer> tempArray){
        if (root == null && tempSum == sum){
            if (!seen.contains(tempArray)){
                ans.add(new ArrayList<Integer>(tempArray));
                seen.add(new ArrayList<Integer>(tempArray));
            }
            return;
        }
        
        if (root == null || tempSum == sum){
            return;
        }
        tempArray.add(root.val);
        tempSum += root.val;
        bt(root.left, sum, tempSum, tempArray);
        bt(root.right, sum, tempSum, tempArray);
        tempArray.remove(tempArray.size() - 1);
    }
}

这种写法是每次进入该node 再加一次 node的值，直到null了在判断是否可以append。 但是这有一个 corner case: [1 2]。 以上述方法来做会return [1]。 最根本的原因是 用 root == null 来判断并不能说明当前的node是leaf。 举个例子：
   1
 2
   3
   
我们一直往左走，node2.left == null, 并且假设我们的target就是3， 那这个时候我们算法会判断成立，因为null。 但是很明显的是 2不是leaf。

所以正确bt的做法是， 每次递归提前加入left or right的值，然后提前判断当前node.left ==null && node.right == null与否。

 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        if (root == null){
            return ans;
        }
        
        int rootVal = root.val;
        //我们需要提前加入根的值
        temp.add(rootVal);
        //同样我们的tempSum的init是根的值
        int tempSum = root.val;
        dfs(root,sum, tempSum, ans, temp);
        
        return ans;
    }
    private void dfs(TreeNode root, int sum, int tempSum, List<List<Integer>> ans, List<Integer> temp){
        //提前判断此刻进来的node是否已经是leaf了，
        if (root.left == null && root.right == null){
            if (tempSum == sum){
                //如果满足tempSum == sum则分配新的内存
                List<Integer> adding = new ArrayList<>(temp);
                ans.add(adding);
                return;
            } else {//不然就是一个失败的路径
                return;
            }
        } 
        /*
        因为是 left 和 right分开来做 bt， 且我们需要在上述代码提前预判是否是leaf，所以必须加入这一层判断
        如果root.left != null
        */
        if (root.left != null){
            /*同样 提前加入left的值，不论成功与否我们这一层就回溯现在加入的值，以便于下面右边的递归
                  5
                 / \
                4   8
               /   / \
              11  13  4
             /  \    / \
            7    2  5   1
            例如这里，我们现在在11， 然后提前加了7，发觉失败，就删掉7， 进入西面的right
            */
            temp.add(root.left.val);
            dfs(root.left, sum, tempSum + root.left.val, ans, temp);
            temp.remove(temp.size() - 1);
        }
        
        if (root.right != null){
            temp.add(root.right.val);
            dfs(root.right, sum, tempSum + root.right.val, ans, temp);
            temp.remove(temp.size() - 1);
        }
        return;
    }
}