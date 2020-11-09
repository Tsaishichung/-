package datastructure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * GraphLinked ͼ-�ڽӱ�
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��07��30�� 8:53
 */
public class GraphLinked {

    public static void main(String[] args) {
        GraphA graph = buildGraph();
        List<Integer> noInDegreeVertix = graph.getVertix();
        boolean[] visited = new boolean[graph.getNodes().length];
        for(Integer vertixIndex : noInDegreeVertix){
            //������ȱ���
            //dfs(graph, vertixIndex, visited);
            //������ȱ���
            bfs(graph, vertixIndex, visited);
        }


    }


    /**
     * GraphLinked
     * @description ������ȱ���
     * @param graphA ͼ
     * @param vertixIndex ��ǰ�����±�
     * @param visited ����������
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
        System.out.println("�ڵ㣺" + graphA.getNodes()[vertixIndex].getValue());
        Vertix vertix = graphA.getNodes()[vertixIndex];
        Node currentNode = vertix.getHead();
        while(currentNode != null){
            dfs(graphA, graphA.getVertixIndexByNodeValue(currentNode.getValue()), visited);
            currentNode = currentNode.getNext();
        }
    }

    /**
     * GraphLinked
     * @description ������ȱ���
     * @param graphA ͼ
     * @param vertixIndex ��ǰ�����±�
     * @param visited ����������
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
            System.out.println("�ڵ㣺" + currentVertix.getValue());
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
 * @description ͼ
 * @author caizhichong
 * @date 2020/11/3
 * @version V1.0
 */
class GraphA{

    /**
     * �ڽӱ�
     * */
    private Vertix[] nodes;

    /**
     * ���ڽӱ�
     * */
    private Vertix[] reverseNodes;

    /**
     * �ڵ��±�λ��,ģ�����ֻ��Ӣ����ĸ26���ڵ�
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
            //��Ŷ�����±�
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
     * @description ͨ��ֵ��ȡ���ڵ�
     * @param ch �ڵ�ֵ
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
     * @description ͨ��ֵ��ȡ���ڵ��±�
     * @param ch �ڵ�ֵ
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
     * @description ��ȡû����ȵĽڵ�
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
        //�����ڽӱ�
        Node fromHead = fromVertix.getHead();
        Node fromNewHead = new Node(toVertix.getValue(),null);
        fromNewHead.setNext(fromHead);
        fromVertix.setHead(fromNewHead);
        //�������ڽӱ�
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
     * ֵ
     * */
    private char value;

    /**
     * ͷ���
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
 * @description ������
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