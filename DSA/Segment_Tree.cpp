/**
 * @brief A Segment Tree implementation for range queries and point updates
 *
 * This class implements a segment tree data structure that supports:
 * - Point updates in O(log n) time
 * - Range queries in O(log n) time
 * - Construction in O(n) time
 * The tree maintains associative operations (like sum, min, max) over ranges
 */
class SegTree {
  public:
    typedef ll T;                // Type alias for the tree's value type
    static constexpr T unit = 0; // Identity element for the operation

    /**
     * @brief Combines two values using the tree's operation
     * @param a First value
     * @param b Second value
     * @return Result of combining a and b
     */
    T f(T a, T b) { return a + b; }

    vector<T> s; // Internal tree storage
    ll n;        // Size of the original array

    /**
     * @brief Constructs a segment tree
     * @param n Size of the array
     * @param def Default value for initialization (default = unit)
     */
    SegTree(ll n = 0, T def = unit) : s(2 * n, def), n(n) {}

    /**
     * @brief Updates a single position in the tree
     * @param pos Position to update (0-based)
     * @param val New value
     * Time complexity: O(log n)
     */
    void update(ll pos, T val) {
        for (s[pos += n] = val; pos /= 2;)
            s[pos] = f(s[pos * 2], s[pos * 2 + 1]);
    }

    /**
     * @brief Builds the tree from an array
     * @param v Input array
     * Time complexity: O(n)
     */
    void build(vector<T> &v) {
        for (ll i = 0; i < (ll)v.size(); i++)
            update(i, v[i]);
    }

    /**
     * @brief Queries a range [b, e)
     * @param b Beginning of range (inclusive)
     * @param e End of range (exclusive)
     * @return Result of combining all elements in the range
     * Time complexity: O(log n)
     */
    T query(ll b, ll e) {
        T ra = unit, rb = unit;
        for (b += n, e += n; b < e; b /= 2, e /= 2) {
            if (b % 2)
                ra = f(ra, s[b++]);
            if (e % 2)
                rb = f(s[--e], rb);
        }
        return f(ra, rb);
    }
};