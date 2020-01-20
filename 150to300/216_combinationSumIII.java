/*
题目要求，找到1~9中 unique的数字组 相加的和 == target

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

这意味着1放入list后， 1 就不可以再用了。 有两个方案，hashset来记录每一层递归层里用过的数字； 或者呢我们用indx，每次递归的indx = i +1； 这样下一层递归的时候 for loop就是从下一个数字开始循环，避免了重复
*/
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n <= 0){
            return ans;
        }
        List<Integer> tempList = new ArrayList<>();
        bt(k, n, tempList, 1);
        return ans;
    }
    
    private void bt(int k, int n, List<Integer> temp, int where){
        
        if (temp.size() == k){
            int sum = 0;
            //计算这个tempArray里的和是否满足要求，如果满足则放入
            for (int i : temp){
                sum += i;
            }
            if (sum == n){
                ans.add(new ArrayList<>(temp));
            } else {
                //不然直接return，然后在return后的递归里删掉这个数
                return;
            }
        }
        
        for (int i = where; i <= 9; i++){
            temp.add(i);
            bt(k,n,temp,i + 1);
            temp.remove(temp.size() - 1);
        }
        
    }
}