'''
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.

这一题是要求求一个正整数的sqrt，且round off到整数。

那么我们知道一个数的sqrt一定小于这个数，所以我们可以遍历所有小于这个数的数，然后计算power2， 如果 == target直接返回num，如果 > target直接返回 num -1


但这个是最brutal的。 

solution2 是 binary search来找这个合适的数字
'''
class Solution:
    def mySqrt(self, x: int) -> int:
        if x == 0:
            return 0
        
        for num in range(x + 1):
            power = num**2
            if power == x:
                return num
            if power > x:
                return num-1
            
            
            
'''Binary Search Solution'''
class Solution:
    def mySqrt(self, x):
        left = 0
        right = x
        
        while(left + 1 < right):
            mid = (right + left) // 2
            if (mid ** 2 == x):
                return mid
            
            if (mid ** 2 > x):
                right = mid
            
            if (mid**2 < x):
                left = mid
        
        
        if ((x - right ** 2) >= 0 and (x - right ** 2) < (x - left ** 2)):
            return right
        
        return left
    
'''gradient descent descent based solution
这个方法可以找到一个最佳的近似值。 我们用gradient descent

f(x) = x^1/2    => gradient = 1/2 * x^ -1/2

a = x 
error = a **2 - x

while (Error/ epislon < t):
    a = a - lr * g
    error = a**2 - x

'''

class Solution:
    def mySqrt(self, x):
        epsilon = 1e-8
        target = x
        x0 = 0.1
        error = x0**2 - target
        lr = 1e-6

        while(error / target > epsilon):
            x0 = x0 - lr * (2 * x0)
            error = x0**2 - target
            # print(a)
        return x0
    # def mySqrt2(self, x):
    #     smaller = False
    #     if x < 1:
    #         smaller = True
    #         x = 1/ x

    #     x0 = x
    #     error = x0**2 - x
    #     lr = 0.01
    #     epislon = 1e-6
    #     while (error / x > epislon):
    #         x0 -= lr * (1/2 * x0 **(-1/2))
    #         error = x0 ** 2 - x
    #         print(error)
            
    #     if smaller:
    #         return 1/x0

    #     return x0
    
if __name__ == '__main__':
    solver = Solution()
    print(solver.mySqrt(1e-4))