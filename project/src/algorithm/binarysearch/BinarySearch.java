package algorithm.binarysearch;

/**
 * BinarySearch 二分查找
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月26日 9:45
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,9,15,27,33,45,54,66,78};
        System.out.println(binarySearchByWhile(arr, 15));
        System.out.println(binarySearchByRecursive(arr,0, arr.length - 1, 45));
    }

    private static int binarySearchByWhile(int[] arr, int target){
        int low = 0;
        int high = arr.length -1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] < target){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearchByRecursive(int[] arr, int low, int high, int target){
        if(low > high){
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if(arr[mid] == target){
            return mid;
        }else if(arr[mid] < target){
            return binarySearchByRecursive(arr, mid + 1, high, target);
        }else{
            return binarySearchByRecursive(arr, low, mid - 1, target);
        }
    }

}
