package algorithm.string;

/**
 * BF �ַ���ƥ�������㷨��Brute-Force��,����ƥ��
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��10�� 14:31
 */
public class BF {

    public static void main(String[] args) {
        String source = "cbacbad";
        String target = "acb";
        int pos = search(source.toCharArray(), target.toCharArray());
        System.out.println(pos);
    }


    /**
     * BF
     * @description BF�����㷨������ƥ��
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
        int searchIndex = 0;
        shift:while(searchIndex <= sourceArr.length - targetArr.length){
            int sameIndex = 0;
            fit:while (sameIndex < targetArr.length){
                if(sourceArr[searchIndex + sameIndex] == targetArr[sameIndex]){
                    sameIndex++;
                }else{
                    searchIndex++;
                    continue shift;
                }
            }
            return searchIndex;
        }

        return -1;
    }
}
