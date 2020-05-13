package sort.radix;

/**
 * RadixSort 基数排序
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月12日 9:37
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] lsdArr = new int[]{
            440604110,
            924510196,
            /*1212*/44372,
            512141259,
            /*7546122*/75,
            661212191,
            215451258,
            644521264,
            995454520,
            335450112,
            132132181,
            921231365,
            154523143};
        lsdSort(lsdArr, 9);
        //输出结果
        System.out.println("排序结果：");
        for (int k = 0; k < lsdArr.length; k++) {
            System.out.println(" " + lsdArr[k]);
        }
    }



    /**
     * RadixSort
     * @description 基于LSD(Least-significant-digit-first)最低有效位的排序，(适合数字排序)
     * 底层基于计数排序，排序字符或数字的长度决定外层计数排序的次数，因为时间复杂度为MN(M为排序字符的长度)
     * @param arr 待排序数组
     * @param m 字符长度
     * @return
     * @author caizhichong
     * @date 2020/5/13
     * @version V1.0
     */
    private static void lsdSort(int[] arr, int m){
        //用于获取数字第n位字符的计算。posCount随外层循环进行*10操作，内层计算vakue第n位字符公式为(value/posCount) %10
        int posCount = 1;
        for(; m > 0; m--){
            //数组只有0~9， 一共10个桶
            int[] bucketCount = new int[10];
            for(int i = 0; i < arr.length; i++){
                bucketCount[(arr[i]/posCount)%10]++;
            }
            //对bucketCount 进行累加求和
            int sum = bucketCount[0];
            for(int i = 1; i < bucketCount.length; i++){
                bucketCount[i] += sum;
                sum = bucketCount[i];
            }
            int[] tempArr = new int[arr.length];
            //这里需要理解一下为什么数组从后往前遍历，因为基数排序必须基于稳定排序算法实现。详细见基数排序分析文档
            for (int i = arr.length - 1; i >= 0; i--){
                tempArr[bucketCount[(arr[i]/posCount)%10] -1] = arr[i];
                bucketCount[(arr[i]/posCount)%10]--;
            }
            //拷贝数据到原数组中
            for (int i = 0; i < arr.length; i++){
                arr[i] = tempArr[i];
            }
            //基数*10，进入下次循环对高一位进行计数排序
            posCount *=10;
        }
    }


    /**
     * RadixSort
     * @description 基于MSD(Most-significant-digit-first)最高有效位的排序，就是一种特殊的桶排序
     *
     * @param arr 待排序数组
     * @return
     * @author caizhichong
     * @date 2020/5/13
     * @version V1.0
     */
    private static void msdSort(int[] arr){

    }

}
