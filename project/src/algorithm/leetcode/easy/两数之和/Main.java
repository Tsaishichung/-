package algorithm.leetcode.easy.两数之和;

/**
 * Main 给定一个整数数组 nums?和一个目标值 target，请你在该数组中找出和为目标值的那?两个?整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * ?
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月15日 16:23
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

