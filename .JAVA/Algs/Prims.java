
import java.util.*;

public class Prims {

    /**
     * Function to compute the minimum spanning tree using Prim's Algorithm.
     *
     * @param neighbors The adjacency list representing the graph.
     * @return The total cost of the minimum spanning tree, or -1 if the graph
     * is disconnected.
     */
    public static long prim(List<List<Pair<Integer, Integer>>> neighbors) {
        int n = neighbors.size();  // number of vertices
        long minCost = 0;
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);  // Initialize distances to infinity
        dist[0] = 0;  // Start from vertex 0
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(Comparator.comparingLong(p -> p.cost));
        q.add(new Pair<Integer, Integer>(0, 0));  // (cost, vertex)
        boolean[] visited = new boolean[n];
        int added = 0;

        while (added < n) {
            if (q.isEmpty()) {
                return -1;  // The graph is disconnected
            }

            Pair<Integer, Integer> top = q.poll();
            long currCost = top.cost;
            int v = top.vertex;

            if (dist[v] < currCost) {
                continue;  // Skip if we've already found a cheaper way to v
            }

            added++;
            visited[v] = true;

            minCost += currCost;
            for (Pair<Integer, Integer> neighbor : neighbors.get(v)) {
                int next = neighbor.vertex;
                long nCost = neighbor.cost;
                if (!visited[next] && nCost < dist[next]) {
                    dist[next] = nCost;
                    q.add(new Pair<Integer, Integer>(nCost, next));  // Add the neighbor with updated cost
                }
            }
        }

        return minCost;
    }

    public static class Pair<T extends Comparable<T>, U extends Comparable<U>> {

        private T cost;
        private U vertex;

        // Constructor to initialize the pair
        public Pair(T first, U second) {
            this.cost = first;
            this.vertex = second;
        }

        // Getter for the first element
        public T getFirst() {
            return cost;
        }

        // Getter for the second element
        public U getSecond() {
            return vertex;
        }

        // Method to compare the first elements of the pair
        public int compareFirst(Pair<T, U> other) {
            return this.cost.compareTo(other.getFirst());
        }

        // Method to compare the second elements of the pair
        public int compareSecond(Pair<T, U> other) {
            return this.vertex.compareTo(other.getSecond());
        }

        // Method to compare the whole pair lexicographically: first then second
        public int compare(Pair<T, U> other) {
            int firstComparison = this.compareFirst(other);
            if (firstComparison != 0) {
                return firstComparison;
            }
            return this.compareSecond(other);
        }

        // Overriding equals() method
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair<?, ?> other = (Pair<?, ?>) obj;
            return Objects.equals(cost, other.cost) && Objects.equals(vertex, other.vertex);
        }

        // Overriding hashCode() method
        @Override
        public int hashCode() {
            return Objects.hash(cost, vertex);
        }

        @Override
        public String toString() {
            return "(" + cost + ", " + vertex + ")";
        }
    }

}
