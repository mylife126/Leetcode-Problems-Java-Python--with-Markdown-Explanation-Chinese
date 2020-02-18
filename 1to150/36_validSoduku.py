'''
00 01 02 | 03 04 05 | 06 07 08 
10 11 12 | 13 14 15 | 16 17 18
20 21 22 | 23 24 25 | 26 27 28
------------------------------
30 31 32 | 33 34 35 | 36 37 38
40 41 42 | 43 44 45 | 46 47 48
50 51 52 | 53 54 55 | 56 57 58
------------------------------
60 61 62 | 63 64 65 | 66 67 68
70 71 72 | 73 74 75 | 76 77 78
80 81 82 | 83 84 85 | 86 87 88

1. check every subgrid 00 03 06 30 33 36 60 63 66's uniquness
   -> topx =  x // 3  *  3     topy = y//3 * 3   重点！！！！
   
2. check every row's uniqueness
3. check every column's uniqueness
'''
from collections import defaultdict
class Solution(object):
    def isValidSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: bool
        """
        subgridHash = defaultdict(set)   #for each subgrid
        colHash = defaultdict(set)       #for each col 这里必须构造一个 max n*m的hash，但其实用n来算
        for i in range(len(board)):
            rowSet = set()
            for j in range(len(board[0])):
                if board[i][j] != ".":
                    topX = i // 3 * 3
                    topY = j // 3 * 3
                    whichGrid = str(topX) + str(topY)
                    thisVal = board[i][j]

                    if thisVal not in rowSet:
                        rowSet.add(thisVal)
                    else:
                        return False

                    if thisVal not in colHash[j]:

                        colHash[j].add(thisVal)
                    else:
                        
                        return False

                    if thisVal not in subgridHash[whichGrid]:

                        subgridHash[whichGrid].add(thisVal)
                    else:
                        
                        return False
                
        return True
                
        
        
            
        