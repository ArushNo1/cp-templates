import java.util.*;

/**
 * A generic implementation of a Lazy Segment Tree.
 * Supports range updates and range queries with lazy propagation.
 *
 * @param <Info> The type representing the information stored in each node.
 * @param <Tag> The type representing the update (tag) applied to nodes.
 */
public class LazySegtree<Info, Tag> {
    private final int n;  // The size of the array being processed
    private final List<Info> tree;  // The segment tree array storing node information
    private final List<Tag> lazy;  // The lazy propagation array storing pending updates

    /**
     * Constructor that initializes the Lazy Segment Tree with a given size.
     *
     * @param n The size of the segment tree (i.e., the number of elements in the array).
     */
    public LazySegtree(int n) {
        this.n = n;
        this.tree = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
        this.lazy = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
    }

    /**
     * Constructor that initializes the Lazy Segment Tree with an array of data.
     *
     * @param a The initial data for the segment tree.
     */
    public LazySegtree(List<Info> a) {
        this.n = a.size();
        this.tree = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
        this.lazy = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
        build(1, 0, n - 1, a);  // Build the tree based on the input array
    }

    /**
     * Builds the segment tree recursively.
     *
     * @param v The current node in the segment tree.
     * @param l The left bound of the range.
     * @param r The right bound of the range.
     * @param a The input data used to initialize the tree.
     */
    private void build(int v, int l, int r, List<Info> a) {
        if (l == r) {
            tree.set(v, a.get(l));  // Leaf node
        } else {
            int m = (l + r) / 2;
            build(2 * v, l, m, a);  // Left subtree
            build(2 * v + 1, m + 1, r, a);  // Right subtree
            tree.set(v, add(tree.get(2 * v), tree.get(2 * v + 1)));  // Combine results
        }
    }

    /**
     * Applies the update (tag) to the current node and propagates it to the children.
     *
     * @param v The current node in the segment tree.
     * @param l The left bound of the range.
     * @param r The right bound of the range.
     * @param x The update (tag) to apply.
     */
    private void apply(int v, int l, int r, Tag x) {
        tree.set(v, applyOperation(tree.get(v), x, l, r));  // Apply update to the current node
        lazy.set(v, applyLazy(lazy.get(v), x));  // Store the lazy update for future pushes
    }

    /**
     * Pushes down the lazy updates to the children of the current node.
     *
     * @param v The current node in the segment tree.
     * @param l The left bound of the range.
     * @param r The right bound of the range.
     */
    private void pushDown(int v, int l, int r) {
        int m = (l + r) / 2;
        apply(2 * v, l, m, lazy.get(v));  // Push update to left child
        apply(2 * v + 1, m + 1, r, lazy.get(v));  // Push update to right child
        lazy.set(v, null);  // Clear the lazy update for the current node
    }

    /**
     * Performs a range update on the segment tree.
     *
     * @param v The current node in the segment tree.
     * @param l The left bound of the range.
     * @param r The right bound of the range.
     * @param ql The query's left bound.
     * @param qr The query's right bound.
     * @param x The update (tag) to apply.
     */
    private void rangeUpdate(int v, int l, int r, int ql, int qr, Tag x) {
        if (qr < l || ql > r) return;  // No overlap, return early
        if (ql <= l && r <= qr) {
            apply(v, l, r, x);  // Apply the update if the range is fully within the query bounds
        } else {
            pushDown(v, l, r);  // Push lazy updates down to children
            int m = (l + r) / 2;
            rangeUpdate(2 * v, l, m, ql, qr, x);  // Update left subtree
            rangeUpdate(2 * v + 1, m + 1, r, ql, qr, x);  // Update right subtree
            tree.set(v, add(tree.get(2 * v), tree.get(2 * v + 1)));  // Recompute the current node's value
        }
    }

    /**
     * Performs a range query on the segment tree.
     *
     * @param v The current node in the segment tree.
     * @param l The left bound of the range.
     * @param r The right bound of the range.
     * @param ql The query's left bound.
     * @param qr The query's right bound.
     * @return The result of the range query.
     */
    private Info rangeQuery(int v, int l, int r, int ql, int qr) {
        if (qr < l || ql > r) return null;  // No overlap, return default value
        if (l >= ql && r <= qr) return tree.get(v);  // Fully within the range, return the node value
        pushDown(v, l, r);  // Push lazy updates down to children
        int m = (l + r) / 2;
        return add(rangeQuery(2 * v, l, m, ql, qr), rangeQuery(2 * v + 1, m + 1, r, ql, qr));  // Combine results from left and right
    }

    /**
     * Updates a range of values with the given tag.
     *
     * @param ql The left bound of the range.
     * @param qr The right bound of the range.
     * @param x The update (tag) to apply.
     */
    public void rangeUpdate(int ql, int qr, Tag x) {
        rangeUpdate(1, 0, n - 1, ql, qr, x);
    }

    /**
     * Queries the sum of values in a range.
     *
     * @param ql The left bound of the range.
     * @param qr The right bound of the range.
     * @return The result of the range query.
     */
    public Info rangeQuery(int ql, int qr) {
        return rangeQuery(1, 0, n - 1, ql, qr);
    }

    /**
     * Combines two `Info` objects (this represents the joining operation for segment tree nodes).
     *
     * @param a The first Info object.
     * @param b The second Info object.
     * @return The combined result.
     */
    private Info add(Info a, Info b) {
        // Implement the addition logic for Info objects
        return (Info) ((SumInfo) a).add((SumInfo) b);  // For Info type SumInfo, adjust accordingly.
    }

    /**
     * Applies the update (tag) to an `Info` object.
     *
     * @param current The current Info object.
     * @param t The update (tag) to apply.
     * @param l The left bound of the range.
     * @param r The right bound of the range.
     * @return The updated Info object.
     */
    private Info applyOperation(Info current, Tag t, int l, int r) {
        // Implement apply operation on Info based on Tag
        return (Info) ((SumInfo) current).apply((SumTag) t, l, r);  // Assuming SumInfo and SumTag classes.
    }

    /**
     * Applies a lazy tag to the current lazy value.
     *
     * @param current The current lazy value.
     * @param t The new lazy tag to apply.
     * @return The updated lazy value.
     */
    private Tag applyLazy(Tag current, Tag t) {
        // Implement lazy propagation combining logic here
        return (Tag) ((SumTag) current).apply((SumTag) t);
    }

    /**
     * Represents the tag (update) in the segment tree. It defines how to apply the update.
     */
    public static class SumTag {
        QueryType type = QueryType.NONE;  // Type of the update (set or add)
        long val = 0;  // The value of the update

        public SumTag apply(SumTag other) {
            if (other == null) return this;
            if (this.type == QueryType.NONE) {
                return other;
            }
            return other;  // This is just a basic example, handle specific cases
        }
    }

    /**
     * Represents the info (data) stored in each node of the tree. In this case, it sums the values.
     */
    public static class SumInfo {
        long sum = 0;  // The sum of the segment

        public SumInfo(long sum) { this.sum = sum; }

        public SumInfo add(SumInfo other) {
            return new SumInfo(this.sum + other.sum);
        }

        public SumInfo apply(SumTag tag, int l, int r) {
            if (tag == null) return this;
            return new SumInfo(sum + tag.val * (r - l + 1));  // Apply tag to this info
        }
    }

    public enum QueryType {
        NONE, SET, ADD
    }
}
