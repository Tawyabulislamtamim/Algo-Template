
import java.util.*;

public class Bfs {

    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] vis;
    static int[] par;
    static int[] dis;
    static Queue<Integer> q;

    public static void bfs(int n) {
        vis = new boolean[n];
        par = new int[n];
        dis = new int[n];
        q = new LinkedList<>();

        Arrays.fill(par, -1);
        Arrays.fill(dis, Integer.MAX_VALUE);
        q.add(0);
        vis[0] = true;

        while (!q.isEmpty()) {
            Integer u = q.poll();
            for (Integer v : graph.get(u)) {
                if (!vis[v]) {
                    vis[v] = true;
                    par[v] = u;
                    dis[v] = dis[u] + 1;
                    q.add(v);
                }
            }
        }
    }

    // print shortest path between source and destination
    public static void print(int s, int d) {
        if (s == d) {
            System.out.println(d);
        } else if (par[d] == -1) {
            System.out.println("No apath available");
        } else {
            print(s, par[d]);
            System.out.print(d);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        bfs(n);
        print(0, 6);
    }
}
