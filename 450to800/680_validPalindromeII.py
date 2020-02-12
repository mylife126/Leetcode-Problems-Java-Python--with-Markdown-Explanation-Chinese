'''Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.


思想是two pointer，收尾看一遍，一旦发觉不match的时候，我们有两个选择的可能性，那就是left+=1 而 right不变，然后继续搜一次，一旦再次出现不对称的情况，则说明不对称的情况发生了两次，return false，

但是这样有一个corner case: "ebcbbececabbacecbbcbe"
例如上述，如果我们优先改变right -= 1， left不变的话，发觉很快又出现了第二次unmatch，我们就return了false，
但是如果我们查看left+=1 而 right不变的话， 则不会有事情。

所以我们需要一个helper函数来同时检测左边和右边，最后return checkLeft or checkright

'''
class Solution(object):
    def validPalindrome(self, s):
        """
        :type s: str
        :rtype: bool
        """
        # abccbea
        if not s:
            return False
        left = 0
        right = len(s) - 1
        count = 0
        while(left <= right):
            top = s[left]
            end = s[right]
            # print(top, end)
            if top == end:
                left  +=1
                right -= 1
            #一旦出现了不对成的情况，就说明这一位（right or left）是可以删掉的，记为修改一次
            #所以需要将其余的情况全部遍历一遍
            else:
                #查看一次如果选择删掉此刻rightpointer上的字符
                checkRight = self.isValid(s, left, right - 1)
                #再尝试一次删掉此刻leftpoint上的字符
                checkLeft = self.isValid(s, left + 1, right)
                return checkRight or checkLeft
        return True
    
    def isValid(self, s, i, j):
        while (i <= j):
            if (s[i] == s[j]):
                i += 1
                j -= 1
            else:
                #因为trigger这个helper函数的情况是已经出现过一次mismatch了，所以一旦出现不对称则return false
                return False
        return True