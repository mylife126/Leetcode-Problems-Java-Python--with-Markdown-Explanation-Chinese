'''
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

意思是找到第n个uglynumber。

那么我们发觉所有的uglynumber都是从 2， 3， 5为倍数generate出来的，且基数也是个ugly number。因为非ugly number一定会有一个非ugly的factor乘以一个基数，但是我们现在只用由ugly factor联乘出来的数字做基数，乘以的倍数也都是ugly factor，那肯定只能是ugly number了!

例如
1 -> 2, 3, 5
2 -> 4, 6, 10
3 -> 6, 9, 15

那么我们可以从1开始放入priorityqueue里，每次都pop出的当前最小，然用这个最小来乘以所有的倍数来generate出所有
可能的新的ugly number， 并且放入pq中。 那么下一次pop出来的基数，又是当下最小值。
我们循环这个过程，不断加count， 当count == n的时候，这个操作终止。 返回最后pop出来的uglynumber即可

但是最重要的是，我们在generate的过程里会有重复的，需要skip这个部分，不然pq会pop出重复的，使得counts出问题。
'''
import heapq
class Solution(object):
    def nthUglyNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        #generate all the ugly numbers
        #因为n <= 1690 所以我们知道一个range，那这样就可以generate所有小于1690的数他们的倍数都是由 2 3 5 组成的
        #1是特殊情况，特殊处理
        
        count = 0
        which = None   #记录最后一个出现的ugly number       
        seen = set()   #去重
        seen.add(1)
        
        pq = []
        heapq.heappush(pq, 1)  #init pq
        
        factors = [2, 3, 5]    #倍数
        while(True):
            curr = heapq.heappop(pq)  #每次循环里都是pop最小基数，再以它来generate 新的 ugly number
            count += 1
            which = curr
            if count == n:
                break
            for prime in factors:
                newValid = curr * prime
                if newValid not in seen:
                    seen.add(newValid)
                    heapq.heappush(pq, newValid)
        
        return which
            
            