package algorithm.sort.bubble;

/**
 * BubbleSort ð������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��08�� 15:18
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{3,2,5,4,6,1};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * BubbleSort
     * @description ð������ֻ��������ڵ��������ݣ�ÿ��ð�ݶ�������ڵ�����Ԫ�ؽ��бȽ�
     * �����Ƿ������С��ϵ���������򻥻�λ�ã�һ��ð�������������һ��Ԫ���ƶ�����Ӧ���ڵ�λ��
     * ���ظ�n�Σ��������n�����ݵ�������
     *
     * @param arr ��Ҫ���������
     * @return
     * @author caizhichong
     * @date 2020/5/8
     * @version V1.0
     */
    private static void sort(int[] arr){
        if(arr.length <=1){
            return;
        }
        for(int i = 0; i < arr.length -1; i++){
            //�ڴ�ѭ���������������λ�ã�������Ѿ�������򣬿���ֱ������ѭ��
            boolean isSorted = true;
            //�Ǳ�j��ȡֵ���������Ƚϵ�˳��
            // ����Ǳ������ÿ��ȷ����Ԫ��Ϊ�Ӻ���ǰ��
            for(int j = 0; j < arr.length - i -1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSorted = false;
                }
            }
            // �Ǳ�ݼ���ÿ��ȷ����Ԫ������Ϊ��ǰ����
            /*for(int j = arr.length -1; j > i; j--){
                if(arr[j] > arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    isSorted = false;
                }
            }*/
            if(isSorted){
                break;
            }
            //������
            System.out.print("��" + (i +1) +"�ν����");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(" " + arr[k]);
            }
            System.out.println();
        }
    }
}
