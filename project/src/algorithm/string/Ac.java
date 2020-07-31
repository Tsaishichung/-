package algorithm.string;

import sun.misc.Queue;

/**
 * Ac AC自动机--敏感词过滤实现，基于26个英文字母简单实现。
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年07月14日 19:14
 */
public class Ac {

    public static void main(String[] args) throws InterruptedException {
        //把匹配到的目标单词替换成**
        String[] patternStrs = new String[]{"fuck","suck","sb","nmlgb"};
        String replaceStr = "*";
        String mainStr = "himotherfucksuckmydickwocaonmlgb";
        TrieNode trie = buildFailPoint(buildTrie(patternStrs));
        String result = match(trie, mainStr, replaceStr);
        System.out.println(result);
    }

    /**
     * Ac
     * @description 模式串匹配
     * @param root Trie树
     * @param target 主串
     * @param replaceStr 匹配替换字符
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
            //当长串不匹配时，寻找最长可匹配后缀子串的匹配。
            //譬如当 模式串为 abd,bc 中寻找abcc中可匹配子串时，ab的孩子节点d与c不匹配，这时
            //寻找模式串ab的失败指针，即最长可匹配后缀子串b，在判断b的孩子节点是否与主串c匹配，这时候bc匹配成功，输出匹配。
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
                //当前匹配之外，再去判断是否多重包含。譬如模式串abc,bc,c中与主串abccd匹配，
                // 前缀abc与模式串abc匹配，但也与bc和c匹配。
                tmp = tmp.failPoint;
            }
        }
        return target;
    }

    /**
     * Ac
     * @description 将匹配到的字符替换为其他字符
     * @param times 字符个数
     * @param replaceStr 替换的字符
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
     * @description 构建Trie树失败指针
     * @param root trie树
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
     * @description 构建Trie树
     * @param strs 模式串数组
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
                //最后一个字符为结束字符
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
     * @description 计算数组下标
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
     * @description 构建Trie树
     * @author caizhichong
     * @date 2020/7/14
     * @version V1.0
     */
    private static class TrieNode{

        /**值*/
        private char value;

        /**孩子*/
        private TrieNode[] children;

        /**父节点*/
        private TrieNode parent;

        /**失败指针*/
        private TrieNode failPoint;

        /**是否结束符*/
        private boolean isEndChar;

        /**结束符长度*/
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

