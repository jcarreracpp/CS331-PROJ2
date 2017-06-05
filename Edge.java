package cs331.primkruskal;

/**
 *
 * @author Jorge
 */
public class Edge {
    private int cost;
    private int vertex1 = 0;
    private int vertex2 = 0;
    
    public Edge(int vertex1, int vertex2, int cost){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.cost = cost;
    }
    
    public void print(){
        System.out.print("("+vertex1+","+vertex2+")");
    }
    
    public int from(){
        return vertex1;
    }
    
    public int to(){
        return vertex2;
    }
    
    public int getCost(){
        return cost;
    }
    
    public void setCost(int i){
        cost = i;
    }
    
}
