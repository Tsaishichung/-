package algorithm.leetcode.easy.反转链表;

/**
 * Main
 *
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年06月18日 15:44
 */
public class Main {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(3);
        node1.next = node2;
        ListNode node3 = new ListNode(4);
        node2.next = node3;
        ListNode node4 = new ListNode(5);
        node3.next = node4;
        ListNode newHead = reverseLinkList(head);
        while(newHead != null){
            System.out.print(" " + newHead.val);
            newHead = newHead.next;
        }
    }

    /**
     * Main
     * @description 从头开始遍历原链表，每遍历一个元素相当于往新的链表插入一个头节点（头插），这样就完成了反转
     *     注意事项：处理当前节点的时候需要先记录当前节点的next属性，以免在处理过程中丢失后继节点。
     * @param
     * @return
     * @author caizhichong
     * @date 2020/6/18
     * @version V1.0
     */
    private static ListNode reverseLinkList(ListNode head){
        ListNode newHead = null;
        ListNode currentNode = head;
        while(currentNode != null){
            ListNode tempNpde = currentNode.next;
            currentNode.next = newHead;
            newHead = currentNode;
            currentNode = tempNpde;
        }
        return newHead;
    }


}
class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}
