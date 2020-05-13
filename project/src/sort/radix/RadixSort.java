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
        //������
        System.out.println("��������");
        for (int k = 0; k < lsdArr.length; k++) {
            System.out.println(" " + lsdArr[k]);
        }
    }



    /**
     * RadixSort
     * @description ����LSD(Least-significant-digit-first)�����Чλ������(�ʺ���������)
     * �ײ���ڼ������������ַ������ֵĳ��Ⱦ�������������Ĵ�������Ϊʱ�临�Ӷ�ΪMN(MΪ�����ַ��ĳ���)
     * @param arr ����������
     * @param m �ַ�����
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


    /**
     * RadixSort
     * @description ����MSD(Most-significant-digit-first)�����Чλ�����򣬾���һ�������Ͱ����
     *
     * @param arr ����������
     * @return
     * @author caizhichong
     * @date 2020/5/13
     * @version V1.0
     */
    private static void msdSort(int[] arr){

    }

}
