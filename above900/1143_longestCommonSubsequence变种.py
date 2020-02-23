'''longest Common SubString
https://www.geeksforgeeks.org/longest-common-substring-dp-29/
Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
Output : 5
The longest common substring is “Geeks” and is of length 5.

Input : X = “abcdxyz”, y = “xyzabcd”
Output : 4
The longest common substring is “abcd” and is of length 4.

Input : X = “zxabcdezy”, y = “yzabcdezx”
Output : 6
The longest common substring is “abcdez” and is of length 6.


state function: dp[m][n], 每一位代表的是string1【0 ~ i] and string2[0 ~ j] 之间多少是common的，
                例如dp[1][8] 代表 y and  zxabcdezy 之间的common的长度，那只能是1

transfer function: 如果end letter相等，那就意味着+1， 且我们要看两个string的前一位的长度dp[i - 1][j - 1]
                例如： string1: ab, string2: ab
                现在我们 b == b， +1， 然后dp[i-1][j - 1] = 1, dp[i][j] = 1 + 1 = 2
                如果不等， 因为是substring 需要连续的，所以直接等于0


     0 0 1 2 3 4 5 6 7 8
     / z x a b c d e z y
0  / 0 0 0 0 0 0 0 0 0 0
1  y 0 0 0 0 0 0 0 0 0 1
2  z 0 1 0 0 0 0 0 0 1 0 
3  a 0 0 0 1 0 0 0 0 0 0 
4  b 0 0 0 0 2 0 0 0 0 0 
5  c 0 
6  d 0 
7  e 0 
8  z 0 
9  x 0 
'''

class Solution(object):
	def longestSubstring(self, string1, string2):
		if not string1 or not string2:
			return 0

		count = 0
		m = len(string1)
		n = len(string2)

		dp = [[0 for j in range(n + 1)] for i in range(m + 1)]
		for i in range(1, m + 1):
			for j in range(1, n + 1):
				if string1[i - 1] == string2[j - 1]:
					dp[i][j] = dp[i - 1][j - 1] + 1
				else:
					dp[i][j] = 0

				count = max(count, dp[i][j])

		return count

if __name__ == '__main__':
	solver = Solution()
	test = [('GeeksforGeeks', 'GeeksQuiz'), ('abcdxyz', 'xyzabcd'),('zxabcdezy', 'yzabcdezx')]

	for tester in test:
		print(solver.longestSubstring(tester[0], tester[1]))
