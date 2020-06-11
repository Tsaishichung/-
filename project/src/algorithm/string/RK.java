package algorithm.string;

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
        int pos = search(source.toCharArray(), target.toCharArray());
        System.out.println(pos);
    }


    /**
     * RK
     * @description Rabin-Karp ģʽ��ƥ���㷨����ʹ����ĸ��
     * @param sourceArr Դ�ַ���
     * @param targetArr ģʽ��
     * @return
     * @author caizhichong
     * @date 2020/6/10
     * @version V1.0
     */
    private static int search(char[] sourceArr, char[] targetArr){
        if(targetArr.length == 0 || targetArr.length > sourceArr.length){
            return -1;
        }
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
