# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
'''
Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true

1. copy everything into an array
2. two pointer check

'''
class Solution(object):
    def isPalindrome(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if not head:
            return True
        
        array = []
        while(head):
            array.append(head.val)
            head = head.next
        return self.isPali(array)
            
    def isPali(self, array):
        start = 0
        end = len(array) - 1
        while(start < end):
            if array[start] != array[end]:
                return False
            start +=1
            end -=1
        return True
        
        