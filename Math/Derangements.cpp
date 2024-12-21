template<typename T>
void derangements1n(T n, vector<T>& der, T m = T(1e9+7)){
    der.resize(n + 1);
    der[0] = 1;
    der[1] = 0;
    for(ll i = 2; i <= n; ++i){
        der[i] = ((i - 1) * (der[i - 1] + der[i - 2]) % m) % m;
    }
}