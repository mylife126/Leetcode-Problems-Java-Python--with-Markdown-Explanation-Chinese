/*

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"



*/

class Solution {
    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new Comparator<String>(){
            @Override
            public int compare(String a, String b){
                String order1 = a + b;
                String order2 = b + a;
                //ascending sorting order
                return order2.compareTo(order1);
            }
        });

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        StringBuilder sb = new StringBuilder();
        for (String numAsStr : asStrs) {
            sb.append(numAsStr);
        }

        return sb.toString();
    }
}