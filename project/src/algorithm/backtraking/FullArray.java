package algorithm.backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * FullArray ȫ���У������㷨��⣩
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��12��18�� 16:41
 */
public class FullArray {

    public static void main(String[] args) {
        //����һ�� û���ظ� ���ֵ����У����������п��ܵ�ȫ���С�
        //��ͨ��leetcode���й���46. ȫ����
        //ִ�н����ͨ��
        //ִ����ʱ��1 ms, ������ Java �ύ�л�����98.70%���û�
        //�ڴ����ģ�38.6 MB, ������ Java �ύ�л�����87.42%���û�
        // 1 2 3 4 5 -- ǰ3λ�̶������2λ����
        // 1 2 3 5 4 -- ǰ3λ�̶������2λ����
        // 1 2 4 5 3 -- ǰ2λ�̶������3λ����
        // 1 2 4 3 5 -- ǰ2λ�̶������3λ����
        // 1 2 5 3 4 -- ǰ2λ�̶������3λ����
        // 1 2 5 4 3 -- ǰ2λ�̶������3λ����

        // 1 3 2 4 5 -- ǰ1λ�̶������4λ����
        // 1 3 2 5 4 -- ǰ1λ�̶������4λ����
        // 1 3 4 5 2 -- ǰ1λ�̶������4λ����
        // 1 3 4 2 5 -- ǰ1λ�̶������4λ����
        // 1 3 5 2 4 -- ǰ1λ�̶������4λ����
        // 1 3 5 4 2 -- ǰ1λ�̶������4λ����

        // 1 4 2 3 5 -- ǰ1λ�̶������4λ����
        // 1 4 2 5 3 -- ǰ1λ�̶������4λ����
        // 1 4 3 2 5 -- ǰ1λ�̶������4λ����
        // 1 4 3 5 2 -- ǰ1λ�̶������4λ����
        // 1 4 5 2 3 -- ǰ1λ�̶������4λ����
        // 1 4 5 3 2 -- ǰ1λ�̶������4λ����

        // 1 5 2 3 4 -- ǰ1λ�̶������4λ����
        // 1 5 2 4 3 -- ǰ1λ�̶������4λ����
        // 1 5 3 2 4 -- ǰ1λ�̶������4λ����
        // 1 5 3 4 2 -- ǰ1λ�̶������4λ����
        // 1 5 5 2 3 -- ǰ1λ�̶������4λ����
        // 1 5 4 3 2 -- ǰ1λ�̶������4λ����
        int[] array = new int[]{1, 2, 3, 4, 5};
        List<List<Integer>> resultList = permute(array);
        for(List<Integer> result : resultList){
            for(int element : result){
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }


    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        fullArray(nums, 0, new boolean[nums.length], resultList, new ArrayList<>());
        return resultList;
    }

    /**
     * FullArray
     * @description
     * @param array ��������
     * @param count ��ǰ��ӵĵ�count��Ԫ��
     * @param visited ����ڵ��Ƿ����
     * @param resultList ��������
     * @param result �����
     * @return
     * @author caizhichong
     * @date 2020/12/21
     * @version V1.0
     */
    private static void fullArray(int[] array, int count, boolean[] visited, List<List<Integer>> resultList, List<Integer> result){
        //�ݹ���ֹ����(���нڵ���ѷ��ʹ���)
        if(count == array.length){
            resultList.add(new ArrayList<>(result));
            return;
        }
        for(int i = 0; i < array.length; i++){
            if(visited[i]){
                continue;
            }
            //�Ե�countλ��ֵ
            if(result.size() < count + 1){
                result.add(array[i]);
            }else{
                result.set(count, array[i]);
            }
            visited[i] = true;
            fullArray(array, count + 1, visited, resultList, result);
            visited[i] = false;
        }
    }
}
