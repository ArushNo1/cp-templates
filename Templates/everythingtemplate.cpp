#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops") 
#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>

using namespace __gnu_pbds;
using namespace std;

typedef long long ll;
typedef vector<ll> vll;
typedef vector<vll> vvll;
typedef long double ld;
typedef pair<ll, ll> pll;
typedef vector<bool> vb;

typedef tree<int,null_type,less<int>,rb_tree_tag,
tree_order_statistics_node_update> indexed_set;


#define endll '\n'

#define all(x) (x).begin(), (x).end()
#define MOD ll(1e9+7)
#define inf int(2e31-1)
#define INF ll(2e63-1)
#define EPS ld(1e-9)
#define dbg(x) cerr << __LINE__ << ": " << #x << "=" << x << endll;
#define print(x) cout << __LINE__ << ": " << #x << "=" << x << endll;
#define ans(x) cout << (x) << endll; return;

template <typename T>
ostream& operator<< (ostream& os, const vector<T>& arr){
	os << "[";
    for(const T x : arr){
		os << x << " ";
	}
	os << "]";
    return os;
}

template <typename T, typename U>
ostream& operator<< (ostream& os, const pair<T, U>& x){
    os << "(" << x.first << ", " << x.second << ")";
    return os;
}

template <typename T, typename U>
ostream& operator<< (ostream& os, const vector<pair<T, U>>& arr){
	os << "{\n";
    for(const pair<T,U>& x : arr){
		os << "    " << x << endll;
	}
	os << "}";
    return os;
}

template <typename T, typename U>
ostream& operator<< (ostream& os, const map<T, U>& arr){
	os << "{\n";
    for(const pair<T,U>& x : arr){
		os << "    " << x << endll;
	}
	os << "}";
    return os;
}

template <typename T>
inline void fillv(vector<T>& v, int n) {
    for (int i = 0; i < n; ++i) {
        std::cin >> v[i];
    }
}

long long binExp(long long a, long long b) {
  if (b == 0)
    return 1;

  long long res = binExp(a, b / 2) % MOD;
  if (b & 1) {
    return (a * ((res * res) % MOD)) % MOD;
  } else
    return (res * res) % MOD;
}

long long binExp(long long a, long long b, long long mod) {
  if (b == 0)
    return 1;

  long long res = binExp(a, b / 2) % mod;
  if (b & 1) {
    return (a * ((res * res) % mod)) % mod;
  } else
    return (res * res) % mod;
}

/** @return nCk mod p using dynamic programming */

ll binomial(ll n, ll k) {
	// dp[i][j] stores iCj

	vector<vector<ll>> dp(n + 1, vector<ll>(k + 1, 0));

	// base cases described above
	for (ll i = 0; i <= n; i++) {
		/*
		 * i choose 0 is always 1 since there is exactly one way
		 * to choose 0 elements from a set of i elements
		 * (don't choose anything)
		 */
		dp[i][0] = 1;
		/*
		 * i choose i is always 1 since there is exactly one way
		 * to choose i elements from a set of i elements
		 * (choose every element in the set)
		 */
		if (i <= k) { dp[i][i] = 1; }
	}

	for (ll i = 0; i <= n; i++) {
		for (ll j = 1; j <= min(i, k); j++) {
			if (i != j) {  // skips over the base cases
				// uses the recurrence relation above
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
			}
		}
    }

	return dp[n][k];  // returns nCk modulo p
}

ll exactBinomial(ll n, ll k) {
    if (k > n - k) { k = n - k; }
	long long ret = 1;
	for (int i = 0; i < k; i++) {
		// this is done instead of *= for divisibility issues
		ret = ret * (n - i) / (i + 1);
	}
	return ret;
}

vector<ll> factorial;

long long binomialFast(ll n, ll k) {
    return factorial[n] * binExp(factorial[k] * factorial[n - k] % MOD, MOD - 2) % MOD;
}

