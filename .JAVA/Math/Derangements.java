import java.util.List;

public class Derangements {

    // Generate all derangements up to n modulo m
    public static void derangements(int n, List<Long> der, long m) {
        der.clear();
        der.add(1L); // der[0] = 1
        der.add(0L); // der[1] = 0
        
        for (int i = 2; i <= n; ++i) {
            long value = (i - 1) * (der.get(i - 1) + der.get(i - 2)) % m;
            der.add(value);
        }
    }

    // Overloaded method with default modulus
    public static void derangements(int n, List<Long> der) {
        derangements(n, der, 1_000_000_007L);
    }
}
