package algorithm.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TopoSort ��������
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��11��10�� 9:09
 */
public class TopoSort {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        int[] visited = new int[graph.getNodes().length];
        //�����ҵ��ڽӱ���û����ȵĽڵ���Ϊ������ȱ�������ʼ�ڵ������������
        List<Integer> reverseStartVertex = graph.getStartNodes();
        for(Integer vertexIndex : reverseStartVertex){
            //dfs topo sort
            dfsTopoSort(graph, vertexIndex, visited);
        }
        //kahn topo sort
        //kahnTopoSort(graph);
    }

    
    /**
     * TopoSort
     * @description kahn �������򣨿�����֤�Ƿ���ڻ���
     * @param graph ͼ�ṹ
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    private static void kahnTopoSort(Graph graph){
        Queue<Integer> vertexQuue = new LinkedList<>();
        //����������Ϊ0�Ķ���������
        for(int i = 0; i < graph.getNodeInDegree().length; i++){
            if(graph.getNodeInDegree()[i] == 0){
                //�˴����±�i��Ӧ����
                vertexQuue.add(i);
            }
        }
        Integer vertexIndex;
        while((vertexIndex = vertexQuue.poll()) != null){
            System.out.println("�ڵ�:" + graph.getNodes()[vertexIndex].getValue());
            Vertex nextVertex = graph.getNodes()[vertexIndex].getNext();
            while (nextVertex != null){
                int nextVertexIndex = graph.getVertexIndex(nextVertex.getValue());
                graph.getNodeInDegree()[nextVertexIndex]--;
                if(graph.getNodeInDegree()[nextVertexIndex] == 0){
                    vertexQuue.add(nextVertexIndex);
                }
                nextVertex = nextVertex.getNext();
            }
        }
    }


    /**
     * TopoSort
     * @description �������ڽӱ�����������������(���������޻�ͼ���޷���֤�Ƿ���ڻ�)
     * @param graph ͼ�ṹ
     * @param nodeIndex ��ǰ�ڵ��±�
     * @param visited ���ʵĽڵ�����
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    private static void dfsTopoSort(Graph graph, int nodeIndex, int[] visited){
        if(visited[nodeIndex] == 1){
            return;
        }
        visited[nodeIndex]++;
        Vertex currentVerext = graph.getReverseNodes()[nodeIndex].getNext();
        while(currentVerext != null){
            dfsTopoSort(graph, graph.getVertexIndex(currentVerext.getValue()), visited);
            currentVerext = currentVerext.getNext();
        }
        System.out.println("�ڵ㣺" + graph.getReverseNodes()[nodeIndex].getValue());
    }



    private static Graph buildGraph(){
        char[] vertex = new char[]{'a','d','g','f','c','e','b','h','i'};
        Graph graph = new Graph(vertex);
        graph.addEdge('a','g');
        graph.addEdge('a','d');
        graph.addEdge('c','d');
        graph.addEdge('g','f');
        graph.addEdge('e','d');
        graph.addEdge('f','b');
        graph.addEdge('f','h');
        graph.addEdge('e','f');
        graph.addEdge('e','h');
        graph.addEdge('e','i');
        //graph.addEdge('h','e');
        graph.addEdge('h','i');
        return graph;
    }


}


/**
 * TopoSort
 * @description ͼ
 * @author caizhichong
 * @date 2020/11/10
 * @version V1.0
 */
class Graph {

    /**
     * ���㼯�ϣ��ڽӱ�
     * */
    private Vertex[] nodes;

    /**
     * ���㼯�ϣ����ڽӱ�
     * */
    private Vertex[] reverseNodes;

    /**
     * �����±�λ��(����ֻ�ܴ��ڲ��ظ���26Ӣ����ĸ�ڵ�)
     * */
    private int[] nodeIndex = new int[26];

    /**
     * �������
     * */
    private int[] nodeInDegree;


    public Graph(char[] nodes){
        super();
        this.buildNodes(nodes);
    }