int phi(int n) {
    int result = n;
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            while (n % i == 0)
                n /= i;
            result -= result / i;
        }
    }
    if (n > 1)
        result -= result / n;
    return result;
}

void phi_1_to_n(int n, vector<int>& phi) {
    phi[0] = 0;
    phi[1] = 1;
    for (int i = 2; i <= n; i++)
        phi[i] = i - 1;

    for (int i = 2; i <= n; i++)
        for (int j = 2 * i; j <= n; j += i)
              phi[j] -= phi[i];
}

vector<ll> factorsPrimes(ll n, vector<ll> &primes) {
    vector<ll> factorization;
    for (ll d : primes) {
        if (d * d > n)
            break;
        while (n % d == 0) {
            factorization.push_back(d);
            n /= d;
        }
    }
    if (n > 1)
        factorization.push_back(n);
    return factorization;
}

vector<int> factor(int n) {
	vector<int> ret;
	for (int i = 2; i * i <= n; i++) {
		while (n % i == 0) {
			ret.push_back(i);
			n /= i;
		}
	}

	if (n > 1) { ret.push_back(n); }
	return ret;
}

long long numberOfDivisors(long long num) {
    long long total = 1;
    for (int i = 2; (long long)i * i <= num; i++) {
        if (num % i == 0) {
            int e = 0;
            do {
                e++;
                num /= i;
            } while (num % i == 0);
            total *= e + 1;
        }
    }
    if (num > 1) {
        total *= 2;
    }
    return total;
}

long long SumOfDivisors(long long num) {
    long long total = 1;

    for (int i = 2; (long long)i * i <= num; i++) {
        if (num % i == 0) {
            int e = 0;
            do {
                e++;
                num /= i;
            } while (num % i == 0);

            long long sum = 0, pow = 1;
            do {
                sum += pow;
                pow *= i;
            } while (e-- > 0);
            total *= sum;
        }
    }
    if (num > 1) {
        total *= (1 + num);
    }
    return total;
}

ll isqrt(ll n){
    ll low = 0, high = n;
    while(low < high){
        ll mid = low + (high - low + 1) / 2;
        ll mid2 = mid * mid;
        if(mid2 == n){
            return mid;
        }
        if(mid * mid <= n){
            low = mid;
        } else {
            high = mid - 1;
        }
    }
    return low;
}

bool isPrime(ll x) {
    for (ll d = 2; d * d <= x; d++) {
        if (x % d == 0)
            return false;
    }
    return x >= 2;
}

map<int, int> prime_factorize_map(int n) {
    map<int, int> ret;
    for (int i = 2; i * i <= n; i++) {
        while (n % i == 0) {
            ret[i]++;
            n /= i;
        }
    }
    if (n > 1) ret[n] = 1;
    return ret;
}

vector<int> prime_factorize_vector(int n) {
    vector<int> ret;
    for (int i = 2; i * i <= n; i++) {
        while (n % i == 0) {
            ret.push_back(i);
            n /= i;
        }
    }
    if (n > 1) ret.push_back(n);
    return ret;
}

long long xor1n(long long n){
    long long mod = n % 4;
    if(mod == 0){
        return n;
    }
    if(mod == 1){
        return 1;
    }
    if(mod == 2){
        return n + 1;
    }
    return 0;
}

long long xorlr(long long l, long long r){
    return xor1n(l - 1) ^ xor1n(r);
}

void sieve(vector<bool>& is_prime, ll n)
{
    is_prime[0] = is_prime[1] = false;
    for (ll i = 2; i <= n; i++) {
        if (is_prime[i] && (ll)i * i <= n) {
            for (ll j = i * i; j <= n; j += i)
                is_prime[j] = false;
        }
    }
}

