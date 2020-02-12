'''
Input: "aabb"
Output: ["abba", "baab"]

1. 记录hashmap，所有char的出现的次数
2. 判断是否满足有且仅有一个奇数次的char
3. 满足后，将所有的char放入一个array
4. 对array里的char进行dfs permuation， 引入seen的set， 每次都以每一个char做一次pivot，然后从0遍历到尾巴（除了自己）来建立pali
'''
class Solution(object):                
    def generatePalindromes(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        if not s:
            return []
        
        from collections import defaultdict
        myHash = defaultdict(int)
        #record each character
        for c in s:
            myHash[c] +=1 
        '''justify if the current string can be used to make a palidrome
           and to find which odd character is to be used later'''
        countOdd = 0
        hasOdd = False
        whichOdd = None
        canMakePali = True
        for c in myHash:
            if (myHash[c] % 2 != 0):
                countOdd +=1
                hasOdd = True
                whichOdd = c
            #should only have one char to be occurred in the odd time
            if (countOdd > 1):
                canMakePali = False
                break
        #if the current string cannot be used to make a palidrome, then return an empty list
        if not canMakePali:
            return []
        
        '''find all permutation of the characters in the hash:
        ab -> a b 
           -> b a
        and use these as the order for hashmap's traversal   
        '''
        allCharSet = [c for c in myHash]
        permutation = []
        seen = set()
        temp = []
        self.bt(allCharSet, permutation, seen, temp) 
        
        #traverse all the letter order in the list, and use this order to build the string
        ans = set()
        for combination in permutation:
            string = ""
            for c in combination:
                howMany = myHash[c]
                adding = howMany // 2
                for i in range(adding):
                    string += c
            #if we have odd character, then we add the odd char to the center,  aaa b 
            #and reverse all other character before the b   aaa b aaa
            if hasOdd:
                string += whichOdd
                string += string[-2::-1] #reverse the string starting from the char before middle 
            else:
                string += string[::-1]   #if no such odd char, we just flip everything
            
            ans.add(string)
        return ans
            
    def bt(self, allCharSet,permutation, seen, temp):
        '''[a, b ,c]
           [a, c, b]
           [b, a, c]
           [b, c, a]
           [c, a, b]
           [c, a, b]'''
        
        #if the temp array has contained the same length of the original list, then means we have found one permutation
        if len(temp) == len(allCharSet):
            permutation.append(temp[::])
            return 
            
        for i in range(len(allCharSet)):
            if i not in seen:
                temp.append(allCharSet[i])
                seen.add(i)
                self.bt(allCharSet, permutation, seen, temp)
                temp.pop()
                seen.remove(i)
                
            
        
        