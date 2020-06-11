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
        String source = "cabcabaacacbabacabacc";
        String target = "bacc";
        int pos = search(source.toCharArray(), target.toCharArray());
        System.out.println(pos);
    }


    /**
     * BM
     * @description 在源字符串中找寻包含目标字符串的下标位置
     * @param sourceArr 源字符串
     * @param targetArr 目标字符串
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int search(char[] sourceArr, char[] targetArr){
        if(targetArr.length == 0 || targetArr.length > sourceArr.length){
            return -1;
        }
        int[] badWordHash = hashIndexArr(targetArr);
        int[] sufiix = new int[targetArr.length];
        boolean[] prefix = new boolean[targetArr.length];
        preProcess(targetArr, sufiix, prefix);
        int shiftIndex = 0;
        //字符串位移
        shift:while(shiftIndex <= sourceArr.length - targetArr.length){
            int shift = 0;
            //匹配
            fit:for(int i = targetArr.length - 1; i >=0; i--){
                //存在坏字符，同时可能存在好后缀，根据其中最大的值进行位移。
                if(targetArr[i] != sourceArr[shiftIndex + i]){
                    //坏字符为单个字符，根据前面预处理的哈希值定位到字符在模式串哪个下标。
                    // 用当前坏字符的下标减去找到的坏字符下标，可能为负值，为负值的情况则一定存在好后缀
                    //最终位移的位数取决于坏字符的位移位数和好后缀的位移位数的最大值。
                    int badWordIndex = i - badWordHash[hash(sourceArr[shiftIndex + i])];
                    int goodSuffixIndex = 0;
                    int length;
                    //存在好后缀的情况
                    if((length = targetArr.length - 1 - i) != 0){
                        //根据前期预处理模式串各个可能的后缀字符串的哈希值，找到当前好后缀字符串最后一个字符的下标
                        int goodSuffixEndIndex = targetArr.length - sufiix[length];
                        int suffixPrefixEndIndex = targetArr.length;
                        //当在模式串中找不到好后缀的情况下，能不能位移整个模式串长度，取决于好后缀和前缀的匹配
                        //当好后缀的前缀能匹配时，则位移好后缀对应前缀的位移数
                        prefix:for(int prefixIndex = length; prefixIndex > 0; prefixIndex--){
                            if(prefix[prefixIndex]){
                                suffixPrefixEndIndex = prefixIndex;
                                break prefix;
                            }
                        }
                        goodSuffixIndex = goodSuffixEndIndex > suffixPrefixEndIndex ? suffixPrefixEndIndex : goodSuffixEndIndex;
                    }
                    shift = badWordIndex > goodSuffixIndex ? badWordIndex : goodSuffixIndex;
                    //模式串位移，进行下一轮判断比较
                    shiftIndex+=shift;
                    continue shift;
                }
            }
            //如果进行了匹配之后，位移数为0，则证明完全匹配，返回匹配的起始数组下标。
            if(shift==0){
                return shiftIndex;
            }
        }
        return -1;
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
    private static void preProcess(char[] target, int[] suffix, boolean[] prefix){
        //初始化值
        for(int i = 0; i < suffix.length; i++){
            suffix[i] = -1;
        }
        int goodSuffixLength = 1;
        while(goodSuffixLength < target.length){
            char[] suffixArr = subArray(target, target.length - goodSuffixLength, target.length);
            long suffixHashCode = hashcode(suffixArr);
            for(int i = target.length - 1; i >= goodSuffixLength; i--){
                char[] fixArr = subArray(target, i - goodSuffixLength ,i);
                long fitHashCode = hashcode(fixArr);
                if(suffixHashCode == fitHashCode){
                    suffix[goodSuffixLength] = i - 1;
                    break;
                }
            }
            char[] suffixPrefixArr = subArray(target,0, goodSuffixLength);
            long suffixPrefixHashCode = hashcode(suffixPrefixArr);
            if(suffixHashCode == suffixPrefixHashCode){
                prefix[goodSuffixLength] = true;
            }
            goodSuffixLength++;
        }

    }

    /**
     * BM
     * @description 根据索引范围截取字符串数组
     * @param sourceArr 源字符串数组
     * @param startIndex 起始索引
     * @param endIndex 终止索引
     * @return
     * @author caizhichong
     * @date 2020/6/11
     * @version V1.0
     */
    private static char[] subArray(char[] sourceArr, int startIndex, int endIndex){
        char[] destArr = new char[endIndex - startIndex];
        int index = 0;
        for(int i = startIndex; i < endIndex; i++){
            destArr[index++] = sourceArr[i];
        }
        return destArr;
    }

    /**
     * BM
     * @description 计算某一串字符的hash值。
     * @param target 字符数组
     * @return
     * @author caizhichong
     * @date 2020/6/11
     * @version V1.0
     */
    private static long hashcode(char[] target){
        long hashcode = hash(target[0]);
        int alphabetWeights = 26;
        for(int i = 1; i < target.length; i++){
            hashcode += hash(target[i]) * alphabetWeights;
            alphabetWeights *= alphabetWeights;
        }
        return hashcode;
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
        for(int i = 0; i < hashIndex.length; i++){
            //默认数组下标不存在
            hashIndex[i] = -1;
        }
        for(int i = 0; i < target.length; i++){
            hashIndex[hash(target[i])] = i;
        }
        return hashIndex;
    }

    /**
     * BM
     * @description 对每个字符进行hash，默认范围为为字母表26个字母
     * @param word 字符
     * @return
     * @author caizhichong
     * @date 2020/6/11
     * @version V1.0
     */
    private static int hash(char word){
        return word - 'a';
    }
}
