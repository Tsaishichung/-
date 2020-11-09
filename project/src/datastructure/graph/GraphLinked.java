package datastructure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * GraphLinked 图-邻接表
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年07月30日 8:53
 */
public class GraphLinked {

    public static void main(String[] args) {
        GraphA graph = buildGraph();
        List<Integer> noInDegreeVertix = graph.getVertix();
        boolean[] visited = new boolean[graph.getNodes().length];
        for(Integer vertixIndex : noInDegreeVertix){
            //深度优先遍历
            //dfs(graph, vertixIndex, visited);
            //广度优先遍历
            bfs(graph, vertixIndex, visited);
        }


    }


    /**
     * GraphLinked
     * @description 深度优先遍历
     * @param graphA 图
     * @param vertixIndex 当前顶点下标
     * @param visited 顶点遍历情况
     * @return
     * @author caizhichong
     * @date 2020/11/4
     * @version V1.0
     */
    private static void dfs(GraphA graphA, Integer vertixIndex, boolean[] visited){
        if(visited[vertixIndex]){
            return;
        }
        visited[vertixIndex] = true;
        System.out.println("节点：" + graphA.getNodes()[vertixIndex].getValue());
        Vertix vertix = graphA.getNodes()[vertixIndex];
        Node currentNode = vertix.getHead();
        while(currentNode != null){
            dfs(graphA, graphA.getVertixIndexByNodeValue(currentNode.getValue()), visited);
            currentNode = currentNode.getNext();
        }
    }

    /**
     * GraphLinked
     * @description 广度优先遍历
     * @param graphA 图
     * @param vertixIndex 当前顶点下标
     * @param visited 顶点遍历情况
     * @return
     * @author caizhichong
     * @date 2020/11/4
     * @version V1.0
     */
    private static void bfs(GraphA graphA, Integer vertixIndex, boolean[] visited){
        Queue<Vertix> queue = new LinkedList<>();
        Vertix rootVertix = graphA.getNodes()[vertixIndex];
        queue.add(rootVertix);
        Vertix currentVertix;
        while((currentVertix = queue.poll()) != null){
            if(visited[graphA.getVertixIndexByNodeValue(currentVertix.getValue())]){
                continue;
            }
            visited[graphA.getVertixIndexByNodeValue(currentVertix.getValue())] = true;
            System.out.println("节点：" + currentVertix.getValue());
            Node currentNode = currentVertix.getHead();
            while(currentNode != null){
                queue.add(graphA.getVertixByNodeValue(currentNode.getValue()));
                currentNode = currentNode.getNext();
            }
        }
    }




    public static GraphA buildGraph(){
        char[] vertix = new char[]{'a','g','c','f','e','d','b'};
        GraphA graph = new GraphA(vertix);
        graph.addEdge('a', 'b');
        graph.addEdge('a', 'c');
        graph.addEdge('b', 'c');
        graph.addEdge('b', 'd');
        graph.addEdge('c', 'd');
        graph.addEdge('c', 'g');
        graph.addEdge('d', 'e');
        graph.addEdge('d', 'f');
        graph.addEdge('e', 'f');
        graph.addEdge('g', 'f');
        /*char[] vertix = new char[]{'a','g','c','f','e','d','b','i','h','j'};
        GraphA graph = new GraphA(vertix);
        graph.addEdge('a', 'b');
        graph.addEdge('a', 'c');
        graph.addEdge('b', 'g');
        graph.addEdge('g', 'f');
        graph.addEdge('g', 'h');
        graph.addEdge('g', 'i');
        graph.addEdge('d', 'c');
        graph.addEdge('d', 'e');
        graph.addEdge('e', 'i');
        graph.addEdge('c', 'g');
        graph.addEdge('b', 'f');*/
        return graph;
    }

}


/**
 * GraphLinked
 * @description 图
 * @author caizhichong
 * @date 2020/11/3
 * @version V1.0
 */
class GraphA{

    /**
     * 邻接表
     * */
    private Vertix[] nodes;

