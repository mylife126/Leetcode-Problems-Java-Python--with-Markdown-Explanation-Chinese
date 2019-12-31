/**
对于一个链表来说 假设 nodeX是不需要的，要想扔掉这个node，我们就需要知道它前面那个node

所以我们可以建立一个dummyHead，dummy.next = head. 相当于多接入了一个pre的node对于原来head的头来说
这个时候，我们就有两个指针，一个是current = head， 另一个是pre = dummy

1. 如果我们循环发现了需要删除的node，我们的pre正好指向在这个node 之前，所以leap掉这个node即可
2. else，这个是重点，我们就需要把pre 移动到current上面去
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode curr = head;
        while(head != null){
            int currVal = head.val;
            if (currVal == val){
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }
        
        return dummy.next;
    }
}