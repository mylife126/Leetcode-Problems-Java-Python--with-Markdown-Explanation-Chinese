/*
给定一个array，和一个target，找到唯一解，[1 2 5] 和 [2 1 5]就属于重复项，这意味着得sort array后用“跳过”的方法来避重。 同时，意味着每一个数字只能用一次，所以combinationI中， [2 3 6 7]这个情况， 2可以用多次，但这里2只能用一次，所以递归的时候i = i+1.

例子
Input: candidates = [10,1,2,7,6,1,5], target = 8, 
 
-> 10
    -> 10 1 return
    -> 10 2 return 
    -> 10 7 return
    -> 10 6 return 
    -> 10 1 return
    -> 10 5 return
    loop over return
->1
    -> 1 2 idx = 2
         -> 1 2 7 idx = 3 return
         -> 1 2 6 idx = 4 return
         -> 1 2 1 idx = 5
                 -> 1 2 1 5 return 
                 loop over return
         -> 1 2 5 success, append to ans, return
         loop over return 
->2
    -> 2 7 ..
    .....
    
避重，重点： 
A. 所以这里的重点是，每次loop的起始位置在 i = idx + 1的位置，也就是当前数字的下一位，这样就避免了重复
B. 但是如果只是上面这样，简单的把 idx = i + 1来确保每次 递归的时候都是找了当前数的下一个数，还有一个case无法避免，那就是重复
[10,1,2,7,6,1,5]， 其中 1 2 5 成立， 而当递归来到 2 这一层， 2 1 5 也是同样的答案。 所以这就重复了。 为了避免这样的重复，我们需要把array sort， 
[1 1 2 5 6 7 10], 这样你会发觉:
idx = 0  i = 0   1 2 5成立return到第一层
idx = 0  i = 1   1 由于sort过，所以可以发觉 当 i > idx 且 array[i] = array[i -1]这个情况就可以直接continue，跳过。
       
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0){
            return ans;
        }
        //重点，避重
        Arrays.sort(candidates);
        int tempSum = 0;
        bt(ans, new ArrayList<Integer>(), tempSum, target, candidates, 0);
        return ans;
    }
    private void bt(List<List<Integer>> ans, List<Integer> tempArray, int tempSum, int target, int[] array, int idx){
        /*
        定义出口，如果tempSum == target了，那就得将此刻的temparray放入答案里
        */
        if (tempSum == target){
            ans.add(new ArrayList<Integer>(tempArray));
            return;
        }
        
        /*定义失败出口，如果此刻的tempsum > target了，那说明失败那就return，上一次call然后for 循环到下一个数字*/
        if (tempSum > target){
            return;
        }
        
        for(int i = idx; i < array.length; i++){
            //必须要是i > idx的先决条件 意味着在同一层级里，如果for 到了一个一模一样的数字那就跳过。
            //不加这个i>idx先决条件的话，深度递归也会被跳过， 但我们要的是在广度遍历时跳过
            if (i > idx && array[i] == array[i -1]) continue;
            tempArray.add(array[i]);
            //递归层
            bt(ans, tempArray, tempSum + array[i], target, array, i+1);
            //回溯
            tempArray.remove(tempArray.size() - 1);
        }
    }
}





