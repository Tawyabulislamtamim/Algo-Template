import java.util.*;

class Bridge {
    int timer = 1;
    public void dfs(int node, int parent, int tin[], int low[], boolean vis[],
            ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> bridges) {
        vis[node] = true;
        low[node] = tin[node] = timer;
        timer++;
        for (int it : g.get(node)) {
            if (it == parent)
                continue;
            if (vis[it] == false) {
                dfs(it, node, tin, low, vis, g, bridges);
                low[node] = Math.min(low[it], low[node]);
                if (tin[node] < low[it]) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(node);
                    temp.add(it);
                    bridges.add(temp);
                }
            } else {
                low[node] = Math.min(low[it], low[node]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<>(); // 1 based ineding n+1
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
        int tin[] = new int[n];
        int low[] = new int[n];
        boolean vis[] = new boolean[n];
        ArrayList<ArrayList<Integer>> bridges = new ArrayList<>();
        Bridge bridge = new Bridge();
        bridge.dfs(0, -1, tin, low, vis, g, bridges);
        //System.out.println("size " +bridges.size());
        for (int i = 0; i < bridges.size(); i++) {
            System.out.print("Bridge " + (i + 1)+" : ");
            for (int it : bridges.get(i)) {
                System.out.print(it + " ");
            }
            System.out.println();
        }
    }
}