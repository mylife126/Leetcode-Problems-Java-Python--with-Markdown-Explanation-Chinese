'''
主要思想是用priorityqueue来维护每次pop出来的node为最小值即可。

corner case：
   a。 [[]], 一个空集包含了一个none head。 

1. 判断edge case， 一个空的list， 直接返回none
2. 建立heap， 写一个wraper class，重载比对函数， 并且先将lists里所有的head放入heap中， 但是有一个edge case需要判断：
   a。 所以只有当head不为空的时候才把head放入heap中
3. 循环pq， 循环直到pq为空为止，在循环里不断pop最小的node，然后放入新建的dummyhead中
   然后再把这个pop出来的next放入pq里， 但是同样需要判断是否为none 不然会有null pointer issue
'''
import heapq
class cell(object):
    def __init__(self, listNode):
        self.cell = listNode
        self.val = self.cell.val
        self.next = self.cell.next 
    
    def __lt__(self, that):
        return self.val < that.val
    
class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        if not lists:
            return None
        
        pq = []
        for head in lists:
            if head != None:
                heapq.heappush(pq, cell(head))
            
        dummyHead = ListNode(-1)
        tail = dummyHead
        
        while(pq):
            smallNode = heapq.heappop(pq)
            tail.next = ListNode(smallNode.val)
            tail = tail.next
            if smallNode.next != None:
                heapq.heappush(pq, cell(smallNode.next))
            
        return dummyHead.next
        
        
        