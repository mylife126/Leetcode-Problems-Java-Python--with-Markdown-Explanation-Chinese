'''
Two Pass solution
[1, 1, 1]  k = 2

prefixSum [0, 1, 2, 3]

iter  prefixSum supposedS2  found
0         0         [2]      
1         1         [3]
2        [2]         4       yes    count++
3        [3]         5       yest   count++

|------|--------------|
      s1---target-----s2     s1 + target = s2
      
      
但是我们很可能在数组里存在负数，那么supposedS2就会有多次出现，也就意味着，如果在某一刻的s1 == supposedS2了，我们其实是找到了多个interval满足此case，所以我们的hash的key是 supposedS2, value是出现了多少次。 如果s1出现在了hash里，我们加上hash对应的value.

1. 建立prefixSumarray， 来记录所有位置下的runningsum的情况
2. 对prefixsumarray里的每一个值当作基， 计算以他为pivot 加上 target应该能得到的s2， 然后将这个supposedS2 放入 hash里， value是出现的次数
3. 如果在某一刻的s1出现在hash里说明我们找到了一个一个s2 == 某一时刻的s1 + target了。 所以就是找到了合法的interval， count += hash【s2】
4. 返回count
'''

class Solution(object):
    def subarraySum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        if not nums:
            return -1
        
        count = 0
        runningSum = 0
        prefixSumArray = [0]
        for num in nums:
            runningSum += num
            prefixSumArray.append(runningSum)

        myHash = {}
        for i in range(len(prefixSumArray)):
            s1 = prefixSumArray[i]
            supposedS2 = s1 + k
                            
            if s1 in myHash:
                count += myHash[s1]

            if supposedS2 not in myHash:
                myHash[supposedS2]  = 1
            else:
                myHash[supposedS2] += 1
                                
        return count
    
'''One pase solution'''
                
class Solution(object):
    def subarraySum(self, nums, k):
        if not nums:
            return -1
        
        count = 0 
        runningSum = 0
        myHash = {}
        myHash[runningSum + k] = 1    #记录从0开始的supposedS2
        for num in nums: 
            runningSum += num         #s1
            supposedS2 = runningSum + k
            if runningSum in myHash:  #当s1用作后一时刻的s2的时候，如果存在于hash说明上个某个时刻出现过这个s2
                count += myHash[runningSum]
            if supposedS2 not in myHash:
                myHash[supposedS2] = 1
            else:
                myHash[supposedS2] +=1
                
        return count
            