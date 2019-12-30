/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]



1 2 3 4, k = 2, 两个数
i = 1
->   recursion @ i = i + 1 = 2
     -> 1 2 size == 2 append return 
     -> 1 3 size == 2 append return 
     -> 1 4 size == 2 append return 
     for loop ove return 
     
i = 2
-> 2 recussion @i = i + 1 = 3
     -> 2 3 size ==2 append return 
     -> 2 4 size ==2 append return 
     for loop over return

i = 4
-> 3 recurrsion @i= i + 1 = 4
     -> 3 4 size == 2 append return
     for loop over return 
     
i = 4
-> 4 recurrsion @i = i + 1 = 4, 4 > n return 
for loop over return function
*/
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int idx = 1;
        bt(ans, n, k, idx, new ArrayList<Integer>());
        
        return ans;        
    }
    
    private void bt(List<List<Integer>> ans, int n, int k, int idx, List<Integer> tempArray){        
        //检查是否可以append temp了
        if (tempArray.size() == k){
            ans.add(new ArrayList<Integer>(tempArray));
            return;
        }
        // 1 2 3 4, for loop 保证了越界直接返回
        for (int i = idx; i <= n ; i++){
            tempArray.add(i);
            bt(ans, n, k, i + 1, tempArray);
            tempArray.remove(tempArray.size() - 1);
        }
    }
}
