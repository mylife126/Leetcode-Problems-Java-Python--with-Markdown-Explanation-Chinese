/*
solution1, heap, brutal force
*/
class Solution {
    public int[] sortedSquares(int[] A) {        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int[] ans = new int[A.length];
        for (int i : A){
            // System.out.println(i * i);
            q.offer(i * i);
        }
        int i = 0;
        while(!q.isEmpty()){
            ans[i] = q.poll();
            i +=1 ;
        }
        return ans;
    }
}


/*
Example 1:

Input: [-4,-1,-3,-20， -5,1,0,3,10]
双指针方案
找到正负分界线，然后同时遍历两个array 看谁大就放入谁进入ans里, 最后会有一方么有全部遍历完，需要在操作一次进行补全
*/
class Solution {
    public int[] sortedSquares(int[] A) {
        int neg = 0;
        //第一步是找到有没有neg
        while (neg < A.length && A[neg] < 0){
            neg+=1;
        }
        //注意indx，如果找到了neg，那indx会++，所以当他退出的时候，idx已经在了pos的地方
        int wherePos = neg;
        //同理，neg则在 - 1 的地方。 这里很巧妙 如果全部的数组都是正数，则-1是无效的，这个pointer就不起作用
        int whereNeg = neg - 1;
        int[] ans = new int[A.length];
        int i = 0;
        //类似于merge sort的merge的思路
        while(whereNeg >= 0 && wherePos < A.length){
            int largestNeg = A[whereNeg];
            int smallestPos = A[wherePos];
            
            if (largestNeg * largestNeg <= smallestPos * smallestPos){
                ans[i] = largestNeg * largestNeg;
                whereNeg -= 1;
            } else {
                ans[i] = smallestPos * smallestPos;
                //注意这里 很可能出现 wherepose +=1后达到了n， 推出了循环，所以最后wherepos的id其实就在最后一位了
                wherePos +=1;
            }
            i++;
        }
        //正因为上述的理由，不能用 wherePos != len 来作为边界条件
        if (wherePos < A.length){
            for (int k = wherePos; k < A.length; k++){
                ans[i] = A[k] * A[k];
                i+=1;
            }
        } 
        
        if (whereNeg >= 0){
            for (int k = whereNeg; k >= 0; k--){
                ans[i] = A[k] * A[k];
                i+=1;
            }
        }
        
        return ans;
    }
}


