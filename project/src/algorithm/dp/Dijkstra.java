package algorithm.dp;


import java.util.*;

/**
 * Dijkstra ��Դ���·���㷨
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020��11��12�� 15:08
 */
public class Dijkstra {

    public static void main(String[] args) {
        Graph graph = buildGraph();
        dijkstra(graph, 'a', 'h');
    }


    /**
     * Dijkstra dijkstra ���Ի���ͼ��������Ⱥ͹�����ȱ����������нڵ�ʵ�֣��������ɱ���ܸߣ�������ʹ�����ȼ�����
     * ʹ��ÿ�δ���Ľڵ㶼��Ȩֵ��С�Ľڵ㣬�Դ�����ֱ���ҵ�Ŀ��ڵ㣬���Ŀ��ڵ����С·����
     * ���ж���·��Ĭ��ֵΪInteger���ֵ��
     * @param graph ������ͼ
     * @param from ��ʼ�ڵ�����
     * @param to ��ֹ�ڵ�����
     * @return
     * @date 2020/12/7
     * @version V1.0
     */
    private static void dijkstra(Graph graph, char from, char to){
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        Vertex fromVertext = graph.getVertext(from);
        fromVertext.setDist(0);
        priorityQueue.add(fromVertext);
        Vertex currentVertext;
        while((currentVertext = priorityQueue.poll()) != null){
            if(currentVertext.getValue() == to){
                System.out.println("���·��Ϊ��" + currentVertext.getDist());
                printPath(currentVertext);
                return;
            }
            for(int i = 0; i < currentVertext.getEdges().size(); i++){
                //��õ�ǰ���㵽����һ���������Ȩֵ��·��ֵ
                //��ֹIntegerֵ�����
                int fromValue = currentVertext.getDist();
                if(fromValue != Integer.MAX_VALUE){
                    fromValue = (Integer.MAX_VALUE - currentVertext.getDist() - currentVertext.getEdges().get(i).getWeight()) > 0 ? currentVertext.getDist() + currentVertext.getEdges().get(i).getWeight() : Integer.MAX_VALUE;
                }
                //��ȡ��ǰ������Ѿ����µ�·��ֵ��Ĭ����Integer.MAX
                Vertex toVertex = graph.getVertices()[currentVertext.getEdges().get(i).getToIndex()];
                //��ǰ�ڵ����ӵ�Ŀ��ڵ��ȨֵС�ڵ�ǰ�ڵ��Ȩֵ�����������������壬����ǰ·���Ѿ�������С�ˡ�û�в����ı�Ҫ
                if(fromValue < toVertex.getDist()){
                    toVertex.setDist(fromValue);
                    toVertex.setPrev(currentVertext);
                    //�жϽڵ��Ƿ��Ѿ����¹��ˣ�Ϊ�˷��㴦�����ȼ������Ǹ��»��ǲ��룬ͬһ����ͬһ�ڵ㲻�ܳ������Ρ�
                    if(!toVertex.isVisited()){
                        //�µĽڵ㣬ֱ�Ӽ������ȼ����У���·��ֵΪInteger.MAX�������
                        toVertex.setVisited(true);
                        priorityQueue.add(toVertex);
                    }
                }
            }
            //������Ϊ�и��¶��е�ֵ�����в�����ѻ����������Լ�дһ��С���ѣ�����ֵҲ�ܴ����ѻ���
            //���������������ȼ����еĹ��캯��ǿ�жѻ�(ֻ��ͨ��Vertexʵ��Comparable�������򣬲���ͨ�����캯���Զ���Compartor)
            priorityQueue = new PriorityQueue<>(Arrays.asList(priorityQueue.toArray(new Vertex[0])));
        }
    }

    private static void printPath(Vertex vertex){
        if(vertex == null){
            return;
        }
        System.out.print("<-" + vertex.getValue());
        printPath(vertex.getPrev());
    }



    private static Graph buildGraph(){
        char[] vertex = new char[]{'a','g','c','f','e','d','b','i','h','j'};
        Graph graph = new Graph(vertex);
        /*graph.addEdge('a', 'b', 8);
        graph.addEdge('a', 'c', 6);
        graph.addEdge('g', 'f', 7);
        graph.addEdge('g', 'h', 11);
        graph.addEdge('g', 'i', 1);
        graph.addEdge('d', 'c', 24);
        graph.addEdge('d', 'e', 19);
        graph.addEdge('e', 'i', 11);
        graph.addEdge('c', 'g', 18);
        graph.addEdge('b', 'f', 14);
        graph.addEdge('b', 'g', 12);*/
        graph.addEdge('a','g', 8);
        graph.addEdge('f','a', 5);
        graph.addEdge('a','c', 6);
        graph.addEdge('c','f', 17);
        graph.addEdge('c','b', 18);
        graph.addEdge('f','b', 34);
        graph.addEdge('g','c', 9);
        graph.addEdge('g','e', 24);
        graph.addEdge('c','d', 21);
        graph.addEdge('c','e', 7);
        graph.addEdge('b','j', 19);
        graph.addEdge('b','h', 11);
        graph.addEdge('b','d', 25);
        graph.addEdge('d','j', 17);
        graph.addEdge('d','h', 22);
        graph.addEdge('d','i', 8);
        graph.addEdge('e','d', 9);
        graph.addEdge('e','i', 11);
        graph.addEdge('i','h', 1);
        return graph;
    }
}

