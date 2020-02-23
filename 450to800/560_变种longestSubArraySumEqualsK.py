'''
https://www.geeksforgeeks.org/longest-sub-array-sum-k/
Input : arr[] = { 10, 5, 2, 7, 1, 9 }, 
            k = 15
Output : 4
The sub-array is {5, 2, 7, 1}.

Input : arr[] = {-5, 8, -14, 2, 4, 12},
            k = -5
Output : 5
[    -5 8   -14  2  4  12]
[0, -5, 3, -11, -9, -5, 7]    target = -5


s1代表着从0到ith的prefix Sum，那么我用这个prefixsum+ target = s2，也就是说假设从s1 到 s2间有一个sum和等于s2， 
那么target == s2 - s1
|--------|---------|
        s1         s2
         |- target-|

那么我们用hash存入此刻 s1 + target == s2 以及此刻的s1的位置，表示我在这个位置想要得到s2的话，这个s2应该等于多少
现在我们有一个prefixsum的array [    -5 8   -14  2  4  12]
                              [0, -5, 3, -11, -9, -5, 7]
那么这个prefixsum既可以代表此刻的s1，也可以代表下一时刻的s2， 因为prefixsum的本质就是runningSum
所以我们用hash记录现在此刻的s1 + target得到应该的s2的读数，那么如果我们在候面一刻得到的prefix sum == 这个s2， 就说明我们
找到了一个interval使得从s1 到 s2的距离为target。 长度则是此刻s2_idx - hash【s2】

iter prefixSum   idx    s2     found
0        0        0     [-5] 
1       -5        1     -10     yes,    len = 1 - 0 = 1   
2        3        2     -2   
3       -11       3     -16     
4       -9        4     -14
5       -5        5     -10     yes,    len = 5 - 0 = 5
6        7        6     -2    


上述是用two pass搭建runningSum array， 我们可以用onePass来边加runningSum （supposed s2）边查看hash的存在的情况，
即为此刻的runningSum是否存在于上一刻（hash):
a. 如果存在调出上一刻的index， len = 此刻的i- idx_last 
b。 不然就存入此刻的indx
'''

class Solution(object):
	def longSubarray(self, array, k):
		if not array:
			return -1

		runningSum = 0
		myHash = dict()
		length = 0

		supposedS2 = runningSum + k
		myHash[supposedS2] = 0

		for i in range(len(array)):
			num = array[i]
			runningSum += num            #计算此刻的s1， 
			supposedS2 = runningSum + k  #计算此刻以s1为基， 所应该得到的s2
			whereNow = i + 1             #加1是因为第0位在runningSum == 0的位置
			if runningSum in myHash:     #同时又以此刻的runningSum当作检测是否出现了前一刻应当有的s2
				length = max(length, whereNow - myHash[runningSum])  #如果有，则说明找到了一个s2
			else:
				myHash[supposedS2] = whereNow

		return length

	def twoPass(self, array, k):
		if not array:
			return -1

		prefixSumArray = [0]
		runningSum = 0
		#建立prefixSumArray来存每一位下的runningSum
		for num in array:
			runningSum += num
			prefixSumArray.append(runningSum)

		length = 0
		myHash = {}
		for i in range(len(prefixSumArray)):
			#调出此刻的runningSum
			s1 = prefixSumArray[i]
			#计算以此刻为基所应该能得到的s2是多少
			supposedS2 = s1 + k
			#假设此刻的s1是某以前时刻算出的应得的s2
			s2 = s1
			#那么如果这个s2存在于hash，说明某一刻s1下+ k 在这一刻得到了
			if s2 in myHash:
				#则长度为 现在的idx - 之前那一刻的位置
				length = max(length, i - myHash[s2])
			else:
				#不然就把此刻得到的应该得到的s2发入hash，然后indx == 现在的s1的位置
				myHash[supposedS2] = i
		return length

if __name__ == '__main__':
	test = [([10, 5, 2, 7, 1, 9 ], 15),
	        ([-5, 8, -14, 2, 4, 12], -5)]

	solver = Solution()
	for tester in test:
		print(solver.twoPass(tester[0], tester[1])) 

