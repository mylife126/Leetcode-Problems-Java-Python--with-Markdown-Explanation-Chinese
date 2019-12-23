/*
先transpose 再对transpose后的row inverse
            Transposed  IDx             Reversed  IDx
00 01 02     00 10 20  00 01 02         20 10 00  02 01 00
10 11 12  -  01 11 21  10 11 12    ->   21 11 01  12 11 10
20 21 22     02 12 22  20 21 22         22 12 02  22 21 20

00 01 02 03    03 02 01 00 

*/


class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        for (int row = 0; row < matrix.length; row++){
            int[] thisRow = matrix[row];
            reverse(thisRow);
        }
    }
    
    private void transpose(int[][] matrix){
        for (int i = 0; i < matrix.length; i++){
            /*
            注意这里不能是 j = 0开始，不然返回的transpose又是自己了，因为row = 0 的时候 00 01 02 和 00 10 20 对调了
            再到row = 1 的时候 10 11 12 其中 10 是之前的 01， 所以又对调了回去。
            所以正确做法是在新的row开始的时候，col的取值从row的取值开始，而不是0，这样当row = 1时， 我们只取到 11 12，这样就避             免了 把10重新对调了回去的情况。 同理到了 row = 2的时候， 我们只取到 22， 而不会把 20 21 重新对调回去。
            */
            for (int j = i; j < matrix[0].length; j++){
                //transpose的原理是 matrix[r][c] = matrix[c][r] 对调
                int rowColDigit = matrix[i][j];
                int colRowDigit = matrix[j][i];
                matrix[i][j] = colRowDigit;
                matrix[j][i] = rowColDigit;
            }
        }
    }
    
    private void reverse(int[] row){
        int start = 0;
        int end = row.length - 1;
        while (start < end){
            int startDigit = row[start];
            int endDigit = row[end];
            row[start] = endDigit;
            row[end] = startDigit;
            start++;
            end--;
        }    
    }
}