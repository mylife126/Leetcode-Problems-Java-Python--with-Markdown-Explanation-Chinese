/**
建立一个pq， 这样traverse linkedlist的时候就是 nlogn的复杂度

最后一个O（n）的one pass给dummy赋值

nlogn + n = nlogn
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null){
            return head;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while(head != null){
            pq.offer(head.val);
            head = head.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        
        while(!pq.isEmpty()){
            int nextVal = pq.poll();
            tail.next = new ListNode(nextVal);
            tail = tail.next;
        }
        return dummy.next;
    }
}