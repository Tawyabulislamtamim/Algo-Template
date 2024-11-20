import java.util.*;
 
public class Dfs {
    public void  dfsOfGraph(int node, ArrayList<ArrayList<Integer>> g, ArrayList<Integer> ord
                            ,boolean vis []) {
           vis[node] = true ;
           ord.add(node) ;
           for(int u : g.get(node)){
              if(vis[u] == false){
                dfsOfGraph(u,g,ord,vis);
              }
           }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<>(n); // 1 based ineding n+1

        for (int i = 0; i < n; i++) { // 1- based indexing  for i= 0 ,i <= n
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
        }
         Dfs sl = new Dfs(); 
         
         
      }
}