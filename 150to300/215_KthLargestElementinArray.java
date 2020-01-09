/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4

用优先队列来做，最后poll出第k个最大即可。
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int count = 0;
        int size = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(size, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                //ascending sort
                return b - a;
            }
        });
        
        for (int i : nums){
            pq.offer(i);
        }
        int which = -1;
        while(!pq.isEmpty()){
            int thisNum = pq.poll();
            count +=1;
            if(count == k){
                which = thisNum;
                break;
            }
        }
        return which;
    }
}