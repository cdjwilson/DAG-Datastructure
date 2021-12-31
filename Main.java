

public class Main {
    public static void main(String[] args) {
        DAG x = new DAG();
        x.add(null, 1);
        x.add(null, 2);
        x.add(null, 3);
        x.add(1, 4);
        x.add(1, 5);
        x.add(2, 6);
        x.remove(1);
        System.out.println(x.allNodes().size());
        //x.printGraphEdges();
        System.out.print(x.search(5));
    }
}
