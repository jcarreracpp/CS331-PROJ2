/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs331.primkruskal;

/**
 *
 * @author Jorge
 */
public class Kruskals {
    private Edge[] input;
    private int size;
    private int[] A;
    private int[] height;
    
    //Initializes a Kruskals object. It loads the data in from the graph and prepares to
    //perform the algorithm.
    public Kruskals(Graph graph){
        input = graph.getEdgeList();
        size = graph.getSize();
        A = new int[size];
        initializeA();
        height = new int[size];
        initializeHeight();
    }
    
    //Performs Kruskals algorithm on the graph provided by the class constructor.
    public Edge[] run(){
        int empty = 0;
        int T = 0;
        int temp = 0;
        int vert1 = 0;
        int vert2 = 0;
        int ms = 0;

        Edge[] result = new Edge[size-1];
        
        while(T < (size-1) && empty < size){
            //Choose lowest cost edge.
            temp = lowestEdge();
            //Delete edge.
            deleteEdge(temp);
            empty++;
            //Merge.
            vert1 = find2(input[temp].from());
            vert2 = find2(input[temp].to());
            if(vert1 == vert2){}
            else{
                merge3(vert1, vert2);
                result[T] = input[temp];
                T++;
            }
        }
        
        return result;
    }
    
    //Re-instates the graph's original values. Kruskals is destructive to the provided edge list.
    public void reinit(Graph graph){
        input = graph.getEdgeList();
        size = graph.getSize();
        A = new int[size];
        initializeA();
        height = new int[size];
        initializeHeight();

    }
    
    //Returns the index of the lowest cost edge.
    private int lowestEdge(){
        int minCost = 1000;
        int minIndex = 0;
        
        for(int i = 0; i < input.length; i++){
            if(input[i].getCost() < minCost){
                minCost = input[i].getCost();
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    //Effectively 'deletes' an edge by making it's cost ridiculously high. The algorithm
    //processes it as if it were deleted.
    private void deleteEdge(int i){
        input[i].setCost(1000);
    }
    
    //Initializes the grouping array, A.
    private void initializeA(){
        for(int i = 0; i < size; i++){
            A[i] = i;
        }
    }
    
    //Initializes the array keeping track of tree heights.
    private void initializeHeight(){
        for(int i = 0; i < size; i++){
            height[i] = 0;
        }
    }
    
    //Find2 algorithm as described in the slides and prompt.
    private int find2(int x){
        int i = x;
        while(A[i] != i){
            i = A[i];
        }

        return i;
    }
    
    //Merge3 algorithm as described in the slides and prompt.
    private void merge3(int a, int b){
        if(height[a] == height[b]){
            A[b] = a;
            height[a] = height[a] + 1;
        } else {
            if(height[a] > height[b]){
                A[b] = a;
            } else {
                A[a] = b;
            }
        }
    }
}
