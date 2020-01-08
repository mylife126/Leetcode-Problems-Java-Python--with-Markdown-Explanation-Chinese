/**
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

重点： 
面试需要问清楚考官： 这个starting point是不是指的是两个linkedlist里有一个共同重复的reference 或者 object。 不然只是看val的话就会有多个可能性。

所以既然存在重复的且一个reference，那就可以直接hash来做
1. traverse所有的headA
2. 二次遍历headB找hash里是否存在重复的object
3. 不然就是null
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        
        Set<ListNode> aSet = new HashSet<>();
        while (headA != null){
            aSet.add(headA);
            headA = headA.next;
        }
        
        while(headB != null){
            if (aSet.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}