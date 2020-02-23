'''
0 1  2 3 4 5 6 7   8
E 10 9 2 5 3 7 101 18
1  1
     1 
       1
         1 => 2 < 5: dp[3] + 1 = 2
            1 => 2 < 3: dp[3] + 1 = 2
              1 => 3 < 7 : dp[5] + 1 = 3 ; 5 < 7: dp[4] + 1=  3; 2 < 7 : dp[3] + 1 = 3
                1 => 7 > 101 : dp[6] + 1 = 4
以每一个数位为endNumber， state function存的是以这个数为end的话，能得到最大的subsequence长度。
因为长度是可以累加的， 所以我们用dp来处理
'''
class Solution(object):
    def lengthOfLIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0
        
        length = 0
        #1 state function and init, 一开始每一个end的长度都为1
        dp = [1 for i in range(len(nums) + 1)]
        
        #3 transfer function
        #dp的idx比数组的idx大一， 所以我们从1开始遍历
        for i in range(1, len(nums) + 1):
            whichNum = nums[i - 1]
            for j in range(1, i):
                if nums[j - 1] < nums[i - 1]:
                    dp[i] = max(dp[i], dp[j] + 1)
            
            length = max(length, dp[i])
            
        return length
        