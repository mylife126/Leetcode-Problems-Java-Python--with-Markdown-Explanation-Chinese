
'''

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

[1 2 3 4 5 6] -> 3 4 5 6 1 2                  target = 2
1。 binary search 找到mid后对比mid和start的大小，
   a  如果mid 》 start，则表示 start ~ mid这一段是依旧sorted [1 2 3 4 5 6] -> 3 4 5 6 1 2 
       这个时候如果target小于mid 且大于 start，则end = mid ， 反之start = mid - 1
   b  反之则是 mid ~ end sorted  [1 2 3 4 5 6] -> 6 1 2 3 4 5 
      如果这时候 target 大于mid 且小于end， 则start = mid， 反之end = mid + 1
    
2. 如果找到直接返回，不然-1
'''


class Solution(object):
    def search(self, nums, target):
        if not nums:
            return -1
        start = 0 
        end = len(nums) - 1
        
        while(start <= end):
            mid = (end + start) // 2
            midNum = nums[mid]
            
            if midNum == target:
                return mid
            
            #注意这里得是 >= 不然有一个cornercase [3, 1], target = 1
            if midNum >= nums[start]:  #meaning from start ~ mid it is still sorted
                if target < midNum and target >= nums[start]:
                    end = mid - 1
                else:
                    start = mid + 1
            else:                     #meaning from mid ~ end, it is still sorted
                if target > midNum and target <= nums[end]:
                    start = mid + 1
                else:
                    end = mid - 1
        
        return -1
                
        







































