
class GraphTraversal {

    /**
     * Performs a depth-first search (DFS) starting from a given node.
     *
     * @param node The starting node.
     * @param visited A boolean array tracking visited nodes.
     * @param adj The adjacency list of the graph.
     */
    public static void dfs(int node, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;
        // Process node here (e.g., print or store it)
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adj);
            }
        }
    }

    /**
     * Performs a breadth-first search (BFS) starting from a given node.
     *
     * @param start The starting node.
     * @param visited A boolean array tracking visited nodes.
     * @param adj The adjacency list of the graph.
     */
    public static void bfs(int start, boolean[] visited, List<List<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            // Process node here (e.g., print or store it)
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
