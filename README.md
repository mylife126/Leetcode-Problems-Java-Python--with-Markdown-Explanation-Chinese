# October 2nd Reviews

## 79 word Search
  Given a 2D board and a word, find if the word exists in the grid.
  The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once. 
  board =
  [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
  ]     

  ```python
  class Solution(object):
      def dfs(self,board,word,x,y):
          if board[x][y] == word[0]:
              #如果词语已经全部找完了
              if len (word[1::]) == 0:   #这里不能判断 len(word) == 0 因为此时已经太晚了，再上面的if                                  就会报错
                  return True
              #flag out the searched grid in this dfs loop
              board[x][y] = '#'  
              # word = word[1::] 不能这里就改变word 不然假设在第一层dfs word变成了ord，然后进入dfs的第一个if， ord 变成了 rd， 这个时候第一个if返回false， 就走到下一个if， 但这个时候判断的word 已经成了rd， 而不是ord
              #往下走， 如果能一直走下去 那就true
              if x < len(board) - 1 and self.dfs(board,word[1::], x + 1, y):
                  return True
              #如果往下走到某处失败了， 那就往上走，如果能一直往上走就true
              if x > 0 and self.dfs(board,word[1::], x - 1, y):
                  return True
              #如果上面还是走不通了， 就在上一个点位往右走， 直到能通
              if y < len(board[0]) - 1 and self.dfs(board,word[1::], x, y + 1):
                  return True
              #不然往左走
              if y > 0 and self.dfs(board, word[1::], x, y - 1):
                  return True
              #如果上面都走完了还是没有return True 那就是没戏了
              #所以这个地方的trigger的dfs是fake的，需要回复到以前的地方
              board[x][y] = word[0]
              return False
      #如果在某一次dfs node的地方没有找到word的第一个字 那就再这个方向上返回 然后换一个方向走
          else:
              return False 
          
      def exist(self, board, word):
          if not word:
              return True
          if not board:
              return False
          for i in range(len(board)):
              for j in range(len(board[0])):
                  if board[i][j] == word[0]:
                      #triggered once
                      print('tiggered once')
                      if self.dfs(board, word, i, j):
                          return True           
          return False
  ```

## 88 Merge sorted Array
```python
#two pointer approach
class Solution(object):
    def merge(self, nums1, m, nums2, n):
        '''注意以下问题！ num1_copy是num1的reference，这意味着 nus1[:]= []空后，num1_copy也         会跟着为空！
        num1_copy = nums1
        nums1[:] = []
        print(num1_copy)
        num1_copy[0]
        num2[0]'''
        #as the nums1 only contains number from 0 ~ m 
        num1_2 = nums1[:m]
        nums1[:] = []
        pointer1 = 0
        pointer2 = 0
        #1 2 2 4
        #2 2
        while pointer1 < m and pointer2 < n:
            if num1_2[pointer1] < nums2[pointer2]:
                nums1.append(num1_2[pointer1])
                pointer1 +=1 
            else:
                nums1.append(nums2[pointer2])
                pointer2 +=1
                
        #if the above loop finishes but one of the list is not looped yet
        if pointer1 < m: #meaning that the array 1 is always greater than array2
            nums1[(pointer1 + pointer2) : ] = num1_2[pointer1:]
        if pointer2 < n:
            nums1[(pointer1 + pointer2) : ] = nums2[pointer2:]
```

## 31 Next Permutation
主要思想： 12432 对于这个数来说，它的下一个更大的数肯定是 13224。 我们可以倒着看这个数字， 2 - 3 - 4 - 2 在到达最后这个2 pivot 的时候数字一直在上升，也就是说在2之后的数字都是下降的且都大于等于2这个数字，那么很明显如果要permutation使得下一个数字大于现在的12432， 肯定是有一个数字要和2交换，那么我们就找2之后的数字，最小程度上大于2即可，我们找到了3， 现在交替得到 13422， 但这个并不是最小的情况，我们还需要在pivot index后排序这之后的所有数组
```python
class Solution(object):
    def nextPermutation(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        #建立后续pointer
        lastPointer = len(nums) - 1
        
        #倒着遍历， 由于lastpointer 》 0， 所以我们取不到0， 也就不会担心 last - 1 
        while lastPointer > 0:
            currentLast = nums[lastPointer] 
            beforeIt = nums[lastPointer - 1]
            #如果从后往前看还是升序的 那我们就继续遍历
            if beforeIt >= currentLast:  #case [1,1,1] 这个时候是不该进入else判断的！
                lastPointer -= 1
            
            #直到找到了第一个pivot
            else:
                pivotPointer = lastPointer - 1#因为我们遍历的是last pointer，所以pivot -=1
                pivotValue = nums[pivotPointer] #取出这个pivotvalue
                #既然我们知道从后往前走是升序，我们要找最小大于pivotValue的值只需要倒叙查找
                for i in range(len(nums) - 1, pivotPointer,-1):
                    if nums[i] > pivotValue:
                        #swap
                        nums[pivotPointer] = nums[i]
                        nums[i] = pivotValue
                        #inline replacement， 在pivot之后的全部sort
                        nums_copy = sorted(nums[pivotPointer + 1::])
                        #将pivot后的数组重新赋值
                        nums[pivotPointer + 1 ::] = nums_copy 
                        return nums       
                    
        #如果while里找不到上述情况满足，我们就返回, 且返回的是原来的数组的，而不是reference
        #如果是 sorted(nums)就是新建了一个reference
        return nums.sort()
```
## 46 Permutations
给定一个数组，找到它所有的组合
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```python
class Solution(object):
    def __init__(self):
        self.ans= []
        self.hash = set()
def bt(self, nums, temp):
    if len(temp) == len(nums):
        self.ans.append(temp[::])
        #不可以self. ans. append(temp)， 因为temp是一个reference， 所以假设你现在
        #append temp， 但是在后面的递归返回的时候会清空temp里的所有数据
        return 
    
    for i in range(len(nums)):
        if nums[i] not in self.hash:
            temp.append(nums[i])
            self.hash.add(nums[i])
            self.bt(nums, temp)
            #once this layer's finished                
            self.hash.remove(nums[i])
            temp.remove(nums[i])
    return #if the for loop is also finished for one layer, return 

def permute(self,nums):
    temp = []
    self.bt(nums, temp)
    return self.ans
```
# October 2nd New Problem
## 609 Find duplicated files
主要思路就是利用hash， key就是content，而value则是所有重复过的path。 但这里的重点是string manipulation， 学会使用split 和 partition

Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
Output:  
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

```python
class Solution(object):
    def findDuplicate(self, paths):
        myHash = collections.defaultdict(list)
        for directory in paths:
            #we need to first split each directory based on space
            #this would give us something like ['root/a', '1.txt(abcd)', '2.txt(efgh)']
            data = directory.split(" ")
            root = data[0]
            for file in data[1:]:
                #this would give us a tuple.The partition() method splits the string at (
                #Thus this would give us 1.txt + （ + abcd)
                name,_,content = file.partition('(')
                #remove the ) of the content
                content = content[:-1] 
                myHash[content].append(root + '/' + name)
                
        returning = [validList for validList in myHash.values() if len(validList)>1]
        return returning 
```

## 9 Palindrome int
given an int, and decide if it is palindrome or not
```python
class Solution(object):
    def reverse_int (self,x):
        temp = 0
        strX = str(x)
        strX_copy = strX[:]
        strX =" ".join(strX).split()
        start = 0
        end  = len(strX) - 1
        while start <= end:
            strX[start], strX[end] = strX[end], strX[start]
            end -=1
            start +=1
        return ''.join(strX), strX_copy
        
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """ 
        rev, original = self.reverse_int(x)
        if rev == original:
            return True
        return False
    
```
## 283 remove zeros
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements. 
Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

```python
'''priorityQueue的思想，遇到0了我就记录一次0出现的位置，如果不是零且此刻queue是有0存在的，那我就把最开始的0的位置给这个数，而queue里重新记录此刻i的位置来作为更新后的0的位置。 这样就可以保证每次有新的非零数可以优先加到最前面的位置'''
import Queue as Q
class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        q = Q.PriorityQueue()
        for i in range(len(nums)):
            if nums[i] == 0:
                q.put(i)
            if nums[i] != 0 and q.qsize() != 0:
                whereIsZero = q.get()
                nums[whereIsZero] = nums[i]
                q.put(i)

        nums[len(nums) - q.qsize() : ] = [0] * q.qsize()

#a python way in line replacement
class Solution(object):
    def moveZeroes(self, nums):
        countZeros = nums.count(0)
        print(countZeros)
        nums[:] = [i for i in nums if i != 0]
        nums += [0] * countZeros
```
# October 3rd Review
## 973 K closet points to origin
这道题原来自己的思想是用priority queue存储所有distance，而hash的key是distance， value是一个list来存贮该distance下的所有index。 但是其实最快的方案是利用sort（distance）取得kth value后再一一遍历points里的数据并且做对比找到所有value。
举个例子： [1,3,2,4] 假设我的k是2，那很明显现在的kth largest就是2了，所以我们便利的时候只需要对比和2的大小即可以
```python
import Queue as Q
import math
class Solution(object):
    def kClosest(self, points, K):
        """
        :type points: List[List[int]]
        :type K: int
        :rtype: List[List[int]]
        """
        myHash = {}
        pQ =Q.PriorityQueue()   #q 的value是hash的key， 然后key的value是list index
        for i in range(len(points)):
            distance = math.sqrt((points[i][0]**2 + points[i][1]**2))
            pQ.put(distance)
            myHash.setdefault(distance, []).append(i) #为了防止同样的距离下，hash的key只能存最近一次，所以我们要存一个list出来 
        ans = []
        count = 1
        while count <= K:
            smallDistance = pQ.get()
            thisIndexList = myHash[smallDistance]
            for i in thisIndexList:
                ans.append(points[i])
                count+=1
        return ans
            
class Solution(object):
#     def get_distance(self, points):
#         return points[0]**2 + points[1]**2
    
    def kClosest(self, points, K):
        #first of all let us get the distances first
        distances = [self.get_distance(x) for x in points]
        print(distances)
        #not we can sort it out 
        sortedDistance = sorted(distances)
        print(sortedDistance)
        
        #now since we want the top K we can get the Kth largest value
        kThLargest = sortedDistance[K - 1]
        ans = [] 
        
        #由于我们已经sort过原数组的distance了，这个时候取Kth，并作为比对一定能找到所有的index
        for i in range(len(points)):
            # print(i)
            if self.get_distance(points[i]) <= kThLargest:
                # print(i)
                ans.append(points[i])
        return ans
        
        #内置函数更快捷
        points.sort(key = lambda P: P[0]**2 + P[1]**2)
        return points[:K]
```
## 91 decode ways
对于一个字符串 1234 能有几个解读的方案， 1 2 3 4, 12 3 4, .... 那么我们看对于 123来说 我们可以是 1+ find(23) or 12 + find(3). 所以我们可以采取递归的方案，而同时我们可以记录在given string长度X下它的result的解有几个 对于:
                              123
           1 w/find(23)    +     12 w/ find(3)  
  2w/find(3) + 23w/find('')          return 1
    return1       return 1
  memo[1]=1       memo[0]=1

所以可见在上面 我们右边树的find（3）可以直接return 左侧树出现过的情况。 
```python
#approach 1, recurrsion. Thinking:two cases:
#1. 126 = 1 + find(26), =》 26 = 2 + find(6) or find(26)
#                           -> 1  +            ->1
#2. 126 = 12 + find(6),    -> 1
#total 3 solutions
#recussion version1
class Solution(object):
    def helper(self, string, memo):
        #define the base case
        if len(string) == 0:  #if we have an empty string, there is only one solution 
            return 1
        if string[0] == '0':  #if the string's first element is 0 then there is no solutio 
            return 0
        #如果中期这个subtring在某一步算过一次了，那么可以直接return它所在的solution
        if memo[len(string)] != None:
            return memo[len(string)]
        
        #recurssion part
        #case 1 we want to find all single digit, once all the 
        result = self.helper(string[1::],memo)
        
        #case 2, current string could be larger than 2, and the top 2 digits are valid as a whole
        #if the condition is met, then we can reparse the substring into it to find if the substring has             #valid cases like this case
        if len(string) >= 2 and int(string[0:2]) <= 26 :
            result += self.helper(string[2::], memo)  
        
        #当这一中期的string的result算出来后 需要记录一下这个中期substring存在的解
        memo[len(string)] = result 
        return result   
    
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        memo = [None] * (len(s) + 1)
        return self.helper(s,memo)
       
#recurrsion version 2
#想法是一样的，除了可以直接parse string来体现我们在不断比对one digit 和 two digits的情况，我们可以用N来记录此刻string的长度，也就是substring被parse后的长度。 但是我们需要计算一次此刻的starting point = totalLen - N
#也就是说，初始的时候长度就是len， 然后遇到case1的时候n = 3， 遇到case2 的时候n = 2
# class Solution(object):
#     def helper(self,string, n):
#         if n == 0:
#             return 1
#         if string[len(string) - n] == '0':
#             return 0
#         currentLocation = len(string) - n
#         result = self.helper(string, n - 1) #往后找一位的substring
#         if n >=2 and int(string[currentLocation: currentLocation + 2]) <= 26 :
#             result += self.helper(string, n - 2)
#         return result 
    
#     def numDecodings(self,s):
#         return self.helper(s, len(s))
```

