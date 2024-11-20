 import java.util.*;
 public class EulerPath {
     public void dfs(ArrayList<ArrayList<Integer>> g,boolean vis[],int node) {
        vis[node] = true;
        for (int u : g.get(node)) {
            if (vis[u] == false) {
                dfs(g, vis, u);
            }
        }
    }
     public boolean isConnected(ArrayList<ArrayList<Integer>> g,ArrayList<Integer> degree,int n){
        int stNode = -1;
        for(int i =0 ; i < n ;i++){
            if (degree.get(i) > 0){
                stNode = i;
                break;
            }
        }
        if (stNode == -1)
            return true;

        boolean vis[] = new boolean[n];
        dfs(g, vis, stNode);

        for (int i = 0; i < n; i++) {
        if ( degree.get(i) > 0 && vis[i] == false ) return false;
    }
    return true;
    }
    public boolean hasEulerianPath(ArrayList<ArrayList<Integer>> g, ArrayList<Integer> degree, int n) {
         
         if (isConnected(g,degree,n) == false)
             return false;
        int oddDegrees = 0 ;
         for (int i = 0; i < n; i++) {
             if (degree.get(i) % 2 == 1)
                 oddDegrees +=  1;
         }
         return (oddDegrees == 2 || oddDegrees == 0);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> g = new ArrayList<>(n); // 1 based ineding n+1
        ArrayList<Integer> degree = new ArrayList<>(n);
         for (int i = 0; i < n; i++) {  
             degree.add(0);
        }
        for (int i = 0; i < n; i++) { // 1- based indexing  for i= 0 ,i <= n
            g.add(new ArrayList<>());
        }
          
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.get(u).add(v);
            g.get(v).add(u);
            degree.set(u, degree.get(u) + 1);
            degree.set(v, degree.get(v) + 1);
        }
          EulerPath ep = new EulerPath();
          if (ep.hasEulerianPath(g,degree,n) == true) {
            System.out.println("The graph has an Eulerian path");
        }
        else {
             System.out.println("The graph does not have  an Eulerian path");           
        }           
      }
}