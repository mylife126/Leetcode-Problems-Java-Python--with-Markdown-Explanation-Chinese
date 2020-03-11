'''
Input: "()"
Output: true

'''
class Solution:
    def isValid(self, s: str) -> bool:
        if not s:
            return True
        
        myHash = {'(' : ')', '[' : ']', '{' : '}'}
        myStack = []
        
        for char in s:
            if char in myHash:
                myStack.append(char)
            else:
                #corner case: 如果"}" 这样的case，已经不合法了，直接false
                if len(myStack) != 0:
                    lastChar = myStack.pop()
                    pair = myHash[lastChar]
                    if pair != char:
                        return False
                else:
                    return False
        #corner case， 如果"((" 上述判断不会进入else， 但依旧不合法，所以false      
        return True if len(myStack) == 0 else False
        