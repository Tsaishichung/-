package sort.counting;

/**
 * CountingSort ��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��05��11�� 23:32
 */
public class CountingSort {

    public static void main(String[] args) {
        //int[] arr = new int[]{6,2,1,5,3,4};
        //int[] arr = new int[]{99,5,20,41,20,5,99,5,99,20,10,50,41};
        int[] arr = new int[]{1,3,1,5,2,2,5,3,3,4,4,5};
        sort(arr);
    }

    /**
     * CountingSort
     * @description ��������
     *
     * @param arr ����������
     * @return
     * @author caizhichong
     * @date 2020/5/11
     * @version V1.0
     */
    private static void sort(int[] arr){
        float factor = 1f;
        //�������ҵ����Ԫ�غ���СԪ�أ�ȷ��Ͱ����ķ�Χ�����ݸ�������ȷ��ÿ��Ͱ��ȡֵ��Χ
        int max = arr[0], min = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max) max = arr[i];
            if(arr[i] < min) min = arr[i];
        }
        //����Ͱ����������bucketͰ��ÿ��Ԫ��һ��Ͱ
        int bucketSize = (int) Math.ceil((max - min) * factor) + 1;
        int[] bucketCount = new int[bucketSize];
        for(int i = 0; i < arr.length; i++){
            bucketCount[arr[i] - min]++;
        }
        //��bucketCount �����ۼ����
        int sum = bucketCount[0];
        for(int i = 1; i < bucketCount.length; i++){
            bucketCount[i] += sum;
            sum = bucketCount[i];
        }
        int[] tempArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            tempArr[bucketCount[arr[i] - min] -1] = arr[i];
            bucketCount[arr[i] - min]--;
        }
        //������
        System.out.print("��������");
        for (int k = 0; k < tempArr.length; k++) {
            System.out.print(" " + tempArr[k]);
        }
        System.out.println();
    }
}
