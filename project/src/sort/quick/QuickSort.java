package sort.quick;

/**
 * QuickSort ��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��09�� 18:18
 */
public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }


    private static void sort(int[] arr){
        sort(arr, 0, arr.length - 1);
        //������
        System.out.print("��������");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(" " + arr[k]);
        }
        System.out.println();
    }

    /**
     * QuickSort
     * @description �������򣨵ݹ����˼�룩
     * ѡȡ����һ��ֵ��Ϊ�м�㣨���Ǽ������<������>��������β�м��ȡһ��ֵѡȡ�м�ֵ������ʱ�临�Ӷ��˻�ΪO(n^n)��
     * ͨ������ѡ������ķ�ʽ�Ѵ���ѡ��ֵ��Ԫ������ѡ��ֵ�������ռ�λ�ã��Ѵ���ѡ��ֵ��Ԫ������ѡ��ֵ�ұ�����ռ�λ��
     * �ڷֱ��ѡ��ֵ�������ߵ���������λ�ý��еݹ�����
     * @param arr ����������
     * @return
     * @author caizhichong
     * @date 2020/5/9
     * @version V1.0
     */
    private static void sort(int[] arr, int start, int end){
        if(start >= end){
            return;
        }
        int pivot = partition(arr, start, end);
        sort(arr, start, pivot - 1);
        sort(arr, pivot + 1, end);
    }

    private static int partition(int[] arr, int start, int end){
        //int mid = (start + (end - start))>>1;
        //��¼�м���±꣬���ں�����ֵ�û�
        int pivot = end;
        //�����м���±��ȡ��Ӧֵ��Ҳ�ǱȽ�ֵ��������С�ڴ�ֵ��Ԫ�ؽ������ڴ�ֵ�±��ǰ�档�����д��ڴ�ֵ��Ԫ�ؽ������ڴ�ֵ�±����
        int pivotValue =  arr[end];
        //��¼���м�ֵ�ȽϺ�С���м�ֵ�������±ꡣС�ڴ��±��ֵԪ�ؾ��������м��֮ǰ��
        int sorted = start;
        for(int i = start; i < end; i++){
            if(arr[i] < pivotValue){
                int temp = arr[sorted];
                arr[sorted++] = arr[i];
                arr[i] = temp;
            }
        }
        //����������󣬽��м�ֵ�±�λ��Ԫ�����м�ֵԭ�±�λ��Ԫ�ػ�����
        arr[pivot] = arr[sorted];
        arr[sorted] = pivotValue;
        return sorted;
    }

}
