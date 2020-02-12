from random import choice 
class RandomizedSet(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.map = dict()
        self.array = []
        
    def insert(self, val):
        """
        Inserts a value to the set. Returns true if the set did not already contain the specified element.
        :type val: int
        :rtype: bool
        """
        if val not in self.map:
            self.map[val] = len(self.array)
            self.array.append(val)
            return True
        return False
        
    def remove(self, val):
        """
        Removes a value from the set. Returns true if the set contained the specified element.
        :type val: int
        :rtype: bool
        """
        '''思路是，如果把array中的一个数字删掉了， 那么会改变原始array的长度，以及所有element再被删的element之后的，所以
        我们可以replace被删掉的那一位，用最后一位数，因为最后一位数的indx是可在下一刻添加新的数字的时候改正的'''
        if val in self.map:
            where = self.map[val]
            lastDigit = self.array[len(self.array) - 1]
            self.array[where] = lastDigit
            self.map[lastDigit] = where
            del self.map[val]
            self.array.pop()
            return True
        return False
            
    def getRandom(self):
        """
        Get a random element from the set.
        :rtype: int
        """
        return choice(self.array)
        


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()