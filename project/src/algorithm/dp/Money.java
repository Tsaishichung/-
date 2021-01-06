package algorithm.dp;


/**
 * Money ��Ǯ����(��̬�滮���)
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��12��29�� 13:51
 */
public class Money {

    public static void main(String[] args) {
        //leetcode-cn 322
        //���� k ����ֵ��Ӳ�ң���ֵ�ֱ�Ϊ c1, c2 ... ck��ÿ��Ӳ�ҵ��������ޣ��ٸ�һ���ܽ�� amount��
        // ����������Ҫ��öӲ�Ҵճ��������������ܴճ����㷨���� -1
        //1.Լ��
            //1 <= coins.length <= 12
            //1 <= coins[i] <= 2^31 - 1
            //0 <= amount <= 10^4
        //2.�ҳ�����
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
            //...�Դ�����
            // f(0) = 0, f(1) = 1, f(2) = 1
            // f(3) = min(f(2), f(1)) + 1 = 2
            // f(4) = min(f(3), f(f2)) + 1 = 2
            // f(5) = min(f(4), f(3), f(0)) + 1 = 1;
        // f(n) = min(f(n-coin)) + 1  coin �� coins n-coin > 0
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
        //Ĭд��̬�滮����Ҫ�أ���׶ξ������Ž�ģ��
        //1.�����ӽṹ  2.�޺�Ч��  3.�ظ�������
        //�Ե����ϣ�����������С��ģ�������Ƶ����ģ�Ľ⣬����ݷ��෴�����ݷ����Զ����£��ȵó����������Ľ⣬�����ν��С��ģ����Ľ⡣
        //���ݹ������մ�ŵĹ���
        for(int i = 1; i <= amount; i++){
            //�Ҳ�����Ӧ�Ľ��Ϊ0
            int min = -1;
            for(int j = 0; j < coins.length; j++){
                int remain = i - coins[j];
                if(remain >= 0){
                    int way = dp[remain];
                    //0�����㺬�壬һ��dp[0]�����0������dp[x] = 0��dp[x] = 0������ƥ�䣬��Ҫ����-1
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
