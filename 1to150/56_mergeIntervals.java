/*
两个interval能够merge的前提是，第一个interval的ending > 第二个interval的starting。 所以需要先将数组sort，按照starting sort。
然后从数组的idx = 1的地方开始遍历，如果此刻的 starting < lastEnding， merge， 不然保留自己。

     [[1,3],[2,6],[8,10],[15,18]]
idx          last          result  
1           [1,3]         [1 3] & [2,6] -> [1,6], ending = 6 while结束因为next = [8, 10], 8 > ending = 6
2           [2,6]          [1,6],[8,10]
3           [15,18]        [1,6][8,10][15,18]

需要注意的是，需要while循环一直找到下一刻可以merge的interval并且保留runningEnding，最后新建一个内存array，将starting
和runningEnding赋值给新的内存array，然后将这个array赋值给list。 最后新建一个ans[sizeofList][2],然后一个一个将list里的
array放入ans中。
       [[1,7],[2,3],[3,5],[5,10],[9,22]]
idx               last       result
0           [1 7]             [1 7] see the next 继续while
1           [1 7] vs [2 3]    [1 7] see the next 继续while
2           [1 7] vs [3 5]    [1 7] see the next 继续while
3           [1 7] vs [5 10]   [1 10] update ending = 10 see next 继续while
4           [1 10] vs [9 22]  [1 22] update ending = 22 see the next, i !< length， while end, for end
retrun ans.
O(n * logn)
*/


class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0){
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < intervals.length ; i++) {
            int[] thisInterval = intervals[i];
            int start = thisInterval[0];
            int runningEnd = thisInterval[1];
            
            int[] newArray = new int[2];
            /*
            第一个先决条件很重要，要保证不越界，同时如果下一个interval的start，小于runningEnd,则表示可以继续merge，
            直到边界条件不允许，说明以这个start为首的interval的merge结束了。
            */
            while ((i+1) < intervals.length && intervals[i+1][0] <= runningEnd){
                // newArray[0] = start;
                runningEnd = Math.max(runningEnd, intervals[i + 1][1]);
                // newArray[1] = runningEnd;
                i++;
            }
            //如果while不存在，则说明这个interval stands alone
            newArray[0] = start;
            newArray[1] = runningEnd;
            list.add(newArray);
        }
        //分配新的ans array内存
        int size = list.size();
        int[][] ans = new int[size][2];
        //将list里的全部赋给ans中即可
        for (int i = 0; i < size; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
}