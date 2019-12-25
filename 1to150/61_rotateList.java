/*
1 2 3 4 5 null, k = 2
假设我们把list连成环状，   -> 1 -> 2 -> 3 -> 4 -> 5 -> 相当于现在的tail.next = 1， 那么如果k = 2， 意味着head，往右移动 n - k = 5 - 2 =3这个地方，所以这意味着，我们找到tail at n - k - 1 = 2这个地方也就是node=3， 将3.next = null, 也就是将链接断开即可。现在的list就变成：
   ->1->2->3- null    4->5->    也就是 4 5 1 2 3 

*/
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null){
            //如果就一个数或者为空，那无论怎么旋转后的结果都是自己
            return head;
        }
        //we need to find how long this linkedlist is, 注意这里是1，因为如果不为空的linkedList，最少长度也是1
        int n = 1;
        ListNode pointer = head;
        /*
        循环找到这个head的tail
        */
        while(pointer.next!= null){
            pointer = pointer.next;
            n++;
        }
        
        //链接tail和head, 此时的pointer已经在尾部了
        pointer.next = head;
        
        /*
        找到新的head， or 其实我们想找新的尾巴 tail @ n - k - 1
        */
        int trueK = k % n;
        for (int i = 0; i < n - trueK - 1; i++){
            head = head.next;
        }
        //现在的head 已经在 newTail这个位置了，所以我们的newHead 指向newTail.next即可
        ListNode newHead = head.next;
        //并且断开newTail后面的pointer
        head.next = null;
        return newHead;
    }
}

