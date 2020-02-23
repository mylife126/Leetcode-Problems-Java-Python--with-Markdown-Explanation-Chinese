'''
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL

Approach 1:
用stack的方法从头到尾append数值

然后从尾巴到头的pop

iterattive solution
'''

class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        
        if not head:
            return head
        
        stack = []
        dummyHead = ListNode(-1)
        tail = dummyHead
        
        while(head):
            stack.append(head)
            head = head.next
            
        while(stack):
            tail.next = ListNode(stack.pop().val)
            tail = tail.next
        
        return dummyHead.next
      
'''
better way solution


inplace修改，

reverse得逻辑是pointer得修改：
      1 -> 2 -> 3 -> 4 -> 5 ->
null<-1 <- 2 <- 3 <- 4 <- 5

所以我们需要记录当前node的prev, 把当前的next指向prev，temp当前等于当前的next， 而prev是当前，
iter    temp     curr  curr.next  =  prev   prevupdate     currupdate
0      1.next     1     ->           None      1              temp = 2
1      2.next     2     ->             1       2              temp = 3
2      3.next     3     ->             2       3              temp = 4
3      4.next     4     ->             3       4              temp = 5
4.     5.next     5     ->             4       5              temp = None
loop end

return prev: null<-1 <- 2 <- 3 <- 4 <- 5

....

最后return prev
 
'''
class Solution(object):
    def reverseList(self, head):
        curr = head
        prev = None
        
        while(curr != None):
            temp = curr.next
            curr.next = prev  #现在的node指向前面
            prev = curr       #而现在的node等同于下一刻的cur的prev
            curr = temp       #而下一刻的curr其实是先一颗的curr
        return prev