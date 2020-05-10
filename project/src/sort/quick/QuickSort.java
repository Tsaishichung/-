package sort.quick;

/**
 * QuickSort 快速排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月09日 18:18
 */
public class QuickSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }


    private static void sort(int[] arr){
        sort(arr, 0, arr.length - 1);
        //输出结果
        System.out.print("输出结果：");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(" " + arr[k]);
        }
        System.out.println();
    }

    /**
     * QuickSort
     * @description 快速排序（递归分治思想）
     * 选取任意一个值作为中间点（考虑极端情况<已排序>可以在首尾中间各取一个值选取中间值，避免时间复杂度退化为O(n^n)）
     * 通过类似选择排序的方式把大于选定值的元素置于选定值左边数组空间位置，把大于选定值的元素置于选定值右边数组空间位置
     * 在分别对选中值左右两边的数组区间位置进行递归排序。
     * @param arr 待排序数组
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
        //记录中间点下标，用于后续的值置换
        int pivot = end;
        //根据中间点下标获取对应值，也是比较值。数组中小于此值的元素将排列在此值下标的前面。数组中大于此值的元素将排列在此值下标后面
        int pivotValue =  arr[end];
        //记录与中间值比较后小于中间值的数组下标。小于此下标的值元素均排列在中间点之前。
        int sorted = start;
        for(int i = start; i < end; i++){
            if(arr[i] < pivotValue){
                int temp = arr[sorted];
                arr[sorted++] = arr[i];
                arr[i] = temp;
            }
        }
        //遍历完数组后，将中间值下标位置元素与中间值原下标位置元素互换。
        arr[pivot] = arr[sorted];
        arr[sorted] = pivotValue;
        return sorted;
    }

}
