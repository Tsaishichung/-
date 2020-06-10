package algorithm.sort.bubble;

/**
 * BubbleSort 冒泡排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月08日 15:18
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{3,2,5,4,6,1};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * BubbleSort
     * @description 冒泡排序只会操作相邻的两个数据，每次冒泡都会对相邻的两个元素进行比较
     * 看看是否满足大小关系，不满足则互换位置，一次冒泡排序会让至少一个元素移动到它应该在的位置
     * ，重复n次，就完成了n个数据的排序工作
     *
     * @param arr 需要排序的数组
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
            //内存循环如果不触发互换位置，则代表已经完成排序，可以直接跳出循环
            boolean isSorted = true;
            //角标j的取值决定两两比较的顺序，
            // 如果角标递增则每次确定的元素为从后往前。
            for(int j = 0; j < arr.length - i -1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSorted = false;
                }
            }
            // 角标递减则每次确定的元素排序为从前往后
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
            //输出结果
            System.out.print("第" + (i +1) +"次结果：");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(" " + arr[k]);
            }
            System.out.println();
        }
    }
}
