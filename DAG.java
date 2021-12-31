import java.util.*;

public class DAG<T> {
    public Node<T> head = new Node<>(null);

    DAG() {
        this.head = head;
    }

    public void add(T parent, T new_data) {
        if(parent == null) { // if parent is null it is the root
            if(!search(new_data)) { // if new_data doesn't exist
                if(head.next != null){
                    head.next.add(new Node<>(new_data, head));
                }
            } else { // new_data does exist
                return;
            }
        } else { // parent isn't null
            if(!search(new_data)) { // new_data doesn't exist
                Node<T> temp = find(parent);
                temp.next.add(new Node<>(new_data, temp)); // find the parent node and add new_data to it with parent as parent
            } else {
                find(parent).next.add(find(new_data)); // find parent and add it as a child to the parent node passed through.
            }
        }
    }

    public void remove(T data) {
        if(!search(data)) { // no data to remove
            return;
        } else {
            Node<T> temp = find(data); // node being removed
            if(temp.next.size() == 0) { // no child nodes to be removed so just remove the temp node
                temp.prev.next.remove(temp);
                return;
            }
            for(Node<T> x: allNodes()) { // for all nodes check if they have node being removed as parent
                if(x.prev == temp) {
                    remove(x.elem);
                    temp.next.remove(x);
                }
            }
            temp.prev.next.remove(temp);
        }
    }

    public boolean search(T data) {
        if (head.next.size() == 0) {
            return false;
        }
        ArrayList<Node<T>> temp = allNodes();
        for(Node<T> x: temp) {
            if(x.elem.equals(data)) {
                return true;
            }
        }
        return false;
    }


    protected Node<T> find(T data) {
        if (head.next.size() == 0) {
            return null;
        }
        ArrayList<Node<T>> temp = allNodes();
        for(Node<T> x: temp) {
            if(x.elem.equals(data)) {
                return x;
            }
        }
        return null;
    }

    public ArrayList<Node<T>> allNodes() {
        if(head.next.size() == 0) {
            return null;
        }
        ArrayList<Node<T>> temp = new ArrayList<>();
        head.allNodesHelper(head.next, temp);
        return temp;
    }

    public void printGraphEdges() {

        if(this.head.getChildren().size() == 0) {
            return;
        }

        //// In here I assume that getChildren() will return ArrayList. If you implementation
        ///     returns a linkedList, you need to change ArrayList in the next line to LinkedList;
        ////     and if your getChildren() returns other types of List, just change it to that
        ArrayList<Node<T>> graph_nodes = new ArrayList<>(); ///-----------------------------------////
        graph_nodes.addAll(this.head.getChildren());

        for (int i=0; i<graph_nodes.size(); i++) {
            if(graph_nodes.get(i).getChildren().size()!=0) {
                graph_nodes.addAll(graph_nodes.get(i).getChildren());
            }
        }

        //Add the nodes to a set to eliminate duplications
        Set<Node<T>> graph_set = new HashSet<Node<T>>();
        for (Node<T> someNode: graph_nodes){
            graph_set.add( someNode);
        }

        //We have graph nodes stored in a set; iterate over them, and print their edges.
        //Start printing edges: (x , y) means the edge direction is from x --> y.
        for(Node<T> someNode: graph_set) {
            //print edge to parent edge first
            if(someNode.getParent() == this.head) {
                System.out.println("PARENT of "+someNode.getElem().toString()+" is  root/sentinel");
            }
            else System.out.println("PARENT of "+ someNode.getElem().toString() +" is "+ someNode.getParent().getElem().toString() );
            //print children edges
            for(Node<T> c: someNode.getChildren()) {
                System.out.println("("+someNode.getElem().toString()+", "+c.getElem().toString()+")");
            }
        }
    }

    public ArrayList<Pair<T, T>> getEdgesArray() {
        //// In here I assume that getChildren() will return ArrayList. If you implementation
        ///     returns a linkedList, you need to change ArrayList in the next line to LinkedList;
        ////     and if your getChildren() returns other types of List, just change it to that
        ArrayList<Node<T>> graph_nodes = new ArrayList<>();
        graph_nodes.addAll(this.head.getChildren());

        for (int i=0; i<graph_nodes.size(); i++) {
            if(graph_nodes.get(i).getChildren().size()!=0)
                graph_nodes.addAll(graph_nodes.get(i).getChildren());
        }

        //Add the nodes to a set to eliminate duplications
        Set<Node<T>> graph_set = new HashSet<Node<T>>();
        for (Node<T> someNode: graph_nodes){
            graph_set.add( someNode);
        }

        //now, visit the nodes in the graph_set and add their edges to the array list edgeArray
        ArrayList<Pair<T,T>> edgeArray = new ArrayList<>();
        for(Node<T> n: graph_set) {
            if(n.getParent() == this.head)
                edgeArray.add(new Pair( n.getElem(), null));//adding parent edge
            else edgeArray.add(new Pair( n.getElem(), n.getParent().getElem()));
            for(Node<T> c: n.getChildren())
                edgeArray.add(new Pair(n.getElem(), c.getElem()));
        }

        return edgeArray;
    }
}
