package algorithm.backtraking;

/**
 * KnapsackProblem 0-1�������⣨ʹ�û����㷨�������̬�滮better��
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��12��15�� 16:44
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        //1.���屳��������
        int totalWeight = 100;
        //2.���������Ʒ������
        //int[] items = new int[]{12, 23, 34, 45, 56, 67, 78, 87, 90, 1};//bug
        //int[] items = new int[]{14, 27, 25, 33, 40, 10, 50, 9, 8, 11};
        //int[] items = new int[]{8, 13, 5, 20, 12, 1, 31, 9, 4, 8};
        int[] items = new int[]{55, 43, 53, 47, 41};
        //3.�������
        int maxWeight = knapsack(totalWeight, items, 0,0,  0);
        System.out.println("���������" + maxWeight);
    }

    private static int knapsack(int totalWeight, int[] items, int cWeight, int maxWeight, int itemIndex){
        //��������������أ�û����Ʒ��װ�˷���
        if(cWeight >= totalWeight || itemIndex >= items.length ){
            if(cWeight <= totalWeight && cWeight > maxWeight){
                return cWeight;
            }
            return maxWeight;
        }
        //��Ʒѡ��,��ӻ��߲���ӵ�����
        int pkgWeight = 0;
        int noPkgWeight = knapsack(totalWeight, items, cWeight, maxWeight, itemIndex + 1);
        if(cWeight + items[itemIndex] <= totalWeight){
            pkgWeight = knapsack(totalWeight, items, cWeight + items[itemIndex], maxWeight, itemIndex + 1);
        }
        return pkgWeight > noPkgWeight ? pkgWeight : noPkgWeight;
    }

}
