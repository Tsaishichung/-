package algorithm.string;

/**
 * KMP Knuth-Morris-Pratt �ַ���ƥ���㷨����BM�㷨˼�����ơ�
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��10�� 16:45
 */
public class KMP {

    public static void main(String[] args) {
        String source = "aabbabxabcabxabxc";
        String target = "abxabcabxabx";
        int pos = kmp(source.toCharArray(), target.toCharArray());
        System.out.println(pos);
    }

    private static int kmp(char[] chars, char[] target){
        int[] nexts = nexts(target);
        //�ַ�������
        int shiftIndex = 0;
        shift:while(shiftIndex <=  chars.length - target.length){
            //ģʽ��ƥ��
            fit:for(int j = 0; j < target.length; j++){
                if(chars[shiftIndex + j] == target[j]){
                    continue fit;
                }
                //��ǰ�±�λ��Ϊj���ַ�Ϊ���ַ�������ƶ�����Ϊj-next[j]��������ƶ�����Ϊj+1��
                // ��С�ƶ�����ȡ����nexts[j]��ֵ������ǰ׺�ܲ����ҵ���Ӧ�Ŀ�ƥ���׺
                shiftIndex += (j - nexts[j]);
                continue shift;
            }
            return shiftIndex;
        }
        return shiftIndex;
    }
    /**
     * KMP
     * @description ������ǰ׺��nexts���飬��������˳���Ϊ1~ģʽ������-1����ĺ�ǰ׺��
     *              �ֱ�洢��nexts�������±�Ϊi��Ԫ���С�
     * @param target ģʽ��
     * @return
     * @author caizhichong
     * @date 2020/6/24
     * @version V1.0
     */
    private static int[] nexts(char[] target){
        int[] nexts = new int[target.length];
        nexts[0] = -1;
        int k = -1;
        for(int i = 1; i < target.length; i++){
            while(k != -1 && target[k +1] != target[i]){
                k = nexts[k];
            }
            if(target[k+1] == target[i]){
                k++;
            }
            nexts[i] = k;
        }
        return nexts;
    }
}