template <class T> class BIT {
  private:

	int size;
	vector<T> bit;
	vector<T> arr;


  public:

	BIT(int size) : size(size), bit(size + 1), arr(size) {}
	/** Sets the value at index ind to val. */
	void set(int ind, T val) { add(ind, val - arr[ind]); }

	/** Adds val to the element at index ind. */

	void add(int ind, T val) {
		arr[ind] += val;
		ind++;
		for (; ind <= size; ind += ind & -ind) { bit[ind] += val; }
	}


	/** @return The sum of all values in [0, ind]. */

	T pref_sum(int ind) {
		ind++;
		T total = 0;
		for (; ind > 0; ind -= ind & -ind) { total += bit[ind]; }
		return total;
	}

};

class Tree {

  private:

	const int log2dist;

	vector<int> par;

	vector<vector<int>> pow2ends;


  public:

	Tree(const vector<int> &parents)

	    : log2dist(std::ceil(std::log2(parents.size() + 1))), par(parents.size() + 1),

	      pow2ends(par.size(), vector<int>(log2dist + 1)) {

		par[0] = -1;

		for (int i = 0; i < parents.size(); i++) { par[i + 1] = parents[i]; }


		// pow2ends[n][k] contains the 2^kth parent of node n

		// if there is no 2^kth parent, the value is -1

		for (int n = 0; n < par.size(); n++) { pow2ends[n][0] = par[n]; }

		for (int p = 1; p <= log2dist; p++) {

			for (int n = 0; n < par.size(); n++) {

				int halfway = pow2ends[n][p - 1];

				if (halfway == -1) {

					pow2ends[n][p] = -1;

				} else {

					pow2ends[n][p] = pow2ends[halfway][p - 1];

				}

			}

		}

	}


	/** @return the kth parent of node n */

	int kth_parent(int n, int k) {

		int at = n;

		// break down k into powers of 2 by looping through its bits

		for (int pow = 0; pow <= log2dist; pow++) {

			if ((k & (1 << pow)) != 0) {

				at = pow2ends[at][pow];

				if (at == -1) {

					break;  // stop when we're past the root

				}

			}

		}

		return at;

	}

};

struct DSU {
  	vector<ll> e;
	DSU(ll N)
	{
		e = vector<ll>(N, -1); 
	}

  	void init(ll N) { 
		e = vector<ll>(N, -1); 
	}

  	ll get(ll x) { 
		return e[x] < 0 ? x : e[x] = get(e[x]); 
	}

  	bool sameSet(ll a, ll b) {
		return get(a) == get(b); 
	}

  	ll size(ll x) {
		return -e[get(x)]; 
	}

  	bool unite(ll x, ll y) { // union by size
  	  x = get(x), y = get(y);
  	  if (x == y)
  	    return 0;
  	  if (e[x] > e[y])
  	    swap(x, y);
	
  	  e[x] += e[y];
  	  e[y] = x;
  	  return 1;
  	}
};

long long prim(const vector<vector<pair<int, int>>> &neighbors) {

	const int n = neighbors.size();  // just a shorthand
	long long min_cost = 0;
	vector<long long> dist(n, std::numeric_limits<long long>().max());
	dist[0] = 0;
	std::priority_queue<pair<long long, int>> q;
	q.push({0, 0});
	vector<bool> visited(n);
	int added = 0;
	while (added < n) {

		if (q.empty()) { return -1; }
		auto [curr_cost, v] = q.top();
		q.pop();
		curr_cost *= -1;
		if (dist[v] < curr_cost) { continue; }

		added++;
		visited[v] = true;

		min_cost += curr_cost;
		for (auto &[next, n_cost] : neighbors[v]) {
			if (!visited[next] && n_cost < dist[next]) {
				dist[next] = n_cost;
				q.push({-n_cost, next});
			}
		}
	}

	return min_cost;
}


class SegTree {
public:
	typedef ll T;
	static constexpr T unit = 0;

  	// (any associative fn)
  	T f(T a, T b) { return a+b; }

  	vector<T> s;
  	ll n;

