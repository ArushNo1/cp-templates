public class StronglyConnected {

    /**
     * Kosaraju's algorithm to find strongly connected components (SCCs) in a
     * directed graph.
     *
     * @param n Number of nodes.
     * @param adj The graph's adjacency list.
     * @return A list of SCCs, where each SCC is represented as a list of nodes.
     */
    public static List<List<Integer>> kosaraju(int n, List<List<Integer>> adj) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        // First DFS pass: fill order by finish time
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsFillOrder(i, visited, adj, stack);
            }
        }

        // Transpose the graph
        List<List<Integer>> transposed = transposeGraph(n, adj);
        Arrays.fill(visited, false);
        List<List<Integer>> sccs = new ArrayList<>();
        // Process all vertices in order defined by Stack
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> scc = new ArrayList<>();
                dfsCollectSCC(node, visited, transposed, scc);
                sccs.add(scc);
            }
        }
        return sccs;
    }

    // Helper method for Kosaraju: DFS to fill stack with finish times.
    private static void dfsFillOrder(int node, boolean[] visited, List<List<Integer>> adj, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsFillOrder(neighbor, visited, adj, stack);
            }
        }
        stack.push(node);
    }

    // Helper method for Kosaraju: DFS to collect nodes in a single SCC.
    private static void dfsCollectSCC(int node, boolean[] visited, List<List<Integer>> adj, List<Integer> scc) {
        visited[node] = true;
        scc.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsCollectSCC(neighbor, visited, adj, scc);
            }
        }
    }

    // Helper method: Transposes the given graph.
    private static List<List<Integer>> transposeGraph(int n, List<List<Integer>> adj) {
        List<List<Integer>> transposed = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            transposed.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int neighbor : adj.get(i)) {
                transposed.get(neighbor).add(i);
            }
        }
        return transposed;
    }
}
