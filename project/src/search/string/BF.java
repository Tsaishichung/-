package search.string;

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
        int pos = search(source, target);
        System.out.println(pos);
    }


    /**
     * BF
     * @description BF�����㷨������ƥ��
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
