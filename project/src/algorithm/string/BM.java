package algorithm.string;

/**
 * BM �ַ���ƥ���㷨��Boyer-Moore��
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��10�� 14:27
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
     * @description ��Դ�ַ�������Ѱ����Ŀ���ַ������±�λ��
     * @param sourceArr Դ�ַ���
     * @param targetArr Ŀ���ַ���
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
        //�ַ���λ��
        shift:while(shiftIndex <= sourceArr.length - targetArr.length){
            int shift = 0;
            //ƥ��
            fit:for(int i = targetArr.length - 1; i >=0; i--){
                //���ڻ��ַ���ͬʱ���ܴ��ںú�׺��������������ֵ����λ�ơ�
                if(targetArr[i] != sourceArr[shiftIndex + i]){
                    //���ַ�Ϊ�����ַ�������ǰ��Ԥ����Ĺ�ϣֵ��λ���ַ���ģʽ���ĸ��±ꡣ
                    // �õ�ǰ���ַ����±��ȥ�ҵ��Ļ��ַ��±꣬����Ϊ��ֵ��Ϊ��ֵ�������һ�����ںú�׺
                    //����λ�Ƶ�λ��ȡ���ڻ��ַ���λ��λ���ͺú�׺��λ��λ�������ֵ��
                    int badWordIndex = i - badWordHash[hash(sourceArr[shiftIndex + i])];
                    int goodSuffixIndex = 0;
                    int length;
                    //���ںú�׺�����
                    if((length = targetArr.length - 1 - i) != 0){
                        //����ǰ��Ԥ����ģʽ���������ܵĺ�׺�ַ����Ĺ�ϣֵ���ҵ���ǰ�ú�׺�ַ������һ���ַ����±�
                        int goodSuffixEndIndex = targetArr.length - sufiix[length];
                        int suffixPrefixEndIndex = targetArr.length;
                        //����ģʽ�����Ҳ����ú�׺������£��ܲ���λ������ģʽ�����ȣ�ȡ���ںú�׺��ǰ׺��ƥ��
                        //���ú�׺��ǰ׺��ƥ��ʱ����λ�ƺú�׺��Ӧǰ׺��λ����
                        prefix:for(int prefixIndex = length; prefixIndex > 0; prefixIndex--){
                            if(prefix[prefixIndex]){
                                suffixPrefixEndIndex = prefixIndex;
                                break prefix;
                            }
                        }
                        goodSuffixIndex = goodSuffixEndIndex > suffixPrefixEndIndex ? suffixPrefixEndIndex : goodSuffixEndIndex;
                    }
                    shift = badWordIndex > goodSuffixIndex ? badWordIndex : goodSuffixIndex;
                    //ģʽ��λ�ƣ�������һ���жϱȽ�
                    shiftIndex+=shift;
                    continue shift;
                }
            }
            //���������ƥ��֮��λ����Ϊ0����֤����ȫƥ�䣬����ƥ�����ʼ�����±ꡣ
            if(shift==0){
                return shiftIndex;
            }
        }
        return -1;
    }



    /**
     * BM
     * @description �ú�׺�ĺ�׺ƥ��λ�ü�¼���Լ��ú�׺ǰ׺ƥ�䡣Ԥ����
     * @param target ģʽ��
     * @param suffix �ú�׺��ģʽ��ƥ��λ�ü�¼����
     * @param prefix �ú�׺ǰ׺��ģʽ����Ӧλ���Ƿ�ƥ���¼
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static void preProcess(char[] target, int[] suffix, boolean[] prefix){
        //��ʼ��ֵ
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
     * @description ����������Χ��ȡ�ַ�������
     * @param sourceArr Դ�ַ�������
     * @param startIndex ��ʼ����
     * @param endIndex ��ֹ����
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
     * @description ����ĳһ���ַ���hashֵ��
     * @param target �ַ�����
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
     * @description ����26����ĸ����hash��λ,��¼ÿ���ַ������ֵ�λ�á������ڻ��ַ���λ��
     * @param target ģʽ��
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int[] hashIndexArr(char[] target){
        int[] hashIndex = new int[26];
        for(int i = 0; i < hashIndex.length; i++){
            //Ĭ�������±겻����
            hashIndex[i] = -1;
        }
        for(int i = 0; i < target.length; i++){
            hashIndex[hash(target[i])] = i;
        }
        return hashIndex;
    }

    /**
     * BM
     * @description ��ÿ���ַ�����hash��Ĭ�Ϸ�ΧΪΪ��ĸ��26����ĸ
     * @param word �ַ�
     * @return
     * @author caizhichong
     * @date 2020/6/11
     * @version V1.0
     */
    private static int hash(char word){
        return word - 'a';
    }
}