## 54 Spiral Matrix
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

那么可以见的我们每次需要直接取出第一row，然后在其余的row里取最后一位数（pop）到了底部后我们需要倒着取出所有row， 然后向上取出所有第一位数， 最后就是下一次循环。
```python
class Solution(object):
    def spiralOrder(self,matrix):
        ans = []
        while matrix:
            #取第一row
            ans += matrix.pop(0)

            #取所有剩余row里的最后一位数
            if matrix and matrix[0]:
                for row in matrix:
                    ans.append(row.pop())

            #取最后一row的所有数但是倒着取
            if matrix:
                lastRow = matrix.pop()
                for digit in lastRow[::-1]:
                    ans.append(digit)

            #取剩余row里的所有第一位数
            if matrix and matrix[0]:
                for row in matrix[::-1]:
                    ans.append(row.pop(0))

        return ans
```
# October 3rd New Questions Below are all DP 

## 139 word break
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
我们可以这么想用dp的思想

1. state function: @boolean dp[n + 1] 这面的i对应的是长度为1，2，3，4，5的时候的state如何
2. init: dp[0] 当字符为零长度的时候它的状态
3. transfer function： 假设我们的string现在i到了6， 也就是'leetco'如何判断dp[6]的情况呢，那就是判断'leetc'（dp[5]）以及'o'是否在dictionary， 然后再判断 'leet' (dp[4])和'co'的情况。 所以传递函数就是 dp[i] = {dp[i - j] | sub(i-j : i)}
4. return： dp[n+1]
```python
class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        if not s:
            return True

        #建立dp array，0 1 2 3...n 代表的是在substring长度为 1 2 3 4 是否为breakable的情况
        #True of False
        #所以最后我们只需要检测 dp[n]的true false情况
        dp = [0] * (len(s) + 1)
        
        #init
        dp[0] = True #当字符为空的时候那一定是可以被breakable的
        
        #get the dictionary
        myHash = set()
        for letter in wordDict:
            myHash.add(letter)
        
        #开始dp循环
        for i in range(len(s) + 1): #这里注意，我们要循环到 n + 1项
            for j in range(i,-1,-1):
                print(j)
                #我们可以优先看一次全部的subtring = s【0：i】是否成立
                #如果不行我们的j就会不断减小，那substring就变成了 string[i-1: i]
                lastSubString = s[i - j: i]   
                #开始判断我们这个第ith string的末尾几个substring是不是在字典里
                #如果在，那么它之前的string是不是为true
                if lastSubString in myHash and dp[i - j] == True:
                    dp[i] = True
                    break
        return dp[-1]
    
class Solution(object):
    def wordBreak(self, s, wordDict):
        #state function, it defines whether at length of i, this substring is breakable
        #meaning if at length i the string is "leet" and it is breakable, then it is T
        #finally, we need to see at the length of len(s) is T or F
        dp = [0] * (len(s) + 1) 
        
        #init, at the length of 0, the substring of "" is definitely a segment
        dp[0] = True
        
        #get a hash to store all the word
        myHash = set()
        for word in wordDict:
            myHash.add(word)
            
        #transfer function: at length X, the string is leetco, 
        #the dp[X] = {substring_j in wordDict | substring_(X - j) in wordDict}
        for i in range(len(s) + 1):
            #目的是每次向前倒着取两位，然后检测这两位是不是在hash里，
            #且在它之前的string是不是可以breakable
            for j in range(1, i + 1):
                # thisString = s[:i]
                lastString = s[:i][-j::]             #i = 4, j = 3
                if lastString in myHash and dp[i - j]:
                    dp[i] = True        
        return dp[len(s)]
```
## 322 Coin Change
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
我们可以这么想：
1. states: int dp[amount + 1] 每一个i对应的是 i amount需要的最小coin的个数
2. init： dp = inf， 因为一开始我们不知道对于每个amount它真的需要多少， 而dp[0] =0 因为当coin的价格为0的时候我们是不需要coin的
3. transfer function：当我们的amount 为 n的时候它的最小情况一定是要么挑选此刻这个coin和挑选了这个coin后remaining coins的需要的个数  =》 1 + amount(remaining)，而remaining = amountN - coin_j， 要么就是此刻挑选该coin的时候，在上一刻这个amount已经有过一次计算了，且那一次需要的个数更少， 所以 dp[amount] = MIN{1 + dp[remaining_j]， dp[amount]}
4. return: dp[amount]
5. Thus this is the bottom up problem where we start from the smallest sub- problem all the way up
```python
class Solution(object):
    def coinChange(self, coins, amount):
        #首先构建dp array， 每一个index i 代表i个amount需要的最小的coins的个数
        dp = [amount + 1] * (amount + 1)
        
        #init dp， 因为 当amount = 0时，我们需要的coin个数也是0
        dp[0] = 0
        
        #transfer function: 在对于i个amount的时候我们有 n 个选择， n 取决于coins的个数，
        #那么对于当下的amount i 它的最小挑选每次都是 拣去 1 个现在的coin 并且加上 对于
        #remaining amounts所对应的个数， 且我们得保证每一个time span都是最优解，那么，
        #我们就得 拿 dp[amount], 1 + dp[amount - 1]来对比，因为很有可能选取这个coin的时候
        #我们之前对于这个amount有过别的选取的combination 而那个combination下 需要的个数最小
        for thisAmount in range(1, amount + 1):
            for coinToPick in coins:
                if coinToPick <= thisAmount:
                    remainedAmount = thisAmount - coinToPick
                    dp[thisAmount] = min(dp[thisAmount], 1 + dp[remainedAmount])
                    
        return dp[amount] if dp[amount] < (amount + 1) else -1
```
# October 8th New Problem

## 70 climbing stairs
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.

1. 1 step + 1 step + 1 step

2. 1 step + 2 steps

3. 2 steps + 1 step
    题目要求一个人只能要么 走一步 要么就是走两步， 给定一个目标步数， 问你有几种走法。可以这么想假设我们的目标是：
    **1：** 1， **2：**（1 + 1） or （2） = 2， **3：** 1 + 1 + 1 or 1 + 2 or  2 +1 = 3， 所以可以见，以 3 为例子， 如果第一步走了1， 那么还剩下两步， 对于剩下来的两步是不是就基于2的可能性了呢， 同理如果我们走了2步，那就还剩下1步所以对于剩余的1步我们就需要找到1对应的可能性。

  - state function : int[] dp = new int[size + 1], 这里表述的是从 0 ~ n步所需要的可能性
  - init state function: dp[0], dp[1] = 1， 因为对于零步和一步的可能性我们知道就是 1 种
  - Transfer function： 从两步开始，dp[now] = {dp[now - 1] + dp[now - 2] | now >= 2}  

  ```python
  class Solution(object):
      def climbStairs(self, n):
          """
          :type n: int
          :rtype: int
          """
          #define the dp array
          dp = [0] * (n + 1)
          
          #init the dp array
          dp[0], dp[1] = 1, 1
          
          #define the transfer function, that is if the current step is greater than 1, 
          #then we have 2 options: 
          #1. if make 1 step, then we have to make the dp[step - 1]ways for the remained
          #2. if we could make 2 step at this time, then we have to make the dp[n-2] for the remained
          if not n or n ==0 or n == 1:
              return dp[-1]
          
          for step in range(2, n + 1):
              #make 1 step
              remainedStep = step - 1
              dp[step] = dp[remainedStep]
              if step >=2:
                  remainedStep2 = step - 2
                  dp[step] += dp[remainedStep2]
              
          print(dp)
          return dp[-1]
  ```

  # October 11 New Problem

## 221 Maximal Square
Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
思路是， 我们treat每一个grid当作一个正方形的右下角，但是该右下角的正方形能组成最大的size是关乎于他的三个另外的corner，且是另外三个corner各自代表的size的最小值
1： state funciton: dp[m][n] 每一个grid代表的是这个grid作为一个正方形的右下角他能组成的正方形的最大size
2. init, 第一个row 和 第一个col都是1 or 0， 因为作为edge 它没法组成正方形 or 只能组成一个size为1的
3： transfer function: dp[i] = {min(dp[up]，dp[left], dp[upLeft])  + 1 | i == "1" }
```python
class Solution(object):
    def maximalSquare(self, matrix):
        """
        :type matrix: List[List[str]]
        :rtype: int
        """
        if not matrix:
            return 0
        #first step is to create the state function
        #the state function is just treat every grid as the right bottom corner of a square, then the 
        #grid[i][j] is the counts of how large this right bottom could be
        dp = matrix[::][::] #本因该是 建立一个空的 matrix 并且init 第一row 和第一col都为1or0，但可以inplace
        
        #由于我们要skip第一行和第一row， 所以我们需要init maxSize
        maxCol = max([int(i) for i in matrix[0]])
        maxRow = max([int(row[0]) for row in matrix])
        maxSize = max(maxCol, maxRow)
        
        #Transfer Function
        #start the dp, the logic is that for each grid as the right bottom, the largest size of square
        #it could be is the min(top, up, topup) + 1 if it is 1
        for i in range(1, len(matrix)):
            for j in range(1, len(matrix[0])):
                if matrix[i][j] == '1':
                    top = matrix[i-1][j]
                    left = matrix[i][j - 1]
                    topLeft = matrix[i - 1][j - 1]
                    
                    dp[i][j] = min(int(top), int(left), int(topLeft)) + 1
                    maxSize = max(dp[i][j] , maxSize)

        return maxSize**2
```
```java
public class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}
```

## 152 Maximum product subarray
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6
'''思路是， 我们维护三个变量， 一个是 localMax, 一个是localMin， 最后是globalMax。 由于乘法的缘故，很可能此刻的最小值（可能为负数）在下一刻会成为最大值。所以这样思路就和max sum subarray类似， ‘如果之前的乘机乘以现在的还不如现在的大，那此刻就不在需要之前的信息了’ '''

```python
class Solution(object):
    def maxProduct(self,nums):
        if len(nums) == 1:
            return nums[-1]
        
        #init the dp
        currentMax = nums[0]
        currentMin = nums[0]
        productMax = nums[0]
        
        
        for i in range(1, len(nums)):
            currentDigit = nums[i]
            productWithMax = currentMax * currentDigit
            productWithMin = currentMin * currentDigit            
            #update the localMax and localMin and productMax
            currentMax = max(currentDigit, productWithMax, productWithMin)
            currentMin = min(currentDigit, productWithMax, productWithMin)            
            productMax = max(productMax, currentMax)
            
        return productMax 
```

# October 12 New Problem

## house robber
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
思路是：

1. state function dp[len(nums) + 1], 每一个位置代表的是在这个house的位置所能抢的最大价值
2. init, 对于house 0 来说， 由于没有这个house， 我们的dp[0]就是0
3. transfer function: dp[i] = max(dp[i - 1], dp[i-2] + itself) 因为我们不能连续抢房子， 那么我们就看 前两个房子下的最大值加上此刻值 是否 比前一个房子下的最大值大。 这就保证了隔位相加
```python
class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        #the dp represents at ith house, the largest amount it could be
        dp = [0 for i in range(len(nums) + 1)]
        
        #init, as if we steal the house 0, the amount is definitly zero
        dp[0] = 0 
        maxSteal = 0
        #the transfer function is dp[i] = max(dp[i - 2]+ itself, dp[i - 1])
        for i in range(1, len(nums) + 1):
            dp[i] = max(dp[i - 2] + nums[i - 1], dp[i - 1])
            maxSteal = max(maxSteal, dp[i])
            
        return maxSteal
```

# October 14th Review
## 256 paint house
有n个屋子 （n rows），每个屋子有三个颜色的选项 （3 j）， 问你n个屋子刷完总价值最低，且要保证两个连接的屋子颜色不同。
house1 [[17,**2**,17],
house2 [16,16,**5**],
house3 [14,**3**,19]]  答案是10. 

思想是建立一个dp = int[3]， 每一个dp代表的是刷到这个屋子的时候，r g b这三个选项下各个价格和

```python
class Solution(object):
    def minCost(self, costs):
        #defensive programming, if there is no costs, then the total cost is just zero
        if not costs:
            return 0
        
        #state function is: dp = int[3], 指的是在此刻 第ith个house的时候 每一个 R G B下的价格         总和
        dp = [0 for i in range(len(costs[0]))]
        
        #init the function: 对于第一个房子来说，也就是循环0th的时候，那第一个房子就是有 R G B这        三个选择，且
        #此刻的价格就是costs的第一row
        dp = costs[0]
        
        #transfer function: dp[0] = costRed + min(dp_last[1], dp_last[2]) 是说，粉刷到下一个          房子的时候，
        #下一个房子依旧是有3个颜色的选择，那么对于red的话它此刻的价格总和应该是自己加上上个state的         dp里的g和b中最小
        for i in range(1, len(costs)):
            #since we are using the 1D array, we need to make a copy of last dp states
            dpLast = dp[::]
            #更新 此刻的价格总和
            dp[0] = costs[i][0] + min(dpLast[1], dpLast[2])
            dp[1] = costs[i][1] + min(dpLast[0], dpLast[2])
            dp[2] = costs[i][2] + min(dpLast[0], dpLast[1])
            
        #当循环到最后一个屋子刷完了，我们的dp已经是三个房子的不同颜色组合下的总加和，返回最小就可
        return min(dp)
```

