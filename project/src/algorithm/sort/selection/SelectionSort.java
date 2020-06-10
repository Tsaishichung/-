package algorithm.sort.selection;

/**
 * SelectionSort ѡ������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��09�� 15:23
 */
public class SelectionSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * SelectionSort
     * @description ѡ������ÿ�δ�δ���������������ѡ��һ����С(���)��Ԫ�أ����������������β�ˡ����ν���n��
     * @param arr ����������
     * @return
     * @author caizhichong
     * @date 2020/5/9
     * @version V1.0
     */
    private static void sort(int[] arr){
        for(int i = 0; i< arr.length - 1; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            //������
            System.out.print("��" + (i + 1) +"�ν����");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(" " + arr[k]);
            }
            System.out.println();
        }

    }
}
