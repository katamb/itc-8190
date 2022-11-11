package itc8190.primitive;

import java.util.ArrayList;
import java.util.List;

public class LongMath {

    /**
     * Find the smallest r such that ord_r(n) > (log_2 n)^2. (if r and n are not co-prime, then skip this r)
     */
    static long findR(long input) {
        double log2OfInput = logBaseTwo(input);

        long maxK = (long) Math.pow(log2OfInput, 2);
        long r = 1L;
        boolean nextR = true;

        while (nextR) {
            r++;
            nextR = false;
            long k = 0;
            while (k <= maxK && !nextR) {
                k++;
                long x = modPow(input, k, r);
                if (x == 0 || x == 1) {
                    nextR = true;
                }
            }
        }

        return r;
    }

    /**
     * https://link.springer.com/content/pdf/10.1007/b12334.pdf pages 19-20
     * Calculate n^k mod r
     */
    private static long modPow(long n, long k, long r) {
        long s = n % r;
        long result = 1;
        while (k > 0) {
            if ((k & 1) == 1) {  // check if n is odd, equivalent to n % 2 == 1, but a little faster
                result = (result * s) % r;
            }
            s = (s * s) % r;
            k = k >> 1;  // equivalent to k = k / 2
        }
        return result;
    }

    /**
     * Find gcd of inputs using Euclidean algorithm
     * O(log(a)log(b))
     */
    static long findGcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    /**
     * Based on "Primality Testing in Polynomial Time": https://link.springer.com/content/pdf/10.1007/b12334.pdf - page 21
     * Check if the number is a perfect power
     * According to the author, complexity should be: O((log n)^2 log log n)
     */
    static boolean isPerfectPower(long input) {
        if (input == 1) {
            return true;  // Currently means 1 would not be considered prime
        }

        int exponent = 2;
        while (Math.pow(2, exponent) <= input) {
            if (binarySearch(exponent, input)) {
                return true;
            }
            exponent++;
        }

        return false;
    }

    /**
     * Binary search for the perfect power
     * Complexity O(log n)
     */
    private static boolean binarySearch(int exponent, long input) {
        long min = 1;
        long max = input;
        while ((max - min) >= 2) {
            long mean = (min + max) / 2;
            long power = (long) Math.pow(mean, exponent);
            if (power == input) {
                return true;
            } else if (power < input) {
                min = mean;
            } else if (power > input) {
                max = mean;
            }
        }
        return false;
    }

    /**
     * Find floor(sqrt(phi(r)) * log_2(n))
     */
    static long findLimit(long n, long r) {
        double log2OfInput = logBaseTwo(n);
        long eulerPhi = calculateEulerPhi(r);
        double sqrtOfEulerPhi = Math.sqrt(eulerPhi);
        return (long) (sqrtOfEulerPhi * log2OfInput);
    }

    /**
     * Counts the amount of numbers relatively prime to r
     */
    private static long calculateEulerPhi(long r) {
        long count = 0;
        for (long i = 1; i <= r; i++) {
            if (findGcd(i, r) == 1) {
                count++;
            }
        }
        return count;
    }

    static double logBaseTwo(long n) {
        return Math.log(n) / Math.log(2);
    }

    static boolean arePolynomialsDivisible(long a, long n, long r) {
        List<Long> remainders = findPolynomialRemainders(a, n, r);
        // subtract the rest
        remainders.set((int) (n % r), remainders.get((int) (n % r)) - 1L);
        remainders.set(0, remainders.get(0) - a);
        return remainders.stream().allMatch(remainder -> remainder == 0);
    }

    private static List<Long> findPolynomialRemainders(long a, long n, long r) {
        List<Long> remainders = new ArrayList<>();
        remainders.add(1L);
        List<Long> base = new ArrayList<>();
        base.add(a);
        base.add(1L);

        long counter = n;
        while (counter > 0) {
            if ((counter & 1) == 1) {  // check if n is odd, equivalent to n%2==1, but a little faster
                remainders = multiplyPolynomials(remainders, base, n, r);
            }
            base = multiplyPolynomials(base, base, n, r);
            counter = counter >> 1;  // divide by 2
        }
        return remainders;
    }

    private static List<Long> multiplyPolynomials(
            List<Long> poly1, List<Long> poly2,
            long n, long r
    ) {
        ArrayList<Long> result = new ArrayList<>();

        for (int i = 0; i < poly1.size() + poly2.size(); i++) {
            result.add(0L);
            if (i > r - 2) {
                break;
            }
        }

        for (int i = 0; i < poly1.size(); i++) {
            for (int j = 0; j < poly2.size(); j++) {
                int pos = (int) ((i + j) % r);
                long m = (result.get(pos) + (poly1.get(i) * poly2.get(j)));
                result.set(pos, m % n);
            }
        }
        return result;
    }

}