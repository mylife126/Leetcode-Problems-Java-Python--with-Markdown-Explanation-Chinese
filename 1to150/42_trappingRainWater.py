'''
建立俩个array
1. 记录每一个indx下的左边的水桶的最大高度
2. 记录每一个indx下的右边的水桶的最大高度
3. one pass每一个indx下自己的高度， 检查它的左边的高度， 它的右边的告诉，查看是否左右的最低的依旧大于自己的高度，如果是的用最低的减去自己
'''

class Solution(object):
    def trap(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        if not height:
            return 0
        
        #Python里需要这样建立一个array
        left2right = [0 for i in range(len(height))]
        right2left = [0 for i in range(len(height))]
        
        leftMax = 0
        rightMax = 0
        
        for i in range(len(height)):
            if i == 0:
                left2right[i] = 0
                continue
                
            leftHeight = height[i - 1]
            leftMax = max(leftMax, leftHeight)
            left2right[i] = leftMax
            
        for i in range(len(height) - 1, -1, -1):
            if i == len(height) - 1:
                right2left[i] = 0
                continue
            
            rightHeight = height[i + 1]
            rightMax = max(rightHeight, rightMax)
            right2left[i] = rightMax
        
        rain = 0
        for i in range(len(height)):
            selfHeight = height[i]
            itsLeft = left2right[i]
            itsRight = right2left[i]
            #找到最低的bar的高度，然后对比自己的高度
            barHeight = min(itsLeft, itsRight)
            if barHeight > selfHeight:
                rain += barHeight - selfHeight
                
        return rain
            
        
        