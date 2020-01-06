/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8


Approach 1:
Sorting, then searching

nlogn
*/

class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (int num : nums){
            if (num != i){
                break;
            }
            i +=1;
        }
        return i; 
    }
}


/*
Approach 2:
HashSet：
1. 将array里的数字全部放入hash
2. 遍历 i， 看i是不是存在于hash， 然后i++ 如果不存在则找到了
*/

class Solution{
    public int missingNumber(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }
        
        int i = 0;
        boolean keepSearch = true;
        
        while(keepSearch){
            if (!set.contains(i)){
                break;
            }
            i++;
        }
        return i;
    }
}