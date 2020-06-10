package algorithm.sort.insertion;

/**
 * InsertionSort 插入排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月09日 9:22
 */
public class InsertionSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * InsertionSort
     * @description 插入排序排序(每次取一个元素与已排序的区间进行比较，找到合适的位置进行插入。
     * 核心，外层循环值为待比较已排序区间插入的值，外层循环的值不断与内层已经排序的元素区间进行比较
     * 满足排序条件则互换，不满足则跳出当前循环。
     * @param arr 待排序数组
     * @return
     * @author caizhichong
     * @date 2020/5/9
     * @version V1.0
     */
    private static void sort(int [] arr){
        for(int i = 1; i< arr.length; i++){
            for(int j = i -1; j >=0 ; j--){
                //将arr[i]从已排序的区间中从后往前依次比较，满足条件则互换位置。内层第一次循环arr[j+1]=arr[i]
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }else{
                    break;
                }
            }
            //输出结果
            System.out.print("第" + (i) +"次结果：");
            for (int k = 0; k < arr.length; k++) {
                System.out.print(" " + arr[k]);
            }
            System.out.println();
        }

    }
}
