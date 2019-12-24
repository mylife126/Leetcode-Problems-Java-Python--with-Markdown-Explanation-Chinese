/*
positive integer ranges from 1 ~ 2^31 - 1
这意味着所有可能的 sqrt ranges from  1 ~ sqrt(2^31 - 1)
那么我们只需binary search 一个合适的数字 使得它的 x^2 <= x 即可

假设 int from  1 ~ 105
则   sqrt from 1 ~ 10
注意退出条件： left < right, 不同于常规的binary search left <= right, 因为我们需要 两个指针相邻，有时候答案是左指针，有时候是右指针。[..... y1 y2]
             l  r 
case 1： 90，    1 2 3 ... 10， 对于binary search来说此刻我们一直移动左指针，一直到left @ 9, right @10 退出。 然后判断发觉9* 9 = 81更合适，所以return了9

case 2：101，  1 2 3 ...10, 同理我们一直移动左指针直到left @9, 退出循环。发觉判断10 * 10 更何时，返回右指针上的数字
*/
class Solution {
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        
        int left = 1;
        int right = (int) Math.sqrt(Integer.MAX_VALUE);
        //left + 1是为了确保两个指针是相邻的，不然会使得指针最后重合
        while(left + 1 < right){
            int mid = (right + left) / 2;
            if (mid * mid == x){
                return mid;
            }
            if (mid * mid > x){
                right = mid;
            } else {
                left = mid;
            }
        }
        int small = left * left;
        int big = right * right;
        //因为上面指针没法到最后重合再退出，所有有可能答案就是一直是 end，但是由于循环会在指针重合前停止，也就是说
        //mid永远到不了end上面，从而没法直接返回end，所以这里的判断得是 >= 0来取到end
        if ((x - big) >= 0 && (x - big) < (x - small)){
            return right;
        } else {
            return left;
        }
        
    }
}

