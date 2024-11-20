import java.util.*;
 
public class TopoSort {
    public void  performTopoSort(int n ,ArrayList<ArrayList<Integer>> g, int inDegree [], ArrayList<Integer> topoOrd ) {
        Stack<Integer> st = new Stack<>();
         for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                st.push(i);
            }
        }
        while (!st.isEmpty()) {
            int node = st.peek();
            st.pop();
            topoOrd.add(node);
            for (int u : g.get(node)) {
                inDegree[u]--;
                if (inDegree[u] == 0) {
                    st.push(u);
                }
            }
            
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<>(n); // 1 based ineding n+1
        int inDegree [] = new int [n];
           

        for (int i = 0; i < n; i++) { // 1- based indexing  for i= 0 ,i <= n
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.get(u).add(v);
            inDegree[v]++;
        }
        TopoSort t = new TopoSort();
        ArrayList<Integer> topoOrd = new ArrayList<>();
        t.performTopoSort(n, g, inDegree, topoOrd);
        for (int i = 0; i < topoOrd.size(); i++) {
            System.out.print(topoOrd.get(i)+" ");
        }
      }
}