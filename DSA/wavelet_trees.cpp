struct wavelet_tree{
	#define vi vector<int>
	#define pb push_back
	int lo, hi;
	wavelet_tree *l, *r;
	vi b;
 
	//nos are in range [x,y]
	//array indices are [from, to)
	wavelet_tree(int *from, int *to, int x, int y){
		lo = x, hi = y;
		if(lo == hi or from >= to) return;
		int mid = (lo+hi)/2;
		auto f = [mid](int x){
			return x <= mid;
		};
		b.reserve(to-from+1);
		b.pb(0);
		for(auto it = from; it != to; it++)
			b.pb(b.back() + f(*it));
		//see how lambda function is used here	
		auto pivot = stable_partition(from, to, f);
		l = new wavelet_tree(from, pivot, lo, mid);
		r = new wavelet_tree(pivot, to, mid+1, hi);
	}
 
	//kth smallest element in [l, r]
	int kth(int l, int r, int k){
		if(l > r) return 0;
		if(lo == hi) return lo;
		int inLeft = b[r] - b[l-1];
		int lb = b[l-1]; //amt of nos in first (l-1) nos that go in left 
		int rb = b[r]; //amt of nos in first (r) nos that go in left
		if(k <= inLeft) return this->l->kth(lb+1, rb , k);
		return this->r->kth(l-lb, r-rb, k-inLeft);
	}
 
	//count of nos in [l, r] Less than or equal to k
	int LTE(int l, int r, int k) {
		if(l > r or k < lo) return 0;
		if(hi <= k) return r - l + 1;
		int lb = b[l-1], rb = b[r];
		return this->l->LTE(lb+1, rb, k) + this->r->LTE(l-lb, r-rb, k);
	}
 
	//count of nos in [l, r] equal to k
	int count(int l, int r, int k) {
		if(l > r or k < lo or k > hi) return 0;
		if(lo == hi) return r - l + 1;
		int lb = b[l-1], rb = b[r], mid = (lo+hi)/2;
		if(k <= mid) return this->l->count(lb+1, rb, k);
		return this->r->count(l-lb, r-rb, k);
	}
	~wavelet_tree(){
		delete l;
		delete r;
	}
};#include <bits/stdc++.h>
using namespace std;

template <typename T, T sigma, int L>
struct WaveletTree
{
    struct Node
    {
        unsigned l, r;
        vector<int> x;
    };

    Node t[L];

    unsigned build(vector<T> seq, T a = 0, T b = sigma)
    {
        static unsigned l = 0;

        if (seq.empty())
            return UINT_MAX;

        unsigned node = l++;
        if (a == b)
            return node;

        t[node].x.resize(seq.size());
        T const mid = (a + b) / 2;
        vector<T> left_seq, right_seq;

        for (size_t i = 0; i < seq.size(); ++i)
        {
            if (seq[i] <= mid)
                left_seq.push_back(seq[i]), t[node].x[i] = 1;
            else
                right_seq.push_back(seq[i]);
        }
        for (size_t i = 1; i < seq.size(); ++i)
            t[node].x[i] += t[node].x[i - 1];

        t[node].l = build(left_seq, a, mid);
        t[node].r = build(right_seq, mid + 1, b);
        return node;
    }

    inline int map_left(unsigned node, int i)
    {
        return i >= 0 ? t[node].x[i] - 1 : -1;
    }

    inline int map_right(unsigned node, int i)
    {
        return i >= 0 ? i - t[node].x[i] : -1;
    }

    unsigned rank(T q, int i, unsigned node = 0, T a = 0, T b = sigma)
    {
        if (i == -1)
            return 0;
        if (a == b)
            return i + 1;

        T mid = (a + b) / 2;
        if (q <= mid)
            return rank(q, map_left(node, i), t[node].l, a, mid);
        else
            return rank(q, map_right(node, i), t[node].r, mid + 1, b);
    }

    T quantile(int i, int j, int k, unsigned node = 0, T a = 0, T b = sigma)
    {
        if (a == b)
            return a;
        int c = map_left(node, j) - map_left(node, i - 1);
        if (c >= k)
            return quantile(map_left(node, i - 1) + 1,
                            map_left(node, j), k, t[node].l, a, (a + b) / 2);
        else
            return quantile(map_right(node, i - 1) + 1,
                            map_right(node, j), k - c, t[node].r, (a + b) / 2 + 1, b);
    }

    unsigned count(int i, int j, T x, T y, unsigned node = 0, T a = 0, T b = sigma)
    {
        if (node == UINT_MAX || b < x || a > y)
            return 0;
        if (x <= a && b <= y)
            return j - i + 1;

        return count(map_left(node, i - 1) + 1, map_left(node, j), x, y, t[node].l, a, (a + b) / 2) +
               count(map_right(node, i - 1) + 1, map_right(node, j), x, y, t[node].r, (a + b) / 2 + 1, b);
    }
};

//WaveletTree<int, 1000000000, 4000000> tree;