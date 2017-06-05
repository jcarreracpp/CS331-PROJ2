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
    private int MinCost = 0;
    private int[] Near;
    private int[][] adjacency;
    private Edge[] edgeList;
    private int size;
    
    public Prims(Graph graph){
        adjacency = graph.getAdjacency();
        size = graph.getSize();
        Near = new int[size];
        nearInitialize();
        edgeList = new Edge[size];
    }
    
    public Edge[] run(){
        
        int j;
        
        for(int i = 0; i < (size-1); i++){
            j = min(i,adjacency, Near);

            System.out.println(i);
            edgeList[i] = new Edge(j, Near[j], adjacency[j][Near[j]]);
            
            MinCost = MinCost + adjacency[i][j];
            Near[j] = -1;
        
            for(int k = 0; k < size; k++){
                if(Near[k] != -1 && adjacency[k][Near[k]] > adjacency[k][j]){
                System.out.print("["+k+","+Near[k]+"] ");
                    Near[k] = j;
                }

            }
            System.out.println();
        }
        connectedCheck();
        return edgeList;
        
    }
    
    private int min(int j,int[][] adj, int[] near){
        int maxCost = 1000;
        int minIndex = 0;
        
        
        for(int i = 0; i < adj.length; i++){
            if(near[i] != -1 &&( (adj[i][near[i]] < maxCost && adj[i][near[i]] != -1) || (near[minIndex] == -1||adj[minIndex][near[minIndex]]==0))){
                minIndex = i;
                maxCost = adj[minIndex][near[minIndex]];
                System.out.println("("+i+","+near[i]+") " + maxCost + " \t" + minIndex + "\t" + near[i]);
            }
        }
        return minIndex;
    }
    
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
