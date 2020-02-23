'''
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N       builder1
A   L S  I G       builder2
Y A   H R          builder3
P     I            builder4

题目的意思是，我们的原来的string是按照从上到下append的， 
p
|
A
|
Y
|
p

当append到尾巴的时候，我们再往上走，但是是斜上一位也就是numRows - 2的地方再从下网上append，直到第一位builder， 然后再从0往下
p     I
|     |
A   L S
|   | |
Y  A  H
| /   |
p/    I


最后把四个row连在一起return ： PIN + ALSIG + YAHR + PI

那么我们可以构建一个stringBuilder array, 它的长度就是nRows， 每一个builder负责一行
所以我们现在可以先从上往下append char， 然后到了尾巴后， 我们再从倒数第二个builder开始继续append char 直到第一位builder。 然后把builderIdx归到第0位，再继续append char。 

直到charPointer达到了maxmium string length即可。

最后把每一个stringbuider 加在一起就好了
'''
class Solution(object):
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        #建立n个builder， 每一个builder负责一行的string appending
        builderArray = ['' for i in range(numRows)]
        #追踪用哪一个builder
        builderId = 0
        #追踪append第几个char
        charPointer = 0
        length = len(s)
        
        while(charPointer < length):
            #先从上到下， 且要保证charPointer 《 length！
            while(builderId <numRows and charPointer < length):
                builderArray[builderId] += s[charPointer]
                charPointer += 1
                builderId += 1
            
            # print(builderId)
            #当我门从上到下结束后我们需要再从下到上但是要满足zigzag， 所以从builder的倒数第二个开始
            builderId = numRows - 2
            while(builderId >= 1 and charPointer < length):
                builderArray[builderId] += s[charPointer]
                charPointer += 1
                #》=1 保证了当我们builderPointer == 1的时候依旧进入这个循环 再给第一行的builder append后， -=-， 归位到0
                builderId -= 1
        
        #最后一部是把builderArray里的所有stringBuilder concat一起即可
        ans = ''
        for builder in builderArray:
            ans += builder
        
        return ans