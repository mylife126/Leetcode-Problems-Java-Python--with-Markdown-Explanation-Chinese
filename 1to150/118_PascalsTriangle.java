/*
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

直观思想：
1.如果n <= 0 直接return空集
2.init ans array，放入一个[1]进入array
3.row循环从idx = 1, 也就是第二行开始
  a. 每一次新的行进入后，起始都是0， 结束都是i， [1  ? ? ? ? 1] 且二者对应的值都是1
  b. 对于中间值来说，第j项的sum = last(i - 1) + last(i). [1 1]
                                                      [1 2 1]  2 @ j = 1 == last(1) + last(0)
*/

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows <=0){
            return ans;
        }
        //init ans list with row 1
        List<Integer> added = new ArrayList<>();
        added.add(1);
        ans.add(added);
        //for loop starting from row 2, idx = 1
        for (int i = 1; i < numRows; i++){
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
        return ans;
    }
}