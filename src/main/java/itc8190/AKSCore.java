package itc8190;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Based on:
 * https://en.wikipedia.org/wiki/AKS_primality_test
 * https://github.com/Ssophoclis/AKS-algorithm/blob/master/AKS.py
 */
public class AKSCore {

    static boolean arePolynomialsDivisible(BigInteger a, BigInteger input, BigInteger r) {


        //todo add actual maths
        return true;
    }

    /**
     * Find floor(sqrt(phi(r)) * log_2(n))
     */
    static BigInteger findLimit(BigInteger input, BigInteger r) {
        BigDecimal log2OfInputDecimal = BigDecimal.valueOf(logBaseTwo(input.longValueExact()));  // bottleneck for the max input value
        BigInteger eulerPhi = calculateEulerPhi(r);
        BigDecimal eulerPhiDecimal = new BigDecimal(eulerPhi);
        BigDecimal sqrtOfEulerPhi = eulerPhiDecimal.sqrt(new MathContext(eulerPhiDecimal.precision() + 6));

        return sqrtOfEulerPhi.multiply(log2OfInputDecimal).toBigInteger();
    }

    /**
     * Counts the amount of numbers relatively prime to r
     */
    private static BigInteger calculateEulerPhi(BigInteger r) {
        BigInteger count = BigInteger.ZERO;
        for (BigInteger i = BigInteger.ONE; i.compareTo(r) <= 0; i = i.add(BigInteger.ONE)) {
            if (i.gcd(r).compareTo(BigInteger.ONE) == 0) {
                count = count.add(BigInteger.ONE);
            }
        }
        return count;
    }

    private static double logBaseTwo(long n) {
        return Math.log(n) / Math.log(2);
    }

    public static void main(String[] args) {
        System.out.println(findLimit(new BigInteger("31"), new BigInteger("29")));
    }
}