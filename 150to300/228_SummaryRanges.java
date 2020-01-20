/*
思路是two Pointer。 一个是starpointer， 代表的是一个range的开始的地方，另一个是endPointer，它代表的是一个range结束的后一位。

1. init: 两个pointer 都是0
2. 循环开始，当endPointer >=1的时候，我们对比 当前的endNum和nums[end- 1]的大小是不是超过了1
   a。 如果超过了1，就说明我们碰到了一个interval的终点，终点在end - 1
       那么需要append， 现在有两个case：
        a.1 如果 startPoint == end -1 则说明这个range只有一个值
        a.2 如果 starPoint != end - 1 则说明我们需要分别将 headNum 和 tailNum转化成string 并且放入答案里
        
      最后将start = end，将start point放到新的一个interval的起始点上
3. 循环结束后，我们会因为循环结束而少加最后一组interval。 一样是判断两个case 分别讨论添加
*/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return ans;
        }
        /*
        代表的是每一段的interval的起始点
        */
        int start = 0;
        /*
        循环每一段的末尾点后一位
        */
        for (int end = 0; end < nums.length; end++){
            int rightNum = nums[end];
            /*
            corner case [-2143523232, 21433423232] 不转化成long的话会越界
            且要等end >=1 才进行往前一项比对，不然没有任何意义。
            */
            if (end >= 1 && (long) rightNum - nums[end - 1] >= 2){
                //case1， 如果现在的interval只有自己一项
                if (start == end - 1){
                    String singleDigit = String.valueOf(nums[start]);
                    ans.add(singleDigit);
                } else {//case 2， interval有多项
                    String startDigit = String.valueOf(nums[start]);
                    String endDigit = String.valueOf(nums[end - 1]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(startDigit);
                    sb.append("->");
                    sb.append(endDigit);
                    ans.add(sb.toString());  
                }
                /*
                重点是每次需要更新下一段的interval的起点
                */
                start = end;
            }
        }
        /*
        最后将最后一组放入ans里
        */
        if (start == nums.length - 1){
            String lastDigit = String.valueOf(nums[start]);
            ans.add(lastDigit);
        } else {
            String startDigit = String.valueOf(nums[start]);
            String endDigit = String.valueOf(nums[nums.length - 1]);
            StringBuilder sb = new StringBuilder();
            sb.append(startDigit);
            sb.append("->");
            sb.append(endDigit);
            ans.add(sb.toString());
        }
        return ans;
    }
}