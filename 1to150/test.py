"""
Results: 1, 2, 3, 4, 5, ... 
c = Counter()
c.inc() -> 1
c.inc() -> 2
c.inc() -> 3
...

'a', 'b', ..., 'z', 'aa', 'ab', ..., 'az', 'ba', 'bb'

ord('a') -> 97
chr(97) -> 'a'
"""

class Counter(object):
    def __init__(self):
        self.count = 0
    
    def inc(self):
        self.count += 1
        letter = self.converter()
        return letter
        
    def converter(self, testNumber, mode):
        number = None
        if mode == 'testing':
            number = testNumber
        else:    
            number = self.count     # 26
        result = ''
        changer = ord('a')          # 97 
        
        while number > 0:
            y  = (number - 1) % 26     #get last one -> 25
            number = (number - 1) // 26 #get the rest
            
            char = chr(y + changer)   
            result = ''.join((char, result))
        
        return result 
            

# num = 52 = 2 * 26 -> 'az'
# num = 3 * 26 -> bz
# num = 26*26  -> yz
# num = 27*26 -> zz
# num = 27*26+1 -> 

#{1: a, 2 :b }
solver = Counter()

myHash = {1: 'a', 2: 'b'}
tester = [1,2]

for number in tester:
    assert solver.converter(number, 'testing') == myHash[number], "Wrong output, the correct is {}".format(myHash[number])

        
        
        
    
