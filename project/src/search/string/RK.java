package search.string;

/**
 * RK Rabin-Karp �ַ���ƥ���㷨�����������㷨��hash�Ż�
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��10�� 14:56
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
     * @description Rabin-Karp ģʽ��ƥ���㷨����ʹ����ĸ��
     * @param source Դ�ַ���
     * @param target ģʽ��
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
     * @description ��ȡ�ַ������λ��26��λ��ֵ
     * @param highChar ���λ���ַ�
     * @param targetLength ģʽ���ַ����ȣ���λ����
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
     * @description Ԥ�����ַ�������ȡ�ô���hashֵ������26��λ�ķ�ʽ�����ϣֵ��
     * @param target �ַ�������
     * @param startIndex ��ʼ����
     * @param endIdex ��ֹ����
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
     * @description ����26��Ӣ����ĸ����hash����
     * @param word �ַ�
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int hash(char word){
        return word - 'a' + 1;
    }

}
