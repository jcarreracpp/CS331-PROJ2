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
    
    public Kruskals(Graph graph){
        input = graph.getEdgeList();
        size = graph.getSize();
    }
    
    public Edge[] run(){
        int T = 0;
        int temp = 0;
        int[] A = new int[size];
        Edge[] result = new Edge[size-1];
        
        while(T < (size-1)){
            //Choose lowest cost edge.
            temp = lowestEdge();
            //Delete edge.
            deleteEdge(temp);
            //Merge.
        }
        
        return result;
    }
    
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
    
    private void deleteEdge(int i){
        input[i].setCost(1000);
    }
}
