package cs331.primkruskal;

import java.util.Arrays;

/**
 *
 * @author Jorge
 */
public class Graph {
    private int[][] adjacency;
    private int nodeCount;
    private Edge[] edgeList;
    
    //Initializes the graph object with a size value.
    public Graph(int n){
        nodeCount = n;
        adjacency = new int[n][n];
    }
    
    //Generates a graph that does not take into consideration the complete
    //connected-ness of all the vertices. The ratio is the total edge ratio,
    //and the method was designed to work with 0.2, 0.4, 0.6, 0.8, or 1. Other
    //values have not been tested and might cause errors.
    public void generateUnconnectedGraph(double ratio){
        int maxEdgeThreshold = (nodeCount*(nodeCount-1))/2;
        int graphEdgeCount = (int)(ratio * maxEdgeThreshold);
        
        for(int i = 0; i < nodeCount; i++){
            for(int j = i; j < nodeCount; j++){
                if(i==j){
                    adjacency[i][j] = 0;
                } else {
                    if(Math.random() < 0.2 && graphEdgeCount > 0){
                        graphEdgeCount--;
                        int edge = (int) (Math.random()*100f);
                        adjacency[i][j] = edge;
                        adjacency[j][i] = edge;
                    } else {
                        adjacency[i][j] = -1;
                        adjacency[j][i] = -1;
                    }
                }
            }
        }
        
        if(graphEdgeCount >= 0){
            processAgain(graphEdgeCount);
        }
        
        initiateEdgeList(ratio);
        
    }
    
    //This method will generate a "spine" network of edges that connect to every
    //vertex in some fashion. The spine is always created using the minimum
    //number of edges which is the same value that 0.2E will always generates.
    //Extra edges are added on after the initial spine to satisfy the edge count.
    //The ratio is the total edge ratio, and the method was designed to work
    //with 0.2, 0.4, 0.6, 0.8, or 1. Other values have not been tested and might
    //cause errors.
    public void generateConnectedGraph(double ratio) {
        int maxEdgeThreshold = (nodeCount * (nodeCount - 1)) / 2;
        int graphEdgeCount = (int) (ratio * maxEdgeThreshold);
        int[] availi = new int[nodeCount];
        int[] availj = new int[nodeCount];
        Arrays.fill(availi, 1);
        Arrays.fill(availj, 1);
        int i = 0;
        int j = 0;

        //This fills the array with 'infinity' items.
        for (int m = 0; m < nodeCount; m++) {
            for (int n = 0; n < nodeCount; n++) {
                adjacency[m][n] = -1;
            }
        }
        
        //This makes the main diagonal all 0, as nodes dont cost anything to
        //travel to themselves.
        for (int mn = 0; mn < nodeCount; mn++) {
            adjacency[mn][mn] = 0;
        }

        //This initializes the initial i and j coordinates for generating the
        //"spine" of the connected graph.
        while (i == j) {
            i = (int) Math.floor(Math.random() * nodeCount);
            j = (int) Math.floor(Math.random() * nodeCount);
        }
        availj[i] = 0;

        //This generates new edges based on what edges were created before,
        //ensuring that every vertex is connected in some fashion.
        for (int k = 0; k < (nodeCount - 1); k++) {
            System.out.print("(" + i + "," + j + ") ");
            graphEdgeCount--;
            int edge = (int) (Math.random() * 100f);
            if(edge == 0)
                edge++;
            adjacency[i][j] = edge;
            adjacency[j][i] = edge;
            availi[i] = 0;
            availj[j] = 0;
            i = j;

            if (k != (nodeCount - 2)) {
                while (i == j || availj[j] == 0) {
                    j = (int) Math.floor(Math.random() * nodeCount);
                }
            }
        }

        if (graphEdgeCount > 0) {
            processAgain(graphEdgeCount);
        }
        initiateEdgeList(ratio);
        connectedCheck();

    }
    
    //Called at the end of both the graph generation methods, this will keep
    //filling the graph so long as the edge requirement has not been met.
    private void processAgain(int remaining) {
        while (remaining > 0) {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = i; j < nodeCount; j++) {
                    if (i == j) {
                    } else {
                        if (Math.random() < 0.2 && remaining > 0) {
                            if (adjacency[i][j] < 0) {
                                remaining--;
                                int edge = (int) (Math.random() * 100f);
                                adjacency[i][j] = edge;
                                adjacency[j][i] = edge;
                            }
                        }
                    }
                }
            }
        }
    }
    
    //This initializes and fills the list of edges when called.
    private void initiateEdgeList(double ratio){
        int temp = (nodeCount*(nodeCount-1))/2;
        int size = (int)(ratio * temp);
        
        edgeList = new Edge[size];
        int eIndex = 0;
        
        for(int i = 0; i < nodeCount; i++){
            for(int j = i; j < nodeCount; j++){
                if(i == j){}else{
                    if(adjacency[i][j] > -1){
                        edgeList[eIndex] = new Edge((i), (j), adjacency[i][j]);
                        eIndex++;
                    }
                }
            }
        }
    }
    
    //Debugging, used to check if a graph has a severed vertex or not.
    private void connectedCheck(){
        int miss = 0;
        boolean connected = true;
        
        for(int i = 0; i < nodeCount; i++){
            miss = 0;
            for(int j = 0; j < nodeCount; j++){
                if(adjacency[i][j] >= 0){
                }else{
                    miss++;
                }
                if(miss == (nodeCount - 1)){
                    connected = false;
                }
            }
        }
        System.out.println(connected + "!");
    }
    
    public int[][] getAdjacency(){
        return adjacency;
    }
    
    public int getSize(){
        return nodeCount;
    }
    
    public Edge[] getEdgeList(){
        return edgeList;
    }
    
    //Automates printing an adjacency matrix.
    public void printAdjacency(){
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
    
    //Automates printing an edge list.
    public void printEdgeList(){
        for(int i = 0; i < edgeList.length; i++){
            edgeList[i].print();
            System.out.print(" ");
        }
    }
    
    
    //Automates printing both an adjacency matrix and edge list.
    public void fullPrint(){
        printAdjacency();
        System.out.println();
        printEdgeList();
        System.out.println();
    
    }
    
}
