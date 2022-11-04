package itc8190;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Based on: https://en.wikipedia.org/wiki/AKS_primality_test
 * https://github.com/Ssophoclis/AKS-algorithm/blob/master/AKS.py
 */
public class FindR {

    /**
     * Find the smallest r such that ord_r(n) > (log_2 n)^2. (if r and n are not coprime, then skip this r)
     */
    static BigInteger findR(BigInteger input) {
        double log2OfInput = logBaseTwo(input.longValueExact());  // bottleneck for the max input value

        BigInteger maxK = BigDecimal.valueOf(log2OfInput).pow(2).toBigInteger();
        BigInteger r = BigInteger.ONE;
        boolean nextR = true;

        while (nextR) {
            r = r.add(BigInteger.ONE);
            nextR = false;
            BigInteger k = BigInteger.ZERO;
            while (k.compareTo(maxK) <= 0 && !nextR) {
                k = k.add(BigInteger.ONE);
                BigInteger x = input.modPow(k, r);
                if (x.compareTo(BigInteger.ZERO) == 0 || x.compareTo(BigInteger.ONE) == 0) {
                    nextR = true;
                }
            }
        }

        return r;
    }

    private static double logBaseTwo(long n) {
        return Math.log(n) / Math.log(2);
    }

    public static void main(String[] args) {
        System.out.println(findR(new BigInteger("31")));
    }
}