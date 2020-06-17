package algorithm.leetcode.easy.����֮��;

/**
 * Main ����һ���������� nums?��һ��Ŀ��ֵ target�������ڸ��������ҳ���ΪĿ��ֵ����?����?���������������ǵ������±ꡣ
 *
 * ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ�������ͬһ��Ԫ�ز���ʹ�����顣
 *
 * ?
 *
 * ʾ��:
 *
 * ���� nums = [2, 7, 11, 15], target = 9
 *
 * ��Ϊ nums[0] + nums[1] = 2 + 7 = 9
 * ���Է��� [0, 1]
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/two-sum
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��15�� 16:23
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{3,3};
        System.out.println(twoSum(arr,6));
    }

    public static int[] twoSum(int[] nums, int target) {
        Node[] hashBucket = new Node[nums.length];
        for(int i = 0; i < nums.length; i++){
            buildHashBucket(nums[i], i, hashBucket);
        }
        for(int i = 0; i < nums.length; i++){
            int numValue = nums[i];
            int minusValue = target - numValue;
            int targetMinusValueIndex = hash(minusValue, hashBucket.length);
            for(Node current = hashBucket[targetMinusValueIndex]; current != null; current=current.getNext()){
                if(current.getKey() == minusValue){
                    return new int[]{i, current.getValue()};
                }
            }
        }
        return null;
    }


    private static void buildHashBucket(int key, int value, Node[] hashBucket){
        int hashIndex = hash(key, hashBucket.length);
        Node node =  hashBucket[hashIndex];
        if(node==null){
            hashBucket[hashIndex] = new Node(key, value, null);
        }else{
            hashBucket[hashIndex] = new Node(key, value, node);
        }
    }

    private static int hash(int key, int bucketLength){
        return  ((key >> 16) ^ key) & bucketLength - 1;

    }

    static class Node{

        private int key;

        private int value;

        private Node next;

        public Node(int key, int value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getKey(){
            return this.key;
        }

        public void setKey(int key){
            this.key = key;
        }


        public Node getNext(){
            return this.next;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public int getValue(){
            return this.value;
        }

        public void setValue(int value){
            this.value = value;
        }
    }

}

