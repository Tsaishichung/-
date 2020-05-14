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
        /*int[] lsdArr = new int[]{
            440604110,
            924510196,
            *//*1212*//*44372,
            512141259,
            *//*7546122*//*75,
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
        System.out.println("lsd排序结果：");
        for (int k = 0; k < lsdArr.length; k++) {
            System.out.println(" " + lsdArr[k]);
        }*/

        String[] msdArr = new String[]{
            "gasdasddadsa",
            "fsdfsdfsafas",
            "fdsgdfghfdgs",
            "asdasdasasfd",
            "fdsfsdhfghfg",
            "gfsfdgsdfgds",
            "sadjghjghhfg",
            "rtretgfdgjhf",
            "jghkjhgdsvfc",
            "dfgdfvxcrhef",
            "jhyrutrfdgfd",
            "jthjfgjhfghs",
            "aafsdf",
            "fhadjdfa",
            "gfghlkqew",
            "hfgkdl",
            "jljkrweo",
            "qqweqtret",
            "yeyzzfb",
            "nhgnllzzcbvc",
            "nbasdxcvkjxj",
            "nbbdasdasf",
            "nbbafdgdf",
            "nbbadasf"
        };
        msdSort(msdArr, 0, msdArr.length -1, 0);
        //输出结果
        System.out.println("msd排序结果：");
        for (int k = 0; k < msdArr.length; k++) {
            System.out.println(" " + msdArr[k]);
        }
    }

    /**
     * RadixSort
     * @description 基于LSD(Least-significant-digit-first)最低有效位的排序，(适合数字排序)
     * 底层基于计数排序，排序字符或数字的长度决定外层计数排序的次数，因为时间复杂度为MN(M为排序字符的长度)
     * @param arr 待排序数组
     * @param m 最大字符长度
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


    /** 桶大小，如果是ASCII值则为256，UniCode为65536*/
    private static final int BUCKET_SIZE = 256;
    /**桶内元素大小小于等于此值则进行归并排序*/
    private static final int MERGE_SORT_LIMIT_SIZE = 1;

    /**
     * RadixSort
     * @description 基于MSD(Most-significant-digit-first)最高有效位的排序，就是一种特殊的桶排序
     * 适合字母排序。
     * @param arr 待排序数组
     * @param start 数组范围-起始下标
     * @param end 数组范围-终止下标
     * @param charIndex 比较字符的位置（比较的第几个字符）
     * @return
     * @author caizhichong
     * @date 2020/5/13
     * @version V1.0
     */
    private static void msdSort(String[] arr, int start, int end, int charIndex){
        if(start >= end){
            return;
        }
        if((start + MERGE_SORT_LIMIT_SIZE) >= end){
            //小于此值则进行插入排序（此处可以使用其他排序算法，必须为稳定排序算法，决定了msd radix sort是否为稳定排序算法）
            insertionSort(arr, start, end, charIndex);
            return;
        }
        //将元素统计到桶中
        int[] bucket = new int[BUCKET_SIZE];
        for(int i = start; i <= end; i++){
            if(charIndex >= arr[i].length()){
                bucket[0]++;
                return;
            }
            int pos = arr[i].charAt(charIndex);
            bucket[pos]++;
        }
        //对bucket数组进行累加求和
        int sum = bucket[0];
        for(int i = 1; i < BUCKET_SIZE; i++){
            bucket[i] += sum;
            sum = bucket[i];
        }
        //对数据进行bucket的位置进行排序
        String[] temp = new String[(end - start)+1];
        for(int i = end; i >= start; i--){
            if(charIndex >= arr[i].length()){
                temp[bucket[0]-1] = arr[i];
                bucket[0]--;
                continue;
            }
            int pos = arr[i].charAt(charIndex);
            temp[bucket[pos]-1] = arr[i];
            bucket[pos]--;
        }
        //拷贝回原数组
        for(int i = 0; i < temp.length; i++){
            arr[start + i] = temp[i];
        }
        //charIndex ++ 递归继续排序
        charIndex++;
        int lowIndex = bucket[0];
        for(int i = 0; i < BUCKET_SIZE; i++){
            if(bucket[i] == 0){
                continue;
            }
            msdSort(arr, start + lowIndex, (start + bucket[i]) - 1, charIndex);
            lowIndex = bucket[i];
        }
    }

    private static void insertionSort(String[] arr, int start, int end, int charIndex){
        for(int i = start + 1; i <= end; i++){
            for(int j = i; j > start; j--){
                if(less(arr[j -1], arr[j], charIndex)){
                    String temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    private static boolean less(String prev, String next, int startIndex){
        for(int i = startIndex; i < Math.min(prev.length(), next.length()); i++){
            if(prev.charAt(i) < next.charAt(i)){
                return false;
            }
            if(prev.charAt(i) > next.charAt(i)){
                return true;
            }
        }
        return prev.length() < next.length();
    }

}
