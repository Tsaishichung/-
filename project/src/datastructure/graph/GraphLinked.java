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
        List<Integer> noInDegreeVertex = graph.getVertex();
        boolean[] visited = new boolean[graph.getNodes().length];
        for(Integer vertexIndex : noInDegreeVertex){
            //������ȱ���
            dfs(graph, vertexIndex, visited);
            //������ȱ���
            //bfs(graph, vertexIndex, visited);
        }


    }


    /**
     * GraphLinked
     * @description ������ȱ���
     * @param graphA ͼ
     * @param vertexIndex ��ǰ�����±�
     * @param visited ����������
     * @return
     * @author caizhichong
     * @date 2020/11/4
     * @version V1.0
     */
    private static void dfs(GraphA graphA, Integer vertexIndex, boolean[] visited){
        if(visited[vertexIndex]){
            return;
        }
        visited[vertexIndex] = true;
        System.out.println("�ڵ㣺" + graphA.getNodes()[vertexIndex].getValue());
        Vertex vertex = graphA.getNodes()[vertexIndex];
        Vertex currentNode = vertex.getNext();
        while(currentNode != null){
            dfs(graphA, graphA.getVertexIndexByNodeValue(currentNode.getValue()), visited);
            currentNode = currentNode.getNext();
        }
    }

    /**
     * GraphLinked
     * @description ������ȱ���
     * @param graphA ͼ
     * @param vertexIndex ��ǰ�����±�
     * @param visited ����������
     * @return
     * @author caizhichong
     * @date 2020/11/4
     * @version V1.0
     */
    private static void bfs(GraphA graphA, Integer vertexIndex, boolean[] visited){
        Queue<Vertex> queue = new LinkedList<>();
        Vertex rootVertex = graphA.getNodes()[vertexIndex];
        queue.add(rootVertex);
        Vertex currentVertex;
        while((currentVertex = queue.poll()) != null){
            if(visited[graphA.getVertexIndexByNodeValue(currentVertex.getValue())]){
                continue;
            }
            visited[graphA.getVertexIndexByNodeValue(currentVertex.getValue())] = true;
            System.out.println("�ڵ㣺" + currentVertex.getValue());
            Vertex currentNode = currentVertex.getNext();
            while(currentNode != null){
                queue.add(graphA.getVertexByNodeValue(currentNode.getValue()));
                currentNode = currentNode.getNext();
            }
        }
    }




    public static GraphA buildGraph(){
        /*char[] vertex = new char[]{'a','g','c','f','e','d','b'};
        GraphA graph = new GraphA(vertex);
        graph.addEdge('a', 'b');
        graph.addEdge('a', 'c');
        graph.addEdge('b', 'c');
        graph.addEdge('b', 'd');
        graph.addEdge('c', 'd');
        graph.addEdge('c', 'g');
        graph.addEdge('d', 'e');
        graph.addEdge('d', 'f');
        graph.addEdge('e', 'f');
        graph.addEdge('g', 'f');*/
        char[] vertex = new char[]{'a','g','c','f','e','d','b','i','h','j'};
        GraphA graph = new GraphA(vertex);
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
        graph.addEdge('b', 'f');
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
    private Vertex[] nodes;

    /**
     * ���ڽӱ�
     * */
    private Vertex[] reverseNodes;

    /**
     * �ڵ��±�λ��,ģ�����ֻ��Ӣ����ĸ26���ڵ�
     * */
    private int[] nodeIndex = new int[26];

    public GraphA(char[] nodes) {
        super();
        this.buildVertex(nodes);
    }
    private void buildVertex(char[] nodes){
        this.nodes = new Vertex[nodes.length];
        this.reverseNodes = new Vertex[nodes.length];
        for(int i = 0; i < nodes.length; i++){
            //��Ŷ�����±�
            this.nodeIndex[this.getVertexIndex(nodes[i])] = i;
            this.nodes[i] = new Vertex(nodes[i], null);
            this.reverseNodes[i] = new Vertex(nodes[i], null);
        }
    }

    public void addEdge(char from, char to){
        this.buildVertex(from, to);
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
    public Vertex getVertexByNodeValue(char ch){
        return this.getNodes()[this.getVertexIndexByNodeValue(ch)];
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
    public int getVertexIndexByNodeValue(char ch){
        return this.getNodeIndex()[this.getVertexIndex(ch)];
    }

    /**
     * GraphA
     * @description ��ȡû����ȵĽڵ�
     * @return
     * @author caizhichong
     * @date 2020/11/4
     * @version V1.0
     */
    public List<Integer> getVertex(){
        List<Integer> noInDegreeVertex = new LinkedList<>();
        for(int i = 0; i < this.reverseNodes.length; i++){
            if(this.reverseNodes[i].getNext() == null){
                noInDegreeVertex.add(i);
            }
        }
        return noInDegreeVertex;
    }


    private void buildVertex(char from, char to){
        // need to validate parameter
        Vertex fromVertex = this.nodes[this.nodeIndex[this.getVertexIndex(from)]];
        Vertex toVertex = this.reverseNodes[this.nodeIndex[this.getVertexIndex(to)]];
        //�����ڽӱ�
        Vertex fromHead = fromVertex.getNext();
        Vertex fromNewHead = new Vertex(toVertex.getValue(),null);
        fromNewHead.setNext(fromHead);
        fromVertex.setNext(fromNewHead);
        //�������ڽӱ�
        Vertex toHead = toVertex.getNext();
        Vertex toNewHead = new Vertex(fromVertex.getValue(), null);
        toNewHead.setNext(toHead);
        toVertex.setNext(toNewHead);
        this.nodes[this.nodeIndex[this.getVertexIndex(from)]] = fromVertex;
        this.reverseNodes[this.nodeIndex[this.getVertexIndex(to)]] = toVertex;
    }


    public int getVertexIndex(char vertex){
        return vertex - 'a';
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
}

class Vertex{

    /**
     * ֵ
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

