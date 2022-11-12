package itc8190.bigint;

import java.math.BigInteger;

/**
 * Based on "Primality Testing in Polynomial Time": https://link.springer.com/content/pdf/10.1007/b12334.pdf - page 21
 */
public class PerfectPower {

    /**
     * Check if the number is a perfect power
     * According to the author, complexity should be: O((log n)^2 log log n)
     */
    static boolean isPerfectPower(BigInteger input) {
        if (input.compareTo(BigInteger.ONE) == 0) {
            return true;
        }

        int exponent = 2;

        while (BigInteger.valueOf(2).pow(exponent).compareTo(input) <= 0) {
            if (binarySearch(exponent, input)) {
                return true;
            }
            exponent++;
        }

        return false;
    }

    private static boolean binarySearch(Integer exponent, BigInteger input) {
        BigInteger min = BigInteger.ONE;
        BigInteger max = input;
        while (max.subtract(min).compareTo(BigInteger.TWO) >= 0) {
            BigInteger mean = (min.add(max)).divide(BigInteger.TWO);
            BigInteger power = mean.pow(exponent);
            if (power.compareTo(input) == 0) {
                return true;
            }
            if (power.compareTo(input) < 0) {
                min = mean;
            }
            if (power.compareTo(input) > 0) {
                max = mean;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPerfectPower(new BigInteger("306680863762905727")));
    }
}