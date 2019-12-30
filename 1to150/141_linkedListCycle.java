/**
Solution 1:

hashset. 每次迭代head的时候检测这个head是否存在于seen中， 如果存在的话，则return true，不然最后循环结束后return false
O(n) time O(n) space
 */
// public class Solution {
//     public boolean hasCycle(ListNode head) {
//         if (head == null){
//             return false;
//         }
//         Set<ListNode> seen = new HashSet<>();
//         while(head != null){
//             if (!seen.contains(head)){
//                 seen.add(head);
//             } else {
//                 return true;
//             }
//             head = head.next;
//         }
//         return false;
//     }
// }

/*
Solution 2: two pointer, o(1) space
Use two pointers, walker and runner.
walker moves step by step. runner moves two steps at time.
if the Linked List has a cycle walker and runner will meet at some
point.

*/
class Solution{
   public boolean hasCycle(ListNode head) {
       if (head == null) {
           return false;
       }
       
       ListNode slow = head;
       ListNode fast = head;
       
       //现在要快慢指针遍历这个linkedlist，
       //因为我们不确定是否有环，如果有则fast永远不会为null， 而slow肯定为null
       //如果没有则fast会提前结束。
       while (fast!= null && fast.next != null){
           slow = slow.next;
           fast = fast.next.next;
           if (slow == fast){
               return true;
           }
       }
       return false;
    } 
}

