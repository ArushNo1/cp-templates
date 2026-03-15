import java.util.List;

public class BinomialCoefficients {

    // Generate every binomial coefficient for all n and k modulo mod
    public static void everyBinomialWithMod(int n, int k, List<int[]> dp, int mod) {
        dp.clear();
        for (int i = 0; i <= n; i++) {
            dp.add(new int[k + 1]);
        }
        for (int i = 0; i <= n; i++) {
            dp.get(i)[0] = 1; // nC0 = 1
            if (i <= k) dp.get(i)[i] = 1; // nCn = 1
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp.get(i)[j] = (dp.get(i - 1)[j - 1] + dp.get(i - 1)[j]) % mod;
            }
        }
    }

    // Generate every binomial coefficient for all n and k without mod
    public static void everyBinomial(int n, int k, List<int[]> dp) {
        dp.clear();
        for (int i = 0; i <= n; i++) {
            dp.add(new int[k + 1]);
        }
        for (int i = 0; i <= n; i++) {
            dp.get(i)[0] = 1; // nC0 = 1
            if (i <= k) dp.get(i)[i] = 1; // nCn = 1
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp.get(i)[j] = dp.get(i - 1)[j - 1] + dp.get(i - 1)[j];
            }
        }
    }

    // Generate every value of nCk for a given n and all k
    public static void allKBinom(int n, List<Long> result) {
        result.clear();
        long value = 1; // nC0 = 1
        result.add(value);
        for (int k = 1; k <= n; k++) {
            value = value * (n - k + 1) / k;
            result.add(value);
        }
    }

    // Generate every value of nCk for a given n and all k modulo mod
    public static void allKBinomWithMod(int n, int mod, List<Long> result) {
        result.clear();
        long value = 1; // nC0 = 1
        result.add(value);
        for (int k = 1; k <= n; k++) {
            value = value * (n - k + 1) % mod;
            value = value * modularInverse(k, mod) % mod;
            result.add(value);
        }
    }

    // Compute nCk using factorial arrays for O(1) retrieval
    public static long binomWithFactorials(int n, int k, long[] fact, long[] invFact, int mod) {
        if (k > n) return 0;
        return fact[n] * invFact[k] % mod * invFact[n - k] % mod;
    }

    // Precompute factorials and modular inverses up to a given size
    public static void precomputeFactorials(int size, int mod, long[] fact, long[] invFact) {
        fact[0] = 1;
        for (int i = 1; i <= size; i++) {
            fact[i] = fact[i - 1] * i % mod;
        }
        invFact[size] = modularInverse(fact[size], mod);
        for (int i = size - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % mod;
        }
    }

    // Compute nCk without any optimization or mod
    public static long binom(int n, int k) {
        if (k > n - k) k = n - k;
        long value = 1;
        for (int i = 1; i <= k; i++) {
            value = value * (n - i + 1) / i;
        }
        return value;
    }

    // Compute modular inverse using Fermat's Little Theorem (mod must be prime)
    private static long modularInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    // Fast modular exponentiation
    private static long power(long base, int exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }
}
