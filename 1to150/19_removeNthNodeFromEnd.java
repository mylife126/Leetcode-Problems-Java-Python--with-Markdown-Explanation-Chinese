/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }

 Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.

思路是它是remove倒数第几个的node，但是我们只有一个head，那就得需要在traverse这个linkedlist的时候需要记录这个node的idx，
然后用hash调出需要被修正的两个node： 1. 倒数nth node A  2. 倒数n+1个node B。
将b.next = a.next 来移除a即可。
但是有一个corner case，那就是 1 2 3 4 5, 需要被移除的是倒数第五个node，那不存在倒数第六个node == null， 所以需要将dummyHead.next = a.next即可
来防止null pointer exception 

 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null){
            return head;
        }
        //建立一个dummyHead
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        
        Map<Integer, ListNode> map = new HashMap<>();
        int idx = 0;
        
        while (head != null){
            tail.next = head;
            tail = tail.next;
            head = head.next;
            //记录此刻pop的node
            map.put(idx, tail);
            idx += 1;
           
        }
        
        int where = idx - n;
        //拿出需要被移除的node
        ListNode theNodeBefore = map.get(where - 1);
        //拿出被移除之前一个
        ListNode theNode = map.get(where);
        
        //判断edge case， 如果之前的node存在
        if (theNodeBefore != null){
            theNodeBefore.next = theNode.next;
        } else {//不然说明我们要移除的是 头， 所以就没有nodebefore
            dummyHead.next = theNode.next;
        }
        
        return dummyHead.next;
    }
}