/*
不同于第一问，现在只要求返回最后第k行的答案。
那思路是一样的，建立完整的list of lists， 返回最后一个list即可。
*/

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ans = new ArrayList<>();
        if (rowIndex <0){
            return new ArrayList<Integer>();
        }
        //init ans list with row 1
        List<Integer> added = new ArrayList<>();
        added.add(1);
        ans.add(added);
        //for loop starting from row 2, idx = 1
        for (int i = 1; i <= rowIndex; i++){
            int start = 0;
            int end = i;
            List<Integer> temp = new ArrayList<>();
            //对于每一行的建立我们的j从0到i
            for (int j = 0; j <= i; j++){
                List<Integer> lastList = ans.get(i - 1);
                if (j == 0){
                    temp.add(0, 1);
                } else if (j == i){
                    temp.add(i, 1);
                } else {
                    int sum = lastList.get(j - 1) + lastList.get(j);
                    temp.add(sum);
                }
            }
            //每一行建立后放入答案中
            ans.add(temp);
        }
        return ans.get(ans.size() - 1);       
    }
}