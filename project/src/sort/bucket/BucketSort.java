package sort.bucket;

import sort.quick.QuickSort;

/**
 * BucketSort 桶排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月11日 22:11
 */
public class BucketSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        int[] arr = new int[]{99,5,60,41,20,17,97,56,98,1,10,50,40};
        sort(arr);
    }
    /**
     * BucketSort
     * @description 桶排序
     * @param arr 待排序数组
     * @return
     * @author caizhichong
     * @date 2020/5/11
     * @version V1.0
     */
    private static void sort(int[] arr){
        float factor = 0.1f;
        //首先先找到最大元素和最小元素，确定桶排序的范围。根据负载因子确定每个桶的取值范围
        int max = arr[0], min = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
            if(arr[i] < min){
                min = arr[i];
            }
        }
        //计算桶数量，创建bucket桶
        int bucketSize = (int) Math.ceil((max - min) * factor);
        int[][] bucket = new int[bucketSize][arr.length];
        int[] bucketCount = new int[bucketSize];
        for(int i = 0; i < arr.length; i++){
            int bucketPos = (int) Math.ceil(arr[i] * factor);
            bucket[bucketPos -1][bucketCount[bucketPos -1]++] = arr[i];
        }
        //对每个桶的元素进行快速排序
        for (int i = 0; i < bucketSize; i++){
            int[] bk = bucket[i];
            QuickSort.sort(bk, 0, bucketCount[i] -1);
        }
        //合并桶
        int count = 0;
        for(int i = 0; i < bucketSize; i++){
            for(int j = 0; j < bucketCount[i]; j++){
                arr[count++] = bucket[i][j];
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
