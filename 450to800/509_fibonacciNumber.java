class Solution {
    public int fib(int N) {
        int[] dp = new int[N + 1];
        if (N == 2) return 1;
        if (N == 1) return 1;
        if (N == 0) return 0;
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++){
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[N];
    }
}