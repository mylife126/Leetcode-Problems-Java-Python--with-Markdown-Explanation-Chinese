/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 When Met this question, ask the interviewer if you want the list to be FULLY SORTED or just partitionning as the order shown.
 
 题目要求是：
 For example, 2 1 4 3 2 5 2， -> 2 1 2 2 3 4 5。 也就是说不需要完全sort， 按照出现的order即可， 但需要partition
 
 思路是两个pointer， 一个是before pointer， 另一个是after pointer。 one pass这个linkedlist，遇到比target 小的放入beforelist
 遇到比target大的放入afterlist里即可。
 
 最后将两个list链接起来，return beforepointer即可。
 
 注意dummyHead的运用，如果不加dummy的一个preDummy Val，假设每次更新就是更新自己的这个node，
 
 node.val = val,
 node = node.next. 但是这个时候的node.next不存在，所以会有null pointer issue
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode before = new ListNode(0);
        ListNode beforeTail = before;
        
        ListNode after = new ListNode(0);
        ListNode afterTail = after;
        
        while (head != null){
            int currVal = head.val;
            if (currVal < x){//Case A， 如果当前的值小于target，把它赋给beforeList
                beforeTail.next = new ListNode(currVal);
                beforeTail = beforeTail.next;
            } else {//CaseB， 如果当前的值大于等于target， 就把它赋给afterList
                afterTail.next = new ListNode(currVal);
                afterTail = afterTail.next;
            }
            
            //迭代下一个node
            head = head.next;
        }
        //当循环结束了，需要补全afterTail.next == null
        afterTail.next = null;
        //而此刻的before的末尾需要连接after的最开始的那个， 也就是dummy后一位
        beforeTail.next = after.next;
        
        //最后before就是一个完成的partition过的list
        return before.next;
    }
}