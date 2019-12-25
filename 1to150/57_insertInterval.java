/*
和mergeinterval一样，现在我们将原来数组重新sort，按照starting来。 然后我们要linear search到数组中找到一项：
newIntervalEnding >= starting 且 newIntervalEnding <= ending， 表示我们找到了可以插入的地方。 然后需要改写此刻的
starting和runningEnd， 因为新的interval可能有一个更小的start 或者 更大的end

找到了可以插入的interval后，就可能存在可以merge的情况，那就开始mergeInterval即可。

同时保留runningEnd，然后其余的就是merge intervals
                   [[1,2],[3,5],[6,7],[8,10],[12,16]]
          idx      newStart     newEnd      last          result          runningEnd      
          0          4           8         [1,2]           [1,2]                2
          1          4           8         [3,5]           [3,8]                8  FOUND one insert place -> merge could start
          2          4           8         [3,8] vs [6 7]  [3,8]                8  while continues
          3          4           8         [3,8] vs [8 10] [3,10]               10 while ends
          4          4           9         [3,10] vs [12, 16] [3,10] [12 16]
*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0){
            int[][] ans = new int[1][2];
            ans[0] = newInterval;
            return ans;
        }
        
        List<int[]> list = new ArrayList<>();
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        //此boolean会在循环中起到作用以及循环结束后起作用
        boolean found = false;
        
        for (int i = 0; i < intervals.length; i++){
            int[] thisInterval = intervals[i];
            int starting = thisInterval[0];
            int runningEnd = thisInterval[1];
            int[] temp = new int[2];
            /*
            必须用found boolean来作为先决条件。因为如果不加这个判断，在找到插入的地方后，每次循环到新的interval都要对比一次,
            newStart和newEnd都会被对比一次，但这步骤的对比仅仅应该发生在插入这一步。 插入后就无需再找插入了。
            
            能使得insert valid的条件：
            1. newStart <= newEnd (不然是个invalid的)
            2. newStart <= runningEnd 这一点同理merge， 但同样还得保证 newEnd >= starting 
            例如 newInterval [2, 3] vs [4 , 4]。 如果光有newStart < runningEnd肯定是会直接进入判断，但此刻的interval并没有重合
            */
            if (!found){
                if (newStart <= runningEnd && newEnd >= starting && newStart <= newEnd){
                    //对于此时找到后的runningEnd 和 starting做一次新的赋值
                    runningEnd = Math.max(runningEnd, newEnd);
                    starting = Math.min(starting, newStart);
                    //并且改写found == true， 这样下一次for循环后就不会因为被改写后的runningStart runningEnd重新找一次可以insert的
                    //interval
                    found = true;
                }
            }
            /*
            其实如果没有found，以下的while不会发生，因为题目说过，原数组是不能merge的。
            但如果found后， 新的runningEnd就有可能被merge，所以直接写一个while循环来不断的找下一刻的可以merge的interval并且
            记录runningEnd
            */
            while ((i + 1) < intervals.length && intervals[i + 1][0] <= runningEnd){
                runningEnd = Math.max(runningEnd, intervals[i + 1][1]);
                i++;
            }
            temp[0] = starting;
            temp[1] = runningEnd;
            list.add(temp);
        }
        /*
        corner Case： 如果以上都没有找到能够insert的地方，那需要手动再次添加这个newInterval
        */
        if (!found){
            list.add(newInterval);
        }
        
        int[][] tempInterval = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++){
            tempInterval[i] = list.get(i);
        }
        
        /*
        由于我们可能新添加了一个newInterval， 那此时的顺序不再是sorted，所以需要重新sort一次
        */
        Arrays.sort(tempInterval, new Comparator<int[]>(){
                @Override
                public int compare(int[] a, int[] b){
                    return a[0] - b[0];
                }
            });
        return tempInterval;
    }
}