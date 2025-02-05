
public class ArticulationPoints {

    /**
     * Finds articulation points (cut vertices) in an undirected graph.
     * Articulation points are nodes whose removal would disconnect the graph.
     *
     * @param n Number of nodes.
     * @param adj The graph's adjacency list.
     * @return A set of nodes that are articulation points.
     */
    public static Set<Integer> findArticulationPoints(int n, List<List<Integer>> adj) {
        Set<Integer> articulationPoints = new HashSet<>();
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[n];
        // time is used to track discovery times; we use an array of one element for pass-by-reference.
        int[] time = {0};
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsArticulation(i, visited, disc, low, parent, articulationPoints, adj, time);
            }
        }
        return articulationPoints;
    }

    // Helper method for finding articulation points.
    private static void dfsArticulation(int u, boolean[] visited, int[] disc, int[] low, int[] parent,
            Set<Integer> articulationPoints, List<List<Integer>> adj, int[] time) {
        visited[u] = true;
        disc[u] = low[u] = ++time[0];
        int children = 0;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfsArticulation(v, visited, disc, low, parent, articulationPoints, adj, time);
                low[u] = Math.min(low[u], low[v]);
                if (parent[u] == -1 && children > 1) {
                    articulationPoints.add(u);
                }
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    articulationPoints.add(u);
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

}
