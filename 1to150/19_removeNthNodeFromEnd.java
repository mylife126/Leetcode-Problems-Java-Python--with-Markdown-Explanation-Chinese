/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 So the key problem of linkedlist is that we could not index， meaning that we need to have a mechanism to record the position <-> node  => use the hash to record the idx <-> node during the population
 
 1. iterate through the linedlist hash [idx, node];
 2. given last n, meaning the idx = length - n;
 3. index out the node along with the node before it from the hash
 4. nodeBefore.next =  thisNode.next
 
 Corner case, the thisNode is the first one and there is no nodeBeforeit, judge if the idx is 0, if yes, the nodeBoforeIt is just the dummyHead;
 */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null){
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        
        Map<Integer, ListNode> map = new HashMap<>();
        int idx = 0;
        while (head != null){
            tail.next = head;
            tail = tail.next;
            head = head.next;
            map.put(idx, tail);
            idx += 1;
           
        }       
        
        int where = idx - n;
        // System.out.println(where);
        ListNode theNodeBefore = map.get(where - 1);
        // System.out.println(theNodeBefore);
        ListNode theNode = map.get(where);
        if (theNodeBefore != null){
            theNodeBefore.next = theNode.next;
        } else {
            dummyHead.next = theNode.next;
        }
        
        return dummyHead.next;
    }
}