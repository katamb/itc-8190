package itc8190.bigint;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Sources used for help/inspiration:
 * https://www.cse.iitk.ac.in/users/manindra/algebra/primality_v6.pdf
 * https://github.com/kisileno/CryptoMethods/blob/master/ProbablePrimeTest/src/main/java/com/kpi/aks/AKS.java
 */
public class AKSComplex {

    /**
     * Step 1 - check if the number is a perfect power
     * Check if this step is faster than SimplePrimality: 59430890413L took ~300ms (check)
     * @param n
     * @return
     */
    static boolean isPerfectPower(long n) {
        if (n == 1) {
            return false;
        }
        long sqrtN = (long) Math.sqrt(n);
        for (long a = 2; a <= sqrtN; a++) {
            for (long b = 2; b <= n; b++) {
                long calculatedPower = (long) Math.pow(a, b);
                //System.out.println("Calculated " + calculatedPower + " using a=" + a + " and b=" + b + ".");
                if (calculatedPower == n) {
                    return true;
                }
                if (calculatedPower > n) {
                    break;
                }
                // Handle long overflow
                if (calculatedPower < 0) {
                    throw new RuntimeException("Long overflow, @isPerfectPower");
                }
            }
        }
        return false;
    }

    /**
     * Step 2 - Find the smallest r such that ord_r(n) > (log_2 n)^2. (if r and n are not coprime, then skip this r)
     * Check if this step is faster than SimplePrimality:
     * @param n
     * @return
     */
    static long findR(long n) {
        double logN = logBaseTwo(n);
        long maxK = (long) (logN * logN);
        long maxR = Math.max(3, (long) Math.pow(logN, 5) + 1);
        boolean nextR = true;
        System.out.println("maxK:" + maxK);

        long r = 2;
        for (; nextR && r < maxR; r++) {
            nextR = false;

            for (long k = 1L; !nextR && k <= maxK; k++) {
                long nToKModR = (long) (Math.pow(n, k) % r);
                nextR = nToKModR == 0 || nToKModR == 1;

                if (nextR) System.out.println("n:" + n + ";\t k:" + k + ";\t r:" + r + ";\t calc:" + nToKModR);
            }

        }

        return r - 1;
    }

    static Long findMultiplicativeOrder(long r, long n) {
        for (long k = 1L; k <= r; k++) {  // todo not sure about the (k <= r) part
            long nToKModR = (long) (Math.pow(n, k) % r);
            if (nToKModR == 0 || nToKModR == 1) {
                return k;
            }
        }
        return null;
    }

    /**
     * Calculate log_2 n
     * @param n
     * @return
     */
    static double logBaseTwo(long n) {
        return Math.log(n) / Math.log(2);
    }

    // https://en.wikipedia.org/wiki/AKS_primality_test
    static boolean isPrime(long n) {
        // Step 1
        if (isPerfectPower(n)) {
            return false;
        }

        // Step 2
        long r = findR(n);

        // Step 3
        for (long a = 2; a <= Math.min(r, n-1); a++) {
            if (n % a == 0) {
                return false;
            }
        }

        // Step 4
        if (n <= r) {
            return true;
        }

        System.out.println("random");
        //todo steps 5 & 6
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(isPerfectPower(59430890413L));
        //System.out.println(logBaseTwo(1024));
        System.out.println(findR(31L));
        // Input value to test here
    }
}