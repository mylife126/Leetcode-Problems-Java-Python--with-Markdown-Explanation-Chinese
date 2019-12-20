/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode head = new ListNode(-1);
        ListNode prev = head; 
        //必须是二者都不为空，这个是merge的一个要点
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        //那么很有可能这个时候有一个list没有循环完，而由于是sorted，所以我们可以直接把所有的赋值给pre
        if (l1 == null){
            prev.next = l2;
        }
        
        if (l2 == null){
            prev.next = l1;
        }
        return head.next;
    }
}