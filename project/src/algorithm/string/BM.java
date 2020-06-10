package algorithm.string;

/**
 * BM 字符串匹配算法（Boyer-Moore）
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月10日 14:27
 */
public class BM {

    public static void main(String[] args) {

    }


    /**
     * BM
     * @description 在源字符串中找寻包含目标字符串的下标位置
     * @param source 源字符串
     * @param target 目标字符串
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int search(String source, String target){
        if(source.length() == 0 || target.length() == 0 || target.length() > source.length()){
            return -1;
        }
        char[] sourceArr = source.toCharArray();
        char[] targetArr = target.toCharArray();
        int[] sufiix = new int[targetArr.length];
        boolean[] prefix = new boolean[targetArr.length];

        return 0;
    }



    /**
     * BM
     * @description 好后缀的后缀匹配位置记录，以及好后缀前缀匹配。预处理
     * @param target 模式串
     * @param suffix 好后缀在模式串匹配位置记录数组
     * @param prefix 好后缀前缀在模式串对应位置是否匹配记录
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static boolean[] preProcess(char[] target, int[] suffix, boolean[] prefix){

        return null;
    }


    /**
     * BM
     * @description 基于26个字母进行hash定位,记录每个字符最后出现的位置。（用于坏字符定位）
     * @param target 模式串
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int[] hashIndexArr(char[] target){
        int[] hashIndex = new int[26];
        for(int i = 0; i < target.length; i++){
            hashIndex[target[i] - 'a'] = i;
        }
        return hashIndex;
    }
}
