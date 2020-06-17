package algorithm.leetcode.easy.������ת;

/**
 * Main
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��03�� 17:32
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(reverse(-12345678));
    }
    private static int reverse(int x){
        long n = 0;
        while(x!=0){
            n = n*10 + x%10;
            x /=10;
        }
        return (int)n == n? (int)n : 0;
    }
}