# October 15 New Problem 
## 276 Paint fence
现在n个fence和k种颜色， 不能超过两个的颜色是一样的， 问总共多少种刷法。 
想法是， 对于 fence只有1的时候， 我们肯定就是k 种。
对于 fence有两个的时候， 我们可以有两个选择， 一个是 连续两个颜色一样 或者 两个颜色不同。 那么 就是 k and k*(k -1)
现在主要是3个屋子或者过多的屋子的情况：

- 和上一个state相同， 那只能是基于上一个state是different color
	
	- same_now = different * 1 (既然是相同的颜色，那此刻只有一个选择)	
	
- 和上一个state不同，那上一个state可以是different 或者 same
	
	- different_now = (different_last + same_last) * (k -1)
	
	```java
	class Solution {
	    public int numWays(int n, int k) {
	        if (k == 0){
	            return 0;
	        }
	        if (n == 0) {
	            return 0;
	        }
	        if (n == 1) {
	            return k;
	        }
	        if (n == 2) {
	            return k + (k * (k -1));
	        }
	        int same = k;
	        int different = k * (k - 1);
	        for (int i = 3; i <= n; i++) {
	            System.out.print("HERE");
	            int diffPrev = different;
	            different = (same + different) * (k  - 1);
	            same = diffPrev * (1);
	        }
	        return same + different; 
	    }
	}
	```
	
## 64. Minimum path sum
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

1. state function: 2D dp, 每一个grid代表的是走到当前位置的最小步数
2. init， 第一个row就是从左加到右边，因为第一个row没法走别的方向。 第一个column也是如此
3. transfer function: dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + grid[i][j] .因为既然我只能从上面走下来，或者从左走过来，那就是找两者间的最小就可以
```java
class Solution {
    public int minPathSum(int[][] grid) {
        //the dp function is just m by n grid
        //each grid represents at this grid the smallest move
        int[][] dp = new int[grid.length][grid[0].length];
        //init the dp, apparently, the first row, it could only moves to the right
        //and the first column, it could only moves down
        dp[0][0] = grid[0][0];
        //init the 1st column
        for (int i = 1; i < grid.length; i++) {
            int[] currentRow = dp[i];
            currentRow[0] = dp[i - 1][0] + grid[i][0];
        }
        //init the 1st row
        for (int i = 1; i < grid[0].length; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        //transfer function is that, at this grid, the last step could either moves from top or moves from
        //left, and we want the min
        for (int i = 1; i < grid.length; i++){
            for (int j = 1; j < grid[0].length; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];   
    }
}
```

## 300 Longest Increasing Subsequence IMORTANT!!!!

