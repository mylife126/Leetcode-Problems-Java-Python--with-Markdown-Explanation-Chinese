/**
1. recurssion
几个cornerCase的避免
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return create(l1,l2,0);
        
    }
    private ListNode create(ListNode l1, ListNode l2, int c){
        
        if (l1 == null && l2 == null && c == 0){
            return null;
        }
        
        int l1Val = (l1 != null) ? l1.val : 0;
        int l2Val = (l2 != null) ? l2.val : 0;
        
        int sum = l1Val + l2Val + c;
        int carry = sum / 10;
        
        ListNode curr = new ListNode(sum % 10);
        
        ListNode l1Next = null;
        ListNode l2Next = null;
        if (l1 != null && l1.next != null) l1Next = l1.next;
        if (l2 != null && l2.next != null) l2Next = l2.next;
        
        curr.next = create(l1Next, l2Next, carry);
        return curr;
    }
}