'''
1. 当fence只有1的时候，我们有k个选择
2. 当fence有两个的时候，我们可以：
   a。 Same： 当fence1涂了红色 randomly， 我们想要fence2也是红色，那就是 k种，fence2追随fence1的颜色，fence1有k种
   b。 different： 当fence1涂了红色 randomly， 我们的fence2需要不同的颜色的话，那就是 k * （k - 1）
   
3. 当fence 》=3的时候：
   a。 f3与之前diff：fence1    fence2   fence3
                    f1f2可以是same的情况，但是要求是不能连续两个一个颜色，所以fence3一定是 k-1种情况 
                    f1f2可以是diff的情况，要求不能连续两个颜色一致，所以fence3一定是k - 1种情况
                    =》 （same_prev + diff_prev）（k - 1）
                    
   b。 f3与f2same：  fence1   fence2   fence3
                    如果fece3等同于fence2的话，只能一种情况那就是到fence2的时候他的情况是 diff
                    这样 那只能是diff_prev * 1

4. 最后我们返回same + diff 两个情况下的总数即可
'''
class Solution(object):
    def numWays(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: int
        """
        if n == 0:
            return 0
        
        if n == 1:
            return k
        
        if n == 2:
            return k * (k - 1) + k * 1
        
        #state function: same 和 diff下的总方法数， same代表了这一个fence与之前的相同的情况， diff代表了这一个
        #fence与之前不同的情况
        
        #init, 我们从fence = 3开始，那么fence2作为init的起点， 
        same = k * 1
        diff = k * (k - 1)
        
        for i in range(3, n + 1):
            lastSame = same
            lastDiff = diff
            
            #case1, this fence has a different color
            diff = (lastSame * (k - 1)) + (lastDiff * (k - 1))
            #case2, this fence has a same color to the previous fence
            #then the previous fence must be diff
            same = lastDiff * 1
            
        return same + diff
        