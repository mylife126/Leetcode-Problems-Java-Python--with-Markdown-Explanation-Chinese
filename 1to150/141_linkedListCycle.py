# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        slow = head
        fast = head
        #如果没有环，则fast会优先到达none，或者此刻的fast已经是尾巴了，它的next也是none
        while(fast != None and fast.next != None):
            slow = slow.next
            fast = fast.next.next
            if (slow == fast):
                return True
        return False
        