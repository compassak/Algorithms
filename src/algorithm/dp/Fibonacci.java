package test;

public class Fibonacci {

    private long fib1(int n) {
        if (n < 1) return 0;
        long[] memo = new long[n + 1];
        return recursion(n, memo);
    }

    private long recursion(int n, long[] memo) {
        if (n == 2 || n == 1) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = recursion(n - 1, memo) + recursion(n - 2, memo);
        System.out.println(n);
        return memo[n];
    }

    private long fib2(int n) {
        if (n == 2 || n == 1) return 1;
        long prev = 1,pPrev = 1;
        long ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = prev + pPrev;
            pPrev = prev;
            prev = ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int num = 4;
        System.out.println(new Fibonacci().fib1(num));
        System.out.println(new Fibonacci().fib2(num));
    }

}
