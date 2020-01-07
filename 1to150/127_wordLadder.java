/*
map: 每一个word 它可能变成的且存在于 list中的单词
for char in word:
    for chane in (a to z):
        if change != char:
            replace the char with change, and see if it we have this in the set
                if yes, put this new word as the edge of the current word
hit -> [hot, ]
hot -> [dot, lot]
dot -> [hot, dog, lot]
dog -> [dot, log, cog]
lot -> [dot, log]
log -> [lot, cog, dog ]
cog -> [log, dog]

开始bfs，先将start word hit放入：
    -> count +=1, hit -> [hot], hot into queue, seen[hit]
      -> count +=1, hot -> [dot, lot] => q[dot,lot], seen[hit, hot]
        -> count +=1, dot -> [hot, dog, lot] => q[lot, dog, lot], seen[hit, hot, dot]
          -> count +=1 lot -> [dot, log] => q[dog, lot, log], seen[hit,hot, dot, lot]
            -> count +=1 dog -> [dot, log, cog] => found cog return count
你会发觉，queue里会存在重复项，但没关系，因为cont的加一是在这一层bfs结束后才加，所以在每一个node的遍历所有
edge的时候总会有答案，或者没有答案，但不管怎样我们的加1都是对于每一个 node 来说的，因为 从一个词语可以变换多哥可能的edge，但无论变成哪一个都是 一次变换。

算法：
1. 建图，将每一个词语的可能且valid的下一个词语找到。 用char的变换加hashset实现
2. 建立queue，que init的时候放入第一个startString
3. 在bfs过程中，pop这一层级的所有nodes， 并且检测是否达到了tartget， 且访问过的node都需要mark，防止有互相访问的情况
4. 每次bfs一层结束后step++
*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //为了避免wordList有重复词语，我们的node不需要重复的所以新建一个set
        Set<String> wordSet = new HashSet<>();
        for (String str : wordList){
            wordSet.add(str);
        }
        //还需要将start放入
        wordSet.add(beginWord);
        
        /*1. build the map for each word in the wordSet*/
        Map<String,List<String>> map = new HashMap<>();
        for (String node : wordSet){
            buildMap(node, map, wordSet);
        }
        
        /*2.init the bfs with the start word*/
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        
        /*3. begin bfs*/
        int count = 0;
        //in order to avoid duplicated visiting
        Set<String> seen = new HashSet<>();
        while(!q.isEmpty()){
            int size = q.size();
            //每次遍历一层node的所有edges，就是变换加1
            count += 1;
            for (int i = 0; i < size; i++){
                String nextWord = q.poll();
                seen.add(nextWord);
                //如果在这一层node下的某一个可能的变换等于target，则说明找到了
                if (nextWord.equals(endWord)){
                    return count;
                }
                //不然则需要将这个word的edges 放入q中
                List<String> edges = map.get(nextWord);
                for (String nextNextWord : edges){
                    if (!seen.contains(nextNextWord)){
                        q.offer(nextNextWord);
                    }
                }
            }
        }
        return 0;
    }
    
    private void buildMap(String node, Map<String, List<String>> map, Set<String> wordSet){
        List<String> edges = new ArrayList<>();
        
        for (int i = 0; i < node.length(); i++){
            char[] nodeArray = node.toCharArray();
            char currentChar = nodeArray[i];
            
            for (char change = 'a'; change < 'z'; change++){
                if (change != currentChar){
                    nodeArray[i] = change;
                    String newString = String.valueOf(nodeArray);
                    if (wordSet.contains(newString)){
                        edges.add(newString);
                    }
                }
            }
        }
        map.put(node, edges);
    }
}