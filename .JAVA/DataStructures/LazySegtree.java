import java.util.*;

public class LazySegtree<Info, Tag> {
    private final int n;
    private final List<Info> tree;
    private final List<Tag> lazy;

    public LazySegtree(int n) {
        this.n = n;
        this.tree = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
        this.lazy = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
    }

    public LazySegtree(List<Info> a) {
        this.n = a.size();
        this.tree = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
        this.lazy = new ArrayList<>(Collections.nCopies(4 * Integer.highestOneBit(n), null));
        build(1, 0, n - 1, a);
    }

    private void build(int v, int l, int r, List<Info> a) {
        if (l == r) {
            tree.set(v, a.get(l));
        } else {
            int m = (l + r) / 2;
            build(2 * v, l, m, a);
            build(2 * v + 1, m + 1, r, a);
            tree.set(v, add(tree.get(2 * v), tree.get(2 * v + 1)));
        }
    }

    private void apply(int v, int l, int r, Tag x) {
        tree.set(v, applyOperation(tree.get(v), x, l, r));
        lazy.set(v, applyLazy(lazy.get(v), x));
    }

    private void pushDown(int v, int l, int r) {
        int m = (l + r) / 2;
        apply(2 * v, l, m, lazy.get(v));
        apply(2 * v + 1, m + 1, r, lazy.get(v));
        lazy.set(v, null);
    }

    private void rangeUpdate(int v, int l, int r, int ql, int qr, Tag x) {
        if (qr < l || ql > r) return;
        if (ql <= l && r <= qr) {
            apply(v, l, r, x);
        } else {
            pushDown(v, l, r);
            int m = (l + r) / 2;
            rangeUpdate(2 * v, l, m, ql, qr, x);
            rangeUpdate(2 * v + 1, m + 1, r, ql, qr, x);
            tree.set(v, add(tree.get(2 * v), tree.get(2 * v + 1)));
        }
    }

    private Info rangeQuery(int v, int l, int r, int ql, int qr) {
        if (qr < l || ql > r) return null;
        if (l >= ql && r <= qr) return tree.get(v);
        pushDown(v, l, r);
        int m = (l + r) / 2;
        return add(rangeQuery(2 * v, l, m, ql, qr), rangeQuery(2 * v + 1, m + 1, r, ql, qr));
    }

    public void rangeUpdate(int ql, int qr, Tag x) {
        rangeUpdate(1, 0, n - 1, ql, qr, x);
    }

    public Info rangeQuery(int ql, int qr) {
        return rangeQuery(1, 0, n - 1, ql, qr);
    }

    private Info add(Info a, Info b) {
        // Implement the addition logic for Info objects
        return (Info) ((SumInfo) a).add((SumInfo) b); // For Info type SumInfo, adjust accordingly.
    }

    private Info applyOperation(Info current, Tag t, int l, int r) {
        // Implement apply operation on Info based on Tag
        return (Info) ((SumInfo) current).apply((SumTag) t, l, r); // Assuming SumInfo and SumTag classes.
    }

    private Tag applyLazy(Tag current, Tag t) {
        // Implement lazy propagation combining logic here
        return (Tag) ((SumTag) current).apply((SumTag) t);
    }

    public static class SumTag {
        QueryType type = QueryType.NONE;
        long val = 0;

        public SumTag apply(SumTag t) {
            if (t.type == QueryType.ADD) {
                val += t.val;
                if (type != QueryType.SET) {
                    type = QueryType.ADD;
                }
            } else if (t.type == QueryType.SET) {
                type = QueryType.SET;
                val = t.val;
            }
            return this;
        }
    }

    public static class SumInfo {
        long sum = 0;

        public SumInfo add(SumInfo other) {
            SumInfo result = new SumInfo();
            result.sum = this.sum + other.sum;
            return result;
        }

        public SumInfo apply(SumTag t, int l, int r) {
            if (t.type == QueryType.SET) {
                sum = t.val * (r - l + 1);
            } else if (t.type == QueryType.ADD) {
                sum += t.val * (r - l + 1);
            }
            return this;
        }
    }

    public enum QueryType {
        NONE, ADD, SET
    }
}