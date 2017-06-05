package cs331.primkruskal;

/**
 *
 * @author Jorge
 */
public class CS331PrimKruskal {
        static int[] N = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300,
        400, 500, 600, 700, 800, 900, 1000};
        static double[] e = new double[]{0.2, 0.4, 0.6, 0.8, 1};

    public static void main(String[] args) {

        test();

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
        
        public static void test(){
            testUnconnected();
            testConnected();
        }
        
        public static void testConnected(){
            System.out.println("Results for connected graph: ");
            for(int i = 0; i < N.length; i++){
                for(int j = 0; j < e.length; j++){
                    Graph graph = new Graph(N[i]);
                    graph.generateConnectedGraph(e[j]);
                    
                    long kr = testKruskal(graph);
                    long pr = testPrim(graph);
                    System.out.print("Graph size: "+N[i]+"    Ratio: "+e[j]+":");
                    System.out.println("\t\t Kruskal: "+kr+"ms      Prim: "+pr+"ms");
                    
                }
            }
        }
        
        public static void testUnconnected(){
            System.out.println("Results for unconnected graph: ");
            for(int i = 0; i < N.length; i++){
                for(int j = 0; j < e.length; j++){
                    Graph graph = new Graph(N[i]);
                    graph.generateUnconnectedGraph(e[j]);
                    
                    long kr = testKruskal(graph);
                    long pr = testPrim(graph);
                    System.out.print("Graph size: "+N[i]+"    Ratio: "+e[j]+":");
                    System.out.println("\t\t Kruskal: "+kr+"ms      Prim: "+pr+"ms");
                    
                }
            }
        }
        
        public static long testKruskal(Graph graph){
            long start;
            long stop;
            long accum = 0;
            Kruskals kru = new Kruskals(graph);
            
            for(int i = 0; i < 10; i++){
                start = System.nanoTime();
                kru.run();
                stop = System.nanoTime();
                kru.reinit(graph);
                accum += ((stop - start) / 1000000);
            }
            accum /= 10;
            return accum;
        }
        
        public static long testPrim(Graph graph){
            long start;
            long stop;
            long accum = 0;
            Prims prim = new Prims(graph);
            
            for(int i = 0; i < 10; i++){
                start = System.nanoTime();
                prim.run();
                stop = System.nanoTime();
                prim.reinit(graph);
                accum += ((stop - start) / 1000000);
            }
            accum /= 10;
            return accum;
        }
}
