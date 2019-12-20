/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
1 2 3 4 5
dummy 1 2 3 4 5
举例， 对于 3 4 来说， 他们需要交换我们需要的nodes是 2 3 4， 因为2是prenode，它的next指针需要指向4，而4的next指向3，3的next指向了4的next。所以从一开始我们的current指在了dummy， swap（prenode = dummy) 
- > dummy 2 1 3 4 5, current 走去current.next.next也就是1， 因为我们是两个两个交换，所以每次走两步走到下一个prenode上 swap(prenode = 1) -> dummy 2 1 4 3 5 同样现在把currentpointer指向curr.next.next = 3这个地方了，但是呢我们发觉current.next.next == null 所以停止， 

另一个case是 dummy 1 2 3 4，current最后在末尾，它的next也是null，所以也停止。 

 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        //construct a dummyNode
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        //put the dummy in front of the head
        dummy.next = head;
        //the loop stop condition
        while (current.next != null && current.next.next!= null){
            ListNode preNode = current;
            swap(current);
            current = current.next.next;
        }
        
        return dummy.next;
    }
    public void swap(ListNode current){
        //get the node after the current node
        ListNode b = current.next;
        //get the node after the current's next node
        ListNode c = current.next.next;
        //get the node after the current's next next node
        ListNode d = current.next.next.next;
        //a -> b -> c -> d  => a > c > b > d
        current.next = c;
        c.next = b;
        b.next = d;
    }
}