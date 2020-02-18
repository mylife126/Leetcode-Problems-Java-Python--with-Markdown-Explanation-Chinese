'''
我们要找的是pivot，也就是rotate的哪个点，那个点一定是最小值
那么这有一个特征：
1. mid + 1 小于mid: 4 5 [6] 1 2 3   所以return mid + 1
2. pivot就在mid ：  mid - 1 > mid and mid < mid + 1       5 6 [1] 2 3 4   所以return mid
3. array本身就是sorted，没有发生旋转的， 那就直接返回 nums[start] 即可     


1 2 3 4 5 6 -> 4 5 6 1 2 3        mid = 6, start ~ mid sorted 那pivot一定在mid之后，我们要找的再mid之后
1 2 3 4 5 6 -> 6 1 2 3 4 5        mid = 2, mid ~ end sorted 那我们要找的在mid之前

'''


class Solution(object):
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return -1
        
        if len(nums) == 1:
            return nums[0]
        
        start = 0
        end = len(nums) - 1
        
        while(start <= end):
            mid = (start + end) // 2
            midNum = nums[mid]
            
            #case 1 数组本来就是sorted 并没有发生旋转，那就直接return 第一个
            if (midNum >= nums[start] and midNum <= nums[end]):
                return nums[start]
            
            #case 2 check if mid is already a pivot 
            if (mid - 1 >= 0 and mid + 1 < len(nums) and midNum < nums[mid- 1] and midNum < nums[mid + 1]):
                return midNum
            
            #case 3 midnumber后一位小于了midNum，那后一位一定是最小的，因为如果数组本身是sorted， 它后面的一定是大的，但现在小了，就说明后面一位是pivot
            if (mid + 1 < len(nums) and nums[mid + 1] < midNum):
                return nums[mid + 1]
            
            #case 2, start ~ mid sorted
            if (midNum >= nums[start]):
                #check                
                start = mid + 1
            
            else:
                end = mid - 1
        
        return -1
        
        