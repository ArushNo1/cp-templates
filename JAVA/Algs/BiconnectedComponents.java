
public class BiconnectedComponents {

    /**
     * Finds biconnected components (BCCs) in an undirected graph.
     * A BCC is a connected component such that removing any vertex from it does not disconnect it.
     *
     * @param n Number of nodes.
     * @param adj The graph's adjacency list.
     * @return A list of biconnected components, each represented as a list of
     * nodes.
     */
    public static List<List<Integer>> biconnectedComponents(int n, List<List<Integer>> adj) {
        List<List<Integer>> bccList = new ArrayList<>();
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[n];
        int[] time = {0};
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsBCC(i, visited, disc, low, parent, stack, bccList, adj, time);
                if (!stack.isEmpty()) {
                    List<Integer> component = new ArrayList<>();
                    while (!stack.isEmpty()) {
                        component.add(stack.pop());
                    }
                    bccList.add(component);
                }
            }
        }
        return bccList;
    }

    // Helper method for BCCs.
    private static void dfsBCC(int u, boolean[] visited, int[] disc, int[] low, int[] parent,
            Stack<Integer> stack, List<List<Integer>> bccList, List<List<Integer>> adj, int[] time) {
        visited[u] = true;
        disc[u] = low[u] = ++time[0];
        stack.push(u);
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                dfsBCC(v, visited, disc, low, parent, stack, bccList, adj, time);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] >= disc[u]) {
                    List<Integer> component = new ArrayList<>();
                    int w;
                    do {
                        w = stack.pop();
                        component.add(w);
                    } while (w != v && !stack.isEmpty());
                    component.add(u);
                    bccList.add(component);
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}
