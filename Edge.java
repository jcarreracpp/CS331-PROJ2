package cs331.primkruskal;

/**
 *
 * @author Jorge
 */
public class Edge {
    private int cost;
    private int vertex1 = 0;
    private int vertex2 = 0;
    
    //Class constructor creates an edge from vertex1 to vertex2 with weight of cost.
    public Edge(int vertex1, int vertex2, int cost){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.cost = cost;
    }
    
    //Prints out an edge's beginning and end points in an easy to read format.
    public void print(){
        System.out.print("("+vertex1+","+vertex2+")");
    }
    
    //Returns one of the edge's points.
    public int from(){
        return vertex1;
    }
    
    //Returns the other edge's point.
    public int to(){
        return vertex2;
    }
    
    //Returns an edge's cost.
    public int getCost(){
        return cost;
    }
    
    //Sets an edge's cost after it has been created. Usually used for edge lists.
    public void setCost(int i){
        cost = i;
    }
    
}
