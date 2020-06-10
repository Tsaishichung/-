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

    }


    /**
     * BM
     * @description ��Դ�ַ�������Ѱ����Ŀ���ַ������±�λ��
     * @param source Դ�ַ���
     * @param target Ŀ���ַ���
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
     * @description �ú�׺�ĺ�׺ƥ��λ�ü�¼���Լ��ú�׺ǰ׺ƥ�䡣Ԥ����
     * @param target ģʽ��
     * @param suffix �ú�׺��ģʽ��ƥ��λ�ü�¼����
     * @param prefix �ú�׺ǰ׺��ģʽ����Ӧλ���Ƿ�ƥ���¼
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
     * @description ����26����ĸ����hash��λ,��¼ÿ���ַ������ֵ�λ�á������ڻ��ַ���λ��
     * @param target ģʽ��
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
