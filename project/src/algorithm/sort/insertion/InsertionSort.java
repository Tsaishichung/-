package algorithm.sort.insertion;

/**
 * InsertionSort ��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��09�� 9:22
 */
public class InsertionSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * InsertionSort
     * @description ������������(ÿ��ȡһ��Ԫ�����������������бȽϣ��ҵ����ʵ�λ�ý��в��롣
     * ���ģ����ѭ��ֵΪ���Ƚ���������������ֵ�����ѭ����ֵ�������ڲ��Ѿ������Ԫ��������бȽ�
     * �������������򻥻�����������������ǰѭ����
     * @param arr ����������
     * @return
     * @author caizhichong
     * @date 2020/5/9
     * @version V1.0
     */
    private static void sort(int [] arr){
        for(int i = 1; i< arr.length; i++){
            for(int j = i -1; j >=0 ; j--){
                //��arr[i]��������������дӺ���ǰ���αȽϣ����������򻥻�λ�á��ڲ��һ��ѭ��arr[j+1]=arr[i]
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }else{
                    break;
                }
            }
            //������
            System.out.print("��" + (i) +"�ν����");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(" " + arr[k]);
            }
            System.out.println();
        }

    }
}
