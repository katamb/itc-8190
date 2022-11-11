package itc8190;

import java.math.BigInteger;

public class SimplePrimality {

    static Boolean isPrime(BigInteger input) {
        if (BigInteger.ZERO.compareTo(input) > 0) {
            System.out.println("Only natural numbers are allowed as input");
            return null;
        }

        BigInteger maxTestValue = input.sqrt();
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(maxTestValue) <= 0; i = i.add(BigInteger.ONE)) {
            if (input.mod(i).compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        // Input value to test here
        Boolean result = isPrime(new BigInteger("1312783547"));
        if (result == null) {
            System.out.println("Unexpected input");
        } else if (result) {
            System.out.println("Prime");
        } else {
            System.out.println("Not Prime");
        }
    }
    // ~350ms for 13127
    // ~400ms for 33469
    // ~440ms for 1312783547
    // ~16sec for 306680863762905727
}