    /**
     * 逆邻接表
     * */
    private Vertix[] reverseNodes;

    /**
     * 节点下标位置,模拟最多只有英文字母26个节点
     * */
    private int[] nodeIndex = new int[26];

    public GraphA(char[] nodes) {
        super();
        this.buildVertix(nodes);
    }
    private void buildVertix(char[] nodes){
        this.nodes = new Vertix[nodes.length];
        this.reverseNodes = new Vertix[nodes.length];
        for(int i = 0; i < nodes.length; i++){
            //存放顶点的下标
            this.nodeIndex[this.getVertixIndex(nodes[i])] = i;
            this.nodes[i] = new Vertix(nodes[i], null);
            this.reverseNodes[i] = new Vertix(nodes[i], null);
        }
    }

    public void addEdge(char from, char to){
        this.buildVertix(from, to);
    }

    /**
     * GraphA
     * @description 通过值获取到节点
     * @param ch 节点值
     * @return
     * @author caizhichong
     * @date 2020/11/9
     * @version V1.0
     */
    public Vertix getVertixByNodeValue(char ch){
        return this.getNodes()[this.getVertixIndexByNodeValue(ch)];
    }



    /**
     * GraphA
     * @description 通过值获取到节点下标
     * @param ch 节点值
     * @return
     * @author caizhichong
     * @date 2020/11/9
     * @version V1.0
     */
    public int getVertixIndexByNodeValue(char ch){
        return this.getNodeIndex()[this.getVertixIndex(ch)];
    }

    /**
     * GraphA
     * @description 获取没有入度的节点
     * @return
     * @author caizhichong
     * @date 2020/11/4
     * @version V1.0
     */
    public List<Integer> getVertix(){
        List<Integer> noInDegreeVertix = new LinkedList<>();
        for(int i = 0; i < this.reverseNodes.length; i++){
            if(this.reverseNodes[i].getHead() == null){
                noInDegreeVertix.add(i);
            }
        }
        return noInDegreeVertix;
    }


    private void buildVertix(char from, char to){
        // need to validate parameter
        Vertix fromVertix = this.nodes[this.nodeIndex[this.getVertixIndex(from)]];
        Vertix toVertix = this.reverseNodes[this.nodeIndex[this.getVertixIndex(to)]];
        //构建邻接表
        Node fromHead = fromVertix.getHead();
        Node fromNewHead = new Node(toVertix.getValue(),null);
        fromNewHead.setNext(fromHead);
        fromVertix.setHead(fromNewHead);
        //构建逆邻接表
        Node toHead = toVertix.getHead();
        Node toNewHead = new Node(fromVertix.getValue(), null);
        toNewHead.setNext(toHead);
        toVertix.setHead(toNewHead);
        this.nodes[this.nodeIndex[this.getVertixIndex(from)]] = fromVertix;
        this.reverseNodes[this.nodeIndex[this.getVertixIndex(to)]] = toVertix;
    }


    public int getVertixIndex(char vertix){
        return vertix - 'a';
    }

    public Vertix[] getNodes() {
        return nodes;
    }

    public void setNodes(Vertix[] nodes) {
        this.nodes = nodes;
    }

    public Vertix[] getReverseNodes() {
        return reverseNodes;
    }

    public void setReverseNodes(Vertix[] reverseNodes) {
        this.reverseNodes = reverseNodes;
    }

    public int[] getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int[] nodeIndex) {
        this.nodeIndex = nodeIndex;
    }
}

class Vertix{

    /**
     * 值
     * */
    private char value;

    /**
     * 头结点
     * */
    private Node head;

    public Vertix(char value, Node head) {
        this.value = value;
        this.head = head;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
}


/**
 * GraphLinked
 * @description 单链表
 * @author caizhichong
 * @date 2020/11/3
 * @version V1.0
 */
class Node{

    private char value;

    private Node next;

    public Node(char value, Node next) {
        this.value = value;
        this.next = next;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}