/**
 * Dijkstra
 * @description ͼ
 * @author caizhichong
 * @date 2020/11/17
 * @version V1.0
 */
class Graph{

    /**
     * ���㼯��
     * */
    private Vertex[] vertices;

    /**
     * �����±꣨���ݶ���ֵ��ϣ��λ�±꣩
     * */
    private int[] verticesIndexs;


    public Graph(char[] vertices){
        super();
        this.init(vertices);
    }

    private void init(char[] vertices){
        this.vertices = new Vertex[vertices.length];
        this.verticesIndexs = new int[vertices.length];
        for(int i = 0; i < vertices.length; i++){
            this.vertices[i] = new Vertex(vertices[i]);
            this.verticesIndexs[this.hashIndex(vertices[i])] = i;
        }
    }

    private int hashIndex(char vertex){
        return vertex - 'a';
    }

    /**
     * Graph
     * @description  ��ӱ�
     * @date 2020/11/23
     * @version V1.0
     */
    public void addEdge(char from, char to, int weight){
        int vertexFromIndex = this.getVertexIndex(from);
        int vertexToIndex = this.getVertexIndex(to);
        Vertex vertexFrom = this.vertices[vertexFromIndex];
        Vertex vertexTo = this.vertices[vertexToIndex];
        Edge fromTo = new Edge(vertexFromIndex, vertexToIndex, weight);
        Edge toFrom = new Edge(vertexToIndex, vertexFromIndex, weight);
        vertexFrom.addEdge(fromTo);
        vertexTo.addReverseEdge(toFrom);
    }


    /**
     * Graph
     * @description ͨ������ֵ��ȡ�����ڶ���������±�
     * @param vertex ����ֵ
     * @return
     * @author caizhichong
     * @date 2020/11/17
     * @version V1.0
     */
    public int getVertexIndex(char vertex){
        return this.verticesIndexs[this.hashIndex(vertex)];
    }

    /**
     * Graph
     * @description ͨ������ֵ��ȡ�������
     * @param vertex ����
     * @return
     * @author caizhichong
     * @date 2020/11/17
     * @version V1.0
     */
    public Vertex getVertext(char vertex){
        return this.vertices[this.getVertexIndex(vertex)];
    }


    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public int[] getVerticesIndexs() {
        return verticesIndexs;
    }

    public void setVerticesIndexs(int[] verticesIndexs) {
        this.verticesIndexs = verticesIndexs;
    }
}

/**
 * Dijkstra ����
 * @author caizhichong
 * @date 2020/11/17
 * @version V1.0
 */
class Vertex implements Comparable<Vertex>{

    /**
     * ����ֵ
     * */
    private char value;

    /**
     * ���ӵı�
     * */
    private List<Edge> edges;

    /**
     * ����ǰ����ľ���
     * */
    private int dist;

    /**
     * �Ƿ���ʹ���
     * */
    private boolean isVisited;

    /**
     * ���·��ǰ���ڵ�
     * */
    private Vertex prev;

    /**
     * �����ӵı�
     * */
    private List<Edge> reverseEdges;

    public Vertex(char value) {
        this.value = value;
        this.dist = Integer.MAX_VALUE;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public void addReverseEdge(Edge edge){
        if(this.reverseEdges == null){
            this.reverseEdges = new ArrayList<>();
        }
        this.reverseEdges.add(edge);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Edge> getReverseEdges() {
        return reverseEdges;
    }

    public void setReverseEdges(List<Edge> reverseEdges) {
        this.reverseEdges = reverseEdges;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    @Override public int compareTo(Vertex o) {
        return this.getDist().compareTo(o.getDist());
    }
}

/**
 * Dijkstra ��
 * @author caizhichong
 * @date 2020/11/17
 * @version V1.0
 */
class Edge{


    /**
     * �����ӽڵ��±�
     * */
    private int fromIndex;

    /**
     * ���ӽڵ��±�
     * */
    private int toIndex;

    /**
     * ·��Ȩ��
     * */
    private int weight;

    public Edge(int fromIndex, int toIndex, int weight) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.weight = weight;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

