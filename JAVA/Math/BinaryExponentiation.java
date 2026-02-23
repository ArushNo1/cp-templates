public class BinaryExponentiation {

    private static final int MOD = 1_000_000_007; // Default mod value for competitive programming

    // Binary Exponentiation with a predefined MOD
    public static long binExp(long a, long b) {
        if (b == 0) {
            return 1;
        }

        long res = binExp(a, b / 2) % MOD;
        res = (res * res) % MOD;
        if ((b & 1) == 1) { // If b is odd
            res = (res * a) % MOD;
        }
        return res;
    }

    // Binary Exponentiation with a custom mod
    public static long binExp(long a, long b, long mod) {
        if (b == 0) {
            return 1;
        }

        long res = binExp(a, b / 2, mod) % mod;
        res = (res * res) % mod;
        if ((b & 1) == 1) { // If b is odd
            res = (res * a) % mod;
        }
        return res;
    }
}