思路是，检测以该数字为结尾的能构成最长的子串。 50 90 3 4 5 6 7， 假设我们知道了6这一位是长度为4， 那么到了7后 我们发觉 7 > 6， 那么是不是可以说 7对应了 dp[6] + 1呢
1. dp function, 对应的是在ith number的时候， 如果以他为结尾，能构成最长的长度
2. init， 每一个数位自己就是1， 自己构成字串
3. transfer function: dp[i] = max(dp[i], dp[j] + 1) | for all j < i iff num[i] > num[j] 说的是 既然这一位大于了j位，所以必然可以在j那一位的基础上加1. 但我们不知道哪一位dp[j] 是最大的 所以需要把所有j遍历完后比对大小
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        //defensive programming
        if (nums == null || nums.length == 0){
            return 0;
        }
        //build the dp array, it is the length + 1, and each represents the length of the max 
        //subsequence if we use ith digit ad the ending digit  
        int[] dp = new int[nums.length];       
        //init the dp; as we want to start the index from 1, we do not care about the index 0
        //and at first, each digit is itself as a length of 1
        dp[0] = 1;
        //init the global max
        int globalMax = 1;
        //transfer function: dp[i] = {max(dp[j] + 1, dp[i]) | j < i && nums[i] > nums[j]}
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j= 0; j < i; j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            globalMax = Math.max(globalMax, dp[i]);
                
            }
        }
        return globalMax;
    }
}
```

## 904 Fruit into baskets
一组数[1,2,3,3,1,2] 每一个数位代表一种水果， 一个basket只能装2种不同水果，问你怎么装能装最多个水果。 上面例子, 2 3 3;  3 3 1 都可以。 
那么这个思路是怎么判断篮子里只有两种水果呢，那就是用hash来记录每次扫过的数字，它作为key， 而value就是这个数字出现的次数。 

那么怎么记录篮子里的东西呢，或者说用什么来记录篮子里总共几个水果了并且当多出来一种水果我们要暂时抛弃篮子里之前的水果来维持两种水果这一个设定呢？ 我们可以用sliding window， 
1        1        2         3
left                      right  此刻明显多了一种水果，那么我们可以把left 移动到2来保证篮子里的东西是两种水果，且还能记录此刻的篮子里的长度（大小）， 因为我们知道left 和 right的 pointer的位置

那么我们的1移动几次呢？ 这个时候count就有用了， 1出现了两次，所以left ++ ++， 同时从hash里删掉1. 

但要注意的是，我们应该每次判断一次此刻 left下的该数位的次数，如果 次数 == 1了， 那就说明这一次循环内它可以被删掉了， 不然就是相应的减去1， 然后left ++。 这么做是为了避免间隔问题：

1 2 1 1 1 2 3， 可见我们必须对2也进行一次减少来使得left成功指向2.

1 2 1 1 1 3 呢，我们不仅要抛弃一个1 还要抛弃一个2. 所以最好的方案就是在while （hashSize > 2）的时候每次循环都判断一次该数位下的出现的次数来确保每个数位都被考虑到了。

```java
class Solution {
    public int totalFruit(int[] tree) {
        //the hash would record each integer occurance frequency
        //and once the hash size is over 2, that just means now we have more than 2 types
        Map<Integer, Integer> myHash = new HashMap<>();
        //sliding window needs leftpointer and rightpointer
        int left = 0;
        int globalMax = 0;
        //moving the right pointer 
        for (int right = 0; right < tree.length; right++){
            //now we keep adding the current
            int currentFruit = tree[right];
            //if we have this fruit already, then we just simply get its previous value and add 1
            //else, we put a new value into it
            myHash.put(currentFruit, myHash.getOrDefault(currentFruit, 0) + 1);
            //now we have to deal with the case where we find more than two fruits
            while (myHash.size() > 2){
                //当我们删的该种类的水果只剩下1歌的时候，就可以把它删掉， 然后再后面left ++
                if (myHash.get(tree[left]) == 1){
                    myHash.remove(tree[left]);
                } else {
                    myHash.put(tree[left] , myHash.get(tree[left]) - 1);
                }
                left++;
            }
            globalMax = Math.max(globalMax, right - left + 1);
        }
        return globalMax;  
    }
}
```

## 96 Unique Binary Search Tree
Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

"用dp的思想是，一次增加n， 当n =3 的时候 我们分类讨论  root = 1， 2， 3 的时候每个root所能对应的节点情况。Hint是tree的左边一定是小的，右边是大的，所以对于1作为root的话，它只可能是右边加nodes，对于2呢它可以左边加（1）个节点 右边加（1）个节点,对于3呢，它只能左边加（2）个节点。 然后呢 这个时候dp[3]  = dp[0] * dp[2]  + dp[1]*dp[1] + dp[2] * dp[0]。 那么对于n来说，左右两边nodes的规律是， 左： 0 ~ n-1, 右： n-1 ~ 0"

1. state function dp = int[n + 1] 每一个位置代表有i个roots的选项的时候有几个structure

2. init： dp[0] = dp [1] =1

3. transfer function: dp[n] = sum(dp[j] * dp[n - 1 - j]) for j <= n - 1 

4. return dp[n]

   ```java
   class Solution {
       public int numTrees(int n) {
           //the dp function is at i roots, how many different structures there could be
           int[] dp = new int[n + 1];
           //init the dp. As for root = 0 and root = 1, there is only one structure
           dp[0] = 1;
           dp[1] = 1;
           //transfer function : for n , dp[n] = sum(dp[j] * dp[n - j - 1]) for all j < n - 1.Because, even
           //though we have n numbers to be used as roots, if take out one as root, only n - 1 options left
           for (int nRoots = 2; nRoots <= n; nRoots++) {
               dp[nRoots] = 0;
               for (int j = 0; j < nRoots; j++) {
                   //as j maximumly == nroots - 1, and thus, by j < nRoots, the last one is nRoots - 1.
                   dp[nRoots] += dp[j] * dp[nRoots - j - 1];
               }
           }
           return dp[n];
       }
   }
   ```

## 62. Unique Paths. 
### similar to 64. Minimum path sum

一个机器人在起始点 要到 m X n的最末点，问有几个走法。 思想就是既然机器人只能从上面下来或者从左边过来，那么肯定这个ith点位的走法就是基于 它上面那个走法 和 左边那个走法的加和

```java
class Solution {
    public int uniquePaths(int m, int n) {
        //the state function is dp m by n, and each represents at this grid, how many paths there could be
        int[][] dp = new int[m][n];
        
        //init the dp, as for the first row, we could only move from left to right, thus for all the 
        //elements in the first row is 1. Similarly, all the columns are 1
        for (int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        
        for (int i = 0; i < m; i++){
            dp[i][0] = 1; 
        }
        
        //transfer function: dp[i][j] = sum(dp[i-1][j] + dp[i][j-1]) 
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
```

# October 17th BFS

## 863 All nodes with distance in K

核心思想是：对于一个tree作为有方向的图的话，我们自然处理只能得到下一层的left 和 right， 但是我们需要往回走的情况，那就得把这个图变成一个无方向性的， 所以我们可以用hash来存每一个node 对应的 parent， 这样我们在后期bfs的时候就可以access到某一层的某一个node的parent。 Queue的作用是处理某一层的所有nodes，例如第1层只有2个node， 那么while(queue.size()!= 0) 我们就可以pop这个node， 
                    node1              node2
             node3         node4    node5      node6
Q[node1, node2] 然后呢 把这个层两个node一次pop出来，并且在queue里加上他们下一层的子node. 那么当这一层的两个node结束了， 我们Q依旧size依旧不为零 所以就会处理第二层。
Q[node3, node4, node5, node6]...循环直到最后一层 全部 pop后就结束了搜索

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private void populateParentFromTree(TreeNode root, TreeNode parentNode, Map<TreeNode, TreeNode> hashTable){
        //for this recussion, we define our base case if when the root is null, that means this leaf is over
        if (root == null){
            return;
        }
        //if this layer is not null yet, let us add the root(leaf) : parentNode
        hashTable.put(root, parentNode);
        //reassign the parentNode to current root(leaf)
        parentNode = root;
        //then we recusive on all the left leaf first
        populateParentFromTree(root.left, parentNode, hashTable);
        //then we recursive on all the right leaf
        populateParentFromTree(root.right, parentNode, hashTable);
    }
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        //first, we need to create a hash set so that we could save each node as the key, and their parent
        //is the value, and during the bfs we could access each's parent
        Map<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
        
        //populate all the child - parent to the hashset
        //init the map with all the child - parent map
        populateParentFromTree(root, null, parentMap);

        //we also need a queue to perform the bfs so that we could save all the nodes at each layer
        Queue<TreeNode> queue  = new LinkedList<TreeNode>();
        queue.add(target);
        
        //now we have to define another set to locate whether this node has been visited or not
        Set<TreeNode> seen = new HashSet<TreeNode>();
        seen.add(target);

        int currentLayer = 0;
        while (!queue.isEmpty()) {
            if (currentLayer == K) {
                //if we reached the counts, we should return an array cotains all the nodes' values in the queue
                List<Integer> returnArray = new ArrayList<Integer>();
                int qSize = queue.size();
                for (int i = 0; i < qSize; i++){
                    TreeNode thisNode = queue.poll();
                    returnArray.add(thisNode.val);
                }
                return returnArray;
            }
            //now we need to perform bfs, the key is to process all the nodes in the queue at this layer
            int qSize = queue.size();
            //search all the nodes in this layer
            for (int i = 0; i < qSize; i++){
                TreeNode thisLayerNode = queue.poll(); 
                //search the left leaf of this node at this layer
                if (thisLayerNode.left !=null && !seen.contains(thisLayerNode.left)){
                    seen.add(thisLayerNode.left);
                    queue.offer(thisLayerNode.left);
                }
                //search the right leaf of this node at this layer
                if (thisLayerNode.right != null && !seen.contains(thisLayerNode.right)){
                    seen.add(thisLayerNode.right);
                    queue.offer(thisLayerNode.right);
                }
                //search the parent node of this node at this layer
                if (parentMap.get(thisLayerNode) != null && !seen.contains(parentMap.get(thisLayerNode))){
                    seen.add(parentMap.get(thisLayerNode));
                    queue.offer(parentMap.get(thisLayerNode));
                }
            }
            //now once this layer is done 
            currentLayer++;            
        }
        //if after performing our bfs, there is no such solution we return an empty list
        return new ArrayList<Integer>();  
    }
}
```

# October 18 New Problem DFS
## 394 Decode String
s = "3[	]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

```java
public class Solution {
    public String decodeString(String s) {
        //我们需要一个stack同时又能存char 又能存 integer type。 所以二者都是object type
        Stack<Object> stack = new Stack<Object>();
        
        //方便检索每一个char
        char[] charArray = s.toCharArray();
        
        //init 一个数字的初始值
        int count = 0;
        for (char c : charArray){
            //case 1, we meet the number
            if (Character.isDigit(c)){
                int thisValueReading = c - '0';
                count = 10 * count + thisValueReading;
            //case 2, we meet the [   =   entrance meaning that the number is finished
            } else if ( c == '[') {
                //convert the int primitive type into an object type of Integer
                Integer savingNum = Integer.valueOf(count);
                stack.push(savingNum);
                
                //reset the number back to 0
                count = 0;
            //case 3, we meet the ] , we need to pop all the chars within this [] and combine to string and reput stack
            } else if ( c == ']') {
                String combinedChar = dfs(stack);
                Integer cnt = (Integer) stack.pop();
                for (int i= 0; i < cnt ; i++){
                    stack.push(combinedChar);  
                }
                
            //case 4, we only find another char, just push it to the stack
            } else {
                //注意这里需要把char变成 string 因为我们的stack里只需要 interger and string type
                String singleStr = String.valueOf(c);
                stack.push(singleStr);
            }
        }
        //now stack only contains all combined string with occurance repectively
        return dfs(stack);        
    }
        //define our dfs function
    private String dfs(Stack<Object> stack){
        //还需要一个stack来暂时存储原来的stack里pop的东西 在后期反向pop
        Stack<String> temp = new Stack<String>();
        StringBuilder sb = new StringBuilder();
        //因为我们的stack里有Integer 也有 String，且integer出现代表了一个[]的结束， 所以我们要把这里的char全部取出来
        while (!stack.isEmpty() && stack.peek() instanceof String) {
            temp.push((String) stack.pop());
        }
        while (!temp.isEmpty()){
            sb.append(temp.pop());
        }
        return sb.toString();
    }
}
```



# October 19 Reviews and New Problems
## 547 Friend Circle
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
思想是，每一个row代表了一个人， 而每个column代表了这个人的朋友，所以我们应当按照人来做dfs的检索他的所有朋友， 而在dfs里我们对于朋友检索的时候就会同时检索到朋友的朋友。

```java
class Solution {
    //create a hashset so that we know if this person has been seen or not
    private HashSet<Integer> seen = new HashSet<Integer>();
    
    private void dfsSearch(int whichPerson, int[][] matrix){
        //find how many friends this person has
        int nFriends = matrix[whichPerson].length;
        //loop through all his friends 
        for (int j = 0; j < nFriends; j++){
            int thisFriend = j;
            int hasFriend = matrix[whichPerson][j];
       
            //if we have not search his friend yet, and this is a friend, let us find this              friend's friend as well
            //because his friend is also this person's circle
            if (!seen.contains(thisFriend) && hasFriend == 1){
                seen.add(j);
                dfsSearch(thisFriend, matrix);
            }
        }
    }
    public int findCircleNum(int[][] M) {
        
        //find how many hiself first, as each row represents one person, and column                  represents the friends of him
        int nSelfs = M.length;
        //the initial friend circle is 0;
        int countCircle = 0;
        for (int i = 0 ; i< nSelfs; i++){
            //if we have not find this person's frineds yet
            if (!seen.contains(i)){
                seen.add(i);
                dfsSearch(i, M);
                //since, even though 1 person without friend could also be 1 friend circle,                  we would always plus 1
                countCircle += 1; 
            }
        }
        return countCircle;
    }
}
```

## 332 Reconstruct the itinerary
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
             
思想是， 既然每一个sublist的开端是一个start，而第二位是一个end，且同一个start可能有不同的end，且要求我们最后的输出是sorted， 那么我们可以直接hash里把每一个 start 对应的 ends们sort 一遍。 然后用dfs来查找 start - ends， 而ends又可以变成下一次的start。 最后如果dfs说true，则说明有这样的一个route， 我们就可以输出ans

```java
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        //find how many stations in total, this needs to be added by 1, because we have one more stop
        int nStations = tickets.size() + 1;
        //we could utilize the hashtableto store Dep - > List(arriavls), and sort the                arrivals, 
        //so that the arrivals are automatically in the lexi order
        Map<String, List<String>> map = new HashMap<String, List<String>>();
                
        //put the JFK into our results first
        List<String> ans = new ArrayList<String>();
        ans.add("JFK");
        
        //put all the destinations into the key value pair
        for (List<String> ticket : tickets){
            String start = ticket.get(0);
            if (!map.containsKey(start)){
                List<String> ends = new ArrayList<String>();
                ends.add(ticket.get(1));
                map.put(start, ends);
            } else {
                map.get(start).add(ticket.get(1));
            }
        }
        //now we can sort the values
        for (List<String> ends : map.values()){
            Collections.sort(ends);
        }
        //now let our dfs to find if there is such a route
        if (dfs(nStations, "JFK", map, ans)) {
            return ans;
        }
        
        //else
        List<String> empty = new ArrayList<String>();
        return empty;
    }
    
    private boolean dfs(int nStations, String start, Map<String, List<String>> map, List<String>ans){
        //now if our ans array is already the same length of the n stops that means we have find the route
        if (ans.size() == nStations){
            return true;
        }
        
        //but there is also another case where there is no such route given this current start
        //becuase for the test case where 1 jfk could go to 2 different ends, and only one end would have a
        //continous ends'ends
        if (!map.containsKey(start)){
            return false;
        }
        List<String> ends = map.get(start);
        for(int i = 0; i < ends.size(); i++){
            String nextStart = ends.get(i);
            ends.remove(i);
            ans.add(nextStart);
            
            boolean exists = dfs(nStations, nextStart, map, ans);
            if (exists){
                return true;
            } 
            //else 说明其中选择的一条路没法走通，那么我们应该把之前上一层添加到ans里的东西删掉 并            把ends里删掉的东西放回去
            //reset the ends list and ans array
            ans.remove(ans.size() - 1);
            ends.add(i, nextStart); //insert the removed "nextStart" back at original i;   
        }
        //at the end, all the for loop in each layer is finished, and we still not returning "True" intermideiatelly, that just means, there is no such a route, then return false 
        return false;
    }
}
```

##  98. Validate Binary Search Tree   
​    5
   / \
  1   4
​     / \
​    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

```java
// //错误的BFS  和上面的DFS 一个问题 那就是 遇到了. 因为你的判断逻辑一直是用这一层的 左子树 和 右子树来比对这一层的root的value。
// //                    6
//    //        10            15
//     // null      null     6     20
// class Solution{
//     public boolean isValidBST(TreeNode root){
//         if (root == null){
//             return true;
//         }
//         Queue<TreeNode> Q = new LinkedList<TreeNode>();
//         Q.add(root);
//         int rootVal = root.val;
        
//         while (!Q.isEmpty()){
            
//             int qSize = Q.size();
//             for (int i = 0 ; i < qSize; i++){
//                 TreeNode thisNode = Q.poll();
//                 int thisNodeVal = thisNode.val;
                
//                 if (thisNode.left != null){
//                     TreeNode thisLeft = thisNode.left;
//                     int leftVal = thisLeft.val;
//                     if (leftVal >= thisNodeVal) return false;
//                     Q.offer(thisLeft);
//                 }
                
//                 if (thisNode.right != null) {
//                     TreeNode thisRight = thisNode.right;
//                     int rightVal = thisRight.val;
//                     if (rightVal <= thisNodeVal) return false;
//                     Q.offer(thisRight);
//                 }  
//             }
            
//         }
//         return true;
//     }
// }

class Solution{
    private boolean dfs(TreeNode root, Integer higherBound, Integer lowerBound){
        //if we have reached the bottom of one sub tree's then just means
        //there is no intermediate false, then we just return true
        if (root == null){
            return true;
        }
        int thisVal = root.val;
        if (higherBound != null && higherBound <= thisVal){
            return false;
        }
        if (lowerBound != null && lowerBound >= thisVal){
            return false;
        }
        //check all the left sub tree first 
        boolean checkRight = dfs(root.right, higherBound, thisVal);
        if (!checkRight) return false;
        
        //else we check the left
        boolean checkLeft = dfs(root.left, thisVal, lowerBound);
        if (!checkLeft) return false;
        
        return true;
    }
    public boolean isValidBST(TreeNode root){
        return dfs(root, null, null);
        
    }
}
```

## 695 Max area of island 

```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]    return is 7 because there are 7 islands connected to each other
```

```java
class Solution {
    private int dfs(int[][] grid, int x, int y){        
        int nRows = grid.length;
        int nCols = grid[0].length;
        //return base case, if we meet this case, meaning that at this direction, 
        //thre is no island, so, the finded is 0, and return it
        if (x < 0 || x > nRows - 1 || y < 0 || y > nCols - 1 || grid[x][y] == 0){
            return 0;
        }
        //else, we need to flag out the searched place
        grid[x][y] = 0;
        //and this means we find one more island
        int finded = 1;
        //go down
        finded += dfs(grid, x + 1, y);
        //go right
        finded += dfs(grid, x, y + 1);
        //go left 
        finded += dfs(grid, x, y - 1);
        //go up
        finded += dfs(grid, x - 1, y);
        // grid[x][y] = 1;
        return finded;
        
//         
    }
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int nRows = grid.length;
        int nCols = grid[0].length;
        
        for (int i =0; i<nRows; i++){
            for (int j = 0; j < nCols; j++){
                if (grid[i][j] == 1){
                    // int num = 1;
                    int numIslands = dfs(grid, i, j);
                    // System.out.println(numIslands);
                    maxArea = Math.max(maxArea, numIslands);
                }
            }
        }
        return maxArea;
    }
}
```



# October 20th New Problem
## 199 Binary Tree Right Side View
Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
这道题容易想到一个很简单的case那就是只遍历一次所有最右边的node就可以了，但是会出现最深层树只有左子树，那么我们就会没有遍历到左边的情况。 所以我们因该根据深度来做判断，因为深度有多少就有多少个output。 那么我们可以用hash来做一个对应的关系， 而且hash有一个有点， 它只保留最后一次添加的值。 那么，也就是说 只要我们先遍历左边的 再遍历右边的， hash里一定保留了每一个深度下最“右”的val
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//以下思路只适用于最深一层下 右子树有一个值 因为现在的遍历逻辑是 先把右边的全搜索了， 一但不能往右边走， 就去rightmost 的 left找value
// class Solution {
//     private void dfs(TreeNode root, List<Integer> ans){
//         if (root.right != null){
//             int rightVal = root.right.val;
//             // System.out.println(rightVal);
//             ans.add(rightVal);
//             dfs(root.right, ans);
//         } else if (root.left != null){
//             int rightMostLeftVal = root.left.val;
//             ans.add(rightMostLeftVal);
//             dfs(root.left, ans);
//         }
        
        
//     }
//     public List<Integer> rightSideView(TreeNode root) {
//         if (root == null){
//             List<Integer> returning = new ArrayList<Integer>();
//             return returning;
//         }
        
//         List<Integer> returning = new ArrayList<Integer>();
//         returning.add(root.val);
//         dfs(root, returning);
//         return returning;
//     }
// }

class Solution{
    private void dfs(TreeNode root, Map<Integer, Integer> map, int depth){
        if (root == null) return;
        //preorder
        map.put(depth, root.val);
        dfs(root.left, map, depth + 1);
        //in order
         // map.put(depth, root.val);
        dfs(root.right, map, depth + 1);
        //post order
        map.put(depth, root.val);
        }
    
    public List<Integer> rightSideView(TreeNode root) {
        //we need to set the depth <-> value, 因为hash的特征是只保留最后更新的value
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null){
            return ans;
        }
        int depth = 0;
        //init the hash and the ans array
        dfs(root, map, depth);
        
        for (int value : map.values()){
            ans.add(value);
        }
        return ans;
    }
}
```

## 101 Symmetric Tree
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3

正确思路是， 一个tree是mirror的 就意味着tree的右root的left == leftroot的right， root的right的right == root的left的left



```java
以下代码是用来检测是否Tree是平衡的，逻辑就是，用dfs，把每一层出现的node放进hash里，然后根据 2^depth 来检测 每一层存进去的tree nodes是否相等来判断tree是不是balanced
// class Solution {
//     private void dfs(TreeNode root, int depth, Map<Integer, List<TreeNode>> map){
//         if (root == null) return;
//         在每一层都加入这一层的node
//         if (!map.containsKey(depth)){
//             List<TreeNode> thisLayerNodes = new ArrayList<>();
//             thisLayerNodes.add(root);
//             map.put(depth, thisLayerNodes);
//         } else {
//             map.get(depth).add(root);
//         }
//         然后往下走一层
//         dfs(root.left, depth + 1, map);
//         dfs(root.right, depth + 1, map); 
//     }
//     public boolean isSymmetric(TreeNode root) {
//         Map<Integer, List<TreeNode>> map = new HashMap<>();
//         if (root == null){
//             return true;
//         }
//         int depth = 0;
//         dfs(root, depth, map);
        
//         int trackDepth = 0;
//         for (List<TreeNode> layer: map.values()){
//             int numNodes = layer.size();
//             int shouldBe = (int) Math.pow(2, trackDepth);
//             if (numNodes != shouldBe){
//                 return false;
//             }
//             trackDepth++;
//         }
//         return true;
//     }
// }

class Solution {
    private boolean isMirror(TreeNode left, TreeNode right){
        //if both reaches the bottom, meaning that both "val" is null, and that is true
        if (left == null && right == null) return true;
        //but, if only one reaches the bottom and the other is not, that would never be a mirror
        if (left == null || right == null) return false;
        
        boolean isValEqual = (left.val == right.val);
        return isValEqual && 
               isMirror(left.right, right.left) && 
               isMirror(left.left, right.right);
    }
    
    public boolean isSymmetric(TreeNode root) {
        //At the beginning, the left and right is just the root itself
        return isMirror(root, root);
    }
}
```

# October 21st New Problems
## 733 Flood Fill 
给定一个原来的色图， 2Darray， 和一个起始point和一个新的颜色， 要求你从startpoint开始辐射，只要路过的颜色和start 颜色一样就给他换成新的颜色. 注意边界条件的写法，什么时候表示走到了头就return。 
```java
class Solution {
    private void dfs(int[][] image, int thisColor, int x, int y, int newColor, boolean[][] flooded){
        int nRows = image.length;
        int nCols = image[0].length;
        
        //边界条件很重要， 如果此刻的x越界了，那就不能执行下面的代码， 或者 如果 现在想要去的方向的原来的颜色和start不一样
        //也不该过去， 最后还得加一个flag来标记找过的地方，因为， 会出现一个cornercase：
        //[[0,0,0],[0,1,1]] @ (1,1) with newColor = 1
        //如果，不引入flooded的flag，只是单一判断thisColor是不是startColor的话会陷入无限循环
        if (x < 0 || x > nRows - 1 || y < 0 || y > nCols - 1 || image[x][y] != thisColor || flooded[x][y] == true){
            return;
        }
        flooded[x][y] = true;
        image[x][y] = newColor;
        dfs(image, thisColor, x + 1, y, newColor, flooded);
        dfs(image, thisColor, x, y+1, newColor, flooded);
        dfs(image, thisColor, x, y-1, newColor, flooded);
        dfs(image, thisColor, x - 1, y, newColor, flooded);
        return; 
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] flooded = new boolean[image.length][image[0].length];
        if (image == null){
            int[][] returnEmpty = new int[0][0];
            return returnEmpty;
        }
        int nRows = image.length;
        int nCols = image[0].length;
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nCols; j++){
                flooded[i][j] = false;
            }
        }
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nCols; j++){
                if (i == sr && j == sc){
                    int thisColor = image[i][j];
                    dfs(image, thisColor, i, j, newColor, flooded);
                }
            }
        }
        return image;
    }
}
```

## 104 Maximum Depth of a BST
思想是： 每一个tree的level = 1 + 下一层的level
  			                             3      = *1 + max(9, 20)   = 1 + 2*
  					                    / \     
*1+max(null,null)=1*        9  20    = *1 + max(15, 7) =  2*
                                            /  \                         
  1 + max(null,null)=1      15   7  = 1 + max(leftnull, rightnull) = 1 + 0=1

```java
class Solution {
  public int maxDepth(TreeNode root) {
      if (root == null){
          return 0;
      }
      // int depth = 1;
      // depth += Math.max(maxDepth(root.right), maxDepth(root.left));
      // return depth;
      int leftDepth = maxDepth(root.left);
      int rightDepth = maxDepth(root.right);
      return 1 + Math.max(leftDepth, rightDepth);
      
  }
}
```

## 417 pacific and atlantic flow
```java
//思路是： 我们用两个不同的数组来记录 流去pacific 和 流去Atlantic的情况如何， 最后呢再一一对比是否 True@pacific 也能 true@atlantic。 
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null ||matrix.length ==0 || matrix[0].length == 0){
            return res;
        }
        
        int nRows = matrix.length;
        int nCols = matrix[0].length;
        
        boolean[][] pacific = new boolean[nRows][nCols];
        boolean[][] atlantic = new boolean[nRows][nCols];
        
        //init: 第一行一定能去 pacific 最后一行一定能去atlantic
        for (int j = 0; j < nCols; j++){
            dfs(0, j, matrix, pacific, matrix[0][j]); //深度搜索pacific， pacific是第一个row作为start point
            dfs(nRows - 1, j, matrix, atlantic, matrix[nRows - 1][j]); //深度搜索Atlantic， Atlantic是第一个row作为start point
        }
        //init: 第一col一定能去 pacific， 最后一个col一定能去atlantic
        for (int i = 0; i < nRows; i++){
            dfs(i, 0, matrix, pacific, matrix[i][0]); //深度搜索pacific， pacific是第一个column作为start point
            dfs(i, nCols - 1, matrix, atlantic, matrix[i][nCols-1]);//深度搜索Atlantic， Atlantic是最后一个column作为start point
        }
        
        //现在判断两个数组里哪些 i j 同时满足了pacific true 和 atlantic true的
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nCols; j++){
                if (pacific[i][j] && atlantic[i][j]){
                    List<Integer> goodPair = new ArrayList<>();
                    //快捷方法直接同时添加 [i,j]
                    goodPair.addAll(Arrays.asList(i,j)); 
                    res.add(goodPair);
                }
            }
        }
        return res;
    }
    private void dfs(int x, int y, int[][] matrix, boolean[][] visited, int currentHeight){
        int nRows = matrix.length;
        int nCols = matrix[0].length;
        //我们的边界条件是x和y的越界以及如果访问过的地方是满足条件的就为true所以就不该再访问第二次
        //再就是能使得深处island通流的情况就是 深处的 大于 现在的height
        //所以如果这类情况不满足 我们都 直接return 表示这个方向结束了
        if (x < 0 || x > nRows - 1 || y < 0 || y > nCols - 1 || visited[x][y] || matrix[x][y] < currentHeight){
            return;
        }
        
        visited[x][y] = true;
        dfs(x + 1, y, matrix, visited, matrix[x][y]);
        dfs(x , y + 1, matrix, visited, matrix[x][y]);
        dfs(x, y - 1, matrix, visited, matrix[x][y]);
        dfs(x - 1, y, matrix, visited, matrix[x][y]);
        return;
    }
}
```
# October 24th New Problem
## 112 Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,
```java
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//思路就是逐层查找加和，先搜索root的左边，并且加上左边的值，如果失败了，他就会返回到上一个node，然后去右边查找
class Solution {
    private boolean dfs(TreeNode root, int currentSum, int sum){
        //一定需要判断一次此刻传入的root的左和右是不是null了来保证我们已经走到底了，不然会出现一个corner case
        //那就是currentSum在中间就等于sum， 然后就return true， 那就不对了，因为i题目要求找到底
        if (currentSum == sum && root.left == null && root.right == null){
            return true;
        }
        //注意这里我们是提前加了root left的val，且最开始的边界条件没有限制rootleft，所以需要提前判断root left的null
        if (root.left != null && dfs(root.left, currentSum + root.left.val, sum)){
            return true;
        }
        if (root.right != null && dfs(root.right, currentSum + root.right.val, sum)){
            return true;
        }
        return false;    
    }
    
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        int currentSum = root.val;
        boolean ans = dfs(root, currentSum, sum);
        return ans;
    }
}
//以下是错误的写法
// class Solution {
//     private boolean dfs(TreeNode root, int currentSum, int sum){
//         //这个写法会导致一开始如果currentSum == sum就直接ruturn 而没有走到底
//         if (currentSum == sum) {
//             return true;
//         }
//         if (root == null && currentSum != sum){
//             return false;
//         }
        
