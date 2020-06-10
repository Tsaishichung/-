package algorithm.sort.heap;

/**
 * HeapSort 堆排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月12日 9:37
 */
public class HeapSort {

    public static void main(String[] args) {
        //int[] heap = new int[]{6,2,1,5,3,4};
        int[] heap = new int[]{-1,1,5,60,41,20,17,97,56,98,99,10,50,40};
        buildHeap(heap);
        heapSort(heap);
        //输出结果
        System.out.print("排序结果：");
        for (int k = heap.length -1 ; k > 0; k--) {
            System.out.print(" " + heap[k]);
        }
        System.out.println();
    }


    /**
     * HeapSort
     * @description 建堆操作
     * @param
     * @return
     * @author caizhichong
     * @date 2020/5/15
     * @version V1.0
     */
    private static void buildHeap(int[] arr){
        //对所有根茎节点进行堆化操作
        for(int i = arr.length -1 >> 1; i >= 1; i--){
            heapify(arr, arr.length, i);
        }
    }

    /**
     * HeapSort
     * @description 移出堆顶元素到数组末尾，重复n次进行排序
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
     * @description 堆化
     * @param
     * @return
     * @author caizhichong
     * @date 2020/5/15
     * @version V1.0
     */
    private static void heapify(int[] arr, int length, int nodeIndex){
        while (true) {
            int maxPos = nodeIndex;
            //父节点和左孩子比较值大小，值大的为maxPos
            if (nodeIndex * 2 < length && arr[nodeIndex * 2] > arr[nodeIndex]) {
                maxPos = nodeIndex * 2;
            }
            //右孩子与最大值比较
            if (nodeIndex * 2 + 1 < length && arr[nodeIndex * 2 + 1] > arr[maxPos]) {
                maxPos = nodeIndex * 2 + 1;
            }
            //如果最大值仍为父节点，则满足大顶堆要求，退出循环
            if (maxPos == nodeIndex) {
                break;
            }
            //交换位置。把最大值的左右孩子置为当前节点去跟它自己的左右孩子比较。直到叶子节点
            int temp = arr[nodeIndex];
            arr[nodeIndex] = arr[maxPos];
            arr[maxPos] = temp;
            nodeIndex = maxPos;
        }
    }


    /**
     * HeapSort
     * @description 堆排序
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
