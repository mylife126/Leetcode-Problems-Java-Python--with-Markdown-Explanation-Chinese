/*
Counting Sort. 既然我们知道数组的范围，那很快的O（n）的solution就counting sort。

1. one pass 找到 0， 1， 2对应的出现的次数
2. 既然总出现的次数等同于nums.length, 我们维护一个global J变量，记录的是nums每次下一次对应的pointer location，
还需要一个local variable count，记录的是每一个数字需要出现的次数。 while循环 count，j++ 即可。
*/


class Solution {
    public void sortColors(int[] nums) {
        int[] counting = new int[3];
        //记录每一个出现的次数
        for (int i = 0; i < nums.length; i++){
            int thisInt = nums[i];
            if (thisInt == 0){
                counting[0] +=1;
            }
            if (thisInt == 1){
                counting[1] +=1;
            }
            if (thisInt == 2){
                counting[2] += 1;
            }
        }
        //global 变量来记录每次nums应该去的pointer
        int j = 0;
        for (int i = 0; i < counting.length; i++){
            //for loop取到每一个数的对应的出现的次数
            int howMany = counting[i];
            int count = 0;
            //对应每一个数位的出现的次数来改变原数组的数字，并且
            while (count < howMany){
                nums[j] = i;
                j++;
                count++;
            }
        }
    }
}