    private void buildNodes(char[] nodes){
        this.nodes = new Vertex[nodes.length];
        this.reverseNodes = new Vertex[nodes.length];
        this.nodeInDegree = new int[nodes.length];
        for(int i = 0; i < nodes.length; i++){
            this.nodes[i] = new Vertex(nodes[i], null);
            this.reverseNodes[i] = new Vertex(nodes[i], null);
            this.nodeIndex[this.hashIndex(nodes[i])] = i;
        }
    }

    /**
     * Graph
     * @description �����������ı�
     * @param from ��ʼ
     * @param to ����
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public void addEdge(char from, char to){
        Vertex fromVertex = this.getVertex(from);
        Vertex fromHead = fromVertex.getNext();
        Vertex newFromHead = new Vertex(to, null);
        newFromHead.setNext(fromHead);
        fromVertex.setNext(newFromHead);
        Vertex toVertex = this.getReverseVertex(to);
        Vertex toHead = toVertex.getNext();
        Vertex newToHead = new Vertex(from, null);
        newToHead.setNext(toHead);
        toVertex.setNext(newToHead);
        //���to�ڵ�����
        this.getNodeInDegree()[this.getVertexIndex(to)]++;
    }

    /**
     * Graph
     * @description ͨ���ڵ��ַ���ȡ�ڵ��ڽڵ�������±�
     * @param ch �ַ�
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public int getVertexIndex(char ch){
        return this.getNodeIndex()[this.hashIndex(ch)];
    }


    /**
     * Graph
     * @description ͨ���ڵ��ַ���ȡ���ڽӱ�ڵ�
     * @param ch �ַ�
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public Vertex getVertex(char ch){
        return this.nodes[this.getVertexIndex(ch)];
    }


    /**
     * Graph
     * @description ��ȡ�ڽӱ�����û����ȵĶ���
     * @return
     * @author caizhichong
     * @date 2020/11/12
     * @version V1.0
     */
    public List<Integer> getStartNodes(){
        List<Integer> nodeList = new LinkedList<>();
        for(int i = 0; i < this.getNodes().length; i++){
            if(this.getNodes()[i].getNext() == null){
                nodeList.add(i);
            }
        }
        return nodeList;
    }



    /**
     * Graph
     * @description ��ȡ����û����ȵĶ����±�
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public List<Integer> getHasNoIndegreeVertexIndex(){
        List<Integer> vertexIndexList = new LinkedList<>();
        for(int i = 0; i < this.getNodeInDegree().length; i++){
            if(this.getNodeInDegree()[i] == 0){
                vertexIndexList.add(i);
            }
        }
        return vertexIndexList;
    }


    /**
     * Graph
     * @description ͨ���ڵ��ַ���ȡ�����ڽӱ�ڵ�
     * @param ch �ַ�
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    public Vertex getReverseVertex(char ch){
        return this.reverseNodes[this.getVertexIndex(ch)];
    }


    /**
     * Graph
     * @description 26��Ӣ����ĸ�Ĺ�ϣ�±�
     * @param ch �ַ�
     * @return
     * @author caizhichong
     * @date 2020/11/10
     * @version V1.0
     */
    private int hashIndex(char ch){
        return ch - 'a';
    }


    public Vertex[] getNodes() {
        return nodes;
    }

    public void setNodes(Vertex[] nodes) {
        this.nodes = nodes;
    }

    public Vertex[] getReverseNodes() {
        return reverseNodes;
    }

    public void setReverseNodes(Vertex[] reverseNodes) {
        this.reverseNodes = reverseNodes;
    }

    public int[] getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int[] nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public int[] getNodeInDegree() {
        return nodeInDegree;
    }

    public void setNodeInDegree(int[] nodeInDegree) {
        this.nodeInDegree = nodeInDegree;
    }
}

/**
 * TopoSort
 * @description ͼ�Ķ���
 * @author caizhichong
 * @date 2020/11/10
 * @version V1.0
 */
class Vertex {

    /**
     * ����ֵ
     * */
    private char value;

    /**
     * �ڽӱ��̽ڵ�
     * */
    private Vertex next;

    public Vertex(char value, Vertex next) {
        this.value = value;
        this.next = next;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Vertex getNext() {
        return next;
    }

    public void setNext(Vertex next) {
        this.next = next;
    }
}

