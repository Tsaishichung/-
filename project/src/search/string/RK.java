package search.string;

/**
 * RK Rabin-Karp 字符串匹配算法，基于朴素算法的hash优化
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月10日 14:56
 */
public class RK {

    public static void main(String[] args) {
        String source = "cbacbad";
        String target = "bad";
        int pos = search(source, target);
        System.out.println(pos);
    }


    /**
     * RK
     * @description Rabin-Karp 模式串匹配算法，（使用字母）
     * @param source 源字符串
     * @param target 模式串
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
        long targetHashCode = preProcess(targetArr,0 , targetArr.length-1);
        long sourceHashCode = preProcess(sourceArr, 0, targetArr.length-1);
        int searchIndex = 0;
        shift:while(searchIndex <= sourceArr.length - targetArr.length){
            if(targetHashCode == sourceHashCode){
                return searchIndex;
            }else{
                if(searchIndex + targetArr.length > sourceArr.length -1){
                    return -1;
                }
                sourceHashCode = (sourceHashCode - highCharHashCode(sourceArr[searchIndex], targetArr.length)) * 26 + hash(sourceArr[searchIndex + targetArr.length]);
                searchIndex++;
            }
        }
        return -1;
    }

    /**
     * RK
     * @description 获取字符串最高位的26进位的值
     * @param highChar 最高位的字符
     * @param targetLength 模式串字符长度（进位数）
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static long highCharHashCode(char highChar, int targetLength){
        int alphabetWeights = 26;
        for(int i = 2; i < targetLength; i++){
            alphabetWeights *= alphabetWeights;
        }
        return hash(highChar) * alphabetWeights;
    }

    /**
     * RK
     * @description 预处理字符串，获取该串的hash值，按照26进位的方式计算哈希值。
     * @param target 字符串数组
     * @param startIndex 起始索引
     * @param endIdex 终止索引
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static long preProcess(char[] target, int startIndex, int endIdex){
        int alphabetWeights = 26;
        int hashcode = hash(target[endIdex]);
        for(int i = endIdex -1; i >= startIndex ; i--){
            hashcode += hash(target[i]) * alphabetWeights;
            alphabetWeights *= alphabetWeights;
        }
        return hashcode;
    }

    /**
     * RK
     * @description 基于26个英文字母进行hash策略
     * @param word 字符
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int hash(char word){
        return word - 'a' + 1;
    }

}
