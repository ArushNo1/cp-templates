import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factorization {

    /**
     * Computes the prime factorization of a number using a list of primes.
     * @param n the number to be factored
     * @param primes list of precomputed primes
     * @return list of prime factors of n
     */
    public static List<Long> factorsPrimes(long n, List<Long> primes) {
        List<Long> factorization = new ArrayList<>();
        for (long d : primes) {
            if (d * d > n) {
                break;
            }
            while (n % d == 0) {
                factorization.add(d);
                n /= d;
            }
        }
        if (n > 1) {
            factorization.add(n);
        }
        return factorization;
    }

    /**
     * Computes the prime factorization of a number.
     * @param n the number to be factored
     * @return list of prime factors of n
     */
    public static List<Integer> factor(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ret.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            ret.add(n);
        }
        return ret;
    }

    /**
     * Computes the number of divisors of a number.
     * @param num the number for which to compute the number of divisors
     * @return the total number of divisors of num
     */
    public static long numberOfDivisors(long num) {
        long total = 1;
        for (int i = 2; (long)i * i <= num; i++) {
            if (num % i == 0) {
                int e = 0;
                do {
                    e++;
                    num /= i;
                } while (num % i == 0);
                total *= e + 1;
            }
        }
        if (num > 1) {
            total *= 2;
        }
        return total;
    }

    /**
     * Computes the sum of divisors of a number.
     * @param num the number for which to compute the sum of divisors
     * @return the sum of divisors of num
     */
    public static long sumOfDivisors(long num) {
        long total = 1;
        for (int i = 2; (long)i * i <= num; i++) {
            if (num % i == 0) {
                int e = 0;
                do {
                    e++;
                    num /= i;
                } while (num % i == 0);

                long sum = 0, pow = 1;
                do {
                    sum += pow;
                    pow *= i;
                } while (e-- > 0);
                total *= sum;
            }
        }
        if (num > 1) {
            total *= (1 + num);
        }
        return total;
    }

    /**
     * Checks if a number is prime.
     * @param x the number to be checked
     * @return true if x is prime, false otherwise
     */
    public static boolean isPrime(long x) {
        for (long d = 2; d * d <= x; d++) {
            if (x % d == 0) {
                return false;
            }
        }
        return x >= 2;
    }

    /**
     * Performs prime factorization and stores the result in a map.
     * @param n the number to be factorized
     * @return a map where keys are prime factors and values are their powers
     */
    public static Map<Integer, Integer> primeFactorizeMap(int n) {
        Map<Integer, Integer> ret = new HashMap<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ret.put(i, ret.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        if (n > 1) {
            ret.put(n, 1);
        }
        return ret;
    }

    /**
     * Performs prime factorization and stores the result in a list.
     * @param n the number to be factorized
     * @return a list of prime factors of n
     */
    public static List<Integer> primeFactorizeVector(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ret.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            ret.add(n);
        }
        return ret;
    }

    /**
     * Implements the Sieve of Eratosthenes algorithm to find all prime numbers up to a given limit.
     * 
     * @param isPrime A boolean array where each index represents a number, and the value indicates whether the number is prime.
     * @param n The upper limit up to which prime numbers will be found.
     */
    public static void sieve(boolean[] isPrime, long n) {
        // Initialize the array to false
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime
    
        // Mark non-prime numbers
        for (long i = 2; i <= n; i++) {
            if (isPrime[(int) i] && i * i <= n) {
                for (long j = i * i; j <= n; j += i) {
                    isPrime[(int) j] = false;
                }
            }
        }
    }
}

