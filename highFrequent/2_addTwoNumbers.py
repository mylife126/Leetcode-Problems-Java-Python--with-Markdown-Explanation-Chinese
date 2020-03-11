# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        if not l1:
            return l2
        if not l2:
            return l1
        
        dummy = ListNode(-1)
        tail = dummy
        carry = 0
        #注意终止条件！ 只要有一个还满足，就一直加下去
        while(l1  or l2  or carry != 0):
            #防止null pointer exception
            l1Val = l1.val if l1 else 0
            l2Val = l2.val if l2 else 0
            
            summation = l1Val + l2Val + carry
            
            this = summation % 10
            tail.next = ListNode(this)
            
            carry = summation // 10
            tail = tail.next
            
            #如果循环到尾巴了，则next 都是None， 来满足退出条件！
            l1 = l1.next if l1 else None
            l2 = l2.next if l2 else None
                
        return dummy.next
            
            
            
        