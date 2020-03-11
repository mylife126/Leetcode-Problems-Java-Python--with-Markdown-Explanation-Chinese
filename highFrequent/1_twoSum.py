'''
我们需要一个有记忆的机制，因为假设现在我们的数字是2， target是9， 那么我们要找的是7， 但现在我们不知道后面有没有7， 所以先把2存起来，如果遇到了7，则对于7来说它的remainder是2，2我们已经用记忆记住了，所以可以直接调取。

算法：
循环数组：
1. remain = target - now， 如果ramin出现在hash里调出这个remain的indx， return 结果
2. 直到循环结束return ans

'''
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        if (not nums):
            return []
        
        ans = []
        where = dict()        
        for i in range(len(nums)):
            now = nums[i]
            remain = target - now
            if remain in where:
                ans.append(where[remain])
                ans.append(i)
                return ans
            where[now] = i
        return ans
                
        
        