'''
现在的街道是一个环，那么抢了第一个屋子就不能抢最后的屋子。

所以我们建立两个dparray， 一个是抢了第一个的， 另一个是没抢的，

然后按照： 我抢了这个屋子就不能抢前面的屋子的原则做transfer

'''

class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0
        
        dpRob1st   = [0 for i in range(len(nums) + 1)]
        dpRob1st[1] = nums[0]
        dpRobNo1st = [0 for i in range(len(nums) + 1)]
        
        for i in range(2, len(nums) + 1):
            currValue = nums[i - 1]
            if i == len(nums):
                currValue = 0
                
            dpRob1st[i] = max(dpRob1st[i - 1], dpRob1st[i - 2]+ currValue)
            dpRobNo1st[i] = max(dpRobNo1st[i - 1], dpRobNo1st[i - 2] + nums[i - 1])
        
        return max(dpRob1st[len(nums)], dpRobNo1st[len(nums)])
            
        
        
        