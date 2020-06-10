package algorithm.sort.selection;

/**
 * SelectionSort 选择排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月09日 15:23
 */
public class SelectionSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * SelectionSort
     * @description 选择排序，每次从未排序的数组区间中选出一个最小(最大)的元素，插入已排序区间的尾端。依次进行n次
     * @param arr 待排序数组
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
            //输出结果
            System.out.print("第" + (i + 1) +"次结果：");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(" " + arr[k]);
            }
            System.out.println();
        }

    }
}
