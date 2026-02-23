
/**
 * Contains algorithms for Eulerian paths and circuits.
 */
class Eulerian {

    /**
     * Checks if the graph has an Eulerian circuit.
     * Cycle where every edge is used exactly once
     *
     * @param n Number of nodes.
     * @param adj The graph's adjacency list.
     * @return True if every vertex has an even degree, false otherwise.
     */
    public static boolean hasEulerianCircuit(int n, List<List<Integer>> adj) {
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the graph has an Eulerian path.
     * Path where every edge is used exactly once
     *
     * @param n Number of nodes.
     * @param adj The graph's adjacency list.
     * @return True if exactly 0 or 2 vertices have an odd degree, false
     * otherwise.
     */
    public static boolean hasEulerianPath(int n, List<List<Integer>> adj) {
        int oddCount = 0;
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount == 0 || oddCount == 2;
    }

    /**
     * Computes an Euler Tour for a tree using DFS. Used for LCA preprocessing
     * and subtree queries.
     *
     * @param graph Adjacency list representation of a tree.
     * @param root Root of the tree (usually 0 or 1).
     * @return A list representing the Euler Tour sequence.
     */
    public static List<Integer> eulerTourTree(List<List<Integer>> graph, int root) {
        List<Integer> tour = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];
        dfsEulerTour(graph, root, tour, visited);
        return tour;
    }

    private static void dfsEulerTour(List<List<Integer>> graph, int node, List<Integer> tour, boolean[] visited) {
        visited[node] = true;
        tour.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfsEulerTour(graph, neighbor, tour, visited);
                tour.add(node); // Adding node again after returning
            }
        }
    }

    /**
     * Finds an Eulerian path or circuit using Hierholzer’s algorithm. Works for
     * both directed and undirected graphs.
     *
     * @param graph Adjacency list representation of the graph.
     * @param start Starting node (usually any vertex with an odd degree for
     * path).
     * @return A list representing the Eulerian path/circuit.
     */
    public static List<Integer> eulerianPath(List<List<Integer>> graph, int start) {
        List<Integer> path = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Deque<Integer>> adj = new HashMap<>();

        // Convert adjacency list to a mutable form
        for (int i = 0; i < graph.size(); i++) {
            adj.put(i, new ArrayDeque<>(graph.get(i)));
        }

        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.peek();
            if (!adj.get(node).isEmpty()) {
                stack.push(adj.get(node).pollFirst()); // Visit the next available edge
            } else {
                path.add(stack.pop()); // Backtrack when no more edges
            }
        }
        Collections.reverse(path);
        return path;
    }
}
