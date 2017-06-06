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
        
//        Graph byHandGraph = new Graph(10);
//        byHandGraph.generateConnectedGraph(0.2);
//        printAdjacency(byHandGraph.getAdjacency());
//        System.out.println();
//        byHandGraph.generateConnectedGraph(0.4);
//        printAdjacency(byHandGraph.getAdjacency());

    }

    //Prints out a cost adjacency matrix in an easy to read format.
    public static void printAdjacency(int[][] adjacency) {
        int nodeCount = adjacency.length;
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (adjacency[i][j] == -1) {
                    System.out.print("[∞]\t");
                } else {
                    System.out.print("[" + adjacency[i][j] + "]\t");
                }
            }
            System.out.println("");
        }
    }

    //Runs a full test of both connected and unconnected graphs.
    public static void test() {
        testUnconnected();
        testConnected();
    }

    //Tests both Kruskals and Prims algorithms with every size/ratio variant of connected graphs.
    public static void testConnected() {
        System.out.println("Results for connected graph: ");
        for (int i = 0; i < N.length; i++) {
            for (int j = 0; j < e.length; j++) {
                Graph graph = new Graph(N[i]);
                graph.generateConnectedGraph(e[j]);

                long kr = testKruskal(graph);
                long pr = testPrim(graph);
                System.out.print("Graph size: " + N[i] + "    Ratio: " + e[j] + ":");
                System.out.println("\t\t Kruskal: " + kr + "ms      Prim: " + pr + "ms");

            }
        }
    }

    //Tests both Kruskals and Prims algorithm with every size/ratio variant of unconnected graphs.
    public static void testUnconnected() {
        System.out.println("Results for unconnected graph: ");
        for (int i = 0; i < N.length; i++) {
            for (int j = 0; j < e.length; j++) {
                Graph graph = new Graph(N[i]);
                graph.generateUnconnectedGraph(e[j]);

                long kr = testKruskal(graph);
                long pr = testPrim(graph);
                System.out.print("Graph size: " + N[i] + "    Ratio: " + e[j] + ":");
                System.out.println("\t\t Kruskal: " + kr + "ms      Prim: " + pr + "ms");

            }
        }
    }

    //Runs Kruskals algorithm 10 times with the same graph provided, and returns the average run-time.
    public static long testKruskal(Graph graph) {
        long start;
        long stop;
        long accum = 0;
        Kruskals kru = new Kruskals(graph);

        for (int i = 0; i < 10; i++) {
            start = System.nanoTime();
            kru.run();
            stop = System.nanoTime();
            kru.reinit(graph);
            accum += ((stop - start) / 1000000);
        }
        accum /= 10;
        return accum;
    }

    //Runs Prims algorithm 10 times with the same graph provided, and returns the average run-time.
    public static long testPrim(Graph graph) {
        long start;
        long stop;
        long accum = 0;
        Prims prim = new Prims(graph);

        for (int i = 0; i < 10; i++) {
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