//         if (dfs(root.left, currentSum + root.val, sum)){
//             return true;
//         }
//         if (dfs(root.right, currentSum + root.val, sum)){
//             return true;
//         }
        
//     }
//     public boolean hasPathSum(TreeNode root, int sum) {
//         if (root == null){
//             return false;
//         }
//         int currentSum = 0;
//         ans = dfs(root, currentSum, sum);
//         return ans;
//     }
// }
```

## 100 Same tree
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
              / \       / \
         2   3     2   3
        [1,2,3],   [1,2,3]

Output: true

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private boolean dfs(TreeNode root1, TreeNode root2){
        //base case is when both roots at the deepest layer are both null, that just means that 
        //the structure is the same
        if (root1 == null && root2 == null){
            return true;
        }
        //another case that one layer is at the deepest but the other is not, that just means that
        //two structurally is not the same
        if (root1 == null || root2 == null){
            return false; 
        }
        //also another case is that at this layer the values are not the same
        if (root1.val != root2.val){
            return false;
        }
        //如果以上条件都没有满足，说明只有一个case 那就是 root没有到底，但是valLeft == valRight
        //先遍历左边
        boolean leftCompare = dfs(root1.left, root2.left);
        //再看右边
        boolean rightCompare = dfs(root1.right, root2.right);
        return leftCompare && rightCompare;
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {        
        boolean ans = dfs(p, q);
        return ans;
    }
}
```

## 207 Course Schedule
给定需要修的课程的数量， 同时给定每门课的先修课的pair， 问你按照这个pair的顺序是否能够修完要求课的数量。
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

