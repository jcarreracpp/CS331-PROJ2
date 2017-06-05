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
public class CS331PrimKruskal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph doink = new Graph(10);
        doink.printAdjacency();
        System.out.println();
        doink.generateConnectedGraph(0.2);
        doink.printAdjacency();
        doink.generateConnectedGraph(0.6);
        doink.printAdjacency();
        Prims prim = new Prims(doink);
        Edge[] e = prim.run();
        printEdge(e);
        //printAdjacency(edgeToMatrix(e));

    }
    
    public static int[][] edgeToMatrix(Edge[] e){
        int size = 0;
        
        for(int i = 0; i < e.length-1; i++){
            if(e[i].from() > size)
                size = e[i].from();
            if(e[i].to() > size)
                size = e[i].to();
        }
        int[][] adjacency = new int[10][10];
        
        for(int j = 0; j < e.length-1; j++){
            adjacency[e[j].to()][e[j].from()] = e[j].getCost();
            adjacency[e[j].from()][e[j].to()] = e[j].getCost();
        }
        return adjacency;
    }
    
        public static void printAdjacency(int[][] adjacency){
            int nodeCount = adjacency.length;
        for(int i = 0; i < nodeCount; i++){
            for(int j = 0; j < nodeCount; j++){
                if(adjacency[i][j] == -1){
                    System.out.print("[∞]\t");
                } else {
                    System.out.print("[" + adjacency[i][j] + "]\t");
                }
            }
            System.out.println("");
        }
    }
        public static void printEdge(Edge[] edgetho){
            for(int i = 0; i < edgetho.length-1; i++){
                System.out.print("(" + edgetho[i].from() + "," + edgetho[i].to() + ") ");
            }
            System.out.println();
        }
}
