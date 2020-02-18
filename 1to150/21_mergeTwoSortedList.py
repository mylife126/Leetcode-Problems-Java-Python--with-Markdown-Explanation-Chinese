# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if not l1:
            return l2
        
        if not l2:
            return l1
        
        head = ListNode(-1)
        tail = head
        
        while(l1 and l2):
            l1Val = l1.val
            l2Val = l2.val
            
            if (l1Val <= l2Val):
                tail.next = ListNode(l1Val)
                l1 = l1.next
            else:
                tail.next = ListNode(l2Val)
                l2 = l2.next
            
            tail = tail.next
            
        if l1:
            tail.next = l1
        
        if l2:
            tail.next = l2
            
        return head.next
            
            
            
        