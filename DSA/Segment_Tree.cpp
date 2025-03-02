class SegTree {
  public:
    typedef ll T;                // Type alias for the tree's value type
    static constexpr T unit = 0; // Identity element for the operation

    /**
     * @brief Combines two values using the tree's operation
     * @return Result of combining a and b
     */
    T f(T a, T b) { return a + b; }

    vector<T> s;
    ll n;        

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
     * @return Result of combining all elements in the range
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