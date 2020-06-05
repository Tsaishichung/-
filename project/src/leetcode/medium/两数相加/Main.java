package leetcode.medium.�������;

/**
 * Main
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��06��05�� 10:38
 */
public class Main {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        /*l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(1);*/
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        addTwoNumbers(l1, l2);

    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //�ڱ��ڵ�
        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode l1Cur = l1;
        ListNode l2Cur = l2;
        int addFlag = 0;
        //�ȴ���ȳ�������
        while(l1Cur != null && l2Cur != null){
            int tempVal = l1Cur.val + l2Cur.val + addFlag;
            addFlag = 0;
            if(tempVal>=10){
                addFlag = 1;
            }
            cur.next = new ListNode(tempVal%10);
            l1Cur = l1Cur.next;
            l2Cur = l2Cur.next;
            cur = cur.next;
        }
        //�����Ȳ��ȵ�����´���
        while(l1Cur != null){
            int tempVal = l1Cur.val+ addFlag;
            addFlag = 0;
            if(tempVal>=10){
                addFlag = 1;
            }
            cur.next = new ListNode(tempVal%10);
            l1Cur = l1Cur.next;
            cur = cur.next;
        }
        while(l2Cur != null){
            int tempVal = l2Cur.val+ addFlag;
            addFlag = 0;
            if(tempVal>=10){
                addFlag = 1;
            }
            cur.next = new ListNode(tempVal%10);
            l2Cur = l2Cur.next;
            cur = cur.next;
        }
        //������λ��
        if(addFlag != 0){
            cur.next = new ListNode(addFlag);
        }
        return head.next;
    }
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
