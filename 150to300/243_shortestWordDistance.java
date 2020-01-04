/*
[a,c,b,e,a,b], string 1 = a , string 2 = b;  mindist = 1

思想是 只有当两个string都找到了才能计算二者的距离，但有可能一开始pair到的距离不是最小的，最小距离在最后才出现。
那其实一旦我们两个boolean都说found了，我们就计算一次minDist，用的indx是found下的indx。 上述例子可以看到哪怕到了”e“这个地方，因为boolean还是都是true， 我们计算 minDist，依旧用的是 idx = 0 和 idx = 2来做了一次. 

这里可能会想到是否可以每找到了一次pair并且计算了minDist后就把boolean归零成false，这就可以避免多余的计算。不行，会有一个corner case：
["a","c","b","a"]
"a"
"b"
很明显 只有当我们走到了 b 才出现一次pair， 计算min = 2 - 0 = 2后把 found1 set false, && found2 set false. 会发觉走到最后一个a的时候，b被清除了，就少了一次计算。

所以还是得一旦found后，不管此刻是不是string 1 或者 2，就计算一次minDist， 反正如果此刻没有一个是匹配的，我们的indx就不会更新，多余的计算用的还是之前pair到的indx


*/


class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        boolean found1 = false;
        boolean found2 = false;
        int where1= 0;
        int where2= 0;
        
        int mindist = words.length;
        for (int i = 0; i <words.length; i++){
            if (words[i].equals(word1)){
                where1 = i;
                found1 = true;
            }
            
            if (words[i].equals(word2)){
                where2 = i;
                found2 = true;
            }
            
            if(found1 && found2){
                mindist = Math.min(mindist, Math.abs(where2 - where1));
            }
        }
        return mindist;
    }
}
