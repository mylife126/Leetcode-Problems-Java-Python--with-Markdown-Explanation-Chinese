/*
思路是第一次one pass的时候记录 为0的<i,j> pair, 第二次one pass的时候只需要检测是否此刻的i or j出现过zeroSet里，如果出现过，
直接set这个grid为零即可。

因为每一个grid的i,j 肯定有一项是和出现0的那个i或者j是一致的；
*/

class Solution {
    public void setZeroes(int[][] matrix) {        
        Set<Integer> zeroCol = new HashSet<>();
        Set<Integer> zeroRow = new HashSet<>();
        
        //找到出现0的ij location
        for (int row = 0; row < matrix.length; row++){
            for (int col = 0; col < matrix[0].length; col++){
                if (matrix[row][col] == 0){
                    zeroCol.add(col);
                    zeroRow.add(row);
                }
            }
        }
        
        //second pass来赋值0
        for (int row = 0; row < matrix.length; row++){
            for (int col = 0; col < matrix[0].length; col++){
                //只要当前的grid的有一项i 或者 j是出现过0 set里的，则说明这个grid需要被set为0
                if (zeroRow.contains(row) || zeroCol.contains(col)){
                    matrix[row][col] = 0;
                }
            }
        }
    }
}