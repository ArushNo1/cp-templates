public class ShortestPath {

    /**
     * Dijkstra's Algorithm for finding the shortest path from a single source node.
     * Uses a priority queue for efficient extraction of the minimum distance node.
     * 
     * @param n The number of nodes in the graph.
     * @param adj The adjacency list representing the graph, where adj[i] contains pairs [neighbor, weight].
     * @param start The starting node.
     * @return An array of the shortest distances from the start node to all other nodes.
     */
    public static int[] dijkstra(int n, int[][] adj, int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {start, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], d = curr[1];
            
            if (d > dist[node]) continue;
            
            for (int[] neighbor : adj[node]) {
                int next = neighbor[0], weight = neighbor[1];
                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    pq.add(new int[] {next, dist[next]});
                }
            }
        }
        
        return dist;
    }

    /**
     * Floyd-Warshall Algorithm for finding the shortest paths between all pairs of nodes.
     * 
     * @param n The number of nodes in the graph.
     * @param graph The adjacency matrix representing the graph, where graph[i][j] is the weight of the edge.
     * @return A distance matrix with the shortest paths between all pairs of nodes.
     */
    public static int[][] floydWarshall(int n, int[][] graph) {
        int[][] dist = new int[n][n];
        
        // Initialize distances
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (i == j) ? 0 : graph[i][j];
            }
        }
        
        // Apply Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        return dist;
    }

    /**
     * Bellman-Ford Algorithm for finding the shortest path from a single source node.
     * Works with graphs that contain negative edge weights, but checks for negative weight cycles.
     * 
     * @param n The number of nodes in the graph.
     * @param edges The edge list representing the graph, where each edge is [u, v, weight].
     * @param start The starting node.
     * @return An array of the shortest distances from the start node to all other nodes.
     * @throws IllegalArgumentException If the graph contains a negative weight cycle.
     */
    public static int[] bellmanFord(int n, int[][] edges, int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        // Relax edges up to n-1 times
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], weight = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
        
        // Check for negative weight cycles
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                throw new IllegalArgumentException("Graph contains a negative weight cycle");
            }
        }
        
        return dist;
    }
}
