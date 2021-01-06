package algorithm.backtraking;


/**
 * Money ��Ǯ����(���ݷ����)
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��12��29�� 13:51
 */
public class Money {

    public static void main(String[] args) {
        //fixme �����֦���������ر�����������amount���С��coins�������׳�ʱ���������

        //���� k ����ֵ��Ӳ�ң���ֵ�ֱ�Ϊ c1, c2 ... ck��ÿ��Ӳ�ҵ��������ޣ��ٸ�һ���ܽ�� amount��
        // ����������Ҫ��öӲ�Ҵճ��������������ܴճ����㷨���� -1
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
            //Integer.MAX_VALUE + 1 Ϊ������ֱ�Ӳ������ˡ�
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
