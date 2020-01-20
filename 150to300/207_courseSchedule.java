/*
需要知道的是每一个能现在修的课，或者node都是说明它当下已经没有先修课了， 而当下这门课对应了它into的
后修课， 对于它的后修课来说，他们的先修课的数量在这个先修课被拿出来后就减少了一门。

所以对于我们的bfs来说，放入Q里的是存在一个拓扑排序先后顺序的，这个顺序就是只有当一个node所对应的先修课的count为零了，再放入其中，以便于循环后再拿出来。 同时，在bfs的时候，我们每拿出一个先修课，就需要对它所有的可能的后修课进行countOfPre--， 因为对于后修课来说，当这个先修课被拿出来了，表示修课了，那所需要的先修课的个数就减少一个。

例子：
course 6 -> couese1
course 7 -> course1
                    course1 -> course2
                    
以上述例子来看， course1的先修课为6 7， 而course2的先修课是2. 所以我们的Q里能一开始放入的只有6，7，因为他们的先修课数量为0， 也就是说入度为0. 而我们Q poll出6的时候， 6 ->1，这个时候1的入度--； 然后1依旧不能修刻，因为它的入度 == 1， 说明还有一门先修课； 接着我们又poll7出来，发觉 7 -> 1， 所以1的入读又--， 这个时候是0了，表示它可以修课了，所以这个时候才能放入Q中。 然后又poll出1这门课， 1 -> 2， 2的先修课数量--， 等于0. 那2又可以放入Q里了。 然后再poll 2出来。这个时候我们的发觉课修完了。 

算法：
1. 入度hash or array。 因为课号对应的就是 0 ~ n, 我们其实可以建立一个 array[numCourse], 每一位对应的是每一门后修课的入度。

2. 建图，建立的是  先修课 -> list《后修课》

3. bfs， 按照拓扑排序来放入Q。 逻辑是，每当一门课的入度为0，将其放入Q中，每次poll node出来对其所有的后修课减少一次入度，拓扑排序。

4. 每次poll一门课出来，count +=1，表示修了一门课

5.最后返回 count == numCourse与否

*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null){
            return false;
        }
        
        /*
        建设入度的hash or array; 以及图，先修into 后修
        */
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> pre2after = new HashMap<>();
        //开始one pass建图并且计算入度
        for (int[] courseRelation : prerequisites){
            int pre = courseRelation[1];
            int after = courseRelation[0];
            pre2after.putIfAbsent(pre, new ArrayList<Integer>());
            /*
            对于每一门先修课把他的对应的后修课全部找到放入list中
            */
            List<Integer> courseAfterList = pre2after.get(pre);
            courseAfterList.add(after);
            
            pre2after.put(pre, courseAfterList);
            //as this after course has one pre, we accumulate its indegree
            indegree[after] +=1;
        }
        //记录现在修了多少课了
        int finished = 0;
        Queue<Integer> q = new LinkedList<>();
        
        for (int course = 0; course < indegree.length; course++){
            int howManyPre = indegree[course];
            if (howManyPre == 0){
                q.offer(course);
            }
        }
        
        while(!q.isEmpty()){
            int thePre = q.poll();
            finished += 1;
            
            List<Integer> courseAfterList = pre2after.get(thePre);
            /*
            注意！ 需要判断这门课是不是最后最后的那门课，如果是的话，它不会有一个后修课的list
            */
            if (courseAfterList == null){
                continue;
            }
            int size = courseAfterList.size();
            //对这个pre之后的所有after course进行一次拓扑排序
            for (int i = 0; i < size; i++){
                int courseAfter = courseAfterList.get(i);
                /*
                首先把每一门后修课的indegree --，
                再看它此刻的入度是不是等于0，如果是则可以修课了，就放入q
                */
                indegree[courseAfter] -= 1;
                if (indegree[courseAfter] == 0){
                    q.offer(courseAfter);
                }
                
            }
            
        }
        return finished == numCourses;
    }
}