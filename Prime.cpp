#include <iostream>
#include <vector>
#include <queue>
#include <climits>
#include <utility>
#include <map>

using namespace std;

const int MAX = 10005;
bool visited[MAX];
vector<vector<pair<int, int>>> adj(MAX);  // Adjacency list storing (neighbor, weight)
vector<pair<int, int>> mstEdges;  // Optional, to store the edges of the MST

// Function to run Prim's algorithm to find the Minimum Spanning Tree (MST)
int primAlgo(int n) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> Q;  // Min-heap based on edge weight
    int minimumCost = 0;

    // Start with node 0 (adjust based on input)
    Q.push({0, 0});  // (weight, node)

    while (!Q.empty()) {
        auto p = Q.top(); Q.pop();
        int x = p.second;  // Node
        int weight = p.first;  // Weight of the edge

        // Skip if this node has already been visited
        if (visited[x]) continue;

        // Mark the node as visited and accumulate the cost
        visited[x] = true;
        minimumCost += weight;

        // Add the adjacent edges to the priority queue
        for (const auto& edge : adj[x]) {
            int y = edge.first;  // Adjacent node
            int edgeWeight = edge.second;  // Edge weight

            if (!visited[y]) {
                Q.push({edgeWeight, y});  // Add (weight, node) to the queue
                // Optionally track the MST edges (not necessary for the final output)
                mstEdges.push_back({x, y});
            }
        }
    }

    return minimumCost;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n, e;  // Number of nodes (cities) and edges (roads)
    cin >> n >> e;

    // Initialize adjacency list for each node (cities)
    for (int i = 0; i < n; i++) {
        adj[i].clear();  // Clear adjacency list for each node
    }

    // Reading edges
    for (int i = 0; i < e; i++) {
        int x, y, w;  // Road between x and y with cost w
        cin >> x >> y >> w;
        adj[x].push_back({y, w});  // Add road from x to y
        adj[y].push_back({x, w});  // Add road from y to x (undirected graph)
    }

    // Run Prim's algorithm starting from node 0
    int minimumCost = primAlgo(n);

    // If the MST doesn't connect all nodes, print "IMPOSSIBLE"
    if (minimumCost == INT_MAX) {
        cout << "IMPOSSIBLE\n";
    } else {
        cout << minimumCost << "\n";  // Output the minimum reparation cost
    }

    return 0;
}