  	SegTree(ll n = 0, T def = unit) : s(2 * n, def), n(n) {}

  	void update(ll pos, T val) {
    	for (s[pos += n] = val; pos /= 2;)
      		s[pos] = f(s[pos * 2], s[pos * 2 + 1]);
  	}

  	void build(vector<T> &v) {
    	for (ll i = 0; i < (ll)v.size(); i++)
      		update(i, v[i]);
  	}

  	T query(ll b, ll e) { // query [b, e)
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

//All-Pairs Shortest Path - O(N^3) time, O(N^2) memory
//With negative edges
//Output: matrix of distances from node i to node j
//Input: adjacency matrix of graph
void floyd_warshall(vector<vector<ll>> &distance, vector<vector<ll>> &adj)
{
	int N = adj.size();
	
	assert(adj.size() == distance.size());
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (i == j) distance[i][j] = 0;
			else if (adj[i][j]) distance[i][j] = adj[i][j];
			else distance[i][j] = INF;
		}
	}
	
	for (int k = 0; k < N; k++) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distance[i][j] = min(distance[i][j],
									distance[i][k]+distance[k][j]);
			}
		}
	}
}


//Shortest distance from node X to all nodes in weighted graph (fast)
//Input: Adjacency List, node X, size of graph (N)
//Output: Distance array & parent array to reconstruct path
//
//Time - O(NM), Space - O(N + M)
void dijkstra(vector<ll>& distance, vector<ll> &parents, vector<vector<pair<ll, ll>>> &adj, ll x, ll n)
{
	vector<bool> processed(n);
	priority_queue<pair<ll, ll>> q{};
	
	for (int i = 0; i < n; i++) distance[i] = INF;
	distance[x] = 0;
	q.push({0,x});
	
	while (!q.empty()) 
	{
		ll a = q.top().second; q.pop();
		if (processed[a]) continue;
		processed[a] = true;
		
		for (auto u : adj[a]) 
		{
			ll b = u.first, w = u.second;
			if (distance[a] + w < distance[b]) 
			{
				distance[b] = distance[a] + w;
				parents[b] = a;
				q.push({-distance[b], b});
			}
		}
	}
}

//Shortest distance from node X to all nodes in weighted graph (slow)
//Input: Edge List, node X, size of graph (N)
//Output: Distance array, bool - if contains neg cycle
//
//Time - O(NM), Space - O(N + M)
bool bellman_ford(vector<ll>& distance, vector<pair<pair<ll, ll>,ll>>& edges, ll x, ll n)
{
	for (int i = 0; i < n; i++) distance[i] = INF;
	distance[x] = 0;
	for (int i = 0; i < n-1; i++) 
	{
		for (auto e : edges) 
		{
			ll a, b, w;
			a = e.first.first;
			b = e.first.second;
			w = e.second;
			distance[b] = min(distance[b], distance[a] + w);
		}
	}
	
	bool negCycle = false;
	for (auto e : edges) 
	{
		ll a, b, w;
		a = e.first.first;
		b = e.first.second;
		w = e.second;
		if(distance[b] > distance[a] + w)
		{
			negCycle = true;
			break;
		}
	}
	
	return negCycle;
}


