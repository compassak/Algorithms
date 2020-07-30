package algorithm.dp;

public class Knapsack {

    /**
     * 背包问题解决函数
     *
     * @param w   背包可装载的质量
     * @param n   物品个数
     * @param wt  每个物品的质量
     * @param val 每个物品的价值
     * @return 背包可以装载物品的最大价值
     */
    private int knapsack(int w, int n, int[] wt, int[] val) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j - wt[i - 1] < 0) {
                    //容量不足
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //容量足够
                    dp[i][j] = Math.max(dp[i - 1][j],
                                        dp[i - 1][j - wt[i - 1]] + val[i-1]);
                }
            }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        int w = 4;
        int n = 3;
        int[] wt = {2, 1, 2};
        int[] val = {4, 2, 3};
        System.out.println(new Knapsack().knapsack(w, n, wt, val));
    }
}