逻辑是，每门课都有自己的先修课，而只有当先修课都结束了它才能被修。这个就是拓扑排序的思想，我们找到所有课程的indegree（有多少课程指向它，也就是说有多少课程需要先修）。 那么就会发觉修课的顺序就是入度为零开始的那门课做bfs， 每次提出一个为零的课，找到它之后的后修课，后修课的indegree减一，然后呢找到此刻是否有新的入度为零的课程，如果有说明又可以修课了，以他又再做一次bfs找到他所有的后修课的情况。

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses]; //to record each course's how many prerequisits
        List[] allAfterCourse = new ArrayList[numCourses]; // to recorde how many after course is after this   
        for (int i = 0; i < numCourses; i++){
            //set up the arraylist inside of this array
            allAfterCourse[i] = new ArrayList<Integer>();
        }
        //now we need to add each course's after courses into this array
        for (int i = 0; i < prerequisites.length; i++){
            int thisPre = prerequisites[i][1];
            int thisCourse = prerequisites[i][0];
            
            //add this prerequisites's after course into it
            allAfterCourse[thisPre].add(thisCourse);
            //因为我们找到了一个pre， 那这门课的pre的个数就加1
            indegree[thisCourse]++;
        }
        //topological sort， 我们的目的是找到没有indegee的课作为起始，因为他是所有课的最最最最最开始
        //所以我们要遍历indegree 找到为零的那门课放入queue里面 然后以他为起始点 做bfs
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++){
            if (indegree[i] == 0){
                queue.offer(i);
            }
        }
        //现在我们的queue里已经有起始课，我们可以进行bfs搜索，搜索是找到这个起始点的所有aftercourse， 然后
        //对这些后续课的indegree--， 因为他们的一个先修课已经pop出来了。 然后如果这些后续课里又出现了
        //一个为零的indegree的课程 说明它可以修了，那就再放入queue里面
        //同时需要记录已经选取了几门课出来了
        int count = 0; 
        while (!queue.isEmpty()){
            int thePre = queue.poll();
            count++;
            int howManyAfterCourse = allAfterCourse[thePre].size();
            //现在我们要模拟把aftercourse里的课的indegree减1的过程，因为我们找到了一门先修课
            for (int i = 0; i < howManyAfterCourse; i++){
                int thisAfterCourse = (int) allAfterCourse[thePre].get(i);
                indegree[thisAfterCourse]--;
                if (indegree[thisAfterCourse] == 0){
                    queue.offer(thisAfterCourse);
                }
            }     
        }
        return count == numCourses;
    }
}
```

# October 25 New Problem
## 529 Minesweeper
Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

- If a mine ('M') is revealed, then the game is over - change it to 'X'.
- If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
- If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
- Return the board when no more squares will be revealed.
  Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['**E**', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

```java
class Solution {
    //下面是八个方向
    int[] dx = {1, 0,  0, -1, 1, -1,  1, -1};
    int[] dy = {0, 1, -1, 0,  1, -1, -1,  1};
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (board[x][y] == 'M'){
            //the first case where the first click is the mine, then game is over
            board[x][y] = 'X';
            return board;
        }
        //now we want the other case where we find an empty case, but remember
        //we need to find all such square which does not have mines adjecently
        // this dfs should have two functions:
        //1 is the reveal all empyty square w/ no bombs recurssively
        //2. label all square's numbers of mines adjacently
        dfs(board, x, y);
        return board;
    }
    private void dfs(char[][] board, int x, int y){
        //define the similar base return case
        int nRows = board.length;
        int nCols = board[0].length;
        if (x < 0 || x > nRows - 1 || y < 0 || y > nCols -1 || board[x][y] != 'E'){
            return;
        }
        //1. 如果这个方块周围没有bomb，那就需要把他变成“B” recurssively
        //所以需要找到当前square下八个方向的所有的bomb的个数
        int numBomb = findBomb(board, x, y);
        if (numBomb == 0){//1. 如果这个方块周围没有bomb，那就需要把他变成“B” recurssively
            board[x][y] = 'B';
            for (int i = 0; i < 8; i++){
                board[x][y] = 'B';
                dfs(board, x + dx[i], y + dy[i]); //上下左右斜对角
            }
        } else {
            //此刻说明有bomb了， 那么我们就把它改成它邻居的总共的炸弹的总个数
            //需要注意的是，这里不需要再call一次函数，一个是因为题目要求，二是上面的
            //函数下会连带到 else 的情况 同时将需要用数字表达的方块修改过来
            board[x][y] = (char) ('0' + numBomb);
        }
    }
    private int findBomb(char[][] board, int x, int y) {
        int numBomb = 0;
        int nRows = board.length;
        int nCols = board[0].length;
        
        for (int i = 0; i < 8; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            //注意边界条件 不能超过界限 如果超过了 就continue这一组 newX newY
            if (newX < 0 || newX > nRows - 1 || newY < 0 || newY > nCols - 1){
                continue;
            }
            if (board[newX][newY] == 'M'){
                numBomb++;
            }
        }
        return numBomb;
    }
}
```

# October 26th New problem 
## 105 construct Binary Tree from preorder and in order 
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:
    3
   / \
  9  20
    /  \
   15   7

```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length != preorder.length){
            return null;
        }
        int preStart = 0;
        int inStart = 0;
        int inEnd = preorder.length - 1;
        return buildTree(preorder, inorder, preStart, inStart, inEnd);
    }
    private TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd){
        if (preStart > preorder.length || inStart > inEnd){
            return null;
        }
        TreeNode currentNode = new TreeNode(preorder[preStart]);
        int i = inStart;
        while (i <= inEnd){
            if (inorder[i] == preorder[preStart]){
                break;
            }
            i++;
        }
        
        currentNode.left = buildTree(preorder, inorder, preStart + 1, inStart, i - 1);
        currentNode.right = buildTree(preorder, inorder, preStart + (i - inStart +1), i + 1, inEnd);
        
        return currentNode;
    }
}
```

# October 28 New problem 
## 93 Restor IP address
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

```java
//思路是 对于一个 ip 地址 他有四个段落组成， 长度在 4 ~ 12，
//它可以由 单个数字组成 1.1.1.1
//它可以由 两个数字形成 
//它可以由 三个数字形成 但是 三位数不能超过255  且 二位数三位数的时候 开头不能是 0 
//所以我们可以按照 三个 情况做一个递归， 每次在已有的string里添加 要么 ".x" 要么 ".xx" 要么 “.xxx” 但是 要注意 三位数的时候不能大于 255。 那么当count == 4 表示四个部分都找到了， 就可以加入这个substring给res
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        
        dfs(s, "", 0, res);
        return res;
    }
    private void dfs(String s, String subString, int count, List<String> res){
        //当我们已经组成了四次parts的时候，且这个时候 给定的 string已经遍历完毕了，说明可以加入到res里了
        if (count == 4 && s.length() == 0){
            //取这个组成的string的 第一项往后所有，因为我们init的时候 传入了 "." , 所以最后组成的是
            //".222.222.222.222"而我们不需要.
            res.add(subString.substring(1));
            //如果已经加入答案了 说明 这个递归可以结束了
            return;
        }
        //出口, 但是如果我们查了四个parts但是string没有遍历完 说明此刻的加法 （单个单个加） 是不能满足要求的
        //同样如果s的length已经遍历完了 但是 count还没有到4 说明 我们此刻的加法（多个多个加）太多了
        if (count == 4 || s.length() == 0){
            return;
        }
        
        //case1 一个一个加
        //传入这个string 第一位后的所有string， 然后给temp substring加入第一位的str， count + 1
        dfs(s.substring(1), subString + "." + s.substring(0, 1), count + 1, res);
        
        //case2 两个两个加
        if (s.charAt(0) != '0' && s.length() >= 2){
            dfs(s.substring(2), subString + "." + s.substring(0, 2), count + 1, res);
            
            //case 3, 三个三个加 但是要保证这三个数的大小不超过 255
            if (s.length() >= 3 && Integer.parseInt(s.substring(0, 3)) <= 255){
                dfs(s.substring(3), subString + "." + s.substring(0,3), count + 1, res);
            }
        }
    }
}
```

# October 29th New Problem 
## 721. Accounts Merge
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'], 
               ["John", "johnnybravo@mail.com"], 
               ["Mary", "mary@mail.com"]]

```java
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if (accounts == null || accounts.size() == 0){
            return res;
        }
        //建立有向图， 每一个email 对应他所有的neighbors
        Map<String, Set<String>> emailsGraph = new HashMap<>();
        //建立一个来存储所有nodes的hashset
        Set<String> emails = new HashSet<>();
        //我们同时也需要一个email 到 人名的机制
        Map<String, String> email2name = new HashMap<>();
        //对于dfs， 我们需要避免重复找了同一个人名下的所有nodes
        Set<String> visited = new HashSet<>();
        
        
        for (List<String> list : accounts){
            String thisName = list.get(0);
            for (int j = 1; j < list.size(); j++){
                //第一步是把email 对 人名找出来
                String thisEmail = list.get(j);
                email2name.put(thisEmail, thisName);
                                
                //第2步是把所有email都放进set
                emails.add(thisEmail);
                
                //第3部是把这个email和他对应的neibhor都找出来 11 对应
                //先new一个hashset 如果这个email没有出现在map里
                //且需要注意的是我们需要再该list里给每一个email都建立一次关系，所以当 j == 1的时候先continue一次
                emailsGraph.putIfAbsent(thisEmail, new HashSet<String>());
                if (j == 1) {
                    continue;
                }
                String lastEmail = list.get(j - 1);
                //先对上一个email做它的neibhor的添加
                emailsGraph.get(lastEmail).add(thisEmail);
                //再对这个email做neibhor的添加
                emailsGraph.get(thisEmail).add(lastEmail);                
            }
        }
        
        //现在我们的图是有向图， 我们可以对emails set里的每个email做dfs来查找它相通的所有edges 且 此时所有都对应了
        //同一个accountname
        for (String thisEmail : emails){
            if (!visited.contains(thisEmail)){
                visited.add(thisEmail);
                String thisPerson = email2name.get(thisEmail);
                List<String> temp = new ArrayList<>();
                temp.add(thisEmail);
                //dfs 遍历 这个email下的nei的nei的nei
                dfs(emailsGraph, visited, thisEmail, temp);
                
                //对于该email下的所有edges查找完毕后 放入答案里
                Collections.sort(temp);
                //把人名插入最开始地方
                temp.add(0, thisPerson);
                res.add(temp);
            }
        }
        return res;   
    }
    private void dfs(Map<String, Set<String>> emailsGraph, Set<String> visited, 
                     String thisEmail, List<String> temp){
        Set<String> neis = emailsGraph.get(thisEmail);
        if (neis.isEmpty()){
            return;
        }
        for (String nei : neis){
            if (!visited.contains(nei)){
                visited.add(nei);
                temp.add(nei);
                dfs(emailsGraph, visited, nei, temp);
            }
        }
    }
}
```

## 108 convert sorted array into BST
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
      0
     / \
   -3   9
   /   /
 -10  5

思想是每次都找一个区间里的中间值为这一层子树的node， 而他的左子树就是 mid 往左一位的所有， 它的右子树就是往右一位的所有， 依次递归。 

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    
    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
       	//到达mid = 0 的时候，call了左，return
        root.left = helper(nums, left, mid - 1);
        //此时mid还是0， 所以mid + 1 = 1，right 还是 1不变
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}
```

## 257 Binary tree path in string 
Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.
Example:
Input:
   1
 /   \
2     3
 \
  5
Output: ["1->2->5", "1->3"]
Explanation: All root-to-leaf paths are: 1->2->5, 1->3

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();                  
        if (root == null){
            return res;
            
        }
        helper(root, res, "");
        return res;
    }
    private void helper(TreeNode root, List<String> res, String str){
        //这里要先加string， 如果放到下面的if 判断之后 res里的string就会少最后一层的val， 因为
        //如果我们到了最后一个layer， 那很明显判断就直接return， 这样value就没法被加入了
        str = str + String.valueOf(root.val);
        //只有当此刻layer的node的左右都为null， 才说明到达了最后一层叶子
        if (root.left == null && root.right == null){
            res.add(str);
            return;
        }
        //不然应该继续尝试查找 左 
        if (root.left != null){
            helper(root.left, res, str + "->"); 
        }
        //当此层layer的左走不通了 就去右
        if (root.right != null){
            helper(root.right, res, str + "->");
        }
        return; 
    }
}
```

# October 30 New Problem 

## 418 Sentence Screen Fitting (Ernin questions)
给定一个list of words，和一个screen mrow ncols， 问你这个screen上面能展示多少次这个词语们
Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
Output: 
1
Explanation:
I-had
apple
pie-I
had--
The character '-' signifies an empty space on the screen.
思想是：要求每个单词之间需要一个空格，也就说空格也占字符。那么假设加上空格后的字符总长度是 x, 在screen上展出的所有字符数为n， 那可不可以说重复的次数是n//x呢？ 所以第一步是将list of string变成 string with space举个例子。 
然后，**1. 如果每一row的最后一项fit到了字符串的结尾（如何判断是一个词语的结尾，那就是单词后面一项是空格），说明此刻的row完全fit了一个单词组，且要换行了，那换行是不是意味着这个单词组后面的空格也可以被抵消了呢？所以此刻的这一row的总字符数是 nCols + 1.** 
但是， **2. 如果此刻我们一个row的最后一项在字符串的中央（即最后一个letter后面不是空格） 那就说明我们的这一row没法fit所有的单词，我们可能在某一个单词的中央了，那就需要后退，字符数--**

例子 [abc de f ] 4  rows   6cols

abc   de  f  abc  de  f   abc   de  f  
xxxxxx                                                                  在“e”之后我们可以看到是空格， 所以此刻的总字符数 = ncols+1                                                      

​              xxxxxx                                                    正好fit到了空格处，那么此row的总字符 = ncols + 0

​                          xxxxxx                                        落到了a上面，不能断开一个单词，所以总字符 = ncols - 1

```java
public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        //第一步convert list of string to string with space
        String phrase = String.join(" ", sentence) + " ";
        int numCharOnScreen = 0;
        int sLength = phrase.length();
        
        for (int i = 0; i < rows; i++){
            //先假设一个row能fit一个完整的词语
            numCharOnScreen += cols;
            //找到下一个char的位置
            int whereNext = numCharOnScreen % sLength; 
            int whereNow = (numCharOnScreen - 1) % sLength;
            if (phrase.charAt(whereNext) == ' '){//case1 下一个char是一个空格
                //那说明这一个row fit了一个完整的词语此刻就在空格上面，且后面那个空格可以因为换行被抵消
                numCharOnScreen++;
            } else {//case2， 下一个char不在空格，那我们能展现的char就得减少,直到现在这个位置在空格上面
                while (numCharOnScreen > 0 && phrase.charAt(whereNow) != ' '){
                    numCharOnScreen--;
                    whereNow = (numCharOnScreen - 1) % sLength;   
                }
            }
        }
        return numCharOnScreen / sLength;
    }
}
```

## 113. Path Sum II
给你一个tree， 和一个需要的的sum的值， 要求你返回这个路径是什么
Given the below binary tree and sum = 22,
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
2    2  5   1

这个思路就是： 我先找右边的，如果dfs右边到了底后能使得和一致，那么就给ans里加入这个temp array， 但是也有可能当前这个node往右走也会出现同一个答案，例如上面的 [5,4,11,2_left] [5,4,11,2_right]，所以我们要回溯一下，把之前在11node那一层加入的left的val删掉，这样进入右边dfs的时候就不会有多余的值。
```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        //defensive一下，判断输入是否有效
        if (root == null){
            return ans;
        }
        //由于我们dfs的做法是在每一层node的时候优先加入下一层的值，我们需要一开始init temp里的值
        int rootVal = root.val;
        temp.add(rootVal);
        //同样init tempSum
        int tempSum = root.val;
        dfs(root,sum, tempSum, ans, temp);
        return ans;
    }
    private void dfs(TreeNode root, int sum, int tempSum, List<List<Integer>> ans, List<Integer> temp){
        //首先判断是否到leaf了
        if (root.left == null && root.right == null){
            if (tempSum == sum){ //case1，tempSum达到了target sum
                //那就加入这个temp里的东西给ans
                List<Integer> adding = new ArrayList<>(temp);
                ans.add(adding);
                //并且结束这一层
                return;
            } else {
                //不然我们什么都不做就return
                return;
            }
        } 
        //拆解部分
        if (root.left != null){//先往左边走
            //提前加入下一层的值
            temp.add(root.left.val);
            dfs(root.left, sum, tempSum + root.left.val, ans, temp);
            //回溯，因为假设在leaf上一层call了dfs，且满足了添加条件，那么我们return到11，
            //这个时候我们也希望11往右边再去看看，那么就说明应该把之前添加的删掉
            //那如果上一层dfs没成功，同样说明上一层加入的是无效数字，那也该删掉
            temp.remove(temp.size() - 1);
        }
        //可见由于上面的temp remove，确保了走到右边的时候temp里只有从上到这一层root的所有值
        //而没有root left的值
        if (root.right != null){//再往右边走
            temp.add(root.right.val);
            dfs(root.right, sum, tempSum + root.right.val, ans, temp);
            //同样回溯
            temp.remove(temp.size() - 1);
        }
        return;
    }
}
```

## 83 Remove duplicates from sorted list
Given a sorted linked list, delete all duplicates such that each element appear only once.
Example 1:
Input: 1->1->2
Output: 1->2
Example 2:
Input: 1->1->2->3->3
Output: 1->2->3

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            //如果下一个val和现在的重复了 那就把 “current的next的next”， 然后下一次循环再比对一次 current.next
            if (current.val == current.next.val){
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
```

# October 31 New Problem
## 339. Nested List Weight Sum
Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.

思路是： 可见如果循环过程中遇到了integer， 那直接就可以对她进行 depth * integer的操作。但是如果遇到sublist了， 就得对这个list进行一次循环找integer 并且操作 depth * integer的操作， 而此刻的depth应该是上一次depth的加一。 那这很明显就是递归。

