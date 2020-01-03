/**
数据结构用到的是linkedlist
因为我们是要从bottom 到 up的traverse， 所以可以在每次最上层pop的时候把temparray 插在anslist的头上。这样每次每层循环后，最后层的信息就被插在了最上面。
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null){
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode thisNode = q.poll();
                temp.add(thisNode.val);
                
                if (thisNode.left != null) q.offer(thisNode.left);
                if (thisNode.right != null) q.offer(thisNode.right);
            }
            ans.offerFirst(temp);
        }
        return  ans;
    }
}