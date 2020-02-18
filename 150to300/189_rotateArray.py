class Solution(object):
    def rotate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        if not nums or k == 0:
            return nums
        copy = nums[::]
        for i in range(len(nums)):
            ithVal = copy[i]
            moveTo = (i + k) % len(nums)
            nums[moveTo] = ithVal
        
        
        