template <typename T> class SparseTable {
private:

	int n, log2dist;
	vector<vector<T>> st;

public:

	SparseTable(const vector<T> &v) {
		n = (int)v.size();
		log2dist = 1 + (int)log2(n);
		st.resize(log2dist);
		st[0] = v;
		for (int i = 1; i < log2dist; i++) {
			st[i].resize(n - (1 << i) + 1);
			for (int j = 0; j + (1 << i) <= n; j++) {
				st[i][j] = min(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
			}
		}
	}

	/** @return minimum on the range [l, r] */
	T query(int l, int r) {
		int i = (int)log2(r - l + 1);
		return min(st[i][l], st[i][r - (1 << i) + 1]);
	}
};

class LCA {
private:
	const int n;
	const vector<vector<int>> &adj;
	SparseTable<pair<int, int>> rmq;
	vector<int> tin, et, dep;
	int timer = 0;

	/** prepares tin, et, dep arrays */
	void dfs(int u, int p) {
		tin[u] = timer;
		et[timer++] = u;
		for (int v : adj[u]) {
			if (v == p) { continue; }
			dep[v] = dep[u] + 1;
			dfs(v, u);
			et[timer++] = u;
		}
	}

public:
	// make sure the adjacency list is 0 indexed
	LCA(vector<vector<int>> &_adj)
	    : n((int)_adj.size()), adj(_adj), tin(n), et(2 * n), dep(n),
	      rmq(vector<pair<int, int>>(1)) {
		dfs(0, -1);
		vector<pair<int, int>> arr(2 * n);
		for (int i = 0; i < 2 * n; i++) { arr[i] = {dep[et[i]], et[i]}; }
		rmq = SparseTable<pair<int, int>>(arr);
	}

	/** @return LCA of nodes a and b */
	int query(int a, int b) {
		if (tin[a] > tin[b]) { swap(a, b); }
		return rmq.query(tin[a], tin[b]).second;
	}
};


class HashedString {
  private:
	// change M and B if you want
	static const long long M = 1e9 + 9;
	static const long long B = 9973;

	// pow[i] contains B^i % M
	static vector<long long> pow;

	// p_hash[i] is the hash of the first i characters of the given string
	vector<long long> p_hash;
	
  public:
	HashedString(const string &s) : p_hash(s.size() + 1) {
		while (pow.size() <= s.size()) { pow.push_back((pow.back() * B) % M); }
		p_hash[0] = 0;
		for (int i = 0; i < s.size(); i++) {
			p_hash[i + 1] = ((p_hash[i] * B) % M + s[i]) % M;
		}
	}


	long long get_hash(int start, int end) {
		long long raw_val =
		    (p_hash[end + 1] - (p_hash[start] * pow[end - start + 1]));
		return (raw_val % M + M) % M;
	}
};

vector<long long> HashedString::pow = {1};


//Topological Sort of a DAG
//Time - O(N + E), Space - O(N + E)
//Input: normal and reverse adjacency list
//Output: sorted order of nodes
bool topSort(vector<ll>& sorted, vector<unordered_set<ll>>& adjList, vector<unordered_set<ll>>& radjList)
{
	int N = adjList.size();
	vector<ll> noEdge{};
	
	for(ll i = 0; i < N; i++)
	{
		if(adjList[i].size() == 0) noEdge.push_back(i);
	}
	
	while(!noEdge.empty())
	{
		ll cur = noEdge.back();
		noEdge.pop_back();
		
		sorted.push_back(cur);
		for(ll n : radjList[cur])
		{
			if(adjList[n].size() == 0) continue;
			
			adjList[n].erase(cur);
			if(adjList[n].size() == 0) noEdge.push_back(n);
		}
	}
	
	bool possible = true;
	for(ll i = 0; i < N; i++)
	{
		possible = possible && adjList[i].empty();
	}
	
	return possible;
}

template <typename T, typename U>
inline void fillv(vector<pair<T, U>>& v, int n, bool inv = false){
	if(inv){
		for(int i = 0 ; i < n; ++i){
			cin >> v[i].second >> v[i].first;
		}
		return;
	}
	for (int i = 0; i < n; ++i) {
        cin >> v[i].first >> v[i].second;
    }
	return;
}

inline void open(string name){
    freopen((name + ".in").c_str(), "r", stdin);
	freopen((name + ".out").c_str(), "w", stdout);
}

void solve(int num_tc)
{
    
}

int32_t main()
{
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);  

    ll T = 1;
    //cin >> T;
    for(ll t = 0; t < T; t++){
        solve(t+1);
    }
}
