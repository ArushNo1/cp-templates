#include <vector>

/*
Generates every single binomial coefficient for all n and k
*/
template<typename T>
void everyBinomial(T n, T k, std::vector<std::vector<T>>& dp, T mod) {
	if(mod == -1){
		mod = MOD;
	}
	dp.resize(n + 1, vector<T>(k + 1, 0));
	for (ll i = 0; i <= n; i++) {
		dp[i][0] = 1;
		if (i <= k) { dp[i][i] = 1; }
	}
	for (ll i = 0; i <= n; i++) {
		for (ll j = 1; j <= min(i, k); j++) {
			if (i != j) {  
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % mod;
			}
		}
    }
}
/*
generates the exact value of every binomial coefficient up to n and k
*/
template<typename T>
void everyBinomial(T n, T k, std::vector<std::vector<T>>& dp) {
	dp.resize(n + 1, vector<T>(k + 1, 0));
	for (ll i = 0; i <= n; i++) {
		dp[i][0] = 1;
		if (i <= k) { dp[i][i] = 1; }
	}
	for (ll i = 0; i <= n; i++) {
		for (ll j = 1; j <= min(i, k); j++) {
			if (i != j) {  
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]);
			}
		}
    }
}
/*
generates every value of nCk for a given n and all k
*/
template<typename T>
void allkbinom(T n, std::vector<T>& result){
	result.clear();
	ll value = 1;   
	result.push_back(value);
	for (ll k = 1; k <= n; ++k) {
		value = value * (n - k + 1) / k;
		result.push_back(value);
	}
}

/*
generates every value of nCk for a given n and all k mod m
*/
template<typename T>
void allkbinom(T n, T m, std::vector<T>& result){
	if(m == -1){
		m = MOD;
	}
	result.clear();
	ll value = 1;   
	result.push_back(value);
	for (ll k = 1; k <= n; ++k) {
		value = value * (n - k + 1) % m;
		value = value * invMod(k) % m;
		result.push_back(value);
	}
}

/*
generates all factorials up to size to calculate future binomials in O(1)
modulo mod
*/
template<typename T>
T binom(T n, T k, std::vector<T>& fact, std::vector<T>& invfact, T mod, T size = 3e5){
	if(mod == -1){
		mod = MOD;
	}
	if(fact.size() < size + 1){
		fact.resize(size + 1);
		invfact.resize(size + 1);
		fact[0] = 1;
		for(int i = 1; i < fact.size(); i++){
			fact[i] = (fact[i - 1] * i) % mod;
		}
		invfact[size] = invMod(fact[size]);
		for(int i = size - 1; i >= 0; i--){
			invfact[i] = invfact[i + 1] * (i + 1) % mod;
		}
	}

	return ((fact[n] * invfact[k]) % MOD * invfact[n - k]) % MOD;
}

/*
regular looping to calculate nCk with no optimization or mod;
*/
template<typename T>
T binom(T n, T k){
	if(k > n - k){
		k = n - k;
	}
	T value = 1;
    for (T i = 1; i <= k; ++i) {
        value = value * (n - i + 1) / i;
    }
	return value;
}


/*calculate a binomial coefficient mod 2*/
int binom2(int n, int k) {
	if(k > n) {
		return 0;
	}
	if(k == 0 || k == n) {
		return 1;
	}
	return (k & (~n)) == 0 ? 1 : 0;
}