递归逻辑是，每一层都有一层自己的sum， 然后return回来供上一层相加。 那么一开始我们进入递归的时候，depth = 1， 最上层是 sum = 0，遇到了一个list， 我们又call了一次递归， 进入了[1,1]， depth = 2， 全是数字， sum += depth * int, 返回第二层sum = 4 给第一层， 现在又回到了第一层， 遇到了2， 2 * depth = 2， sum = 4 + 2 = 6

```java
class Solution {
    public int depthSum(List<NestedInteger> nestedList){
        int firstDepth = 1;
        int sum = dfs(nestedList, firstDepth);
        return sum;
    }
    private int dfs(List<NestedInteger> nestedList, int depth){
        int thisLayerSum = 0;
        for (NestedInteger n : nestedList){
            //case 1 如果此刻是integer
            if (n.isInteger()){
                thisLayerSum += n.getInteger() * depth;
            } else {//不然就是碰到了一个新的sublist
                //把这个list拿出来
                List<NestedInteger> nextList = n.getList();
                int nextDepth = depth + 1;
                thisLayerSum += dfs(nextList, nextDepth);
            }
        }
        return thisLayerSum;
    }
}
```

## 364. Nested List Weight Sum II

和上面一样，只是这次它要求的是在最上层的weights最大，而越往leaf走它的weights越小。 举个例子[1[4[6]]] - > 1 + 1 + 1 + 4 + 4 + 6 = 17
所以思想是，维护两个变量， 一个是 layersum， 另一个是global sum， layersum是记录在此刻layer的时候，连同上层layer的加和是多少，这样就会把上层的数据记忆到这一层了， 而这个时候sum += layerSum，意味着将上层的layerSum 又加了这次的layersum。 以上面为例：
layerSum = 1  -> layerSum = 1 + 4  -> layerSum = 1 + 4 + 6
Sum = 1          -> sum = 1 + 1 + 4    -> sum  = 1+ 1 + 4 + 1 + 4 +6

```java
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0){
            return 0;
        }
        //那么如果我们是每个layer都是处理这一层的东西，那这就是first in first out的情况
        Queue<NestedInteger> q = new LinkedList<>();
        for (NestedInteger n : nestedList){
            q.offer(n);
        }
        //total
        int sum = 0;
        //每一层此刻的sum
        int layerSum = 0;
        while (!q.isEmpty()){
            //现在要做的是把每一层拿出来分为int 和 list来处理， 如果是int， 那就此刻的layerSum += int
            //如果是list 那就把list的东西重新放回q里面，循环处理下一层
            int size = q.size(); //必须先定义size，不然他会动态改变
            for (int i = 0; i < size; i++){
                NestedInteger thisObject = q.poll();
                //case 1 是integer
                if (thisObject.isInteger()){
                    //那就把这一层的sum加上他, 这样到了下一层的num的时候 layerSum += newDigit
                    // -> layerSum = 1st + newDigit
                    layerSum += thisObject.getInteger();
                } else {//case2, 遇到了一个list， 那说明我们要去下一层了
                    List<NestedInteger> next = thisObject.getList();
                    for (NestedInteger n : next){
                        q.offer(n);
                    }
                }
            }
            //这意味着在第一层结束后 sum = 1st, 第二次呢 sum = 1st + 1st + 2nd
            sum += layerSum; 
        }
        return sum;
    }
}
```

# November 1st New Problem BackTrack
## 39 Combination Sum
given an array of integers [2,3,5] target = 8, find all combinations that sum up to 8,  The **same** repeated number may be chosen from `candidates` unlimited number of times.  but the answer could not be duplicated
[[2，2，2，2], [2,3,3], [3,5]]， 且数组没有重复项 （意味着代码无需去重）

brutal force是，建立for循环，然后对于每个数做dfs，每次dfs的起始地方都是0，退出条件是此刻的tempSum == sum了，或者此刻的temSum>sum。 然后呢，每次tempSum == sum的时候再添加到答案里的时候我们需要将tempArray里的数字 sort一次，然后用hashSet来检测这个答案是不是出现过了，如果没有出现过才会放入答案里。 

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0){
            return ans;
        }
        //这个是为了避免我们出现的combination的重复项
        Set<List<Integer>> set = new HashSet<>();
        //建立一个暂时存贮的array
        List<Integer> temp = new ArrayList<>();
        int tempSum = 0;
        bt(candidates, target, tempSum, 0, ans, temp, set);
        return ans;
    }
    private void bt(int[] candidates, int target, int tempSum, int index, List<List<Integer>> ans, List<Integer> temp, Set<List<Integer>> set) {
        //如果此刻他的tempSum == target了， 那就可以把答案放入arraylist里
        if (tempSum == target){
            List<Integer> returning = new ArrayList<>(temp);
            //但是我们可能出现 223 232 这样的情况，这不该有，所以我们sort一边 保证都是 223
            Collections.sort(returning);
            //判断这个组合出现过没有，没有出现过才加入
            if (!set.contains(returning)){
                ans.add(returning);
                set.add(returning);
            }
            return;
        }
        //但是如果这一层加入的值大于target了那就返回
        if (tempSum > target){
            return;
        }
        for (int i = 0; i < candidates.length; i++){
            temp.add(candidates[i]);
            bt(candidates, target, tempSum + candidates[i], i,  ans, temp, set);
            //不管有没有成功，这一层加入的数都该回溯
            temp.remove(temp.size() - 1);
        }
    }
}
```

但很明显上面可以剪纸的地方在于， [2,3,5]  -> 2 2 2 2 返回到第一层 [2] 这个地方， i开始循环到3 -> [2,3]，我们又会从2这个地方再找了一次循环 -> [2,3,2] 但其实我们想要做的是如果当前指针在一个数字K，我们要加入的数字从K开始往后， 我们想要的是 2 3 ->3 那其实我们只需要让循环从上一层递归指定的start开始即可。 

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        //defensive programming
        if (candidates == null || candidates.length == 0){
            return ans;
        }
        
        List<Integer> temp = new ArrayList<>();
        int tempSum = 0;
        int idx = 0; //一开始从0这一项开始深度重复查找
        bt(candidates, temp, ans, target, tempSum, idx);
        return ans;
    }
    
    private void bt(int[] candidates, List<Integer> temp, List<List<Integer>> ans, int target, int tempSum, int idx){
        //如果此刻的递归使得tempSum == sum了那说明我们找到了一组答案了
        if (tempSum == target){
            ans.add(new ArrayList<>(temp));
            return;
        }
        //但是如果这次查找反而大于了target 那就是无效的 返回
        if (tempSum > target){
            return;
        }
        //拆解，我们要使得每次递归都从查找的这个数开始并且往后深度查找
        for (int i = idx; i < candidates.length; i++){
            temp.add(candidates[i]);
            //这里传入i，使得下一次的递归从 i 也就是当前数开始往后递归， 避免了重复查找了i之前的
            bt(candidates, temp, ans, target, tempSum + candidates[i], i);
            temp.remove(temp.size() - 1);
        }
    }
}
```

## Combination sum2
和上面不同的是 一个数字不能被重复使用， 且不能出现重复的答案，且数组里出现了重复项!! 

看一个例子    

