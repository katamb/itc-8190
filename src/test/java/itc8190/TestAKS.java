package itc8190;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class TestAKS {

    // Currently 1 is not considered prime number https://www.wgtn.ac.nz/science/ask-a-researcher/is-1-a-prime-number
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 6, 9, 21, 169, 1207})
    void testIsPrimeByAKS_notPrimeNumbers(int number) {
        assertThat(AKS.isPrime(BigInteger.valueOf(number))).isFalse();
    }

    // prime numbers according to http://compoasso.free.fr/primelistweb/page/prime/liste_online_en.php
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 73, 127, 233, 283, 467, 661, 877, 1087, 1223})
    void testIsPrimeByAKS_withPrimeNumbers(int number) {
        assertThat(AKS.isPrime(BigInteger.valueOf(number))).isTrue();
    }

    //@Test
    void testtt() {
        System.out.println("start");

        long startTime = System.nanoTime();
        for (long a = 2; a < 1000L; a++) {
            AKS.isPrime(BigInteger.valueOf(a));
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(duration);


        startTime = System.nanoTime();
        for (long a = 2; a < 1000L; a++) {
            SimplePrimality.isPrime(BigInteger.valueOf(a));
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println(duration);

        for (long a = 2; a < 1000L; a++) {
            assertThat(AKS.isPrime(BigInteger.valueOf(a))).isEqualTo(SimplePrimality.isPrime(BigInteger.valueOf(a)));
        }
    }
}
