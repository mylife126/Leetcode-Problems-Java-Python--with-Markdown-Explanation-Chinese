'''
思路是： 木桶原理，一个地方能储存的水量取决于其左右最矮的那一项， 且那一项要高于自己的高度

3 passes
1. 第一个pass扫描每一个位置的左边的最高的高度
2. 第二个pass扫描每一个位置的右边的最高的高度
3. 第三个pass，对于每一个地方做一次木桶原理， min（left，right）取到最矮的那个bar， 然后diff = bar - 自己。 
   那么如果这个diff < 0 则说明这里存不了水
   或者 rain += diff

最后返回rain即可
'''

class Solution:
    def trap(self, height: List[int]) -> int:
        if not height:
            return 0
        
        left2right = [0 for i in range(len(height))]
        right2left = [0 for i in range(len(height))]
        
        leftMax = 0
        rightMax = 0
        
        for i in range(1, len(height)):
            leftMax = max(height[i - 1], leftMax)
            left2right[i] = leftMax
        
        for i in range(len(height) - 2, -1, -1):
            rightMax = max(height[i + 1], rightMax)
            right2left[i] = rightMax
            
        rain = 0
        
        for i in range(len(height)):
            itsLeft = left2right[i]
            itsRight = right2left[i]
            thisHeight = height[i]
            
            bar = min(itsLeft, itsRight)
            diff = bar - thisHeight
            if diff > 0:
                rain += diff
                
        return rain