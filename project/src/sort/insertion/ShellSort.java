package sort.insertion;

/**
 * ShellSort ϣ������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��11�� 10:53
 */
public class ShellSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40,132,25,49,76,77,93,22};
        sort(arr);
    }

    /**
     * ShellSort
     * @description ϣ������Ҳ�ǲ��������һ�֣��ֳ���С����������ʱ��Ч��������������������нϴ�ĵĸĽ���
     *
     * @param arr ����������
     * @return
     * @author caizhichong
     * @date 2020/5/11
     * @version V1.0
     */
    private static void sort(int[] arr){
        for(int gap = arr.length >> 1; gap >= 1; gap >>= 1){
            for(int i = gap; i < arr.length; i++){
                for(int j = i - gap; j >= 0; j -= gap){
                    if(arr[j] > arr[j+gap]){
                        int temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
        //������
        System.out.print("��������");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(" " + arr[k]);
        }
        System.out.println();
    }
}
