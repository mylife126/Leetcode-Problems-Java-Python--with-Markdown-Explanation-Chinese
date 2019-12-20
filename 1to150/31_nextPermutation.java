/*
1 2 4 5 6 -> 1 2 4 6 5
1 2 6 5 4 -> 1 4 6 5 2 -> 1 4 2 5 6 
1 2 3 5 4 - > 1 2 4 5 3 -> 1 2 4 3 5
1 2 3 5 4 2 -> 1 2 4 5 3 2 -> 1 2 4 2 3 5  
6 5 4 3 2 1 -> 1 2 3 4 5 6

1 2 3 4 5 6
Thus, we reversely iterate the array, if the digit infront is still higher than the current, then we keep loop until we find the first small digit, meaning that is the pivot digit. 
i.e 4 -> 5 -> 6 -> 2. 

this is because we want the number to be just larger than the current as small as possible; thus, if the digit after the pivot is descending, meaning that there is nothing could be changed after the pivot. The only digit we could change is the pivot digit by the last digit, which is the smallest next larger digit than the pivot;

thus:
1. if we could find there is still a larger digit before the current, we keep move the pointer to the front
2. else, meaning that we have found the indx after the pivot, thus, the pivot is idx - 1;
3. next we need to find the first larger digit than the pivot digit and swap
4. finally, sort the array after the pivot
*/

class Solution {
    public void nextPermutation(int[] nums) {
        int pointer = nums.length - 1;
    
        while (pointer > 0){
            if (nums[pointer - 1] >= nums[pointer]){
                pointer -= 1;
            } else {
                int pivotPointer = pointer - 1;
                
                int pivot = nums[pivotPointer];
                for (int i = nums.length - 1; i > pivotPointer; i--){
                    int compare = nums[i];
                    if (compare > pivot){
                        nums[pivotPointer] = compare;
                        nums[i] = pivot;
                        
                        Arrays.sort(nums, pivotPointer + 1, nums.length);
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }
}
