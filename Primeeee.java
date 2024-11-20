
import java.util.*;

public class Primeeee {

    static final int MAX = 10005;
    static boolean[] visited = new boolean[MAX];
    static ArrayList<ArrayList<Map.Entry<Integer, Integer>>> adj = new ArrayList<>();
    static List<Map.Entry<Integer, Integer>> mstEdges = new ArrayList<>();

    // Prim's algorithm with the correct (weight, node) order
    public static int primAlgo(int startNode) {
        // PriorityQueue stores (weight, node), sorted by weight (default priority)
        PriorityQueue<Map.Entry<Integer, Integer>> Q = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));
        int minimumCost = 0;

        // Start with node 'startNode' with a 0 weight (starting point of MST)
        Q.add(new AbstractMap.SimpleEntry<>(0, startNode));

        while (!Q.isEmpty()) {
            Map.Entry<Integer, Integer> p = Q.poll();
            int x = p.getValue();  // Node
            int weight = p.getKey();  // Weight of the edge

            // Skip if this node has already been visited
            if (visited[x]) {
                continue;
            }

            // Mark the node as visited
            visited[x] = true;
            minimumCost += weight;

            // Add the adjacent edges to the priority queue
            for (Map.Entry<Integer, Integer> edge : adj.get(x)) {
                int y = edge.getKey();  // Adjacent node
                int edgeWeight = edge.getValue();  // Edge weight

                // Only add unvisited nodes to the priority queue
                if (!visited[y]) {
                    Q.add(new AbstractMap.SimpleEntry<>(edgeWeight, y));  // Store (weight, node)
                    // Track the edge in mstEdges (optional step for visualization)
                    mstEdges.add(new AbstractMap.SimpleEntry<>(x, y));  // Add edge (x, y)
                }
            }
        }

        return minimumCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // Number of nodes
        int e = sc.nextInt();  // Number of edges

        // Initialize adjacency list for each node
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Reading edges
        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int w = sc.nextInt();  // Weight of the edge (use Integer instead of Long)

            // Undirected graph, so add both directions
            adj.get(x).add(new AbstractMap.SimpleEntry<>(y, w));  // (node, weight)
            adj.get(y).add(new AbstractMap.SimpleEntry<>(x, w));  // (node, weight)
        }

        // Run Prim's algorithm starting from node 0
        int minimumCost = primAlgo(0);
        System.out.println(minimumCost);

        sc.close();
    }
}
