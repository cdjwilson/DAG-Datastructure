import java.util.*;

public class Node<T> {
    public T elem;
    public ArrayList<Node<T>> next;
    public Node<T> prev;

    public Node(T elem) {
        this.elem = elem;
        this.next = new ArrayList<>();
        this.prev = null;
    }

    public Node(T elem, Node<T> parent) {
        this.elem = elem;
        this.prev = parent;
        this.next = new ArrayList<>();
    }

    public ArrayList<Node<T>> getChildren() {
        return this.next;
    }

    public Node<T> getParent() {
        return this.prev;
    }

    public T getElem() {
        return this.elem;
    }

    public void allNodesHelper(ArrayList<Node<T>> data, ArrayList<Node<T>> allNodes) {
        for(Node<T> x: data) {
            if(x.next.size() == 0) {
                allNodes.add(x);
            } else {
                allNodes.add(x);
                allNodesHelper(x.next, allNodes);
            }
        }
    }
}
