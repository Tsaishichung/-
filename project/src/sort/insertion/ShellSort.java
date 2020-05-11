package sort.insertion;

/**
 * ShellSort 希尔排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月11日 10:53
 */
public class ShellSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * ShellSort
     * @description 希尔排序也是插入排序的一种，又称缩小增量排序。在时间效率上相比其他插入排序有较大的的改进。
     *
     * @param
     * @return
     * @author caizhichong
     * @date 2020/5/11
     * @version V1.0
     */
    private static void sort(int[] arr){
        for(int gap = arr.length >> 1; gap >= 1; gap >>= 1){
            for(int i = 0; i < arr.length - gap; i++){
                if(arr[i] > arr[i + gap]){
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                }
            }
        }
        //输出结果
        System.out.print("排序结果：");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(" " + arr[k]);
        }
        System.out.println();
    }
}
