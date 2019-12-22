/*
for this question, we want to make sure that the prefix is continuous and the first letter is 
FOR SURE started with the first letter of each word. 

Thus, we can loop through the first word's every character, 
and check if the looped character is also occurred in the same location of all OTHER word in the array.

Thus, the boundary condition is that current char's indx is out of the current looped word,
or the current word's current location does not have the same character. 
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        //set the idx for each character's location
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : strs[0].toCharArray()){
            for (int i = 1; i < strs.length; i++){
                String currentString = strs[i];
                //注意 不能判断是否当前的string contains 现在这个character，因为 aa ab, 第二个a对于ab来说依旧存在，
                //所以得判断这个位置上的ab的char是否等于a这个char
                // if (idx >= currentString.length() || !currentString.contains(Character.toString(c))){
                if (idx >= currentString.length() || currentString.charAt(idx) != c) {
                    return sb.toString();
                }  
            }
            //if after looping through all other word, the character satisfies every word, we append this into the sb,
            //other it would be returned before 
            sb.append(c);
            idx++;
        }
        return sb.toString();
    }
}