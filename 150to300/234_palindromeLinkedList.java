/**
1. 首先linkedlist populate所有值，并且一一存在 一个 新的linkedlist中。
2. one pass 新的linkedlist的首尾，如果有一刻首位不等那就是false 不然是 true

 */
class Solution {
    private LinkedList<Integer> list = new LinkedList<>();
    
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        
        if (list.size() == 1) return true; 
        
        boolean ans = isPali(list);
        return ans;
    }
    
    private boolean isPali(LinkedList<Integer> list){
        boolean ans = true;
        /*
        需要预防奇偶问题， [1 0 1] 这个case， 如果用 while (!list.isEmpty())来判断 会在第二个循环的时候导致pollLast null 
        pointer。 所以其实如果list里还剩下一个的时候 就可以停止了，因为1个数也是pali
        */
        while (list.size() > 1) {
            int first = list.pollFirst();
            int last = list.pollLast();
            
            if (first != last){
                return false;
            }
        }
        return ans;
    }
}