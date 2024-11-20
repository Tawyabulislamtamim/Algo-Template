
import java.util.*;

public class Scc {

    public void dfs1(int node, ArrayList<ArrayList<Integer>> g, boolean vis[], Stack<Integer> st) {
        vis[node] = true;
        for (int u : g.get(node)) {
            if (vis[u] == false) {
                dfs1(u, g, vis, st);
            }
        }
        st.push(node);
    }

    public void reverseGraph(int n, ArrayList<ArrayList<Integer>> g, ArrayList<ArrayList<Integer>> Tg) {
        for (int i = 0; i < n; i++) {
            for (int u : g.get(i)) {
                Tg.get(u).add(i);
            }
        }
    }

    public void dfs3(int node, ArrayList<ArrayList<Integer>> Tg, boolean vis[], ArrayList<Integer> currComponents) {
        vis[node] = true;
        currComponents.add(node);
        for (int u : Tg.get(node)) {
            if (vis[u] == false) {
                dfs3(u, Tg, vis, currComponents);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<>();
        ArrayList<ArrayList<Integer>> Tg = new ArrayList<>();
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i <= n; i++) {
            Tg.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.get(u).add(v);
        }
        boolean vis[] = new boolean[n + 1];
        Stack<Integer> st = new Stack<>();
        Scc scc = new Scc();
        for (int i = 1; i <= n; i++) {
            if (vis[i] == false) {
                scc.dfs1(i, g, vis, st);
            }
        }
        scc.reverseGraph(n, g, Tg);

        for (int i = 0; i <= n; i++) {
            vis[i] = false;
        }

        while (!st.isEmpty()) {
            int u = st.peek();
            st.pop();
            if (vis[u] == false) {
                ArrayList<Integer> currComponents = new ArrayList<>();
                scc.dfs3(u, Tg, vis, currComponents);
                components.add(currComponents);
            }
        }
        for (int i = 0; i < components.size(); i++) {
            System.out.println("component " + (i + 1) + " : ");
            for (int it : components.get(i)) {
                System.out.print(it + " ");
            }
            System.out.println();
        }

    }
}
