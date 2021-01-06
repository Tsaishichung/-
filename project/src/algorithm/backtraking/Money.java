package algorithm.backtraking;


/**
 * Money 零钱问题(回溯法求解)
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年12月29日 13:51
 */
public class Money {

    public static void main(String[] args) {
        //fixme 这里剪枝操作不是特别合理，大点数的amount配合小的coins集合容易超时或者溢出。

        //给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
        // 问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
        //1 <= coins.length <= 12
        //1 <= coins[i] <= Integer.MAX_VALUE
        //0 <= amount <= 10000
        int[] coins = new int[]{1, 2, 5};
        int amount = 5000;
        int[] memo = new int[amount+1];
        for(int i = 0; i < memo.length; i++){
            memo[i] = -1;
        }
        moneyMoney(coins, amount, 0, memo, 0);
        System.out.println(memo[amount]);
    }
    private static void moneyMoney(int[] coins, int amount, int cMount, int[] memo, int cCount){
        if(cMount == amount){
            return;
        }
        for(int i = 0; i < coins.length; i++){
            int tempMount = cMount + coins[i];
            int tempCount = cCount + 1;
            //Integer.MAX_VALUE + 1 为负数，直接不处理了。
            if(tempMount > amount){
                continue;
            }
            if(memo[tempMount] != -1 && tempCount > memo[tempMount]){
                return;
            }
            if(memo[amount] != -1 && tempCount > memo[amount]){
                return;
            }
            moneyMoney(coins, amount, tempMount, memo, tempCount);
            memo[tempMount] = tempCount;
        }
    }
}
