'''
完整的binary tree的特征是除了leaf， 其余的必须有children
    1
  2    3
4  5  n  7      False

最后一层的none必须在最右边而不是中间


    1
  2   3
 5 n 7         False
 
 
   1
  2 3
 5 6          True
 
 
A 那么思路是我们知道每一层该有的nodes的个数，例如在第一层 是 2**1 = 2个， 且这些nodes在中间层不能是none。 
  那么我们可以每一层都存储一个变量来记录这一曾出现过的非none的nodes的counts， 最后判断counts是否等于 2**layer
 
B 同理我们也需要维护layer变量，每一层bfs结束后 layer++， init为0
 
C 但是我们发觉到了最后一层leaf，我们可以有none 但是none必须出现在整个leaf的最右边 也就是说 不可能存在 none之后出现了一个
  非none的情况！ 那么我们可以用一个array存储每一层pop出来的nodes然后 for loop检测是否array[i] == none and array[i + 1]不等于none。 

  最后什么情况下我们知道可以对上述的array进行检测呢，那就是到了最后一层， 最后一层用boolean 来记录。 我们知道最后一层的话，假设有
  3个非none的leafs， 那么他们就得出现 3 * 2 个nones。 所以我们还需要维护noneCounts， 如果noneCounts 在一层全部遍历完后 ==         
  counts * 2， 则说明我们到了最后一层了。

1. bfs记录第一个root
2. 进入bfs循环每一层， 维护每一层的:
    a. nodesArray： 记录每一层的所有nodes， 包括none的情况
    b。 counts： 记录这一层有多少个非none的nodes
    c。 noneCount： 记录这一次有多少个none的child nodes
    d。 每一层结束后，判断是否 counts == 2 ** layer
    e。 判断是否 noneCounts == 2 * counts  来看是否到了最后一层了
         如果是最后一层， 看nodesArray里有没有出现array[i] == none and array[i + 1] != none的情况
    f， layer+=1

'''

class Solution:
    def isCompleteTree(self, root: TreeNode) -> bool:
        if not root:
            return True
        
        q = []
        q.append(root)
        layer = 0 
        lastLayer = False   #维护是否到了最后一层
        while(q):
            #开始bfs每一层， 拿出这一层的所有nodes
            size = len(q)
            supposedLeaf = 2 ** layer    #计算这一层该有的所有非none的nodes的个数
            countNone = 0                #记录有多少个none的子nodes
            
            layersArray = []             #记录这一层里所有的nodes的情况，以便于最后到了最后一层判断是否满足最后一层的情况
            counts = 0                   #记录这一层里所有的非none的nodes， 可以对比supposedLeaf 来判断中间层是否满足
            for i in range(size):
                node = q.pop(0)          #拿出这一层的每一个node
                layersArray.append(node)
                if node == None:         #如果是none的话，它没有余下的操作
                    continue
                    
                counts += 1              #如果不是none 我们的非none的counts ++
                if node.left != None:    #放入子node
                    q.append(node.left)
                else:
                    q.append(None)       #如果是子node为none， 一样放入，且记录有几个子None nodes，这个可以方便查看是否最后一层
                    countNone +=1
                
                if node.right != None:
                    q.append(node.right)
                else:
                    q.append(None)
                    countNone +=1
                    
            if countNone == counts * 2: #如果是最后一层那么子none 的nodes的counts 一定等于这一层的所有非零nodes * 2
                lastLayer = True        #更新boolean
                    
            layer +=1                   #更新layer
            if supposedLeaf != counts and not lastLayer:  #对于非最后一层我们的对比操作只需要看 是否这一层非none的nodes
                return False                              #个数等于该有的个数即可
            
            if lastLayer:               #当到了最后一层，我们就得做查看none是否都在最右边的查看操作
                for i in range(len(layersArray) - 1):
                    if layersArray[i] == None and layersArray[i + 1]!= None:
                        return False
                    
        return True
    
    

                