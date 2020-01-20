/*
同理courseScheduleI， 我们的bfs的顺序是按照拓扑排序来的，放入q的顺序都是先修课修完的课程 即入度为0.
然后每次poll一门课的时候，就意味着修了一门课，放入orderList中即可。

但是需要注意的是，有可能我们此刻能放入orderList中的课程不等于numCourses，因为可能中途先修课和后修课的联系断了，或者这个关系是一个环。 所以我们需要最后判断 finished == numCourses与否，如果不等于，则返回空。
*/

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null){
            int[] order = {};
            return order;
        }
        /*
        假设我们给定的prerequisites是可以形成一个courselist的，而不是一个环，那么这个order的size
        一定等于numCourses，因为numCourses代表的是总共需要上的课
        */
        int[] order = new int[numCourses];
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> pre2after = new HashMap<>();
        /*
        建图 pre into listAfters
        indegree[whichafter] ++
        */
        for (int[] courseRelation : prerequisites) {
            int pre = courseRelation[1];
            int after = courseRelation[0];
            /*
            将pre 和 afterCourses的关系建立
            */
            if (pre2after.containsKey(pre)){
                List<Integer> afterCourseList = pre2after.get(pre);
                afterCourseList.add(after);
                pre2after.put(pre, afterCourseList);
            } else {
                List<Integer> afterCourseList = new ArrayList<>();
                afterCourseList.add(after);
                pre2after.put(pre, afterCourseList);
            }
            indegree[after] +=1;
        }
        
        int finished = 0;
        /*
        找到第一门可以修的课，也就是入度为零
        */
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++){
            int howManyPre = indegree[i];
            if (howManyPre == 0){
                q.offer(i);
            }
        }
        
        while(!q.isEmpty()){
            int pre = q.poll();
            order[finished] = pre;
            finished += 1;
            /*
            拿出该课程的对应的后修课的list
            */
            List<Integer> afterCourseList = pre2after.get(pre);
            //同样如果list为空则说明修到了最后最后的那门课，它的list是空。直接跳过
            if (afterCourseList == null) continue;
            int size = afterCourseList.size();
            
            for(int i = 0; i < size; i++){
                int after = afterCourseList.get(i);
                indegree[after] -= 1;
                if (indegree[after] == 0){
                    q.offer(after);
                }
            }
        }
        
        if (finished == numCourses){
            return order;
        } else {
            int[] empty = {};
            return empty;
        }
        
    }
}