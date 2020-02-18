'''
hashMap, 

每次把string sort一下，这样sort后的strin是一样的，把他作为key， 相同的key的word放一起即可
'''

from collections import defaultdict
class Solution(object):
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        if not strs:
            return []
        
        myHash = defaultdict(list)
        for string in strs:                   #O(n)
            newString = sorted(string)        #klog(k) , k is the maximum length of word    => overall nklogk
            newString = "".join(newString)    #重点，需要join一次，不然是一个list！！！
            myHash[newString].append(string)
            
        ans = []
        for key in myHash.keys():
            ans.append(myHash[key])
            
        return ans
        