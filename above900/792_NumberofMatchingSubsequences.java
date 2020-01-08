/*
Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".


brutal force 双循环
1. 外层循环所有的words里的string
2. 对于每一个string 内循环一次 S， 双指针的方法， 一开始的wordPointer就在0，每次string找到一次一样的char，就把wordPointer往后走一步。 
    一旦wordPointer == wordSize了则表示找到了一个word， count +=1
3. 剪枝： 每次找到了一个成立的词语，就放入hash里，然后下一次外循环到新的词语了就检测一次，如果存在，则不需要内循环，直接count++ 且continue

但这样会很慢
*/


class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        if (S == null || S.length() == 0){
            return 0;
        }
        Set<String> found = new HashSet<>();
        // Arrays.sort(words);
        
        char[] cArray = S.toCharArray();
        int count = 0;
        
        for (int i = 0; i < words.length; i++){
            String word = words[i];
            /*
            剪枝，如果之前这个词就表示找到了，直接跳过
            */
            if (found.contains(word)){
                count +=1;
                continue;
            }
            //双指针方案
            int wordPointer = 0;
            int wordSize = word.length();
            
            for (int sPointer = 0; sPointer < cArray.length; sPointer++){
                char currChar = word.charAt(wordPointer);
                char sChar = cArray[sPointer];
                //如果现在word的char找到了 就找下一个char
                if (sChar == currChar){
                    wordPointer +=1;
                }
                //如果wordpointer == size 则表示找完了 直接break
                if (wordPointer == wordSize){
                    count +=1;
                    found.add(word);
                    break;
                }
                
            }
        }
        return count;
    }
}