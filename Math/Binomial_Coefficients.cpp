ll binomial(ll n, ll k) {
	vector<vector<ll>> dp(n + 1, vector<ll>(k + 1, 0));
	for (ll i = 0; i <= n; i++) {
		dp[i][0] = 1;
		if (i <= k) { dp[i][i] = 1; }
	}
	for (ll i = 0; i <= n; i++) {
		for (ll j = 1; j <= min(i, k); j++) {
			if (i != j) {  
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
			}
		}
    }
	return dp[n][k];  
}

ll exactBinomial(ll n, ll k) {
    if (k > n - k) { k = n - k; }
	long long ret = 1;
	for (int i = 0; i < k; i++) {
		ret = ret * (n - i) / (i + 1);
	}
	return ret;
}

vector<ll> factorial(100000);
vector<ll> invFactorial(factorial.size());
/*
factorial[0] = 1;
for(int i = 1; i < factorial.size(); i++){
	factorial[i] = (factorial[i - 1] * i) % MOD;
}
invFactorial.back = invMod(factorial.back());
for(int i = factorial.size() - 1; i >= 0; i--){
	invFactorial[i] = invFactorial[i + 1] * (i + 1) % MOD;
}
*/
long long binomial(ll n, ll k) {
    return factorial[n] * invMod(factorial[k] * factorial[n - k] % MOD) % MOD;
}