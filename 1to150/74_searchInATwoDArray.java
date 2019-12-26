/*
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false

思路是： 
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
我们发觉如果以右上角的数字为pivot可以达到二分的效果，如果target > corner, 则说明，我们这一row的左边的所有数字都没用，因为
这一row的数字是sort过的，所以我们可以直接i++; 而如果 target < corner， 则说明这一row下面的数字都没用，因为越往下走的数字越大，
如果此刻的corner都大于target了，那我们根本无需再往下找，所以，j--;

递归的时候，优先判断越界情况，一旦越界，则说明找不到这个数字，返回false
第二个if链，是三个case，1. 等于target 直接返回true， 如果 target > corner 递归[i++], 最后target < corner, 递归[j--]
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        //starting from the upright corner
        int x = 0;
        int y = matrix[0].length - 1;
        
        boolean ans = bisearch(matrix, x, y, target);
        return ans;
    }
    
    private boolean bisearch(int[][] matrix, int x, int y, int target){
        /*
        以下两个if可以分开
        */
        if (x > matrix.length - 1 || y < 0){
            return false;
        } 
        /*
        这里必须是 if 链，不然return statement不完全。
        */
        
        if (matrix[x][y] == target){
            return true;
        } else if (matrix[x][y] > target){
            return bisearch(matrix, x, y - 1, target);
        } else {
            return bisearch(matrix, x + 1, y, target);
        }
        
    }
}