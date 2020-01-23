/*
prime指的是 除了0， 1以外的数字， 它的倍数只有自己和1.
那么给定例子10， 也就是 0 1 2 3 4 5 6 7 8 9， 对于每一个数字作为pivot，我们对他进行不断的乘以1 ~ n倍， 直到pivot * n 不大于10，就可以找到以他为底的所有合数。
所以建立一个array 来记录该数字是否是个prime 

                                               pivot
[0   1    2    3   4   5   6   7     8     9]   2
[f   f    t        f       f         f      ]   3
[f   f    t    t   f       f               f]   4
[f   f    t    t   f       f         f     f]   5
[f   f    t    t   f   t   f         f     f]   6
*/


class Solution {
    public int countPrimes(int n) {
        /*
        edge case: 如果数字负数或者小于等于1，这些都不是prime by definition
        */
        if (n <= 1) return 0;
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;
        int count = 0;
        for (int i = 2; i < n; i++){
            /*
            如果当前数字是prime， 我们直接 ++， 因为如果是合数的话，会在下一行代码中全部被更新为true
            */
            if (notPrime[i] == false){
                // System.out.println("find one");
                notPrime[i] = false;
                count+=1;
            }
            /*
            如果是合数了，那直接过，因为它之后的所有合数肯定被之前的更小的约数全部更新过一次  例如 2 -》 4 -》 8， 而 4 - 》 8 其实已经更新过了无需再走下一步
            */
            if (notPrime[i] == true) continue;
            
            /*
            以当前数字为pivot，不断乘以 1 ~ n的倍数，直到新的倍数小于 n 停止，并且更新array为true
            */
            int j = i;
            int pivot = i;
            while (j < n) {
                notPrime[j] = true;
                j += pivot;
            }
        }
        return count;
    }
}