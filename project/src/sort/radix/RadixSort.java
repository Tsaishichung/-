package sort.radix;

/**
 * RadixSort ��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��12�� 9:37
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
        //������
        System.out.println("lsd��������");
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
        //������
        System.out.println("msd��������");
        for (int k = 0; k < msdArr.length; k++) {
            System.out.println(" " + msdArr[k]);
        }
    }

    /**
     * RadixSort
     * @description ����LSD(Least-significant-digit-first)�����Чλ������(�ʺ���������)
     * �ײ���ڼ������������ַ������ֵĳ��Ⱦ�������������Ĵ�������Ϊʱ�临�Ӷ�ΪMN(MΪ�����ַ��ĳ���)
     * @param arr ����������
     * @param m ����ַ�����
     * @return
     * @author caizhichong
     * @date 2020/5/13
     * @version V1.0
     */
    private static void lsdSort(int[] arr, int m){
        //���ڻ�ȡ���ֵ�nλ�ַ��ļ��㡣posCount�����ѭ������*10�������ڲ����vakue��nλ�ַ���ʽΪ(value/posCount) %10
        int posCount = 1;
        for(; m > 0; m--){
            //����ֻ��0~9�� һ��10��Ͱ
            int[] bucketCount = new int[10];
            for(int i = 0; i < arr.length; i++){
                bucketCount[(arr[i]/posCount)%10]++;
            }
            //��bucketCount �����ۼ����
            int sum = bucketCount[0];
            for(int i = 1; i < bucketCount.length; i++){
                bucketCount[i] += sum;
                sum = bucketCount[i];
            }
            int[] tempArr = new int[arr.length];
            //������Ҫ���һ��Ϊʲô����Ӻ���ǰ��������Ϊ���������������ȶ������㷨ʵ�֡���ϸ��������������ĵ�
            for (int i = arr.length - 1; i >= 0; i--){
                tempArr[bucketCount[(arr[i]/posCount)%10] -1] = arr[i];
                bucketCount[(arr[i]/posCount)%10]--;
            }
            //�������ݵ�ԭ������
            for (int i = 0; i < arr.length; i++){
                arr[i] = tempArr[i];
            }
            //����*10�������´�ѭ���Ը�һλ���м�������
            posCount *=10;
        }
    }


    /** Ͱ��С�������ASCIIֵ��Ϊ256��UniCodeΪ65536*/
    private static final int BUCKET_SIZE = 256;
    /**Ͱ��Ԫ�ش�СС�ڵ��ڴ�ֵ����й鲢����*/
    private static final int MERGE_SORT_LIMIT_SIZE = 1;

    /**
     * RadixSort
     * @description ����MSD(Most-significant-digit-first)�����Чλ�����򣬾���һ�������Ͱ����
     * �ʺ���ĸ����
     * @param arr ����������
     * @param start ���鷶Χ-��ʼ�±�
     * @param end ���鷶Χ-��ֹ�±�
     * @param charIndex �Ƚ��ַ���λ�ã��Ƚϵĵڼ����ַ���
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
            //С�ڴ�ֵ����в������򣨴˴�����ʹ�����������㷨������Ϊ�ȶ������㷨��������msd radix sort�Ƿ�Ϊ�ȶ������㷨��
            insertionSort(arr, start, end, charIndex);
            return;
        }
        //��Ԫ��ͳ�Ƶ�Ͱ��
        int[] bucket = new int[BUCKET_SIZE];
        for(int i = start; i <= end; i++){
            if(charIndex >= arr[i].length()){
                bucket[0]++;
                return;
            }
            int pos = arr[i].charAt(charIndex);
            bucket[pos]++;
        }
        //��bucket��������ۼ����
        int sum = bucket[0];
        for(int i = 1; i < BUCKET_SIZE; i++){
            bucket[i] += sum;
            sum = bucket[i];
        }
        //�����ݽ���bucket��λ�ý�������
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
        //������ԭ����
        for(int i = 0; i < temp.length; i++){
            arr[start + i] = temp[i];
        }
        //charIndex ++ �ݹ��������
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
