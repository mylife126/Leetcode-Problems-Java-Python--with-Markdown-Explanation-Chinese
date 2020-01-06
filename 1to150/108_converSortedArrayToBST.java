/**
[-10,-3,0,5,9]

对于每一个tree，它的root是当前array的中心值。 而这个root.left值都是从中间值往左取， root.right的值都是从中间值往右取。

所以逻辑是每一个node都是一个tree， 而每个tree取的都是当前array下的中间值，递归到root.left， array取了左一半， 然后对于root.left 这个新的root 取的依旧是左半array的中间值。 以此类推

例子1:  [-10, -3, 0, 5, 9]
                                root = 3
                [-10 -3]      /          \  [5, 9]
                3.left      -10           5
              no left array  / \[-3]     / \
                         null  -3      null 9 
 */

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        
        TreeNode root = build(nums, left, right);
        return root;
    }
    private TreeNode build(int[] nums, int left, int right){
        if (left > right){
            return null;
        }
        //get the mid number from the current region of array
        int mid = (left + right) / 2;
        //construct the root of the current sub- tree with the mid number
        TreeNode root = new TreeNode(nums[mid]);
        //pass the left half array to the current root.left
        root.left = build(nums, left, mid - 1);
        //pass the right half array to the current root.right
        root.right = build(nums, mid + 1, right);
        
        //return the current root to last call; it could be rootUpper.left or rootUpper.right
        return root;
    }
}