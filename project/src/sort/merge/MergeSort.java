package sort.merge;


/**
 * MergeSort 归并排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月09日 15:39
 */
public class MergeSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }

    /**
     * MergeSort
     * @description 归并排序（递归分治思想）
     * 利用分治递归实现排序，取数组mid为分区将数组分为左分区和右分区，分别进行递归排序，
     * 最后把两个分区的排序结果进行合并。合并的逻辑为分别对两个分区的元素进行大小比较，
     * 满足条件的分区下标做++操作，并且把元素写入到合并的数组里面，其中任意一个分区的
     * 数组元素清空后在把另外未清空的元素写入到合并数组，完成最终合并。递归的终止条件为
     * 数组起始下标=数组尾标（即元素为1的数组）
     * @param arr 待排序数组
     * @return
     * @author caizhichong
     * @date 2020/5/9
     * @version V1.0
     */
    private static void sort(int[] arr){
        sort(arr, 0, arr.length -1);
        //输出结果
        System.out.print("输出结果：");
        for (int k = 0; k < arr.length; k++) {
            System.out.print(" " + arr[k]);
        }
        System.out.println();
    }

    public static void sort(int[] arr, int start, int end){
        if(start >= end){
            return;
        }
        int mid = (start +(end - start)) >> 1;
        int[] sectionLeft = splitArr(arr, start, mid);
        int[] sectionRight = splitArr(arr, mid + 1, end);
        sort(sectionLeft, 0, sectionLeft.length - 1);
        sort(sectionRight, 0, sectionRight.length - 1);
        merge(arr, sectionLeft, sectionRight);
    }

    private static void merge(int[] dest, int[] sectionLeft, int[] sectionRight){
        //定义左右两个区间和合并数组的位置浮标，
        int destPoint = 0, lPoint = 0,rPoint = 0;
        while (lPoint <= sectionLeft.length - 1 && rPoint<= sectionRight.length - 1){
            if(sectionLeft[lPoint] > sectionRight[rPoint]){
                dest[destPoint++] = sectionRight[rPoint++];
            }else{
                dest[destPoint++] = sectionLeft[lPoint++];
            }
        }
        //判断哪个区间的元素已经清空，则把剩下区间的元素全部添加到目标数组
        if(lPoint == sectionLeft.length){
            addElement(dest, destPoint,sectionRight, rPoint);
        }else{
            addElement(dest, destPoint,sectionLeft, lPoint);
        }
    }

    private static void addElement(int[] dest, int startIndex, int[] source, int sourceIndex){
        for(int i = sourceIndex; i < source.length; i++){
            dest[startIndex++] = source[i];
        }
    }

    private static int[] splitArr(int[] target, int startIndex, int endIndex){
        int[] temp = new int[(endIndex - startIndex) + 1];
        int tempPonit = 0;
        for (int i = startIndex; i < target.length; i++) {
            if(i <= endIndex){
                temp[tempPonit++] = target[i];
            }
        }
        return temp;
    }

}
