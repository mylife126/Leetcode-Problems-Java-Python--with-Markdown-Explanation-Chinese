/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 1 ->  1 ->  1 -> 1 -> 2 ->  3 ->  3
 h    h.next   => h.next = h.next.next
 
 1 ->  1 ->  1 -> 1 -> 2 ->  3 ->  3
 h           h.next => h.next = h.next.next
       
 1 ->  1 ->  1 -> 1 -> 2 ->  3 ->  3
 h                 h.next => h.next = h.next.next
              
 1 ->  1 ->  1 -> 1 -> 2 ->  3 ->  3
 h                     h.next  => h = h.next
 
 1 ->  1 ->  1 -> 1 -> 2 ->  3 ->  3
                       h     h.n   => h = h.next

 1 ->  1 ->  1 -> 1 -> 2 ->  3 ->  3
                             h     h.n => h.n = h.n.n
                             
 1 -> 2 -> 3
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return head;
        }
        //sign a reference to head
        ListNode tail = head;
        
        while(tail != null && tail.next != null){
            //case 1, 当前的值出现了重复
            if (tail.val == tail.next.val){
                //leap the current link to the next node, but instead connect the link to the duplicated node's next
                tail.next = tail.next.next;
            } else {
                //otherwise, move to the next node and check the next node
                //注意这段话必须加else，因为这句话能执行的情况必须是以上的补集，而不是任何情况下都能执行的
                tail = tail.next; 
            }

        }
        
        return head;
    }
}