package algorithm.sort.heap;

/**
 * HeapSort ������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��12�� 9:37
 */
public class HeapSort {

    public static void main(String[] args) {
        //int[] heap = new int[]{6,2,1,5,3,4};
        int[] heap = new int[]{-1,1,5,60,41,20,17,97,56,98,99,10,50,40};
        buildHeap(heap);
        heapSort(heap);
        //������
        System.out.print("��������");
        for (int k = heap.length -1 ; k > 0; k--) {
            System.out.print(" " + heap[k]);
        }
        System.out.println();
    }


    /**
     * HeapSort
     * @description ���Ѳ���
     * @param
     * @return
     * @author caizhichong
     * @date 2020/5/15
     * @version V1.0
     */
    private static void buildHeap(int[] arr){
        //�����и����ڵ���жѻ�����
        for(int i = arr.length -1 >> 1; i >= 1; i--){
            heapify(arr, arr.length, i);
        }
    }

    /**
     * HeapSort
     * @description �Ƴ��Ѷ�Ԫ�ص�����ĩβ���ظ�n�ν�������
     * @param
     * @return
     * @author caizhichong
     * @date 2020/5/15
     * @version V1.0
     */
    private static void removeTop(int[] arr, int bound){
        if(bound == 0){
            return;
        }
        int temp = arr[1];
        arr[1] = arr[bound-1];
        arr[bound-1] = temp;
        heapify(arr,bound - 1, 1);
    }


    /**
     * HeapSort
     * @description �ѻ�
     * @param
     * @return
     * @author caizhichong
     * @date 2020/5/15
     * @version V1.0
     */
    private static void heapify(int[] arr, int length, int nodeIndex){
        while (true) {
            int maxPos = nodeIndex;
            //���ڵ�����ӱȽ�ֵ��С��ֵ���ΪmaxPos
            if (nodeIndex * 2 < length && arr[nodeIndex * 2] > arr[nodeIndex]) {
                maxPos = nodeIndex * 2;
            }
            //�Һ��������ֵ�Ƚ�
            if (nodeIndex * 2 + 1 < length && arr[nodeIndex * 2 + 1] > arr[maxPos]) {
                maxPos = nodeIndex * 2 + 1;
            }
            //������ֵ��Ϊ���ڵ㣬������󶥶�Ҫ���˳�ѭ��
            if (maxPos == nodeIndex) {
                break;
            }
            //����λ�á������ֵ�����Һ�����Ϊ��ǰ�ڵ�ȥ�����Լ������Һ��ӱȽϡ�ֱ��Ҷ�ӽڵ�
            int temp = arr[nodeIndex];
            arr[nodeIndex] = arr[maxPos];
            arr[maxPos] = temp;
            nodeIndex = maxPos;
        }
    }


    /**
     * HeapSort
     * @description ������
     * @param
     * @return
     * @author caizhichong
     * @date 2020/5/15
     * @version V1.0
     */
    private static void heapSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            removeTop(arr, (arr.length + 1) - i);
        }
    }
}
