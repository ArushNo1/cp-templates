ll invMod(ll x) {
  if (x <= 1) {
    return x;
  }
  return MOD - MOD / x * invMod(MOD % x) % MOD;
}
ll invMod(ll x){
  return binExp(x, MOD - 2);
}