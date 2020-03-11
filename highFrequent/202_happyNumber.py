'''
Happy Number 定义是不断循环将每一个数位的数字的平方加起来，如果能等于1，则是happy number， 否则不是
'''
class Solution:
    def cal(self, n):
        runningSum = 0
        while(n > 0):
            lastDigit = n % 10
            runningSum += lastDigit**2
            n = n//10 
        return runningSum
    
    def isHappy(self, n: int) -> bool:
        '''
        20
        
        4 + 0 = 4
        16 + 0 = 16
        1 + 36 = 37
        9 + 49 = 58
        25 + 64 = 89
        64 + 81 = 145
        1 + 16 + 25 = 17 + 25 = 42
        16 + 4 = 20    重复！ False
        '''
        if n == 0 :
            return False
        if n == 1:
            return True
        
        mySet = set()
        mySet.add(n)
        
        ans = False
        while(True):
            n = self.cal(n)
            if n == 1:
                ans = True
                break
            if n not in mySet:
                mySet.add(n)
            else:
                return False
        return ans
            
        
        