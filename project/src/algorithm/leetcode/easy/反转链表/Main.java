package algorithm.leetcode.easy.��ת����;

/**
 * Main
 *
 * ��תһ��������
 *
 * ʾ��:
 *
 * ����: 1->2->3->4->5->NULL
 * ���: 5->4->3->2->1->NULL
 * ����:
 * ����Ե�����ݹ�ط�ת�������ܷ������ַ����������⣿
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/reverse-linked-list
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��18�� 15:44
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
     * @description ��ͷ��ʼ����ԭ����ÿ����һ��Ԫ���൱�����µ��������һ��ͷ�ڵ㣨ͷ�壩������������˷�ת
     *     ע���������ǰ�ڵ��ʱ����Ҫ�ȼ�¼��ǰ�ڵ��next���ԣ������ڴ�������ж�ʧ��̽ڵ㡣
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
