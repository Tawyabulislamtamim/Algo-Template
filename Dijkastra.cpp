#include <bits/stdc++.h>
using namespace std;
void Dijkstra(const vector<vector<pair<int, long long>>>& adjList, vector<long long>& distance, int source) {
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    pq.push({0, source}); // (distance, node)
    distance[source] = 0;
    while (!pq.empty()) {
        long long dis = pq.top().first; // pq.top() returns an pair
        int node = pq.top().second;
        pq.pop();
        for (const auto& neighbor : adjList[node]) {
            int adjNode = neighbor.first;
            long long adjWeight = neighbor.second;

            // relaxation 
            if (dis + adjWeight < distance[adjNode]) {
                distance[adjNode] = dis + adjWeight;
                pq.push({distance[adjNode], adjNode});
            }
        }
    }
}
int main() {
    int n, m, k, start, end;
    cin >> n >> m >> k >> start >> end;
    vector<vector<pair<int, long long>>> adjList(n + 1);
     for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adjList[u].push_back({v, 1});
        adjList[v].push_back({u, 1}); 
    }
     for (int i = 0; i < k; i++) {
        int u, v;
        cin >> u >> v;
        adjList[u].push_back({v, 2});
        adjList[v].push_back({u, 2});  
    }
    vector<long long> distance(n + 1, LLONG_MAX);
    Dijkstra(adjList, distance, start);

    if (distance[end] == LLONG_MAX)
        cout << "-1" << endl; 
    else
        cout << distance[end] << endl;  

    return 0;
}
/*
 - n cities
 - m roads
 - k hyperloops( cost 2 dollars ), otherwise 1 dollar
 - startting and ending city
 - If it is impossible to reach the end city then print -1, 
   Otherwise print the minimum cost to reach the end city from the starting city
*/
