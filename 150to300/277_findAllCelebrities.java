/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

/*
Solution1 Brutal Force:
1. 双遍历n，对于每一对 i, j pair我们看一下是不是朋友， 如果是就用hashmap记录这个 i的朋友的个数
2. 第二个pass是来看map里是否有一个人的朋友只有1，也就是自己
3. 第三个pass是double check这个人的确是个celebraty， 因为会有一个corner case：
  [1,0]
  [0,1] 那这里压根就没有celebrtity， 所以我们要对找到的那个celebrity作最后一次检测 那就是
  如果这个人认识了任意一个i， 或者 有i不认识他， 那就是 不存在 return - 1
  
  最后才能retrun找到的这个人
*/
public class Solution extends Relation {
    public int findCelebrity(int n) {
        // System.out.println(n);
        
        if (n == 1) return 0;
        if (n == 0) return -1;
        //第一步是找到所有人的朋友的数量
        Map<Integer, Integer> friends = new HashMap<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (knows(i,j)){
                    friends.put(i, friends.getOrDefault(i,0) + 1);
                }
            }
        }
        //第二步是找看看有没有人的朋友数量只有1
        int where = -1;
        for (Integer key : friends.keySet()){
            int counts = friends.get(key);
            if (counts == 1) {
                where = key;
                break;
            }
        }
        //第三步是确定这个人的确就是celebrty
        if (where != -1){
            for (int i = 0; i < n ; i++){
                if (i != where && (knows(where, i) || !knows(i, where))) return -1;
            }
        }
        return where;
    }
}

/*
solution2: O(n)

我们可以将所有的人放入queue里，然后两两比对
knows(i,j) 
那如果 i 认识 j，则说明这个人肯定不可能是celebrity了，
同理，如果 i 不认识 j， 那根据定义 j必须所有人都认识，所以j也不可能是celebrity了

1. 所以我们可以用Queue把每个人放进去
2. 如果有这个人，queue最后肯定只能有一个，所以while（qsize >1) 在这个循环里每次都pop两个人出来，对这两个人做判断
3. 最后一步依旧是1 pass double check这个人是否是真的celebrity 因为有一个corner case
   1 0
   0  1
   
  itoj   jtoi   q    size
0  /      /    0 1     2
1  no     no    0      1     end

但此刻0并不是一个合法的 celebrity
所以第二个pass double check
*/

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n == 0) return -1;
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++){
            q.offer(i);
        }
        
        while(q.size() > 1){
            int p1 = q.poll();
            int p2 = q.poll();
            
            if (knows(p1,p2)){
                q.offer(p2);
            } else {
                q.offer(p1);
            }
        }
        
        int candidate = q.poll();
        // System.out.println(candidate);
        for (int i = 0; i < n ; i++){
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
}

