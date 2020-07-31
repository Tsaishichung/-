package algorithm.string;

import sun.misc.Queue;

/**
 * Ac AC�Զ���--���дʹ���ʵ�֣�����26��Ӣ����ĸ��ʵ�֡�
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��07��14�� 19:14
 */
public class Ac {

    public static void main(String[] args) throws InterruptedException {
        //��ƥ�䵽��Ŀ�굥���滻��**
        String[] patternStrs = new String[]{"fuck","suck","sb","nmlgb"};
        String replaceStr = "*";
        String mainStr = "himotherfucksuckmydickwocaonmlgb";
        TrieNode trie = buildFailPoint(buildTrie(patternStrs));
        String result = match(trie, mainStr, replaceStr);
        System.out.println(result);
    }

    /**
     * Ac
     * @description ģʽ��ƥ��
     * @param root Trie��
     * @param target ����
     * @param replaceStr ƥ���滻�ַ�
     * @return
     * @author caizhichong
     * @date 2020/7/27
     * @version V1.0
     */
    private static String match(TrieNode root, String target, String replaceStr){
        TrieNode p = root;
        char[] targetChars = target.toCharArray();
        for(int i = 0; i < targetChars.length; i++){
            int hashIndex = hash(targetChars[i]);
            //��������ƥ��ʱ��Ѱ�����ƥ���׺�Ӵ���ƥ�䡣
            //Ʃ�統 ģʽ��Ϊ abd,bc ��Ѱ��abcc�п�ƥ���Ӵ�ʱ��ab�ĺ��ӽڵ�d��c��ƥ�䣬��ʱ
            //Ѱ��ģʽ��ab��ʧ��ָ�룬�����ƥ���׺�Ӵ�b�����ж�b�ĺ��ӽڵ��Ƿ�������cƥ�䣬��ʱ��bcƥ��ɹ������ƥ�䡣
            while(p.children[hashIndex] == null && p != root){
                p = p.failPoint;
            }
            p = p.children[hashIndex];
            if(p == null){
                p = root;
            }
            TrieNode tmp = p;
            while(tmp != null){
                if(tmp.isEndChar){
                    int pos = i - tmp.length + 1;
                    if(pos <= 0){
                        target = replace(tmp.length, replaceStr) + target.substring(pos + tmp.length, targetChars.length);
                    }else{
                        target = target.substring(0, pos) + replace(tmp.length, replaceStr) + target.substring(pos + tmp.length, targetChars.length);
                    }
                }
                //��ǰƥ��֮�⣬��ȥ�ж��Ƿ���ذ�����Ʃ��ģʽ��abc,bc,c��������abccdƥ�䣬
                // ǰ׺abc��ģʽ��abcƥ�䣬��Ҳ��bc��cƥ�䡣
                tmp = tmp.failPoint;
            }
        }
        return target;
    }

    /**
     * Ac
     * @description ��ƥ�䵽���ַ��滻Ϊ�����ַ�
     * @param times �ַ�����
     * @param replaceStr �滻���ַ�
     * @return
     * @author caizhichong
     * @date 2020/7/31
     * @version V1.0
     */
    private static String replace(int times, String replaceStr){
        StringBuilder sb = new StringBuilder();
        while(times != 0){
            sb.append(replaceStr);
            times--;
        }
        return sb.toString();
    }

    /**
     * Ac
     * @description ����Trie��ʧ��ָ��
     * @param root trie��
     * @return
     * @author caizhichong
     * @date 2020/7/27
     * @version V1.0
     */
    private static TrieNode buildFailPoint(TrieNode root) throws InterruptedException {
        Queue<TrieNode> queue = new Queue<>();
        root.failPoint = null;
        queue.enqueue(root);
        TrieNode currentNode;
        while(!queue.isEmpty()){
            currentNode = queue.dequeue();
            TrieNode[] children = currentNode.children;
            for(int i = 0; i < children.length; i++){
                TrieNode pc = children[i];
                if(pc == null){
                    continue;
                }
                if(pc.parent == root){
                    pc.failPoint = root;
                }else{
                    TrieNode pcFail = currentNode.failPoint;
                    while(pcFail != null){
                        TrieNode pcFailChild = pcFail.children[hash(pc.value)];
                        if(pcFailChild != null){
                            pc.failPoint = pcFailChild;
                            break;
                        }
                        pcFail = pcFail.failPoint;
                    }
                    if(pcFail == null){
                        pc.failPoint = root;
                    }
                }
                queue.enqueue(pc);
            }

        }
        return root;
    }


    /**
     * Ac
     * @description ����Trie��
     * @param strs ģʽ������
     * @return
     * @author caizhichong
     * @date 2020/7/27
     * @version V1.0
     */
    private static TrieNode buildTrie(String[] strs){
        TrieNode root = new TrieNode('/', new TrieNode[26], null, null, false, 0);
        for(int i = 0; i < strs.length; i++){
            char[] chars =  strs[i].toCharArray();
            TrieNode currentNode = root;
            for(int j = 0; j < chars.length; j++){
                TrieNode childNode;
                TrieNode[] children = currentNode.children;
                int hashIndex = hash(chars[j]);
                if((childNode = children[hashIndex]) == null){
                    childNode = new TrieNode(chars[j], new TrieNode[26], currentNode, null, false, j + 1);
                }
                //���һ���ַ�Ϊ�����ַ�
                if(j == chars.length - 1){
                    childNode.isEndChar = true;
                }
                children[hashIndex] = childNode;
                currentNode = childNode;
            }
        }
        return root;
    }

    /**
     * Ac
     * @description ���������±�
     * @param
     * @return
     * @author caizhichong
     * @date 2020/7/27
     * @version V1.0
     */
    private static int hash(char ch){
        return ch - 'a';
    }


    /**
     * Ac
     * @description ����Trie��
     * @author caizhichong
     * @date 2020/7/14
     * @version V1.0
     */
    private static class TrieNode{

        /**ֵ*/
        private char value;

        /**����*/
        private TrieNode[] children;

        /**���ڵ�*/
        private TrieNode parent;

        /**ʧ��ָ��*/
        private TrieNode failPoint;

        /**�Ƿ������*/
        private boolean isEndChar;

        /**����������*/
        private int length;

        public TrieNode(char value, TrieNode[] children, TrieNode parent, TrieNode failPoint, boolean isEndChar, int length) {
            this.value = value;
            this.children = children;
            this.parent = parent;
            this.failPoint = failPoint;
            this.isEndChar = isEndChar;
            this.length = length;
        }

    }
}

