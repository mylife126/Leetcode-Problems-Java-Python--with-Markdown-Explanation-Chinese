/*
area基于长，高，而高基于最矮的那一边。
brutal force可以，以每一个bar为pivot，分别计算自己的area， 和往后所有的area = (width + 1)*(min(min, nextbar))
然后global变量maxarea一致计算最大的area即可。
*/
 public class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            //每次到新的bar之前init一次 bar height
            int minheight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                int width = j - i + 1;
                //找最矮小的bar
                minheight = Math.min(minheight, heights[j]);
                //对比计算maxArea即可
                maxarea = Math.max(maxarea, minheight * width);
            }
        }
        return maxarea;
    }
}