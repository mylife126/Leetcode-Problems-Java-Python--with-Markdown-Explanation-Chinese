# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def detectCycle(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if not head:
            return None
        
        seen = set()
        while(head != None):
            if head not in seen:
                seen.add(head)
            else:
                return head
            head = head.next
            
        return None
            
        