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
        List<Integer> noInDegreeVertex = graph.getVertex();
        boolean[] visited = new boolean[graph.getNodes().length];
        for(Integer vertexIndex : noInDegreeVertex){
            //深度优先遍历
            dfs(graph, vertexIndex, visited);
            //广度优先遍历
            //bfs(graph, vertexIndex, visited);
        }


    }


    /**
     * GraphLinked
     * @description 深度优先遍历
     * @param graphA 图
     * @param vertexIndex 当前顶点下标
     * @param visited 顶点遍历情况
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
        System.out.println("节点：" + graphA.getNodes()[vertexIndex].getValue());
        Vertex vertex = graphA.getNodes()[vertexIndex];
        Vertex currentNode = vertex.getNext();
        while(currentNode != null){
            dfs(graphA, graphA.getVertexIndexByNodeValue(currentNode.getValue()), visited);
            currentNode = currentNode.getNext();
        }
    }

    /**
     * GraphLinked
     * @description 广度优先遍历
     * @param graphA 图
     * @param vertexIndex 当前顶点下标
     * @param visited 顶点遍历情况
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
            System.out.println("节点：" + currentVertex.getValue());
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
 * @description 图
 * @author caizhichong
 * @date 2020/11/3
 * @version V1.0
 */
class GraphA{

    /**
     * 邻接表
     * */
    private Vertex[] nodes;

    /**
     * 逆邻接表
     * */
    private Vertex[] reverseNodes;

    /**
     * 节点下标位置,模拟最多只有英文字母26个节点
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
            //存放顶点的下标
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
     * @description 通过值获取到节点
     * @param ch 节点值
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
     * @description 通过值获取到节点下标
     * @param ch 节点值
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
     * @description 获取没有入度的节点
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
        //构建邻接表
        Vertex fromHead = fromVertex.getNext();
        Vertex fromNewHead = new Vertex(toVertex.getValue(),null);
        fromNewHead.setNext(fromHead);
        fromVertex.setNext(fromNewHead);
        //构建逆邻接表
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
     * 值
     * */
    private char value;

    /**
     * 邻接表后继节点
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

