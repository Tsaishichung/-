package leetcode.hard.寻找两个正序数组的中位数;

/**
 * Main
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月05日 11:33
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] dest = new int[nums1.length + nums2.length];
        int nums1IteIndex = 0;
        int nums2IteIndex = 0;
        int destIndex = 0;
        while(nums1IteIndex < nums1.length && nums2IteIndex < nums2.length){
            if(nums1[nums1IteIndex] < nums2[nums2IteIndex]){
                dest[destIndex++] = nums1[nums1IteIndex++];

            } else if(nums1[nums1IteIndex] > nums2[nums2IteIndex]){
                dest[destIndex++] = nums2[nums2IteIndex++];
            }
        }
        while(nums1IteIndex < nums1.length){
            dest[destIndex++] = nums1[nums1IteIndex++];
        }
        while(nums2IteIndex < nums2.length){
            dest[destIndex++] = nums2[nums2IteIndex++];
        }
        int doubleMid = dest.length%2;
        if(doubleMid==1){
            return dest[dest.length/2];
        }else{
            return ((double)dest[dest.length/2] + (double)dest[dest.length/2 - 1])/2;
        }
    }
}
