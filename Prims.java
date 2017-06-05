/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs331.primkruskal;

import java.util.Arrays;

/**
 *
 * @author Jorge
 */
public class Prims {
    private int MinCost;
    private int[] Near;
    private int[][] adjacency;
    private Edge[] edgeList;
    private int size;
    
    //Initializes a Prims object. Takes in data from the provided graph and prepares to
    //perform prims algorithm on the given graph.
    public Prims(Graph graph){
        adjacency = graph.getAdjacency();
        size = graph.getSize();
        Near = new int[size];
        nearInitialize();
        edgeList = new Edge[size];
    }
    
    //Runs Prims algorithm on the provided graph.
    public Edge[] run() {
        MinCost = 0;

        int j;

        for (int i = 0; i < (size - 1); i++) {
            j = min(i, adjacency, Near);

            if (adjacency[j][Near[j]] != -1) {
                edgeList[i] = new Edge(j, Near[j], adjacency[j][Near[j]]);

                MinCost = MinCost + adjacency[j][Near[j]];
                Near[j] = -1;

                for (int k = 0; k < size; k++) {
                    if (Near[k] != -1 && adjacency[k][Near[k]] > adjacency[k][j]) {
                        Near[k] = j;
                    }

                }

            }else{
                for(int k = 1; k < size; k++){
                    if(Near[k] != -1)
                        Near[k] = j;
                }
            }
        }
        return edgeList;

    }
    
    //Re-instates the graph's original values.
    public void reinit(Graph graph){
        adjacency = graph.getAdjacency();
        size = graph.getSize();
        Near = new int[size];
        nearInitialize();
        edgeList = new Edge[size];
    }
    
    //Finds the index of the minimum cost edge.
    private int min(int j,int[][] adj, int[] near){
        int maxCost = 1000;
        int minIndex = 0;
        
        
        for(int i = 0; i < adj.length; i++){
            if(near[i] != -1 &&( (adj[i][near[i]] < maxCost && adj[i][near[i]] != -1) || (near[minIndex] == -1||adj[minIndex][near[minIndex]]==0))){
                minIndex = i;
                maxCost = adj[minIndex][near[minIndex]];
            }
        }
        return minIndex;
    }
    
    //Initializes the array, Near.
    private void nearInitialize(){
        Arrays.fill(Near, 0);
        Near[0] = -1;
    }
 
    
    //Debugging, used to check if a graph has a severed vertex or not.
    private void connectedCheck(){
        int miss = 0;
        boolean connected = true;
        
        for(int i = 0; i < size; i++){
            miss = 0;
            for(int j = 0; j < size; j++){
                if(adjacency[i][j] >= 0){
                }else{
                    miss++;
                }
                if(miss == (size - 1)){
                    connected = false;
                }
            }
        }
        System.out.println(connected + "!");
    }    
}
