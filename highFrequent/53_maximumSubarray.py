'''
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.



'''
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        if not nums:
            return 0
        
        #corner Case: 不能赋值为0， 因为存在一个case，nums= [-1], return 应该是-1 而不是0
        localMax = nums[0] 
        globalMax = nums[0]
        
        for num in nums[1::]:
            currSum = localMax + num
            localMax = max(currSum, num)
            globalMax = max(localMax, globalMax)
            
        return globalMax