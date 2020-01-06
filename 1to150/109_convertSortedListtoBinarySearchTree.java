/**
思路：
1. populate all the nodes in the linkedlist to the array
2. build the bst from the sorted array

 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        //get all the nodes val into a list
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        int end = list.size() - 1;
        //build the BST from a sorted array
        TreeNode root = build(list, 0, end);
        return root;
    }
    private TreeNode build(List<Integer> list, int start, int end){
        if (start > end){
            return null;
        }
        
        int mid = (start + end) / 2;
        int midNum = list.get(mid);
        TreeNode root = new TreeNode(midNum);
        root.left = build(list, start, mid - 1);
        root.right = build(list, mid + 1, end);
        return root;    
    }
}