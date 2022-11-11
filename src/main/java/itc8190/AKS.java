package itc8190;

import java.math.BigInteger;

public class AKS {

    // https://en.wikipedia.org/wiki/AKS_primality_test
    static boolean isPrime(BigInteger input) {
        // Validations
        if (BigInteger.ONE.compareTo(input) > 0) {
            throw new RuntimeException("Invalid input!");
        }

        // Step 1
        if (PerfectPower.isPerfectPower(input)) {
            return false;
        }

        // Step 2
        BigInteger r = FindR.findR(input);

        // Step 3
        BigInteger minVal = r.min(input.subtract(BigInteger.ONE));  // min(r, input - 1)
        for (BigInteger a = BigInteger.TWO; a.compareTo(minVal) <= 0; a = a.add(BigInteger.ONE)) {
            if (a.gcd(input).compareTo(BigInteger.ONE) > 0) {
                return false;
            }
        }

        // Step 4
        if (input.compareTo(r) <= 0) {
            return true;
        }

        // Step 5
        for (BigInteger a = BigInteger.ONE; a.compareTo(AKSCore.findLimit(input, r)) <= 0; a = a.add(BigInteger.ONE)) {
            if (!AKSCore.arePolynomialsDivisible(a, input, r)) {
                return false;
            }
        }

        // Step 6
         return true;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(new BigInteger("2147483647")));
    }
}