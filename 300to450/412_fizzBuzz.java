/*
如果一个数字是三的倍数 就是 'Fizz', 如果是五的倍数 就是 “Buzz” 如过同时都是3 和 5 的倍数那就是 “FizzBuzz”
*/


class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        String fizz = "Fizz";
        String buzz = "Buzz";
        String fizzbuzz = "FizzBuzz";
        
        for (int i = 1; i <= n; i++){
            if (i % 5 == 0 && i % 3 == 0){
                ans.add(fizzbuzz);
                continue;
            }
            if (i % 5 == 0){
                ans.add(buzz);
                continue;
            }
            if (i % 3 == 0){
                ans.add(fizz);
                continue;
            }
            ans.add(String.valueOf(i));
        }
        return ans;
    }
}