import java.util.*;

class DSU {
    private final List<Integer> e;

    // Constructor to initialize the DSU with a size N
    public DSU(int N) {
        e = new ArrayList<>(Collections.nCopies(N, -1)); 
    }

    // Initialize the DSU with a size N
    public void init(int N) {
        e.clear();
        e.addAll(Collections.nCopies(N, -1));
    }

    // Find the representative of the set containing x
    public int get(int x) {
        if (e.get(x) < 0) {
            return x;
        } else {
            e.set(x, get(e.get(x))); 
            return e.get(x);
        }
    }

    // Check if elements a and b belong to the same set
    public boolean sameSet(int a, int b) {
        return get(a) == get(b);
    }

    // Return the size of the set containing x
    public int size(int x) {
        return -e.get(get(x));
    }

    // Union the sets containing x and y by size
    public boolean unite(int x, int y) {
        x = get(x);
        y = get(y);
        if (x == y) return false;
        
        if (e.get(x) > e.get(y)) {
            int temp = x;
            x = y;
            y = temp;
        }
        
        e.set(x, e.get(x) + e.get(y));
        e.set(y, x);
        return true;
    }
}
