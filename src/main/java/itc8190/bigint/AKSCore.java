package itc8190.bigint;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Based on:
 * https://en.wikipedia.org/wiki/AKS_primality_test
 * https://github.com/Ssophoclis/AKS-algorithm/blob/master/AKS.py
 */
public class AKSCore {

    static boolean arePolynomialsDivisible(BigInteger a, BigInteger n, BigInteger r) {
        List<BigInteger> remainders = findPolynomialRemainders(a, n, r);
        remainders.set(n.mod(r).intValue(), remainders.get(n.mod(r).intValue()).subtract(BigInteger.ONE));
        remainders.set(0, remainders.get(0).subtract(a));
        return remainders.stream().allMatch(rem -> rem.compareTo(BigInteger.ZERO) == 0);
    }

    static List<BigInteger> findPolynomialRemainders(BigInteger a, BigInteger n, BigInteger r) {
        List<BigInteger> remainders = new ArrayList<>();
        remainders.add(BigInteger.ONE);
        List<BigInteger> base = new ArrayList<>();
        base.add(a);
        base.add(BigInteger.ONE);

        BigInteger counter = n;
        while (counter.compareTo(BigInteger.ZERO) > 0) {
            if (counter.testBit(0)) {  // check if odd number
                remainders = multiplyPolynomials(remainders, base, n, r);
            }
            base = multiplyPolynomials(base, base, n, r);
            counter = counter.shiftRight(1);  // divide by 2
        }
        return remainders;
    }

    static List<BigInteger> multiplyPolynomials(
            List<BigInteger> poly1, List<BigInteger> poly2,
            BigInteger n, BigInteger r
    ) {
        ArrayList<BigInteger> polyResult = new ArrayList<>();

        for (int i = 0; i < r.intValue(); i++) {
            polyResult.add(BigInteger.ZERO);
            if (i > r.intValue() - 2) {
                break;
            }
        }

        for (int i = 0; i < poly1.size(); i++) {
            for (int j = 0; j < poly2.size(); j++) {
                int pos = (i + j) % r.intValue();
                polyResult.set(pos, polyResult.get(pos).add(poly1.get(i).multiply(poly2.get(j))).mod(n));
            }
        }
        return polyResult;
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