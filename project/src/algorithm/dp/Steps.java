package algorithm.dp;

/**
 * Steps 青蛙跳楼梯（动态规划求解）
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年12月25日 17:06
 */
public class Steps {

    public static void main(String[] args) {
        //一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
        //dp[1] = 1 dp[2] = 2  dp[3] = 3 求dp[n]
        System.out.println(climbStairs(30));
    }

    public static int climbStairs(int n) {
        int steps = n;
        int[] dp = new int[steps+1];
        if(n == 0 || n == 1 || n == 2){
            return n == 0 ? 1 : n;
        }
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i < steps + 1; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
