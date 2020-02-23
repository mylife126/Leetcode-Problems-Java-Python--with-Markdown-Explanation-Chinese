""" 
        1                        1 ->none
     2     3                 2 ----> 3 ->none
  4    5 6    7            4-->5 ->6--->7 -> none
  
题目要求将每一个node的nextpointer指向自己的右边那个node，如果没有右边的node则直接指向none

所以我们可以level order traversal。 
1。 建立Queue， 和 stack。  q先offer第一个root， stack则是在每一层layer 存入除了最右边那个node供下一时刻使用
2. bfs来traverse tree， 首先我们需要一个idx来记录现在pop出来的node是第几个
   然后存入此时的node进入stack，供下一个node当作右边的node去point to。 
   a。如果idx == 0， 则说明我们现在pop的是最右边的， 所以这个node的nextpointer 指向none
   b。其余的情况则是最右边之后的node， 这个时候我们就需要pop stack存入的node，也就是
   
   然后 idx ++， 从右到左的存入right child 和left child进入queue中即可
   
3.最后这个root返回即可，此时的root的指针都是全部fully pointed

"""
class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return root
        
        q = []
        q.append(root)
        while(q):
            size = len(q)
            idx = 0
            stack = []
            for i in range(size):
                node = q.pop(0)
                if idx == 0:
                    node.next = None
                    
                if len(stack) != 0:
                    last = stack.pop()
                    node.next = last
                        
                if node.right != None:
                    q.append(node.right)
                    
                if node.left != None:
                    q.append(node.left)
                idx += 1
                stack.append(node)
                
        return root
                    
            
            
        