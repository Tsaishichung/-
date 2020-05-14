package sort.merge;


/**
 * MergeSort �鲢����
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��09�� 15:39
 */
public class MergeSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * MergeSort
     * @description �鲢���򣨵ݹ����˼�룩
     * ���÷��εݹ�ʵ������ȡ����midΪ�����������Ϊ��������ҷ������ֱ���еݹ�����
     * �����������������������кϲ����ϲ����߼�Ϊ�ֱ������������Ԫ�ؽ��д�С�Ƚϣ�
     * ���������ķ����±���++���������Ұ�Ԫ��д�뵽�ϲ����������棬��������һ��������
     * ����Ԫ����պ��ڰ�����δ��յ�Ԫ��д�뵽�ϲ����飬������պϲ����ݹ����ֹ����Ϊ
     * ������ʼ�±�=����β�꣨��Ԫ��Ϊ1�����飩
     * @param arr ����������
     * @return
     * @author caizhichong
     * @date 2020/5/9
     * @version V1.0
     */
    private static void sort(int[] arr){
        sort(arr, 0, arr.length -1);
        //������
        System.out.print("��������");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(" " + arr[k]);
        }
        System.out.println();
    }

    public static void sort(int[] arr, int start, int end){
        if(start >= end){
            return;
        }
        int mid = (start +(end - start)) >> 1;
        int[] sectionLeft = splitArr(arr, start, mid);
        int[] sectionRight = splitArr(arr, mid + 1, end);
        sort(sectionLeft, 0, sectionLeft.length - 1);
        sort(sectionRight, 0, sectionRight.length - 1);
        merge(arr, sectionLeft, sectionRight);
    }

    private static void merge(int[] dest, int[] sectionLeft, int[] sectionRight){
        //����������������ͺϲ������λ�ø��꣬
        int destPoint = 0, lPoint = 0,rPoint = 0;
        while (lPoint <= sectionLeft.length - 1 && rPoint<= sectionRight.length - 1){
            if(sectionLeft[lPoint] > sectionRight[rPoint]){
                dest[destPoint++] = sectionRight[rPoint++];
            }else{
                dest[destPoint++] = sectionLeft[lPoint++];
            }
        }
        //�ж��ĸ������Ԫ���Ѿ���գ����ʣ�������Ԫ��ȫ����ӵ�Ŀ������
        if(lPoint == sectionLeft.length){
            addElement(dest, destPoint,sectionRight, rPoint);
        }else{
            addElement(dest, destPoint,sectionLeft, lPoint);
        }
    }

    private static void addElement(int[] dest, int startIndex, int[] source, int sourceIndex){
        for(int i = sourceIndex; i < source.length; i++){
            dest[startIndex++] = source[i];
        }
    }

    private static int[] splitArr(int[] target, int startIndex, int endIndex){
        int[] temp = new int[(endIndex - startIndex) + 1];
        int tempPonit = 0;
        for (int i = startIndex; i < target.length; i++) {
            if(i <= endIndex){
                temp[tempPonit++] = target[i];
            }
        }
        return temp;
    }

}
