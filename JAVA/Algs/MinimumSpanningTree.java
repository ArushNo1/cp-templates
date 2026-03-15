
import java.util.*;

class MinimumSpanningTree {

    /**
     * Function to compute the minimum spanning tree using Prim's Algorithm.
     *
     * @param neighbors The adjacency list representing the graph.
     * @return The total cost of the minimum spanning tree, or -1 if the graph
     * is disconnected.
     */
    public static long prims(List<List<Pair<Integer, Integer>>> neighbors) {
        int n = neighbors.size();  // number of vertices
        long minCost = 0;
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);  // Initialize distances to infinity
        dist[0] = 0;  // Start from vertex 0
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(Comparator.comparingLong(p -> p.cost));
        q.add(new Pair<Long, Integer>(0, 0));  // (cost, vertex)
        boolean[] visited = new boolean[n];
        int added = 0;

        while (added < n) {
            if (q.isEmpty()) {
                return -1;  // The graph is disconnected
            }

            Pair<Long, Integer> top = q.poll();
            long currCost = top.cost;
            int v = top.vertex;

            if (dist[v] < currCost) {
                continue;  // Skip if we've already found a cheaper way to v
            }

            added++;
            visited[v] = true;

            minCost += currCost;
            for (Pair<Long, Integer> neighbor : neighbors.get(v)) {
                int next = neighbor.vertex;
                long nCost = neighbor.cost;
                if (!visited[next] && nCost < dist[next]) {
                    dist[next] = nCost;
                    q.add(new Pair<Long, Integer>(nCost, next));  // Add the neighbor with updated cost
                }
            }
        }

        return minCost;
    }

    /**
     * Kruskal's algorithm to compute the total weight of a minimum spanning tree (MST).
     *
     * @param n     Number of nodes.
     * @param edges List of edges, where each edge is represented as an Edge object.
     * @return The total weight of the MST.
     */
    public static int kruskals(int n, List<Edge> edges) {
        //REQUIRES DSU
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        DSU uf = new DSU(n);
        int mstWeight = 0;
        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                mstWeight += edge.weight;
            }
        }
        return mstWeight;
    }
    
    public static class Edge {
        public int u, v, weight;
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    

}
