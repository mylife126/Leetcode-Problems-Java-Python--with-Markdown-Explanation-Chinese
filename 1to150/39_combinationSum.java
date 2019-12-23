/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations 
in candidates where the candidate numbers sums to target.

[2 3 6 7] 7，   -> means call the function once
进入
2
 -> 2 2 idx = 0
        -> 2 2 2 idx = 0
                -> 
                   2 2 2 2  idx = 0return 
                -> 2 2 2 3  idx = 1return
                -> 2 2 2 6 idx = 2 return
                -> 2 2 2 7 idx = 2 return 
        ->2 2 3 idx = 1 success, append to ans return
        ->2 2 6 idx = 2 return 
        ->2 2 7 idx = 3 return 
 -> 2 3 idx = 1
       -> 2 3 6 return 
       -> 2 3 7 return 
 -> 2 6 idx = 2 return 
 -> 2 7 idx = 3 return 
 .......
重点是，每次组合的时候，for loop的起始是从新加入的这个数字开始，以确保下一次递归的时候新加入的数字是从该数字开始，而不是从0重新开始了一次，这样就会重复
例如
2 2 3 再一次递归的时候， for loop从 3 6 7中找可能的数字，而不会又从头开始了一次。
*/

class Solution{
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> ans = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return ans;
        }
        
        
        int tempSum = 0;
        bt(ans, new ArrayList<Integer>(), target, tempSum, 0, candidates);
        return ans;
    }
    private void bt(List<List<Integer>> ans, List<Integer> tempArray, int target, int tempSum, int idx, int[] array){
        //定义出口，也就是当tempSUm == target了，那就把此刻的tempArray放入ans里
        if (tempSum == target) {
            /*需要注意，java是reference type， 需要建设一个新的内存存放之前的array里的所有东西不然就会在之后的运算
            里改写此刻的array
            */
            List<Integer> added = new ArrayList<>(tempArray);
            ans.add(added);
            return;
        }
        
        //定义失败出口，如果tempSum > target了，那就得直接return，然后在之后的for loop 里会去尝试下一个数
        if (tempSum > target){
            return;
        }
        
        //for 循环在每一层遍历所有可能性
        for(int i = idx; i < array.length; i++){
            tempArray.add(array[i]);
            /*
            递归层，刚进来是array里的第零项
            */
            bt(ans, tempArray, target, tempSum + array[i], i, array);
            
            //回溯
            tempArray.remove(tempArray.size() - 1);
        }
    }
}