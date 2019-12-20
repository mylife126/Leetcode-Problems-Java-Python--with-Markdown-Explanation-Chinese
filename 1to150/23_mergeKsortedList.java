/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 since now we have K nodes, one single compare is operated on the values, this means even if we could find the smallest val, we would not be able to find the node; 
 
 Thus we can set a priorityqueue,
 1. init the queue with all the heads in the lists
 2. poll the smallest node and add to the dummy
 3. if the smallest node has a next, then offer the smallest.next
 4. while the pq is all polled out 
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        int size = lists.length;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(size, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }
        });
        
        for (ListNode node : lists){
            if (node != null) pq.offer(node);
        }
        
        while(!pq.isEmpty()){
            ListNode smallest = pq.poll();
            tail.next = smallest;
            tail = tail.next;
            
            if (smallest.next != null){
                pq.offer(smallest.next);
            }
        }
      return dummy.next;   
    }
}