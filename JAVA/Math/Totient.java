import java.util.List;

public class Totient {

    /**
     * Calculates the Euler's Totient Function (phi) for a single number.
     * @param n the number for which to calculate phi
     * @return the value of phi(n)
     */
    public static int phi(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    /**
     * Calculates Euler's Totient Function for all numbers from 1 to n.
     * @param n the range of numbers for which to calculate phi
     * @param phi a list to store the results (phi[i] = phi(i))
     */
    public static void phi_1_to_n(int n, List<Integer> phi) {
        phi.set(0, 0);
        phi.set(1, 1);
        for (int i = 2; i <= n; i++) {
            phi.set(i, i - 1);
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2 * i; j <= n; j += i) {
                phi.set(j, phi.get(j) - phi.get(i));
            }
        }
    }
}
