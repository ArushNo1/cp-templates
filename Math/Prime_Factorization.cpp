template<typename T>
map<T, int> prime_factorize_map(T n) {
    map<T, int> ret;
    for (T i = 2; i * i <= n; i++) {
        while (n % i == 0) {
            ret[i]++;
            n /= i;
        }
    }
    if (n > 1) ret[n] = 1;
    return ret;
}

template <typename T>
vector<T> prime_factorize_vector(T n) {
    vector<T> ret;
    for (T i = 2; i * i <= n; i++) {
        while (n % i == 0) {
            ret.push_back(i);
            n /= i;
        }
    }
    if (n > 1) ret.push_back(n);
    return ret;
}