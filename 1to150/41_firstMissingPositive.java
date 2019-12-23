/*
思路是，什么情况下一个当前的数字前可以放入一个missing positive，那就是此刻的数字和上一刻的数字的距离大于1，例如 3 4之间就不行， [3 5], [3,8]
之间就可以，且一定是上一刻的数字+1，这样能使得插入的数字最小。 同时还有一个快速的cornercase，那就是第一个正整数距离0的长度大于1， 例如 [-1,4,5,6] 2到0的距离大于1，所以4之前还能插入一个
4 - (4 -1) = 1

1. 所以我们可以用stack记录每一刻的数字，然后当stack不为空的时候对比此刻和上一刻的距离
    a. 如果此刻的数字和上一刻peek的数字之距离大于1，那么直接返回 pop() + 1
    b. 不然每次都stack里push此刻的数字
2. 在上述之前我们还需要快速判断cornercase， 如果此刻的数字是第一个非零的正整数，且它到0的距离大于1，则直接返回 current - (current - 0 - 1);
    那么怎么判断此刻的数字是 《第一个》 正整数呢，我们可以维护一个变量， firstNoneZero, 如果循环中遇到负数或者0， firstNoneZero++, 直到出现了
    第一个正整数，那此刻 firstNoneZero - idx == 0. 以此可以判断

[0 1 2 4] -> 4        1 - 1 = 0 - >stack[1] -> 1 vs 2 -> stack[1,2] -> 2 vs 4 = 2 > 1 -> pop 2 + 1= 3

[-1 1 3 4] -> 2       1- 1 = 0 -> stack[1] -> 1 vs 3 = 2 > 1 -> pop 1 + 1 = 2
    
[7 8 9 11 12] -> 1    7 - 1 = 6 > 0 -> return 7 - 6 = 1

[-1, 1010]    ->      1010 - 1 > 0  -> return 1010 - (1010 - 0 -1) = 1

*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        //首先把数字从小到大排序一次
        Arrays.sort(nums);
        Stack<Integer> stack = new Stack<>();
        //corner case
        if (nums == null || nums.length == 0){
            return 1;
        }
        //corner case， 最后一位数都还是小于等于0，说明原数组里最大的都是负数或者0，所以答案直接是1
        if(nums[nums.length - 1] <= 0){
            System.out.println(nums[nums.length - 1]);
            return 1;
        }
        int firstNoneZero = 0;
        
        for (int i = 0; i < nums.length; i++){
            int current = nums[i];
            if (current <= 0){
                firstNoneZero++;
                continue;
            }
            //find out the none zero digit's distance from 0
            int farFromZero = current - 0;
            //if the current digit is the first none zero digit and its distance from 0 is greater than 1
            if ((firstNoneZero - i) == 0 && farFromZero - 1 > 0){
                //this means that there is a room for the first missing positive digit to be infront of it
                return current - (farFromZero - 1);
            } else {//不然就说明此刻的数字之前没有空间放一个first missing正整数
                if (!stack.isEmpty()){
                    //所以我们需要判断此刻的数字和上一课的数字有多远
                    int last = stack.peek();
                    int diff = current - last;
                    //如果距离大于1，则说明这个数字前有空间，所以我们就可以把上一个数字加1，返回
                    if (diff > 1){
                        return stack.pop() + 1;
                    }
                }
                //不然每一刻都把此刻的数字放入stack里
                stack.push(current);
            } 
        }
        //如果上面的循环里没能找到一个可以insert的地方，那说明答案就是最后一位加1
        //1 2 3 4 5 6 -> 7
        return nums[nums.length - 1] + 1;
    }
}