[1,2,2', 2'', 5]  target = 5

​                                                                         null

start=0                  **1**                                   2                                          2'                                          2''                                                    							*idx= 0 							       idx = 1									idx = 2 										    idx= 3* 

start=1         **2**                 *2'*           2''                      

​               *idx =1        idx=2    idx =3*	        

start=2    **2'**

​             idx = 2

发觉 ： 在深度优先找到了 1 2 2' 后， 我们到了start = 1， idx =2 的时候 又找了一次 1 2' 那这就意味着重复了.所以我们应当在同一个level的时候 判断 此刻这个数是不是等于前一个数， 如果是的话就得skip

```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0){
            return ans;
        }
        int tempSum = 0;
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates); //必须sort，不然没法判断下一个是不是重复的地方
        //5 1 2 1 - > 1 1 2 5 这样才能查重
        bt(candidates, tempSum, temp, ans, target, 0);
        return ans;
    }
    //1 1 2 5 7 10
    private void bt(int[] candidates, int tempSum, List<Integer> temp, List<List<Integer>> ans, int target, int index){
        if (tempSum == target){
            ans.add(new ArrayList<>(temp));
            return;
        }
        if (tempSum > target){
            return;
        }
        for (int i = index; i < candidates.length; i++){
            //当深度递归返回到这一层后开始广度遍历，发觉这个重复了，那就得skip 避免重复查找
            if (i > index && candidates[i] == candidates[i -1]) continue;
            temp.add(candidates[i]);
            //且每一个数不能重复使用，那就得把 idx + 1，让下一次递归深度的的时候找下一个数字
            bt(candidates, tempSum + candidates[i], temp, ans, target, i + 1);
            temp.remove(temp.size() -1);
        }
    }
}
```

## 216 Combination sum 3

和之前不同的是 现在是给定了每个array里最多能存多少数字，和一个target，且数字都是从 1 ~ 9 选取，这就意味着不存在重复（CS2里的case） 不存在每个数字重复（CS1里的case）。 那思路就很清楚了，每次递归的时候都往后index一位， 然后判断此刻 arraySize == limit && tempSum == sum与否。 

```java
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        //题目说明不存在0的情况所以任意一个出现了0， 那就得返回空集
        if (k == 0 || n == 0){
            return ans;
        }
        List<Integer> temp = new ArrayList<>();
        int tempSum = 0;
        int limit = k;
        int target = n;
        int idx = 1;
        bt(ans, temp, tempSum, target, limit, idx);
        return ans;
    }
    private void bt(List<List<Integer>> ans, List<Integer> temp, int tempSum, int target, int limit, int idx){
        //如果现在的size == limit了且tempSum == target了那就加入答案里并且返回
        if (temp.size() == limit && tempSum == target){
            ans.add(new ArrayList<>(temp));
            return;
        }
        //但是呢如果size达到了limit 但 sum还没有说明这条路错了 返回
        if (temp.size() == limit && tempSum != target){
            return;
        }
       	//同理，如果sum到了但是size还没到那也不行
        if (tempSum == target && temp.size() != limit){
            return;
        }
        //每次都从这个数开始往后找
        for (int i = idx; i < 10; i++){
            temp.add(i);
            //然下一次递归的时候找到下一个数字
            bt(ans, temp, tempSum + i, target, limit, i + 1);
            temp.remove(temp.size() -1);
        }
    }
}
```

## 77. Combinations
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n. 
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

思路和combination Sum3类似，每次深度查找的时候 indx + 1，在下一层递归的时候是从下一个数字开始找。 同时由于范围从 1 ~ n确保了没有重复问题，所以可以直接在广度的时候直接做，

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int idx = 1;
        int limit = k;
        int range = n;
        bt(range, limit, temp, ans, idx);
        return ans;
    }
    
    private void bt(int range, int limit, List<Integer> temp, List<List<Integer>> ans, int idx){
        if (temp.size() == limit) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = idx; i < range + 1; i++){
            temp.add(i);
            bt(range, limit, temp, ans, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
```

## 78 Subsets
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
思路其实和上面类似， 需要在每个idx往后遍历并且添加新的数字，然后走到底了后，将上一层填入tempArray里的数字删掉以便于下次遍历的时候不会有多余的， 但不同点在于现在是每走一步就得加一次现在的tempArray进ans里面。 那么这一步就放在循环里做
                                 1 放入                              回溯 ->[]                                   2  [2]放入               回溯[]->   3 [3]放入
          2   [1,2]放入 -> 回溯[1]  3 [1,3]->回溯[1]                                  3  [2, 3]放入 - > 回溯 [2]
     3 [1,2,3]放入  ->回溯[1,3]

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int size = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        //pre added the empty set
        ans.add(new ArrayList<>());
        if (size == 0){
            return ans;
        }
        List<Integer> temp = new ArrayList<>();
        //从第一位开始， indx= 0
        bt(nums, ans, temp, size, 0);
        return ans;
    }
    
    private void bt(int[] nums, List<List<Integer>> ans, List<Integer> temp, int size, int idx){   //这里不会越界，因为for loop最多能加入nums里所有的东西    
        // if (temp.size() > size){
        //     return;
        // }
        for (int i = idx; i < size; i++){
            temp.add(nums[i]);
            //每走一步就加入这一步的东西
            ans.add(new ArrayList<>(temp));
            bt(nums, ans, temp, size, i + 1);
            //当走到底了就回溯
            temp.remove(temp.size() - 1);
        }
    }
}
```

# November 2nd New Problem BT
## 131 Palindrome Partitioning 
给定一个String， 找到所有的可以切分的可能的组合，在组合里的substring是一个palindrome。 
"aab" -> [[a,a,b],[aa, b]]
这道题不同于之前的是，之前是每次递归的pointer往后移动一位，就对那一位进行操作，然后深度结束回归到广度遍历的时候依旧是一位pointer一位数字进行加和或者添加。 现在是，深度的时候将单项找出来， 到了广度遍历的时候需要将string cat一下，例如a - > a' -> b， 返回到a'这一层，start = 1， i =2了， 之前直接去pointer = 2上面的 b就好了， 现在是要取start ~ pointer的所有substring： a'b， 然后这个时候发掘a'b不是palindrome那就不添加，这一层for loop结束循环， 返回到 a, 回溯， 进入a', 同理这个时候广度上的遍历是取到了substring， aa'。

​                                                                                                      null 

​    strt=0                                        a[a]  -> []                                                                                 a'  [aa']

strt=1                a' [a,a'] ->[a] loop to b         b    'ab' is not, return                             strt = 2 b  [aa', b]

strt= 2 b [a,a',b] -> [a, a'] return

return 

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        //defensive programming
        if (s == null || s.length() == 0){
            List<String> returning = new ArrayList<>();
            ans.add(returning);
            return ans;
        }
        List<String> temp = new ArrayList<>();
        int start = 0;
        bt(s, start, temp, ans);
        return ans;
    }
    private void bt(String s, int start, List<String> temp, List<List<String>> ans){
        if (start == s.length()){
            //if the starting point after one layer of dfs reaches the bottom, that means 
            //we have reached the leaf of this string/ the end of this string
            ans.add(new ArrayList<>(temp));
            return;
        }
        //index is just from start to legth
        for (int i = start; i < s.length(); i++){
            //because, we will add 1 to partition the substring
            //get the subString from this layer's start point to current pointer
            String thisSubString = s.substring(start, i + 1);
            if (isPalindrome(thisSubString)){
                temp.add(thisSubString);
                //depth first
                bt(s, i + 1, temp, ans);
                temp.remove(temp.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String subString){
        int l = 0;
        int r = subString.length() - 1;
        while (l < r){
            if (subString.charAt(l) != subString.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    //Slower
    private boolean isPali(String subString){
        String reversed = new StringBuilder(subString).reverse().toString();
        return reversed.equals(subString);
    }
}
```

## Permutation II
Given a collection of numbers that might contain duplicates, return all possible unique permutations.
Example:
Input: [1,1',2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
这道题考察点在于： **避重的前提是sort**， 以及如何避重。 combination sumIII中避重的方法是当index > start就说明这不是第一次查找这一层。 但是我们的这里每次的每层的循环都从0开始，start就不存在，那一开始是引入了depth变量，当i > depth的时候就continue， 这个对 第0层来说没有问题，因为当遇到1'的时候 index = 1 >0， continue， 但是当查找 2->1-1' 返回后，到了第一层 2 - > 1' ， 由于1'的index是1， 并不大于depth，这个就不会被continue， 反而重复算了。所以我们可以用hashcontains(i-1)来判断是否这一层是第一次查找，当一次递归返回后i -1的index就会被从hash里拿走，那这个时候 如果 nums[i-1] == nums[i] 且 i >0 and noHashContains就说明我们在该层遇到了重复值，那就continue

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0){
            ans.add(new ArrayList<Integer>());
            return ans;
        }
        
        Set<Integer> indexSeen = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        int depth = 0;
        Arrays.sort(nums); //重点！！ 去重的第一步是sort
        bt(nums, indexSeen, temp, ans, depth);
        return ans;
    }
    
    private void bt(int[] nums, Set<Integer> indexSeen, List<Integer> temp, List<List<Integer>> ans, int depth){
        //if the temp array already holds the same numbers of elements in the given nums
        //that just means we have find one combination
        if (temp.size() == nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (!indexSeen.contains(i)){
                //本来是用depth 来作为判断是否在同一层该pointer的数字重复了与否，
                //例如对于 1 1' 2来说， 跑回到第0层， 1'的index = 1 >0  就skip
                //但是这有一个bug在于当我们跑到了第一层的时候 2 -> 1(0),这个时候 1'的index == 1
                //那就不会被skip， 所以会出现两次 2 1 1,  2 1' 1
                //所以用indexSeen.contains(i-1)来判断，第0层第一次返回后hash里没有 1(0)了，然后
                //pointer跑去了1’， 这个时候发掘 1‘ == 1 且hash里没有0这个index，这个意味着这一层已经被查找过一次
                //而不是第一次查找， 这就取得了上面的那个同样的功效： 第一次查找不避重，第二次才避重
                if (i > 0 && nums[i] == nums[i-1] && !indexSeen.contains(i - 1)) {
                    continue;
                }
                indexSeen.add(i);
                temp.add(nums[i]);
                bt(nums, indexSeen, temp, ans, depth + 1);
                temp.remove(temp.size() - 1);
                indexSeen.remove(i);
            }
        }
    }
}
```

# November 3rd New Problem BT

## 1066 Campus bike

给定了 bikes M个的坐标系[[x1,y1],[x2,y2]] 以及N个work的坐标系。 M>=N. 问你如何分配使得 员工到bike的L1距离总和最小，返回距离和

思想是，由于bike大于worker，我们可以遍历所有bike并且assign当下bike给每一个员工找到 C（m，n）个所有组合，然后每次dfs查找时，如果发觉此刻已经找到了员工最后一位，说明这一次assign结束了，那就比对一下此刻的 disSum 和global min的大小，然后return， 但是如果中途的disSum已经大于global sum了，那就说明可以直接return了

举个例子  bikes = [[1,2],[3,3]],  workers = [[0,0],[2,1]]

​                                                                                   null

​                                                   车子1                                                   车子2

​                                     work1        or     work2                                work1 or work2

所以在广度遍历的时候就和permutation类似，因为每一个人都可以拿到所有车子的可能性，和combinationsum不同的是 这一位数字加上了后 下一层这个数字不可以再用，只能用下一个数字了，所以一定也是从0开始遍历，但是要加入一个visited的变量

```java
class Solution {
    int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        int m = bikes.length;
        //用来记录这个车子用过没有，和combination一个套路
        boolean[] used = new boolean[m];
        //从worker0开始，然后此刻的sum = 0
        dfs(0, workers, bikes, 0, used);
        return min;
    }
    
    private void dfs(int workerIndex, int[][] workers, int[][] bikes, int sum, boolean[] used){
        //如果此刻我们已经找到了最后一个worker了，那就比对一下minvalue
        if (workerIndex >= workers.length){
            min = Math.min(sum, min);
            return;
        }
        //但是如果中途大于min了，那说明这分配已经不对了
        if (sum > min){
            return;
        }
        //将所有的bike都遍历一次，这个是广度
        for (int i = 0; i < bikes.length; i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            // sum =  sum + dis(workers[workerIndex], bikes[i]);
            dfs(workerIndex + 1, workers, bikes, sum + dis(workers[workerIndex], bikes[i]), used);
            used[i] = false;
        }
    }
    public int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
```

## 90. Subset II
区别于subsetI的是，现在给定的数组里会有duplicates，但依旧是要找到这个数组里连续的subsets'''
```
Input: [1,2,2]
Output:
[
  [1],
  [1,2],
  [1,2,2],
  [1,2']
  [2],
  [2,2']
  []
]
```

那相同的思想是每次在深度查找时，都是往后查一位数， start = i +1, start init with 0, 然后呢每次temp加入新的后就给ans里加temp， 但现在有了duplicates， 所以我们首先需要sort array， 在一层深度结束后到了广度运算的时候，我们可以判断当前index > start与否，表示这一层已经是第二次查找了，所以这个时候nums[i]== nums[i-1]我们就直到这个duolicates得被skipped

str=0                                           *[1]*          **1** *->[]*       ->          *[2]*           **2**  *->[]*                         **2'** skipped

str=1                                    *[1 2]*      **2** ->*[1]*      **2'** skipped     *[2,2']* **2'**      *[2]* return

str=2                              *[1 2 2']*   **2'**   *-> [1,2]* return                       return 

​                                            return  

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //preadding an empty array to the ans array
        ans.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0){
            return ans;
        }
        int start = 0;
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, start, temp, ans);
        return ans;
    }
    
    private void dfs(int[] nums, int start, List<Integer> temp, List<List<Integer>> ans){   
        if (temp.size() >= nums.length){
            return;
        }
        for (int i = start; i < nums.length; i++){
            if (i > start && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            ans.add(new ArrayList<>(temp));
            //一定要注意是 i + 1 !!! 不是 start + 1
            dfs(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}
```

## 784. Letter Case Permutation
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create. 

重点是： 我们的String是immutable的，所以为了操作需要变成chararray， 然后在append去ans的时候做一个charArray到string的操作 ``new String(charArray) `` 除此之外如果用之前的套路 for 循环在广度上做遍历，会出现一个重复的问题。所以我们可以直接call 函数，在当前为ltter的时候做一次permutation，然后递归，再bt， 然后呢bt后 其实我们返回到了同一层，这个时候再在非letter的情况下又call一次permutation， 就可以在同一层级以同一个pivot传入下一层递归，但是传入的charArray一个是upper的 另一个是 lower的

```
Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
```

```java
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        if (S == null || S.length() == 0){
            return ans;
        }
        char[] chars = S.toCharArray();
        bt(chars,0, ans);
        return ans;
    }
    
    private void bt(char[] chars, int index, List<String> ans){
        if (index == chars.length){
            String returning = new String(chars);
            ans.add(returning);
        } else {
            //如果此刻是一个letter 那么我们需要permutation
            if (Character.isLetter(chars[index])){
                chars[index] = Character.toUpperCase(chars[index]);
                //例子： 传入了 [A1B2], 现在pointer在2
                bt(chars, index + 1, ans);
                //当这一层返回后，复原这一层被改变的
                chars[index] = Character.toLowerCase(chars[index]);
            }
            //那么此刻如果这一层后面还有， 且是数字的， 那么我们再进行一次深度查找，但此刻需要注意的是
            //尽管我们再对同一个pivot做了深度，此数位上的这个元素再上面if里的被upper又lower回来了，所以
            //char array里的元素是不同的
            //传入了[A1b2],pinter还是在2
            bt(chars, index + 1, ans);
        }
    }
}
```

# November 4th New problem BackTrack
## 52. NQueenII
在一个n by n的棋盘上面放nQueen 问有多少个摆法使得这些queens不会打架。 要求是 每一个row， 每一个列， 每一个对角线都只有一个Queen。 那么思想很直接，那就是递归的时候，每次对下一行做处理，这就保证了row之间不存在重复，然后呢每一列我们用hash来判断这一列是否出现过，对角线来看， 从左上往右下走， j - i 是相同的， 从右上往左下走呢， i + j是相同的，所以我们还需要每一层记录一次这一层的 ijsum 和 jideduction， 那么在下一层就可以判断是否出现过了

```
Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
```

**以下实现存在一定bug，原因是只判断了是否与上一刻的sum 和 deduction的差别，而不是全局判断。 所以不该用stack 而是 hash来记录summation 和 deduction**

```java
class Solution {
    public int ans = 0;
    public int totalNQueens(int n) {
        if (n <= 0){
            return ans;
        }
        
        Set<Integer> colSeen = new HashSet<Integer>();
        Stack<Integer> summation = new Stack<Integer>();
        Stack<Integer> deduction = new Stack<Integer>();
        int rowIndex = 0; //用来记录下一次去哪一行
        int colIndex = 0;//用来记录上一刻在哪一列，以便于这一层判断我现在的j是往左还是往右
        bt(n, colSeen, summation, deduction, rowIndex, colIndex);
        return ans;
    }
    private void bt(int n, Set<Integer> colSeen, Stack<Integer> summation, Stack<Integer> deduction, int rowIndex, int colIndex){
        if (rowIndex == n){
            System.out.println("here");
            ans += 1;
            return;
            // return true;
        }
        //开始遍历列
        for (int j = 0; j < n; j++){
            if (!colSeen.contains(j)){
                int currentSum = j + rowIndex;
                int currentDeduction = j - rowIndex;
                //如果我们现在往右边走，那就得判断是否此刻的差等同于上一刻的差
                if (!summation.isEmpty() && j > colIndex && currentDeduction == deduction.peek()) continue;
                //如果我们现在往左走，那就得判断是否此刻的和等同于上一刻的和
                if (!deduction.isEmpty() && j < colIndex && currentSum == summation.peek()) continue;
                //如果上面的判断都可以了，那说明这一刻走的路子是对的
                //将其这一列放入hash里
                colSeen.add(j);
                //同样需要记录这一刻的 col + row 和 col - row
                summation.add(currentSum);
                deduction.add(currentDeduction);
                bt(n, colSeen, summation, deduction, rowIndex + 1, j);
                colSeen.remove(j);
                summation.pop();
                deduction.pop();   
            }
        }
    }
}
```

那其实最好的实现是直接每次for 循环的时候判断三个hash里是否有重复的出现，如果没有说明这一步走对了。

```java
class Solution {
    private int ans = 0;
    public int totalNQueens(int n) {
        if (n <= 0){
            return ans;
        }
        Set<Integer> colSeen = new HashSet<>();
        Set<Integer> sumSeen = new HashSet<>();
        Set<Integer> deductionSeen = new HashSet<>();
        int rowIndex = 0;
        bt(n, colSeen, sumSeen, deductionSeen, rowIndex);
        return ans;
     }
    
    private void bt(int n, Set<Integer> colSeen, Set<Integer> sumSeen, Set<Integer> deductionSeen, int rowIndex){
        if (rowIndex == n){
            ans++;
            return;
        }
        //对每一个col开始进行遍历，且start == 0， 因为每一层都有相同的combinations
        for (int j = 0; j < n; j++){
            int currentSum = j + rowIndex;
            int currentDeduction = j - rowIndex;
            if (!colSeen.contains(j) && !sumSeen.contains(currentSum) && !deductionSeen.contains(currentDeduction)){
                //meaning this is a valid move
                colSeen.add(j);
                sumSeen.add(currentSum);
                deductionSeen.add(currentDeduction);
                bt(n, colSeen, sumSeen, deductionSeen, rowIndex + 1);
                colSeen.remove(j);
                sumSeen.remove(currentSum);
                deductionSeen.remove(currentDeduction);
            }
        }
    }
}
```

