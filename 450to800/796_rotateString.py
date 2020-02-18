
'''
每一次循环将A的首字母放在A的尾， check是否能这样操作后使得A == B

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false

abcdeabced包含了所有可能的substring， 那么如果B存在于这个里面 则成立

'''
class Solution(object):
    def rotateString(self, A, B):
        """
        :type A: str
        :type B: str
        :rtype: bool
        """
        if len(A) != len(B):
            return False
        
        fullCombo = A + A
        # print(fullCombo.index(B))
        return B in fullCombo
        
        
        