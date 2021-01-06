package algorithm.dp;


/**
 * Money 零钱问题(动态规划求解)
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年12月29日 13:51
 */
public class Money {

    public static void main(String[] args) {
        //leetcode-cn 322
        //给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
        // 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
        //1.约束
            //1 <= coins.length <= 12
            //1 <= coins[i] <= 2^31 - 1
            //0 <= amount <= 10^4
        //2.找出规律
            //f(11) = f(11-1) + 1
                // f(10) = f(10-1) + 1
                // f(10) = f(10-2) + 1
                // f(10) = f(10-5) + 1
            //f(11) = f(11-2) + 1
                // f(9) = f(9-1) + 1
                // f(9) = f(9-2) + 1
                // f(9) = f(9-5) + 1
            //f(11) = f(11-5) + 1
                // f(6) = f(6-1) + 1
                // f(6) = f(6-2) + 1
                // f(6) = f(6-5) + 1
            //...以此类推
            // f(0) = 0, f(1) = 1, f(2) = 1
            // f(3) = min(f(2), f(1)) + 1 = 2
            // f(4) = min(f(3), f(f2)) + 1 = 2
            // f(5) = min(f(4), f(3), f(0)) + 1 = 1;
        // f(n) = min(f(n-coin)) + 1  coin ∈ coins n-coin > 0
        /*int[] coins = new int[]{1, 2 ,5};
        int amount = 11;*/
        /*int[] coins = new int[]{1, Integer.MAX_VALUE};
        int amount = 10000;*/
        int[] coins = new int[]{2};
        int amount = 3;
        System.out.println(moneyMoney(coins, amount));

    }
    private static int moneyMoney(int[] coins, int amount){
        if(amount == 0){
            return 0;
        }
        int[] dp = new int[amount+1];
        dp[0] = 0;
        //默写动态规划核心要素：多阶段决策最优解模型
        //1.最优子结构  2.无后效性  3.重复子问题
        //自底向上，根据问题最小规模解依次推导大规模的解，与回溯法相反，回溯法是自顶向下，先得出大规则问题的解，在依次解决小规模问题的解。
        //画递归树掌握大概的规律
        for(int i = 1; i <= amount; i++){
            //找不到对应的金币为0
            int min = -1;
            for(int j = 0; j < coins.length; j++){
                int remain = i - coins[j];
                if(remain >= 0){
                    int way = dp[remain];
                    //0有两层含义，一是dp[0]定义的0，二是dp[x] = 0。dp[x] = 0无银币匹配，需要返回-1
                    if(way == 0 && remain != 0){
                        continue;
                    }
                    if(min == -1 || way < min){
                        min = way;
                    }
                }
            }
            dp[i] = min + 1;
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }


}
