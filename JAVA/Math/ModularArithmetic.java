public class ModularArithmetic {

    private static final long MOD = 1_000_000_007; // Default MOD value

    /**
     * Computes the modular inverse of x.
     * @param x the number to compute the inverse of
     * @return the modular inverse of x
     */
    public static long invMod(long x) {
        if (x <= 1) {
            return x;
        }
        return MOD - MOD / x * invMod(MOD % x) % MOD;
    }

    /**
     * Computes a^b % MOD using binary exponentiation.
     * @param a the base
     * @param b the exponent
     * @return a^b % MOD
     */
    public static long binExp(long a, long b) {
        if (b == 0)
            return 1;

        long res = binExp(a, b / 2) % MOD;
        if ((b & 1) != 0) {
            return (a * ((res * res) % MOD)) % MOD;
        } else {
            return (res * res) % MOD;
        }
    }

    /**
     * Computes a^b % mod using binary exponentiation.
     * @param a the base
     * @param b the exponent
     * @param mod the modulus
     * @return a^b % mod
     */
    public static long binExp(long a, long b, long mod) {
        if (b == 0)
            return 1;

        long res = binExp(a, b / 2) % mod;
        if ((b & 1) != 0) {
            return (a * ((res * res) % mod)) % mod;
        } else {
            return (res * res) % mod;
        }
    }

    /**
     * Computes a + b % MOD.
     * @param a first operand
     * @param b second operand
     * @return (a + b) % MOD
     */
    public static long add(long a, long b) {
        return (a + b) % MOD;
    }

    /**
     * Computes a - b % MOD.
     * @param a first operand
     * @param b second operand
     * @return (a - b) % MOD
     */
    public static long subtract(long a, long b) {
        return (a - b + MOD) % MOD;
    }

    /**
     * Computes a * b % MOD.
     * @param a first operand
     * @param b second operand
     * @return (a * b) % MOD
     */
    public static long multiply(long a, long b) {
        return (a * b) % MOD;
    }

    /**
     * Computes a / b % MOD using modular inverse.
     * @param a the numerator
     * @param b the denominator
     * @return (a / b) % MOD
     */
    public static long divide(long a, long b) {
        return multiply(a, invMod(b));
    }
}
