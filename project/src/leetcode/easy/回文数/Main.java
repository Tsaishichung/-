package leetcode.easy.回文数;

/**
 * Main
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月04日 9:02
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1234321));
    }

    private static boolean isPalindrome(int x){
        if(x < 0){
            return false;
        }
        int nb = x;
        int n = 0;
        while(x != 0){
            n = n*10 + x%10;
            x/=10;
        }
        return n==nb;
    }
}
