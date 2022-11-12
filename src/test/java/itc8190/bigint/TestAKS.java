package itc8190.bigint;

import itc8190.bigint.AKS;
import itc8190.bigint.SimplePrimality;
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

}
