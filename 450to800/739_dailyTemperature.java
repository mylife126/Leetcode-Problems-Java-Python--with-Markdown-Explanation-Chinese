/*
[73 74 75 71 69 72 76 73]
[1   1  4  2  1  1  0  0]
 0   1  2  3  4  5  6  7
 
num      peek           idx     pop     add       stack
73         /             0       /       /         E - > 0
74       73 < 74         1       0      1-0 =1     1
75       74 < 75         2       1      2-1 =1     2
71        75             3       /       /         2 3
69        71             4       /       /         2 3 4
72       69 < 72         5       4      5-4 =1     2 3
         71 < 72         5       3      5-3 =2     2 5

76        72             6       5      6-5 =1     2
          75             6       2      6-5 =4     6

73        76             7       /       /         6 7 

for loop 循环所有num【i】
1. 如果stack为空的时候直接加入数字
2. 不为空的时候需要判断：
    1. while stack.peek() <num[i], 我们就pop 上一刻的数字的indx， 然后计算i - idx 的距离，array[idx] = i - idx
    2. 当上述while结束了，push num[i]
3. for loop循环完毕后， 将stack里剩余的idx对应的needed Days 赋值0
*/
class Solution {
    public int[] dailyTemperatures(int[] nums) {
        int[] needed = new int[nums.length];
        if (nums == null || nums.length == 0) return needed;
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < nums.length; i++){
            int curr = nums[i];
            if (stack.isEmpty()){
                stack.push(i);
            } else {
                //需要找到所有的之前的temperture小于此刻的temperature的， 且要保证不越界
                while(!stack.isEmpty() && nums[stack.peek()] < curr){
                    int whichIdx = stack.pop();
                    int diff = i - whichIdx;
                    needed[whichIdx] = diff;
                }
                //最后将此刻的temp放入stack
                stack.push(i);
            }
        }
        //那么如果上述循环结束了， stack里还有东西，则说明这些indx对应的都是0
        while(!stack.isEmpty()){
            needed[stack.pop()] = 0;
        }
        return needed;
    }
}

