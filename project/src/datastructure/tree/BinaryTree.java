package datastructure.tree;

/**
 * BinaryTree 二叉树
 *
 * @author caizhichong
 * @version V1.0.0
 * @date 2020年05月27日 9:38
 */
public class BinaryTree {

    public static void main(String[] args) {
        Node rootNode = new Node("-",null);
        Node left1 = new Node("+",rootNode);
        rootNode.setLeftChild(left1);
        Node right1 = new Node("/",rootNode);
        rootNode.setRightChild(right1);
        Node rightL = new Node("e",right1);
        Node rightR = new Node("f",right1);
        right1.setLeftChild(rightL);
        right1.setRightChild(rightR);
        Node leftL = new Node("a",left1);
        left1.setLeftChild(leftL);
        Node leftR = new Node("*", left1);
        left1.setRightChild(leftR);
        Node leftRL = new Node("b",leftR);
        Node leftRR = new Node("-",leftR);
        leftR.setLeftChild(leftRL);
        leftR.setRightChild(leftRR);
        Node leftRRL = new Node("c", leftRR);
        Node leftRRR = new Node("d", leftRR);
        leftRR.setLeftChild(leftRRL);
        leftRR.setRightChild(leftRRR);
        System.out.print("前序遍历：");
        Node.preTraverse(rootNode);
        System.out.println();
        System.out.print("中序遍历：");
        Node.midTraverse(rootNode);
        System.out.println();
        System.out.print("后序遍历：");
        Node.postTraverse(rootNode);
    }



}

class Node{

    private String data;

    private Node parent;

    private Node leftChild;

    private Node rightChild;

    public String getData() {
        return data;
    }

    public Node(String data, Node parent) {
        this.data = data;
        this.parent = parent;
    }

    public static void preTraverse(Node currentNode){
        if(currentNode == null){
            return;
        }
        System.out.print(currentNode.getData() + " ");
        preTraverse(currentNode.leftChild);
        preTraverse(currentNode.rightChild);

    }

    public static void midTraverse(Node currentNode){
        if(currentNode == null){
            return;
        }
        midTraverse(currentNode.leftChild);
        System.out.print(currentNode.getData() + " ");
        midTraverse(currentNode.rightChild);
    }

    public static void postTraverse(Node currentNode){
        if(currentNode == null){
            return;
        }
        postTraverse(currentNode.leftChild);
        postTraverse(currentNode.rightChild);
        System.out.print(currentNode.getData() + " ");
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
