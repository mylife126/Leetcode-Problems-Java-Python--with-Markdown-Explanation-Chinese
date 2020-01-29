/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

/*
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example :

Input: n = 10, pick = 6
Output: 6

重点是小心python里的int越界的问题， 所以需要用long来cast
*/
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        long end = (long) n;
        long start = 1;
        while (start <= end) {
            long mid = (start + end) / 2;
            int guessResult = guess((int) mid);            
            if (guessResult == 0) return (int) mid;
            if (guessResult == 1){
                start = mid + 1;
            }
            if (guessResult == -1){
                end = mid - 1;
            }
        }
        return -1;
    }
}