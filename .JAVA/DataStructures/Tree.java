import java.util.*;

public class Tree<T extends Number> {
    private final int log2dist;
    private final T[] par;
    private final T[][] pow2ends;

    // Constructor that takes a list of parent nodes
    public Tree(List<T> parents) {
        log2dist = (int) Math.ceil(Math.log(parents.size() + 1) / Math.log(2)); // log2 of the size of the tree
        par = (T[]) new Number[parents.size() + 1];  // Using Number array
        pow2ends = (T[][]) new Number[parents.size() + 1][log2dist + 1];  // 2D Number array

        par[0] = (T) Integer.valueOf(-1); // root node's parent is -1 (non-existent)

        // Initialize parent array
        for (int i = 0; i < parents.size(); i++) {
            par[i + 1] = parents.get(i);
        }

        // Initialize pow2ends array where pow2ends[n][k] contains the 2^k-th parent of node n
        for (int n = 0; n < par.length; n++) {
            pow2ends[n][0] = par[n];
        }

        // Fill the rest of pow2ends using dynamic programming
        for (int p = 1; p <= log2dist; p++) {
            for (int n = 0; n < par.length; n++) {
                T halfway = pow2ends[n][p - 1];
                if (halfway.intValue() == -1) {
                    pow2ends[n][p] = (T) Integer.valueOf(-1);
                } else {
                    pow2ends[n][p] = pow2ends[halfway.intValue()][p - 1];
                }
            }
        }
    }

    /** 
     * @return the kth parent of node n
     */
    public T kthParent(int n, long k) {
        T at = par[n];

        // Break down k into powers of 2 by looping through its bits
        for (int pow = 0; pow <= log2dist; pow++) {
            if ((k & (1L << pow)) != 0) {
                at = pow2ends[at.intValue()][pow];
                if (at.intValue() == -1) {
                    break; // Stop when we've reached the root or there's no parent
                }
            }
        }

        return at;
    }
}
