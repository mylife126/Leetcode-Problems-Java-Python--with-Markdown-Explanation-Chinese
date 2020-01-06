/*
Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false

关键是 首先建立str的strArray。 然后如果strArray的长度不等同于pattern的array的长度，直接false。 其次是map来记录pattern 与 str的一一对应关系。 如果不对应则false。 

接着判断：
1.如果此刻的pattern不存在hashmap里，
   a. 我们还需要规避一个cornercase， 那就是 'abba' 'dog dog dog dog', 可以看到 pattern = b的时候不存在于key中，但      是 dog却存在于values中，这意味着return false
   b. 上述判断过了后可以将此刻的 key value pair 放入map中
2. 但如果此刻的pattern存在于map里，只需要判断此刻的str 是否等于 它应该key value pair下的那个string即可
*/

class Solution {
    public boolean wordPattern(String pattern, String str) {
        char[] pArray = pattern.toCharArray();
        List<String> strArray = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == ' '){
                strArray.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(str.charAt(i));
            }
        }
        /*
        重点！ 需要将最后的sb 在loop外放一次，因为当str扫完后，例如'dog dog' 最后一个dog扫完后loop会因为
        idx越界而结束，所以需要额外加一次这个string
        */
        strArray.add(sb.toString());
        //首先判断是否size相同，如果不同直接一票否决
        if (strArray.size() != pArray.length){
            return false;
        }
        
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pArray.length; i ++){
            char whichPattern = pArray[i];
            String itsWord = strArray.get(i);
            
            if (!map.containsKey(whichPattern)){
                /*
                corner case 那就是此刻的key不存在，但是此刻的word曾经出现过，这违背了一key 一value的原则，
                return  false

                "abba"
                "dog dog dog dog"
                */
                if (map.containsValue(itsWord)){
                    return false;
                }
                //不然就是添加此刻的新的pair
                map.put(whichPattern, itsWord);
            } else {
                //如果有过这个key，则需要判断是否相同
                String pairedWord = map.get(whichPattern);
                if (!pairedWord.equals(itsWord)){
                    return false;
                }
            }
        }
        //全部扫完后return true即可
        return true;
    }
}