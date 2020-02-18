# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

'''
1 2 3 4 5 none
5 1 2 3 4 none
4 5 1 2 3 none

1 2 3 | 4 5 1 2 3 4 
如果将首尾相连接后， 在第k位的时候切断link，将3的next指向none即可

newHead在n - k处
所以分割tail在 n - k - 1

但是k需要取余n得到转了多少圈后落到的位置
'''

class Solution(object):
    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if not head or k == 0:
            return head
        
        dummyTail = head
        dummyHead = head
        
        #找到尾巴
        n = 1
        while(dummyTail.next != None):
            dummyTail = dummyTail.next
            n +=1
            
        #首尾相连
        dummyTail.next = dummyHead
        tail = dummyHead 
        count = 0 
        
        #注意这里我们是要找到分割之后的head在哪里， 那肯定就是 n - k
        #所以我们需要停止在tail，因为只有知道了tail我们才能 新创一个内存head，指向了tail.next ，然后再把tail.next 指向空。
        #所以分割点在n - k - 1
        #但是 k要是大于n了，怎么办，我们需要取余 k % n得到转了多少圈后落到的位置
        
        while(count < n - k % n - 1):
            tail = tail.next
            count += 1
        
        print(tail.val)
        newHead = tail.next
        tail.next = None
        
        return newHead