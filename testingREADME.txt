
This is a readme for testing hw4.

First, copy Pair.java class to your source code..... IMPORTANT: if you already have Pair.java, replace it with the attached one

Then, copy the method to your DAG class:

  //The method returns an arrayList of edges as Pair<T,T>. 
  // Note that when the sentinel node is the parent, it returns Pair<null,T>
    
    public ArrayList<Pair<T,T>> getEdgesArray() {
    
        //// In here I assume that getChildren() will return ArrayList. If you implementation 
        ///     returns a linkedList, you need to change ArrayList in the next line to LinkedList;
        ////     and if your getChildren() returns other types of List, just change it to that
        ArrayList<Node<T>> graph_nodes = new ArrayList<>();
        graph_nodes.addAll(this.sentinel.getChildren());

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
        	if(n.getParent() == this.sentinel)
        		edgeArray.add(new Pair( n.getElem(), null));//adding parent edge
        	else edgeArray.add(new Pair( n.getElem(), n.getParent().getElem()));
        		for(Node<T> c: n.getChildren())
        			edgeArray.add(new Pair(n.getElem(), c.getElem()));
        }
        
		return edgeArray;
}
    
    
NOTE: the method returns an ArrayList. If your getChildren() returns LinkedList, change the line only:
        ArrayList<Node<T>> graph_nodes = new ArrayList<>();
        
        to
   
        LinkedList<Node<T>> graph_nodes = new LinkedList<>();
        
        No other changes should be required for it to work.

                      


Then, copy the testDag class to your source, or make a junit test classw tith the same name, and copy the code from it to your class. 

Note: the method has two commented print statement. You could uncomment for debugging your code to know which edge was missing 
       when the failure happened. 

///////////////////////////////////////////////////////////////////////////

HOW TO WRITE A TEST:

We have one array, addElem, we use for adding its element to the dag. Here is an example: 
Integer[][] addElem = { { null, 5 }, { 5, 6 }, { 5, 4 }, { 6, 7 } };
            This means we add edges: add(null,5), add(5,6), add(5,4), and (6,7)

The expected edge array is the following:
Integer[][] expectedResult = { { 5, null }, { 5, 6 }, { 6, 5 }, { 5, 4 }, { 4, 5 }, { 6, 7 }, { 7, 6 } };

You get expectedResult array from addElem as follows:

1- You need to replace any edge with a null parent by an edge with fliped order of parent and new_data: edge {null, 5} becomes {5, null} in expectedResult
2- Also, for every edge with no null parent, you need to provide extra edge with flipped order of parent and new_child: for edge {5, 6} we add extra edge {6, 5}
    HOWEVER, there is one excepton to number 2: if the edge {x, y} is added, but y has aparent other than y, then do not add the flipped order edge {y,x} see the next example.
   
   Example: We have the same array as above, but with one exception: we added an edge {4,6}, where parent of 6 is 7. This means graph will not have edge from 6 to 4.
           See below:

Integer[][] addElem = { { null, 5 }, { 5, 6 }, { 5, 4 }, { 6, 7 }, { 4, 6 } };

The expected array is the following:
Integer[][] expectedResult = { { 5, null }, { 5, 6 }, { 6, 5 }, { 5, 4 }, { 4, 5 }, { 6, 7 }, { 7, 6 }, { 4, 6 } };



