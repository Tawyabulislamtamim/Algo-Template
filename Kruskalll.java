
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Edge implements Comparable<Edge> {

    int src, dest;
    long weight;

    public Edge(int src, int dest, long weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Long.compare(this.weight, other.weight);
    }
}

public class Kruskalll {

    private static final int MAX = 10005;
    private static int[] parent = new int[MAX];
    private static ArrayList<Edge> edges = new ArrayList<>();
    private static ArrayList<Edge> mstEdges = new ArrayList<>();  // To store MST edges
    private static int nodes, edgeCount;

    private static void initialize() {
        for (int i = 0; i < MAX; ++i) {
            parent[i] = i;
        }
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }

    private static long kruskal() {
        long minimumCost = 0;
        Collections.sort(edges);

        for (Edge edge : edges) {
            int srcRoot = find(edge.src);
            int destRoot = find(edge.dest);

            // Check if including this edge forms a cycle
            if (srcRoot != destRoot) {
                minimumCost += edge.weight;
                mstEdges.add(edge);  // Add edge to MST
                union(srcRoot, destRoot);
            }
        }
        return minimumCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initialize();

        nodes = sc.nextInt();
        edgeCount = sc.nextInt();

        for (int i = 0; i < edgeCount; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            long weight = sc.nextLong();
            edges.add(new Edge(x, y, weight));
        }

        long minimumCost = kruskal();
        System.out.println("Minimum Cost: " + minimumCost);

        // System.out.println("Edges in the MST:");
        // for (Edge edge : mstEdges) {
        //     System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        // }
        sc.close();
    }
}
