ll binExp(ll a, ll b) {
  if (b == 0)
    return 1;

  ll res = binExp(a, b / 2) % MOD;
  if (b & 1) {
    return (a * ((res * res) % MOD)) % MOD;
  } else
    return (res * res) % MOD;
}