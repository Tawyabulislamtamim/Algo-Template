#include<bits/stdc++.h>
using namespace std;
int MAX = 1e9;
void dfs (int node){
    vis[node] = true;
    for(int v : adj[v]){
        if(!vis[v]){
            dfs(v);
        }
    }
    st.push(node);
}
int main(){


}