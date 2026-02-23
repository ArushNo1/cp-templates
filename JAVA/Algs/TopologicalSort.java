
class TopologicalSort {

    /**
     * Performs a topological sort on a directed acyclic graph using Kahn's
     * algorithm.
     *
     * @param n Number of nodes.
     * @param adj The graph's adjacency list.
     * @return A list of nodes in topologically sorted order.
     */
    public static List<Integer> topologicalSort(int n, List<List<Integer>> adj) {
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : adj.get(node)) {
                if (--indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

}
