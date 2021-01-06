package algorithm.dp;

/**
 * Steps ������¥�ݣ���̬�滮��⣩
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��12��25�� 17:06
 */
public class Steps {

    public static void main(String[] args) {
        //һֻ����һ�ο�������1��̨�ף�Ҳ��������2���������������һ��n����̨���ܹ��ж�����������
        //dp[1] = 1 dp[2] = 2  dp[3] = 3 ��dp[n]
        int step = 30;
        System.out.println(climbStairs(step));
    }

    private static int climbStairs(int n) {
        int[] dp = new int[n+1];
        if(n == 0 || n == 1 || n == 2){
            return n == 0 ? 1 : n;
        }
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i < n + 1; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
