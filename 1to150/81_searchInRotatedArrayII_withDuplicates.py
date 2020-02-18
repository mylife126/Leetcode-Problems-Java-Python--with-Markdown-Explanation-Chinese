'''
相比于没有重复值的rotated array来说，有了重复值会出现一个worst case， 那就是 mid == start， 那么这个时候我们就没法判断到底
sorted那部分是在左半边还是在右半边了。 如下情况：

1 1 1 1 1 1 1 2 2 2   -> 1 1 1 1 1 2 2 2 1 
                      ->   1 1 1 1 2 2 2 1 
                      ->     1 1 1 2 2 2 1  

所以我们可以检测一次，如果此刻的mid == start，那么我们就该start++, continue这一次，直到一刻我们的mid > start了即可。


'''

class Solution(object):
    def search(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: bool
        """
        if not nums:
            return False
        start = 0
        end = len(nums) - 1
        while(start <= end):
            mid = (start + end) //2
            midNum = nums[mid]
            
            if midNum == target:
                return True
            
            #如果出现了重复导致的没法判断哪一边是sorted话，我们跳过这一项start
            if midNum == nums[start]:
                start += 1
                continue
            
            if midNum > nums[start]: #meaning from start ~ mid it is still sorted
                if target < midNum and target >= nums[start]:
                    end = mid - 1
                else:
                    start = mid + 1
            else:
                if target > midNum and target <= nums[end]:
                    start = mid + 1
                else:
                    end = mid - 1
        return False
                
                
        