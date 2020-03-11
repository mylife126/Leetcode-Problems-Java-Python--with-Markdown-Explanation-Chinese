# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

'''
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4

'''
class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        if not l1:
            return l2
        if not l2:
            return l1
        dummy = ListNode(-1)
        tail = dummy
        
        while(l1 and l2):
            l1Val = l1.val
            l2Val = l2.val
            if l1Val <= l2Val:
                tail.next = ListNode(l1Val)
                l1 = l1.next
            else:
                tail.next = ListNode(l2Val)
                l2 = l2.next
            tail = tail.next
        
        if l1 and not l2:
            tail.next = l1
            
        if l2 and not l1:
            tail.next = l2
            
        return dummy.next
    
        